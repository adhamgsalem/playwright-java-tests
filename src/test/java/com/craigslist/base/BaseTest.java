package com.craigslist.base;

import com.microsoft.playwright.Page;
import com.craigslist.core.PlaywrightFactory;
import org.junit.jupiter.api.*;

public class BaseTest {

    protected Page page;

    @BeforeEach
    protected
    void setup() {
        page = PlaywrightFactory.createPage();
    }

    @AfterEach
    void tearDown() {
        page.context().browser().close();
    }

    public Page getPage() {
        return page;
    }
}