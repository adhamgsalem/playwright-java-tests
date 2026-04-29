package com.craigslist.core;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    public static Page createPage() {
        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false));

        return browser.newPage();
    }
}