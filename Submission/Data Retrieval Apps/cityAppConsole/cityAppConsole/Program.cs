using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Mvc.Ajax;
using System.IO;
using System.Xml;
using System.Text.RegularExpressions;
using Newtonsoft.Json;

namespace cityAppConsole
{
	class commServRow         
	{             
		internal string Register_Key;             
		internal string C_CATEGORY;
		internal string C_RECORD_LAST_UPDATED;
		internal string C_CONTACT_FIRST_NAME;
		internal string C_CONTACT_SURNAME;
		internal string C_PRESIDENT;
		internal string C_RATING;
		internal string C_SECRETARY;
		internal string C_TREASURER;
		internal string C_COUNCIL_SERVICE;
		internal string C_DESCRIPTION_COMMENT;
		internal string C_ACTIVITIES;
		internal string C_OPERATING_HOURS;
		internal string C_TARGET_GROUP;
		internal string C_ELIGIBILITY;
		internal string C_FEE;
		internal string C_AGM_HELD_IN;
		internal string C_MEMBERSHIP_REQUIREMENTS;
		internal string C_GROUP_ORGANISATION_STATUS;
		internal string C_MEETING_FREQUENCY;
		internal string C_CAN_VENUE_BE_HIRED;
		internal string C_VENUE_RESOURCE_FACILITIES;
		internal string C_POSSIBLE_USES_OF_VENUE;
		internal string C_PHYSICAL_SPECS_OF_VENUE;
		internal string NAME_ROLE_101;
		internal string NAME_ROLE_102;
		internal string NAME_ROLE_103;
		internal string COMM_TYPE;
		internal string DESCRIPTION;
		internal string ContactData;
		internal string Propnum;
	}

	class FlatService {
		public string categoryName;
		public string subCategoryName;
		public string serviceName;
		public string description;
		public string activities;
		public string contactName;
		public string openingHours;
		public string mailingAddress;
		public string streetAddress;
		public string businessPhone;
		public string mobilePhone;
		public string email;
		public string website;
		public string fax;

		public FlatService (string categoryName, string subCategoryName, string serviceName, string description, string activities, string contactName, string openingHours, string contactDetailsXML)
		{
			this.categoryName = categoryName;
			this.subCategoryName = subCategoryName;
			this.serviceName = serviceName;
			this.description = description;
			this.activities = activities;
			this.contactName = contactName;
			this.openingHours = openingHours;
			ContactInformation contactDetails = new ContactInformation  (contactDetailsXML);
			this.mailingAddress = contactDetails.mailingAddress;
			this.streetAddress = contactDetails.streetAddress;
			this.businessPhone = contactDetails.businessPhone;
			this.mobilePhone = contactDetails.mobilePhone;
			this.email = contactDetails.email;
			this.website = contactDetails.website;
			this.fax = contactDetails.fax;
		}
	}
//	class ServiceCategory         
//	{                          
//		public string name;
//		public List<ServiceSubCategory> subCategories;
//		public ServiceCategory(string name)
//		{
//			this.name = name;
//			this.subCategories = new List<ServiceSubCategory> ();
//
//		}
//		public void addSubCategory (commServRow row)
//		{
//			var subCategoryName = row.NAME_ROLE_102;
//			if (this.subCategories.Count > 0) {
//				var check = this.subCategories.Find (x => x.name == subCategoryName);
//				if (check == null) {
//					this.subCategories.Add (new ServiceSubCategory (subCategoryName));
//				} else {
//					check.addLocalService (row);
//				}
//			} else {
//				this.subCategories.Add (new ServiceSubCategory (subCategoryName));
//			}
//		}
//	}
//
//
//	class ServiceSubCategory
//	{
//		public string name;
//		public List<LocalService> localServices = new List<LocalService>();
//		public ServiceSubCategory(string name)
//		{
//			this.name = name;
//		}
//		public void addLocalService (commServRow row)
//		{	
//			this.localServices.Add (new LocalService (row.NAME_ROLE_103, row.C_DESCRIPTION_COMMENT, row.C_ACTIVITIES, row.ContactData, row.C_OPERATING_HOURS));
//		}
//	}
//
//	class LocalService
//	{
//		public string title;
//		public string description;
//		public string activities;
//		public ContactInformation contactDetails;
//		public LocalService (string title, string description, string activities, string contactDetailsXML, string openingHours)
//		{
//			this.title = title;
//			this.description = description;
//			this.activities = activities;
//			this.contactDetails = new ContactInformation (contactDetailsXML, openingHours);
//		}
//	}
//
	class ContactInformation
	{
		public string mailingAddress = "";
		public string streetAddress = "";
		public string businessPhone = "";
		public string mobilePhone = "";
		public string email = "";
		public string website = "";
		public string fax = "";

