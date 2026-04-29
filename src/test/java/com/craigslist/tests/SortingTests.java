package com.craigslist.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.craigslist.base.BaseTest;
import com.craigslist.core.PlaywrightFactory;
import com.craigslist.listeners.TestListener;
import com.craigslist.pages.DashboardPage;
import com.craigslist.pages.HousingPage;

@ExtendWith(TestListener.class)
public class SortingTests extends BaseTest {

    private DashboardPage dashboard;
    private HousingPage housing;

    @BeforeEach
    protected void setup() {
        page = PlaywrightFactory.createPage();
        
        dashboard = new DashboardPage(page);
        dashboard.navigateToDashboardPage();

        housing = dashboard.clickOnHousingLink();
        housing.verifyHousingPageLoaded();
    }

    @Test
    public void verifyDefaultSortOrder() {
        List<String> actualSortingOptions = housing.getSortOptions();
        assertTrue(actualSortingOptions.stream().anyMatch(o -> o.contains("﹩")));
        assertTrue(actualSortingOptions.stream().anyMatch(o -> o.contains("newest")));
        System.out.println(actualSortingOptions);
    }

    @Test
    void verifySortingOptionsAfterSearch() {
        housing.search("house");
        List<String> actualSortingOptions = housing.getSortOptions();
        assertTrue(actualSortingOptions.stream().anyMatch(o -> o.contains("upcoming")));
        assertTrue(actualSortingOptions.stream().anyMatch(o -> o.contains("relevance")));
    }

    @Test
    void verifyPriceAscending() {
        housing.sortBy("priceasc");
        List<Integer> actualPrices = housing.getPrices();
        List<Integer> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        assertEquals(expectedPrices, actualPrices, "Prices are not sorted ascending");
    }

    @Test
    void verifyPriceDescending() {
        housing.sortBy("pricedsc");
        List<Integer> actualPrices = housing.getPrices();
        List<Integer> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());
        assertEquals(expectedPrices, actualPrices, "Prices are not sorted descending");
    }
}
