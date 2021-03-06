package org.example.page;

import org.example.Browser;
import org.openqa.selenium.WebElement;

public class PastebinPage {
    private Browser browser;
    private static final String PASTEBIN_PAGE_URL = "https://pastebin.com/";
    private static final String POST_FORM_XPATH = "//*[@id='postform-text']";

    private static final String PAST_EXPIRATION_XPATH = "//div[@class='form-group field-postform-expiration']//span[contains(@class, 'select2-selection--single')]";
    private static final String TEN_MINUTES_XPATH = "//li[text() ='10 Minutes']";
    private static final String PAST_HIGHLIGHTING_XPATH = "//div[@class='col-sm-9 field-wrapper']//span[contains(@id, 'select2-postform-format-container')]";
    private static final String BASH_XPATH = "//li[text()='Bash']";
    private static final String FORM_NAME_XPATH ="//*[@id='postform-name']";
    private static final String POST_FORM_VALUE = "git config --global user.name  \"New Sheriff in Town\"\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\ngit push origin master --force";
    private static final String FORM_NAME_VALUE = "how to gain dominance among developers";
    private static final String SUBMIT_BUTTON_XPATH="//button[text()='Create New Paste']";

    public PastebinPage() {
        browser = Browser.getInstance();
    }

    public void openPage(){
        browser.open(PASTEBIN_PAGE_URL);
    }

    public void fillCode(){
        waitVisible(POST_FORM_XPATH).sendKeys(POST_FORM_VALUE);
    }

    public void fillExpiration(){
        waitVisible(PAST_EXPIRATION_XPATH).click();
        waitVisible(TEN_MINUTES_XPATH).click();
    }

    public void fillBashHighlighting(){
        waitVisible(PAST_HIGHLIGHTING_XPATH).click();
        waitVisible(BASH_XPATH).click();
    }

    public void fillTitle(){
        waitVisible(FORM_NAME_XPATH).sendKeys(FORM_NAME_VALUE);
    }

    public void submitForm(){
        waitVisible(SUBMIT_BUTTON_XPATH).click();
    }



    private WebElement waitVisible(String xpathLocator) {
        WebElement el = browser.waitVisible(xpathLocator);
        return el;
    }

    public boolean isTitleCorrect(){
        boolean result = false;
        try {
            String xpathString = "//h1";
            waitVisible(xpathString);
            result = FORM_NAME_VALUE.equals(waitVisible(xpathString).getText());
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean isExpirationCorrect(){
        boolean result = false;

        try {
            String xpathString = "//div[@class='expire']";
            waitVisible(xpathString);
            result = "10 MIN".equals(waitVisible(xpathString).getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public boolean isHighlightingBash(){
        boolean result = false;

        try {
            String xpathString = "//ol[@class='bash']";
            waitVisible(xpathString);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    public boolean isCodeCorrect(){
        boolean result = false;

        try {
            String xpathString = "//textarea[@class='textarea']";
            waitVisible(xpathString);
            result = POST_FORM_VALUE.equals(waitVisible(xpathString).getText().trim());
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