		public ContactInformation (string contactDetailsXML)
		{
			XmlReaderSettings settings = new XmlReaderSettings();
			settings.ConformanceLevel = ConformanceLevel.Fragment;
			settings.IgnoreWhitespace = true;
			string cleanedXML = Regex.Replace(contactDetailsXML, @"<Business ", "<Business");
			cleanedXML = Regex.Replace(cleanedXML, @"\""", "");
			cleanedXML = Regex.Replace(cleanedXML, @"</Business ", "</Business");
			cleanedXML = Regex.Replace(cleanedXML, @"<Residential ", "<Residential");
			cleanedXML = Regex.Replace(cleanedXML, @"</Residential ", "</Residential");
			cleanedXML = Regex.Replace(cleanedXML, @"<Mobile ", "<Mobile");
			cleanedXML = Regex.Replace(cleanedXML, @"</Mobile ", "</Mobile");
			cleanedXML = Regex.Replace(cleanedXML, @"<Home ", "<Home");
			cleanedXML = Regex.Replace(cleanedXML, @"</Home ", "</Home");
			cleanedXML = Regex.Replace(cleanedXML, @"<Contact ", "<Contact");
			cleanedXML = Regex.Replace(cleanedXML, @"</Contact ", "</Contact"); 
			cleanedXML = Regex.Replace(cleanedXML, @"<Toll Free ", "<TollFree");
			cleanedXML = Regex.Replace(cleanedXML, @"</Toll Free ", "</TollFree"); 
			cleanedXML = Regex.Replace(cleanedXML, @"wendouree.cricketvictoria.com.au</Website></Contact", "wendouree.cricketvictoria.com.au</Website></ContactData1>");
			cleanedXML = Regex.Replace(cleanedXML, @"&", "&amp;");
			cleanedXML = Regex.Replace(cleanedXML, @"<Website>www.countryracing.com.au/clubs/", "<Website>www.countryracing.com.au/clubs/</Website></ContactData1>");
			cleanedXML = Regex.Replace (cleanedXML, @"www.vcfl.ballaratumpires.com.au</Website></ContactData1><ContactData2></ContactDat", "www.vcfl.ballaratumpires.com.au</Website></ContactData1><ContactData2></ContactData2>");
			cleanedXML = Regex.Replace(cleanedXML, @"Peel Street North, BALLARAT EAST  VIC 335</BusinessAddress></ContactData1><ContactData2></ContactData", "Peel Street North, BALLARAT EAST  VIC 335</BusinessAddress></ContactData1><ContactData2></ContactData2>"); 
			cleanedXML = Regex.Replace(cleanedXML, @"www.mcjfc.vcfl.com.au</Website></ContactData1><ContactData2></ContactData2", "www.mcjfc.vcfl.com.au</Website></ContactData1><ContactData2></ContactData2>");
			cleanedXML = Regex.Replace(cleanedXML, @"ContactData2></ContactData$", "ContactData2></ContactData2>");
			string XMLToParse = Regex.Replace(cleanedXML, @"(<[\/].+)\s(.+>)", "$1$2");
			//string XMLToParse = ""; //Regex.Replace(contactDetailsXML, @"(<[\/].+)\s(.+>)", "$1$2");

			using (XmlReader reader = XmlReader.Create (new StringReader (XMLToParse), settings)) {
				string readName = "";
				string readValue = "";

				while (reader.Read())
				{
					//Console.WriteLine(reader.NodeType);
					if (reader.NodeType == XmlNodeType.Element) {
						readName = reader.Name;
					}
					if (reader.NodeType == XmlNodeType.Text) {
						readValue = reader.Value;
					}
					if (reader.NodeType == XmlNodeType.EndElement) {
						this.addXMLData (readName, readValue);
					}

				}
			}
		}
		private void addXMLData(string name, string value) 
		{
			switch (name)
			{
				case "mailAddress":
					this.mailingAddress = value;
				Console.WriteLine("mailAddress " + value);
					break;
			case "Email":

				this.email = value;
				Console.WriteLine("Email " + value);
				break;
			case "ResidentialAddress":
				this.streetAddress = value;
				Console.WriteLine("ResidentialAddress " + value);
				break;
			case "Website":
				this.website = value;
				Console.WriteLine("Website " + value);
				break;
			case "BusinessPhone":
				this.businessPhone = value;

				Console.WriteLine("BusinessPhone " + value);
				break;
			case "MobilePhone":
				this.mobilePhone = value;

				Console.WriteLine("MobilePhone " + value);
				break;
			case "BusinessFax":
				this.fax = value;

				Console.WriteLine("BusinessFax " + value);
				break;
			}
		}

	}

