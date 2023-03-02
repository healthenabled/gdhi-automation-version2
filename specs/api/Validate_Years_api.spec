Verify Bff, Get and Post methods for
=====================
Tags: api
Created by ramakrishna.cthoughts.com on 01/03/23

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Validate Submit Year Response
----------------
* Given the "SUBMIT_YEAR" exists in the "GDHI_Endpoint"
* Create body for "year"
* Send the "POST" request to "SUBMIT_YEAR"
* Then the Status code should be "201"

Validate Get Years Response
----------------
* Given the "GET_YEARS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "GET_YEARS"
* Then the Status code should be "200"

Validate Bff Years Response
----------------
* Given the "BFF_YEARS" exists in the "GDHI_Endpoint"
* Send the "GET" request to "BFF_YEARS"
* Then the Status code should be "200"
* and response should include "Version1" as default version



