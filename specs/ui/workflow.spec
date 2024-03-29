 # User views the overall digital health maturity
Tags: ui
This scenario verifies the various functionalities related to the overall digital health maturity
     
## User submits responses to a country and verifies various details

* Given when the User opens the Brower
* User navigates to admin page
* User generates input form URL for "Sri Lanka"
* User copies the form link for "Sri Lanka"
* User navigates to the input form for "Sri Lanka"
* User submits the form without entering any details
* User should see error messages for the mandatory fields
* User fills the form for "Sri Lanka" with contact and resource information
* User should be able to save the partially filled form successfully
* User should not be able to submit the partially filled form
* User fills the form for "Sri Lanka" with health indicator answers
* User should be able to submit the data for "Sri Lanka" without any validation errors thrown
* User views the submitted responses for "Sri Lanka" in read only format after submission
* User Verifies the Validation of the Message displayed for status "Review in Pending"
* User navigates to admin page
* User navigates to review URL for "Sri Lanka" from admin page
* User edits the form for "Sri Lanka" and removes data
* User publishes the data for "Sri Lanka"
* User Verifies the Validation of the Message displayed for status "Published"
* User navigates to admin page
* User Selects the current year from the default year dropdown

* User opens the application url in a browser
* The default map view appears
* User navigates to list of countries page
* User should see the "Sri Lanka" and "3" in list of published countries page along with phase
* User navigates to country details page for "Sri Lanka"
//* User should see the digital health indicator data
////* User goes the country details page
* User should see the data they have submitted for "Sri Lanka" and score "3"
////* User should see the footer information

//## User Verifies the Spider and line graph in country's page.
//* Create Base Images
//* User Verifies the spider graph in the countryPage
//* User Verifies the line graph in the countryPage
* Close the Browser