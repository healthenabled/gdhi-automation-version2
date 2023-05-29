package ui.pages;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    @FindBy(xpath = "//div[@class='phase-overview box']")
    private static WebElement SpiderImage;

    @FindBy(xpath = "//div[@class='comparison-graph-panel']")
    private static WebElement LineGraph;


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

    public void ExpectedSpiderGraph() throws IOException {
        focusOnElement(SpiderImage);
        File actual = SpiderImage.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(actual, new File(System.getProperty("user.dir") +"/Images/SpiderGraph.png"));
    }

    public void ExpectedLineGraph() throws IOException {
        focusOnElement(LineGraph);
        File actual = LineGraph.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(actual, new File(System.getProperty("user.dir") +"/Images/LineGraph.png"));
    }

    public boolean isSpidergraphValid() throws IOException {

        focusOnElement(SpiderImage);
        File actual = SpiderImage.getScreenshotAs(OutputType.FILE);
        BufferedImage expectedImage = ImageIO.read(
                new File(System.getProperty("user.dir") +"/Images/SpiderGraph.png"));
        BufferedImage actualImage = ImageIO.read(actual);
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        ImageDiff dif = diff.withDiffSizeTrigger(2500);

        System.out.println(diff.hasDiff());
        System.out.println(diff.getDiffSize());
        ImageIO.write(diff.getDiffImage(), "png", new File(System.getProperty("user.dir") +"/Images/spiderdiff.png"));

        return diff.hasDiff();
    }

    public boolean isLinegraphValid() throws IOException {

        focusOnElement(LineGraph);
        File actual = LineGraph.getScreenshotAs(OutputType.FILE);
        BufferedImage expectedImage = ImageIO.read(
                new File(System.getProperty("user.dir") +"/Images/LineGraph.png"));
        BufferedImage actualImage = ImageIO.read(actual);
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        ImageDiff dif = diff.withDiffSizeTrigger(2500);

        System.out.println(diff.hasDiff());
        System.out.println(diff.getDiffSize());
        ImageIO.write(diff.getDiffImage(), "png", new File(System.getProperty("user.dir") +"/Images/spiderdiff.png"));

        return diff.hasDiff();
    }
}
