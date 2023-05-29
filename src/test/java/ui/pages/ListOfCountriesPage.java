package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ListOfCountriesPage extends BasePage {

    @FindBy(xpath = "//li[@class='countries-list-details-country'][1]/span[1][contains(@class, 'country-score')]")
    private WebElement countryScore;

    @FindBy(xpath = "//li[@class='countries-list-details-country'][1]/span[2][contains(@class, 'country-name')]")
    private WebElement countryNameLink;

    @FindBy(css = ".countries-list-details")
    private WebElement countriesList;

    @FindBy(xpath = "//span[@class='country-name'][text()='Sri Lanka']")
    private WebElement countryNameText;

    @FindBy(xpath = "//span[@class='country-name'][text()='Sri Lanka']/parent::li/span[1][text()='2']")
    private WebElement countryScoreText;

    @FindBy(xpath = "//div[@class='country-name page-title']/div[contains(text(),'Sri Lanka')]")
    private WebElement countryPageTitle;

    public ListOfCountriesPage() {
        PageFactory.initElements(driver, this);

    }

    public boolean verifyListOfCountriesPageDetails() {
        return isElementVisible(countriesList);
    }

    public boolean iscountryScoreDisplayed(String countryScore, String countryName) {
        return isElementVisible(driver.findElement(By.xpath("//span[@class='country-name'][text()='"+countryName+"']" +
                "/parent::li/span[1][text()='"+countryScore+"']")));
    }

    public boolean iscountryNameDisplayed(String countryName) {
        return isElementVisible(driver.findElement(By.xpath("//span[@class='country-name'][text()='"+countryName+"']")));
    }


    public void navigateToCountryPage(String countryName) {
        driver.findElement(By.xpath("//span[@class='country-name'][text()='"+countryName+"']"))
                .click();
        waitForElementToBeVisible(driver
                .findElement(By.xpath("//div[@class='country-name page-title']/div[contains(text(),'"+countryName+"')]")));
    }

    public List getActualListOfCounties() {
        List<String> actualCountries = new ArrayList();
        List<WebElement> actualcountriesText = getListofElements(By.xpath("//ul[@class='countries-list-details']//span[@class='country-name']"));
        for (WebElement element:actualcountriesText) {
            actualCountries.add(element.getText());
        }
        return actualCountries;
    }
}