	class MainClass
	{
		
		public static void Main (string[] args)
		{
			List<commServRow> commServicesCSVData;
			List<FlatService> communityServices = new List<FlatService>();

			string[] allLines = System.IO.File.ReadAllLines(@"/Users/victorramanauskas/dev/govhack/ballaratcommunitydirectory.txt");
			commServicesCSVData = new List<commServRow> ();
			for (int index = 1; index < allLines.Length; index++) {
				var fields = allLines[index].Split ('\t');

				commServicesCSVData.Add (new commServRow () {                     
					Register_Key = fields [0],                     
					C_CATEGORY = fields [1],                     
					C_RECORD_LAST_UPDATED = fields [2],                     
					C_CONTACT_FIRST_NAME = fields [3],                     
					C_CONTACT_SURNAME = fields [4],                     
					C_PRESIDENT = fields [5],                     
					C_RATING = fields [6],                     
					C_SECRETARY = fields [7],                     
					C_TREASURER = fields [8],                     
					C_COUNCIL_SERVICE = fields [9],                     
					C_DESCRIPTION_COMMENT = fields [10],                     
					C_ACTIVITIES = fields [11],                     
					C_OPERATING_HOURS = fields [12],                     
					C_TARGET_GROUP = fields [13],                     
					C_ELIGIBILITY = fields [14],                     
					C_FEE = fields [15],                     
					C_AGM_HELD_IN = fields [16],                     
					C_MEMBERSHIP_REQUIREMENTS = fields [17],                     
					C_GROUP_ORGANISATION_STATUS = fields [18],                     
					C_MEETING_FREQUENCY = fields [19],                     
					C_CAN_VENUE_BE_HIRED = fields [20],                     
					C_VENUE_RESOURCE_FACILITIES = fields [21],                     
					C_POSSIBLE_USES_OF_VENUE = fields [22],                     
					C_PHYSICAL_SPECS_OF_VENUE = fields [23],                     
					NAME_ROLE_101 = fields [24],                     
					NAME_ROLE_102 = fields [25],                     
					NAME_ROLE_103 = fields [26],                     
					COMM_TYPE = fields [27],                     
					DESCRIPTION = fields [28],                     
					ContactData = fields [29],                     
					Propnum = fields [30]               
				});             
			}

			for (int index = 1; index < commServicesCSVData.Count; index++) {

				communityServices.Add (new FlatService (commServicesCSVData[index].NAME_ROLE_101,
					commServicesCSVData[index].NAME_ROLE_102,
					commServicesCSVData[index].NAME_ROLE_103,
					commServicesCSVData[index].C_DESCRIPTION_COMMENT,
					commServicesCSVData[index].C_ACTIVITIES,
					commServicesCSVData[index].C_CONTACT_FIRST_NAME + " " + commServicesCSVData[index].C_CONTACT_SURNAME,
					commServicesCSVData[index].C_OPERATING_HOURS,
					commServicesCSVData[index].ContactData
				));


			}
//			communityServices.ForEach(delegate(ServiceCategory category)
//			{
//				Console.WriteLine(category.name);
//				category.subCategories.ForEach(delegate(ServiceSubCategory subCategory)
//				{
//					Console.WriteLine("- " + subCategory.name);
//				});
//			});
			string json = JsonConvert.SerializeObject(communityServices);
			//Console.WriteLine (json);
			System.IO.StreamWriter file = new System.IO.StreamWriter("/Users/victorramanauskas/dev/govhack/services.json");
			file.Write(json);
			file.Close();
		}
	}


}
