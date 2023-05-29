package ui.steps;

import api.steps.ApiBaseStep;
import com.github.javaparser.metamodel.LiteralExprMetaModel;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.data.DataFactory;
import ui.pages.CountryPage;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CountryPageSteps extends BaseStep {

    @Step("User should see the data they have submitted for <countryName> and score <3>")
    public void User_sees_country_details(String countryName, String score) {
        assertTrue(countryPage.areDetailsValid(DataFactory.getValidInputFormDataFor(countryName), score));
    }

    @Step("User navigates to list of countries page")
    public void User_goes_to_list_of_countries_page() {
        //landingPage.visitListOfCountries();
        landingPage.visitListOfCountriesAlternative();
            assertTrue(listOfCountriesPage.verifyListOfCountriesPageDetails());

    }

//    @Step("User should see list of countries along with phases")
//    public void verify_country_details() {
//        assertTrue(listOfCountriesPage.verifyListOfCountriesPageDetails());
//        assertTrue(listOfCountriesPage.iscountryNameDisplayed(countryName));
//        assertTrue(listOfCountriesPage.iscountryScoreDisplayed(countryScore));
//    }

    @Step("User should see the overall phase calculated correctly for a country")
    public void verifyPhaseCalcultionsForCountryLevel() {

    }


    @Step("User should see the <Sri Lanka> and <3> in list of published countries page along with phase")
    public void verifyPublishedCountryDisplayedInList(String countryName, String countryScore) {
        listOfCountriesPage.iscountryNameDisplayed(countryName);
        listOfCountriesPage.iscountryScoreDisplayed(countryScore,countryName);
    }

    @Step("User navigates to country details page for <Sri Lanka>")
    public void naviagteToCountryDetailsPAge(String countryName) {
listOfCountriesPage.navigateToCountryPage(countryName);
    }

    @Step("User should see the list of Countries")
    public void verifyCountryList() {
        ApiBaseStep Api = new ApiBaseStep();
        Api.GetUrl("HEALTH_INDICATOR_COUNTRIES", "GDHI_Endpoint");
        Response response = Api.sendRequest("GET","HEALTH_INDICATOR_COUNTRIES");
        JSONObject jsonobject = new JSONObject(response.getBody().asString());
        JSONArray jsonarray = new JSONArray(jsonobject.getJSONArray("countryHealthScores"));
        ArrayList<String> countries = new ArrayList();
        jsonarray.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            countries.add(obj.getString("countryName"));
        });
        System.out.println("Expected countries ::: "+countries);
        System.out.println("Actual countries :::" +listOfCountriesPage.getActualListOfCounties());
        assertTrue((countries.containsAll(listOfCountriesPage.getActualListOfCounties())));
    }


    @Step("Create Base Images")
    public void createbaseimage() throws IOException {
        countryPage.ExpectedSpiderGraph();
        countryPage.ExpectedLineGraph();
    }

    @Step("User Verifies the spider graph in the countryPage")
    public void ValidateSpidergraph() throws IOException {
        assertFalse(countryPage.isSpidergraphValid());
    }

    @Step("User Verifies the line graph in the countryPage")
    public void ValidateLinegraph() throws IOException {
        assertFalse(countryPage.isLinegraphValid());
    }
}
