package com.craigslist.pages;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.craigslist.config.FrameworkConfig;

public class DashboardPage {

    private final Page page;
    private final Locator headerAppLogo;
    private final Locator linkHousingPage;


    public DashboardPage(Page page) {
        this.page = page;
        this.headerAppLogo = page.locator("#logo");
        this.linkHousingPage = page.locator("a[href*='/search/hhh']");
    }

    public void navigateToDashboardPage() {
        page.navigate(FrameworkConfig.baseUrl());
    }

    public void verifyDashboardPageLoaded() {
        headerAppLogo.waitFor();
    }

    public HousingPage clickOnHousingLink() {
        linkHousingPage.click();
        return new HousingPage(page);
    }
    
}
