Verify Phases, Indicators and countries response.
=====================
Tags: api
This Specification validates 3 scenarios, - Phases, - Indicators, - Countries

     
Validate Phases Response
----------------
* Given the "PHASES" exists in the "GDHI_Endpoint"
* Send the "GET" request to "PHASES"
* Then the Status code should be "200"
* and response should include "5" Phases

Validate Indicators Response
----------------
* Given the "INDICATORS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "INDICATORS"
* Then the Status code should be "200"
* and response should include "7" categories and "31" indicators


Validate Countries Response
----------------
* Given the "COUNTRIES" exists in the "GDHI_Endpoint"
* Send the "GET" request to "COUNTRIES"
* Then the Status code should be "200"
* and response should include "194" countries
