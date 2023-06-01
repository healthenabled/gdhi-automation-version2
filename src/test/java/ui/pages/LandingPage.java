package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    @FindBy(css = ".indicator-panel-button")
    private WebElement panelButton;

    @FindBy(css = ".indicator-panel")
    private WebElement indicatorPanelbox;

    @FindBy(css = ".indicator-panel-container-name")
    private WebElement panelName;

    @FindBy(css = ".sub-header-title")
    private WebElement SubHeader;

    @FindBy(css = ".indicator-panel-error")
    private WebElement panelError;

    @FindBy(css = ".indicator-panel-container-context-section")
    private WebElement contextSection;

    @FindBy(id = "search-box")
    private WebElement searchBox;

    @FindBy(css = "div .autocomplete__inputs input")
    private WebElement autocompleteTextBox;

    @FindBy(css = ".countries-list-heading")
    private WebElement listOfCountriesHeading;

    @FindBy(css = ".indicator-panel-container-category-section-name")
    private WebElement categoryName;

    @FindBy(css = "//div[@class='footer']//span/a[@href='/methodology']")
    private WebElement footerMethodologyLink;

    @FindBy(css = "a[href='/indicators_info']")
    private WebElement listOfIndicatorHeaderLink;

    @FindBy(css = "//div[@class='footer']//span/a[@href='/api/export_global_data']")
    private WebElement exportGlobalDataLink;

    @FindBy(xpath = "//span[@class='logo-name hd-element']")
    private WebElement HomePageHeader;

    public LandingPage() {

        PageFactory.initElements(driver, this);
    }

    public void visit() {
        System.out.println("Opening URL "+System.getenv("APP_BASE_URL"));
        visitUrl(System.getenv("APP_BASE_URL"));

    }

    public void waitForPageToLoad() {
            waitForElementToBeVisible(HomePageHeader);
            sleep(2);
        }

    public String getIndicatorPanelName() {

        return waitForElementToBeVisible(SubHeader).getText();
    }

    public String getPanelButtonName() {

        return waitForElementToBeVisible(panelButton).getText();
    }

    public boolean doesDigitalHealthDataAppear() {
        return isElementVisible(categoryName);
    }

    public boolean doesDigitalHealthDataErrorAppear() {
        return isElementVisible(panelError);
    }

    public boolean doesContextSectionAppear() {
        return isElementVisible(contextSection);
    }

    public void searchForCountry(String countryName) {
        autoCompleteSearch(autocompleteTextBox, countryName);
        sleep(5);
    }

    public void visitListOfCountries() {
        focusOnElement(panelButton);
        panelButton.click();
    }

    public void visitCountryDetails() {
        visitListOfCountries();
    }


    public void visitListOfIndicators() {
        listOfIndicatorHeaderLink.click();
    }

    public boolean verifyFooterMethodologyLinkIsVisible() {
        return isElementVisible(footerMethodologyLink);
    }

    public boolean verifyIndicatorsLink() {

        return isElementVisible(listOfIndicatorHeaderLink);
    }

    public boolean verifyExportCountryDataLink() {
        return isElementVisible(exportGlobalDataLink);
    }

    public void visitListOfCountriesAlternative() {
        String currentUrl = driver.getCurrentUrl();
        String newUrl = currentUrl.substring(0, currentUrl.indexOf("/map"));
        driver.get(newUrl + "/country_list");
    }
}
