package com.craigslist.pages;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HousingPage {

    private final Page page;
    private final Locator headerHousingPage;
    private final Locator inputFieldSearch;
    private final Locator comboBoxSortOrder;
    private final Locator sortNewest;
    private final Locator sortOldest;
    private final Locator sortPriceAsc;
    private final Locator sortPriceDesc;
    private final Locator sortUpcoming;
    private final Locator priceElement;
    private final Locator listResults;

    public HousingPage(Page page) {
        this.page = page;

        this.headerHousingPage = page.getByRole(AriaRole.HEADING);
        this.inputFieldSearch = page.getByPlaceholder("search housing");
        this.comboBoxSortOrder = page.locator(".cl-search-sort-mode");
        this.sortNewest = page.locator(".items .cl-search-sort-mode-newest");
        this.sortOldest = page.locator(".items .cl-search-sort-mode-oldest");
        this.sortPriceAsc = page.locator(".items .cl-search-sort-mode-price-asc");
        this.sortPriceDesc = page.locator(".items .cl-search-sort-mode-price-desc");
        this.sortUpcoming = page.locator(".items .cl-search-sort-mode-upcoming");
        this.priceElement = page.locator(".cl-search-result .priceinfo");
        this.listResults = page.locator(".cl-search-result");
    }

    public void verifyHousingPageLoaded() {
        headerHousingPage.waitFor();
    }

    public void waitForResults() {
        listResults.first().waitFor();
    }

    public void search(String text) {
        inputFieldSearch.clear();
        inputFieldSearch.fill(text);
        inputFieldSearch.press("Enter");
        waitForResults();
    }

    public List<String> getSortOptions() {
        comboBoxSortOrder.click();

        return page.locator(".items button .label")
                .allTextContents()
                .stream()
                .map(String::trim)
                .toList();
    }

    public void sortBy(String option) {
        comboBoxSortOrder.click();
        page.waitForSelector(".items");

        switch (option.toLowerCase()) {
            case "newest":
                sortNewest.click();
                break;
            case "oldest":
                sortOldest.click();
                break;
            case "priceasc":
                sortPriceAsc.click();
                break;
            case "pricedsc":
                sortPriceDesc.click();
                break;
            case "upcoming":
                sortUpcoming.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid sort option: " + option);
        }

        waitForResults();
    }

    public List<Integer> getPrices() {
        List<String> priceTexts = priceElement.allTextContents();
        List<Integer> prices = new ArrayList<>();

        for (String price : priceTexts) {
            price = price.replaceAll("[^0-9]", "");
            if (!price.isEmpty()) {
                prices.add(Integer.parseInt(price));
            }
        }

        return prices;
    }

}
