package org.example;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.example.page.PastebinPage;

public class PastebinTest {
    private static Browser browser;

    @BeforeAll
    public static void setup() {
        browser = Browser.getInstance();
    }

    @AfterAll
    public static void tearDown() {
        browser.stopBrowser();
    }
    @Test
    public void baseTest(){

        PastebinPage page = new PastebinPage();
        page.openPage();
        page.fillCode();
        page.fillBashHighlighting();
        page.fillExpiration();
        page.fillTitle();
        page.submitForm();

        Assertions.assertTrue(page.isTitleCorrect());
        Assertions.assertTrue(page.isExpirationCorrect());
        Assertions.assertTrue(page.isCodeCorrect());
        Assertions.assertTrue(page.isHighlightingBash(), "Highlighting is not bash");
    }

}
