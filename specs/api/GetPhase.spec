Verify Phases, Indicators and countries response.
=====================
Tags: api
Created by ramakrishna.cthoughts.com on 18/01/23

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
Validates Phases Response
----------------
* Given the Phases exists in the "GDHI_Endpoint"
* Send the "GET" request to "PHASES"
* Then the Status code should be "200"
* and response should include "5" Phases