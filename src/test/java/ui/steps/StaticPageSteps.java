package ui.steps;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import static org.junit.jupiter.api.Assertions.*;


public class StaticPageSteps extends BaseStep {

    @Step("User goes to the methodology page")
    public void User_goes_to_methodology_page() {
        methodologyPage.visit();
    }

    @Step("User goes to the list of indicators page from the methodology page")
    public void User_goes_to_indicators_page_from_methodology_page() {
        methodologyPage.clickIndicatorsLink();
        assertTrue(listOfIndicatorsPage.isLoaded());
    }


    @Step("User should see below list of indicators <table>")
    public void verifyIndicatorList(Table table) {
        assertTrue(listOfIndicatorsPage.isLoaded());
        System.out.println("Expected"+table.getColumnValues("Indicators"));
        //assertTrue(listOfIndicatorsPage.getActualListOfIndicators().equals(table.getColumnValues("Indicators")));
        assertEquals(table.getColumnValues("Indicators"),listOfIndicatorsPage.getActualListOfIndicators());
    }


}
