package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;

public class CountryPage extends BasePage {

    @FindBy(xpath = "//div[@class='country-name page-title']/div[contains(text(),'Sri Lanka')]")
    private WebElement countryNameHeading;

    @FindBy(xpath = "//div[contains(@class,'overall-score')]")
    private WebElement overallScore;

    @FindBy(css = ".health-indicators")
    private WebElement digitalHealthIndicatorSection;

    @FindBy(css = ".development-indicators")
    private WebElement developmentIndicatorSection;

    @FindBy(xpath = "//a[contains(@href,'/export_country_data')]")
    private WebElement exportLink;

    @FindBy(css = ".country-summary")
    private WebElement countrySummarySection;

    @FindBy(xpath = "//div[contains(@class,'country-summary-text')]")
    private WebElement countrySummaryText;

    @FindBy(xpath = "//div[contains(@class,'country-resource-title header-bold')][normalize-space(text())='Resources']")
    private WebElement resourcesText;

    @FindBy(xpath = "//ul[@class='country-text']")
    private WebElement resourcesSection;


    public CountryPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean  areDetailsValid(HashMap<String, String> data, String score) {
        boolean isValid;
        System.out.println(countryNameHeading.getText());
        System.out.println(data.get("countryName"));
        //isValid = countryNameHeading.getText().equals(data.get("countryName"));
        isValid = isElementVisible(countryNameHeading);
        isValid = isValid &&  overallScore.getText().equals(score);
        //isElementVisible(overallScore);
        isValid = isValid && isElementVisible(digitalHealthIndicatorSection);
        isValid = isValid && isElementVisible(developmentIndicatorSection);
        isValid = isValid && isElementVisible(exportLink);
        isValid = isValid && countrySummaryText.getText().equals(data.get("countrySummary"));
        isValid = isValid && resourcesText.getText().equalsIgnoreCase(data.get("resourcesText"));
        isValid = isValid && isElementVisible(resourcesSection);
        return isValid;
    }
}
