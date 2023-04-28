Validate the End to End Scenarios for Digital Health Index
=====================
Created by ramakrishna.cthoughts.com on 16/03/23
Tags: api
This is an executable specification file which Follows the end to end point flow of the app right
from the admin generating the url to user checking the country specific API's.

Admin Generates a URL for Country "NEPAL"
----------------
* Given the "GENERATE_URL" exists in the "GDHI_Endpoint"
* Get the UUID, Country Codes for the Country "NEPAL"
* Send the "POST" request to "GENERATE_URL" with "UUID" as "PATH" parameter
* Then the Status code should be "200"
* Validate the response should be Country success should be "true"
* Verify the Country Status Summary with Status "New"

User Saves, Submits for Country "NEPAL"
----------------
* Given the "SAVE" exists in the "GDHI_Endpoint"
* Create body for "country_data"
* Adder Header "Content-Type" and value "application/json"
* Send the "POST" request to "SAVE"
* Then the Status code should be "200"
* Verify the Country Status Summary with Status "DRAFT"
* Verify the Country questionnaire data

* Given the "SUBMIT" exists in the "GDHI_Endpoint"
* Create body for "country_data"
* Adder Header "Content-Type" and value "application/json"
* Send the "POST" request to "SUBMIT"
* Then the Status code should be "201"
* Verify the Country Status Summary with Status "REVIEW PENDING"

Admin Reviews and Publishes the data for Country "NEPAL"
----------------

* Given the "SAVECORRECTION" exists in the "GDHI_Endpoint"
* Create body for "country_data"
* Adder Header "Content-Type" and value "application/json"
* Send the "POST" request to "SAVECORRECTION"
* Then the Status code should be "200"
* Verify the Country Status Summary with Status "REVIEW PENDING"

* Given the "PUBLISH" exists in the "GDHI_Endpoint"
* Create body for "country_data"
* Adder Header "Content-Type" and value "application/json"
* Send the "POST" request to "PUBLISH"
* Then the Status code should be "201"
* Verify the Country Status Summary with Status "PUBLISHED"

Admin Verifies the ViewPublish API.
----------------

* Given the "VIEW_PUBLISH" exists in the "GDHI_Endpoint"
* Send the "GET" request to "VIEW_PUBLISH" with "UUID" as "PATH" parameter
* Then the Status code should be "200"
* Validate the Published Attributes in the response

Admin Changes the default year to Current Year
----------------
* Given the "SUBMIT_YEAR" exists in the "GDHI_Endpoint"
* Create body for CurrentYear
* Send the "POST" request to "SUBMIT_YEAR"
* Then the Status code should be "201"

User Verifies Country Summary and Country health indicator details
----------------
* Given the "COUNTRY_SUMMARY" exists in the "GDHI_Endpoint"
* Send the "GET" request to "COUNTRY_SUMMARY" with "CountryCode" as "QUERY" parameter
* Then the Status code should be "200"
* Verify the Country Summary is Displayed as expected

* Given the "HEALTH_INDICATORS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "HEALTH_INDICATORS" with "CountryCode" as "QUERY" parameter
* Then the Status code should be "200"
* Verify the HealthIndicators scores are Displayed as expected

* Given the "DEVELOPMENT_INDICATORS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "DEVELOPMENT_INDICATORS" with "CountryCode" as "QUERY" parameter
* Then the Status code should be "200"
* Verify the Development indicators data is Displayed as expected

* Given the "BENCHMARK" exists in the "GDHI_Endpoint"
* Send the "GET" request to "BENCHMARK" with "CountryCode" as "QUERY" parameter
* Then the Status code should be "200"
* Verify the Benchmark data is Displayed as expected

User Verifies the Overall Countries Health indicator
----------------
* Given the "HEALTH_INDICATOR_COUNTRIES" exists in the "GDHI_Endpoint"
* Send the "GET" request to "HEALTH_INDICATOR_COUNTRIES"
* Then the Status code should be "200"
* Verify the Country  data is Displayed overall indicators data

User Verifies the Published Countries years and Country Progress Over years
----------------
* Given the "PUBLISHED_YEARS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "PUBLISHED_YEARS" with "CountryCode" as "QUERY" parameter
* Then the Status code should be "200"
* Verify the Country for Published Years

* Given the "COUNTRY_PROGRESS_OVER_YEARS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "COUNTRY_PROGRESS_OVER_YEARS" with "CountryCode" as "QUERY" parameter
* Then the Status code should be "200"
* Verify the Country progress over the years data

