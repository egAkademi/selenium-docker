package com.gkhnsit.tests.vendorportal.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "password",
        "monthlyEarning",
        "annualEarning",
        "profitMargin",
        "availableInventory",
        "searchKeyword",
        "searchResultsCount"

})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Getter
@Setter
public class VendorPortalTestData1 {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("monthlyEarning")
    private String monthlyEarning;
    @JsonProperty("annualEarning")
    private String annualEarning;
    @JsonProperty("profitMargin")
    private String profitMargin;
    @JsonProperty("availableInventory")
    private String availableInventory;
    @JsonProperty("searchKeyword")
    private String searchKeyword;
    @JsonProperty("searchResultsCount")
    private int searchResultsCount;
}
