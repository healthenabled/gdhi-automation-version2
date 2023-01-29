package ui.steps;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.Step;
import ui.core.Driver;


public class commonSteps  {


    @Step("Given when the User opens the Brower")
    public void openBrowser() {
        Driver.beforeSuite();
    }

    @AfterSuite
    @Step("Close the Browser")
    public void closeBrowser() {
        Driver.afterSuite();
    }
}
