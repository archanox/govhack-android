package au.gov.vic.ballarat.ballarat.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pierce on 12/07/2014.
 */
public class NeighbourhoodItem implements Serializable {
    private String suburb;
    private float populationDensity;
    private int population;
    private float area;
    private String description;
    private ArrayList<LandUse> landUses = new ArrayList<LandUse>();
    private ArrayList<Population> populations = new ArrayList<Population>();
    private ArrayList<KeyStat> keyStats = new ArrayList<KeyStat>();
    private Map map;

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public float getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(float populationDensity) {
        this.populationDensity = populationDensity;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public ArrayList<LandUse> getLandUses() {
        return landUses;
    }

    public void setLandUses(ArrayList<LandUse> landUses) {
        this.landUses = landUses;
    }

    public ArrayList<Population> getPopulations() {
        return populations;
    }

    public void setPopulations(ArrayList<Population> populations) {
        this.populations = populations;
    }

    public ArrayList<KeyStat> getKeyStats() {
        return keyStats;
    }

    public void setKeyStats(ArrayList<KeyStat> keyStats) {
        this.keyStats = keyStats;
    }

    public Map getMap() {
        return map;
    }

    public String getDescription() {
        return description;
    }

    public int getPopulation() {
        return population;
    }

    private class LandUse implements Serializable {
        private String type;
        private float value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    private class Population implements Serializable {
        private int year;
        private ArrayList<Percentage> percentages = new ArrayList<Percentage>();

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public ArrayList<Percentage> getPercentages() {
            return percentages;
        }

        public void setPercentages(ArrayList<Percentage> percentages) {
            this.percentages = percentages;
        }

        private class Percentage implements Serializable {
            private String age;
            private float value;

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public float getValue() {
                return value;
            }

            public void setValue(float value) {
                this.value = value;
            }
        }
    }

    private class KeyStat implements Serializable {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class Map implements Serializable {
        private float lat;
        private float lng;
        private int zoom;
        private float viewLat;
        private float viewLng;

        public float getLat() {
            return lat;
        }

        public float getLng() {
            return lng;
        }

        public int getZoom() {
            return zoom;
        }

        public float getViewLat() {
            return viewLat;
        }

        public float getViewLng() {
            return viewLng;
        }
    }
}
