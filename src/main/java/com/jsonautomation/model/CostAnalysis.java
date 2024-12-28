package com.jsonautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostAnalysis {
    @JsonProperty("YearId")
    private String yearId;
    @JsonProperty("GeoRegionId")
    private int geoRegionId;
    @JsonProperty("CountryId")
    private int countryId;
    @JsonProperty("RegionId")
    private int regionId;
    @JsonProperty("SchemeId")
    private int schemeId;
    @JsonProperty("SchmTypeId")
    private int schmTypeId;
    @JsonProperty("Cost")
    private  double cost;

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public int getGeoRegionId() {
        return geoRegionId;
    }

    public void setGeoRegionId(int geoRegionId) {
        this.geoRegionId = geoRegionId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public int getSchmTypeId() {
        return schmTypeId;
    }

    public void setSchmTypeId(int schmTypeId) {
        this.schmTypeId = schmTypeId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}