package com.craigslist.tests;

import com.craigslist.base.BaseTest;
import com.craigslist.pages.DashboardPage;
import com.craigslist.pages.HousingPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.craigslist.listeners.TestListener;
import io.qameta.allure.*;


@ExtendWith(TestListener.class)
public class NavigationTests extends BaseTest {

    private DashboardPage dashboard;

    @BeforeEach
    void setupPage() {
        dashboard = new DashboardPage(page);
        dashboard.navigateToDashboardPage();
    }

    @Test
    void verifyUserNavigatesToDashboardPage() {
        dashboard.verifyDashboardPageLoaded();
    }

    @Test
    void verifyUserNavigatesToHousingPage() {
        HousingPage housing = dashboard.clickOnHousingLink();
        housing.verifyHousingPageLoaded();
    }
}