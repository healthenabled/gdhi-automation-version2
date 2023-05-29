Regions
=====================
Tags: api
Created by ramakrishna.cthoughts.com on 25/04/23

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Validate Regions indicators
----------------
* Given the "Regions" exists in the "GDHI_Endpoint"
* Send the "GET" request to "Regions"
* Then the Status code should be "200"
* and response should include below regions
 |id    |RegionName                  |
 |PAHO  |Pan American Region         |
 |AFRO  |African Region              |
 |EURO  |European Region             |
 |WPRO  |Western Pacific Region      |
 |SEARO |South-East Asian Region     |
 |EMRO  |Eastern Mediterranean Region|

Validate Regions Get Years
----------------
* Given the "REGION_GET_YEARS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "REGION_GET_YEARS" with "SEARO" as "path" parameter
* Then the Status code should be "200"
* Write the Respone to Report

Validate Regions Get Data
----------------
* Given the "REGION_GET_DATA" exists in the "GDHI_Endpoint"
* Send the "GET" request to "REGION_GET_DATA" with "PAHO" as "path" parameter
* Then the Status code should be "200"
* Write the Respone to Report