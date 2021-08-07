package xyz.akashchaudhary.covid_19tracker.api;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CountryData {

    @SerializedName("updated")
    private String updated;

    @SerializedName("country")
    private String countryName;

    @SerializedName("cases")
    private String totalCases;

    @SerializedName("todayCases")
    private String todayCases;

    @SerializedName("deaths")
    private String totalDeaths;

    @SerializedName("todayDeaths")
    private String todayDeaths;

    @SerializedName("recovered")
    private String totalRecovered;

    @SerializedName("todayRecovered")
    private String todayRecovered;


    @SerializedName("active")
    private String totalActive;


    @SerializedName("tests")
    private String totalTests;


    @SerializedName("countryInfo")
    private Map<String,String> countryInfo;

    /*
    @SerializedName("flag")
    private String flag; */

    public CountryData(String updated, String countryName, String totalCases, String todayCases,
                       String totalDeaths, String todayDeaths, String totalRecovered, String todayRecovered,
                       String totalActive, String totalTests, Map<String, String> countryInfo) {
        this.updated = updated;
        this.countryName = countryName;
        this.totalCases = totalCases;
        this.todayCases = todayCases;
        this.totalDeaths = totalDeaths;
        this.todayDeaths = todayDeaths;
        this.totalRecovered = totalRecovered;
        this.todayRecovered = todayRecovered;
        this.totalActive = totalActive;
        this.totalTests = totalTests;
        this.countryInfo = countryInfo;
    }
    // 10:22

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(String totalActive) {
        this.totalActive = totalActive;
    }

    public String getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(String totalTests) {
        this.totalTests = totalTests;
    }

    public Map<String, String> getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(Map<String, String> countryInfo) {
        this.countryInfo = countryInfo;
    }

}
