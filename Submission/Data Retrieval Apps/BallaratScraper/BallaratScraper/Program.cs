using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using BallaratScraper.Properties;
using CsQuery;
using Newtonsoft.Json;

namespace BallaratScraper
{
	class Program
	{
		static void Main(string[] args)
		{
			//Testing
            //string testJson = JsonConvert.SerializeObject(ParseEvents(Resources.EventsPage));


            var parsedEvents = new List<Event>();

            for (int i = 1; i <= 12; i++)
            {
                var request =
                (HttpWebRequest)
                    WebRequest.Create(System.Web.HttpUtility.HtmlDecode(string.Format(@"http://www.ballarat.vic.gov.au/lae/events.aspx?Act=Goto&Month={0}&Year=2014", i))); //"http://www.ballarat.vic.gov.au/lae/events.aspx"

                request.Method = "GET";

                using (HttpWebResponse response = (HttpWebResponse)request.GetResponse())
                {
                    if ((!request.HaveResponse) || (response.StatusCode != HttpStatusCode.OK))
                        throw new Exception("Failed to get webpage");
                    using (var sr = new StreamReader(response.GetResponseStream()))
                    {
                        CsQuery.CQ test = sr.ReadToEnd();

                        parsedEvents.AddRange(ParseEvents(test));
                    }
                }
            }

            string json = JsonConvert.SerializeObject(parsedEvents);
            Debug.WriteLine(json);
        }

        private static List<Event> ParseEvents(CQ test)
        {
            var events = test.Select("[id^=mEvent_]");

            var parsedEvents = new List<Event>();

            foreach (var calendarEvent in events)
            {
                var parsedEvent = new Event
                {
                    title = calendarEvent.FirstChild.ChildNodes[1].FirstChild.InnerText,
                    location = calendarEvent.FirstChild.ChildNodes[2].InnerText,
                    category = calendarEvent.FirstChild.ChildNodes[1].GetAttribute("class").Split(' ')[0]
                };

                var eventIDString = calendarEvent.FirstChild.ChildNodes[1].FirstChild.Attributes.Single(i => i.Key == "onclick").Value;

                var eventID = eventIDString.Substring(eventIDString.IndexOf('(') + 1, eventIDString.IndexOf(')') - eventIDString.IndexOf('(') - 1);

                var popup = test.Select("#Event_" + eventID).Children()[0];

                parsedEvent.description = popup.ChildNodes[3].InnerText;
                parsedEvent.contactName = popup.ChildNodes[4].InnerText.Replace("Contact :","").Trim();

                var calendarDate = popup.ChildNodes.SingleOrDefault(i => i.ClassName == "SummaryCalTime");
                if (calendarDate != null)
                {
                    var halves = calendarDate.InnerText.Split('-');

                    parsedEvent.startDate = DateTime.Parse(halves[0]);
                    parsedEvent.endDate = DateTime.Parse(halves[1]);
                }

                var email = popup.LastChild.ChildNodes.SingleOrDefault(i => i.InnerText.Contains("Email"));
                parsedEvent.email = email != null ? email.GetAttribute("href").Replace("mailto:", "") : null;

                var website = popup.LastChild.ChildNodes.SingleOrDefault(i => i.InnerText.Contains("Website"));
                parsedEvent.website = website != null ? website.GetAttribute("href").Replace("http://http://", "http://") : null;

                var facebook = popup.LastChild.ChildNodes.SingleOrDefault(i => i.InnerText.Contains("Visit Facebook Page"));
                parsedEvent.facebookPage = facebook != null ? facebook.GetAttribute("href") : null;

                parsedEvents.Add(parsedEvent);
            }
            return parsedEvents;

        }

        class Event
        {
            public string title;
            public string location;
            public DateTime startDate;
            public DateTime endDate;
            public string category; //Blue
            public string description;
            public string contactName;
            public string email;
            public string website;
            public string facebookPage;
        }
    }
}
