package com.jsonautomation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonautomation.model.CostAnalysis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JsonTest {

    @Test
    public void  testJsonDeserialization() throws Exception{

        // Set up ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Load JSON file
        File jsonFile = new File("src/test/resources/CostAnalysis.json");
        List<CostAnalysis> costList = Arrays.asList(mapper.readValue(jsonFile, CostAnalysis[].class));

        // Assert the number of items in the list
        Assertions.assertEquals(53, costList.size());

        // Find the top item ordered by Cost descending and assert CountryId
        CostAnalysis maxCostItem = costList.stream()
                .max(Comparator.comparingDouble(CostAnalysis::getCost))
                .orElse(null);
        Assertions.assertNotNull(maxCostItem);
        Assertions.assertEquals(0, maxCostItem.getCountryId()); // Adjust the expected CountryId

        // Sum the Cost for the year 2016 and assert the total
        double totalCost2016 = costList.stream()
                .filter(item -> "2016".equals(item.getYearId()))
                .mapToDouble(CostAnalysis::getCost)
                .sum();

        Assertions.assertEquals(77911.37445609999, totalCost2016, 0.0001); // Adjust the total as needed
    }
}