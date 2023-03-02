Validate the Export functionlity for global and Individual countries
=====================
Tags: api
Created by ramakrishna.cthoughts.com on 02/03/23

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Validate Global export Functionality
----------------
* Given the "GLOBAL_EXPORT" exists in the "GDHI_Endpoint"
* Send the "GET" request to "GLOBAL_EXPORT"
* Then the Status code should be "200"


Validate Country export Functionality
----------------
* Given the "COUNTRY_EXPORT_DATA" exists in the "GDHI_Endpoint"
* Send the "GET" request to "COUNTRY_EXPORT_DATA"
* Then the Status code should be "200"