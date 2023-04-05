Validation of Reject
=====================
Created by ramakrishna.cthoughts.com on 30/03/23

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Admin Generates a URL for Country "NEPAL"
----------------
* Given the "GENERATE_URL" exists in the "GDHI_Endpoint"
* Get the UUID, Country Codes for the Country "NEPAL"
* Send the "POST" request to "GENERATE_URL" with "UUID" as "PATH" parameter
* Then the Status code should be "200"
* Validate the response should be Country success should be "true"
* Verify the Country Status Summary with Status "New"

User Submits for Country "NEPAL"
----------------
* Given the "SUBMIT" exists in the "GDHI_Endpoint"
* Create body for "country_data"
* Adder Header "Content-Type" and value "application/json"
* Send the "POST" request to "SUBMIT"
* Then the Status code should be "201"
* Verify the Country Status Summary with Status "REVIEW PENDING"

Admin Rejectes the data for Country "NEPAL"
----------------
* Given the "REJECT" exists in the "GDHI_Endpoint"
* Adder Header "Content-Type" and value "application/json"
* Send the "DELETE" request to "REJECT" with "UUID" as "PATH" parameter
* Then the Status code should be "200"
* Verify the country is not Available in Country Status Summary