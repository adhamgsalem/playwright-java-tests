package com.craigslist.listeners;

import com.craigslist.base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;

import java.io.ByteArrayInputStream;

public class TestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        Object testInstance = context.getRequiredTestInstance();

        if (!(testInstance instanceof BaseTest baseTest)) {
            return;
        }

        Page page = baseTest.getPage();

        if (page == null) {
            return;
        }

        try {
            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions().setFullPage(true));

            Allure.addAttachment("Failure Screenshot",new ByteArrayInputStream(screenshot));

        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}