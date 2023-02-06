# GDHI-Backend
[![GDHI-Backend](https://github.com/healthenabled/gdhi-automation-version2/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/healthenabled/gdhi-automation-version2/actions/workflows/build.yml)

# gdhi-automation-version2
UI and API automation tests for GDHI project using Gauge, WebDriver and RestAssured


## Pre-requisites
* Install gauge (brew install gauge)

## For running from a remote CI agent
* Install xvfb and run it as a daemon
* Create a ~/.pgpass file with the Postgres DB password
* Add a task in the CI job to run the data_reset.sh file (this task will require the ~/.pgpass file to connect to the database)
* Set an environment variable `DISPLAY=0:0` to let chrome run in a virtual display

## Environment variables to be set
* `BROWSER=<chrome/firefox>`
* `APP_BASE_URL=<URL for the map page of the app>`
* `INPUT_FORM_URL=<URL for the questionairre>`

## Running specs
`./gradlew clean gauge`

## Running Specs based on Tags
`./gradlew clean gauge -Ptags=\'<TagName>\' ` 

for UI -> `./gradlew clean gauge -Ptags='ui' ` 
for API -> `./gradlew clean gauge -Ptags='api' `
