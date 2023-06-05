# gdhi-automation-version2

UI and API automation tests for GDHI project using Gauge, WebDriver and RestAssured


## Pre-requisites
* Install gauge 
```sh
brew install gauge
```

## Environment variables to be set
- We have 2 `environments` where testsuite can be executed, setup the variables in the respective properties file:
  - QA
  - Showcase
* `BROWSER=<chrome/firefox>`
* `APP_BASE_URL=<URL for the map page of the app>`


## Running specs locally 
`./gradlew clean gauge`


## Running Specs based on Tags in QA
`./gradlew clean gauge -Ptags=\'<TagName>\' ` 
```sh
`./gradlew clean gauge -Ptags='ui' `  - For UI tests
`./gradlew clean gauge -Ptags='api' ` - For API tests
```

## Running Specs based on Tags in Showcase
`./gradlew clean gaugeShowcase -Ptags=\'<TagName>\' ` 
```sh
`./gradlew clean gaugeShowcase -Ptags='ui' `  - For UI tests
`./gradlew clean gaugeShowcase -Ptags='api' ` - For API tests
```

## Running Specs in headless for CI.
```sh
`CHROME_HEADLESS=false ./gradlew clean gauge -Ptags='ui'`
```


## TestSuite Integration with Pipeline.
- TestSuite is integrated with below pipeline,
  - [Backend](https://github.com/healthenabled/gdhi-service-version2/actions/workflows/build.yml)
  - [Frontend](https://github.com/healthenabled/gdhi-app-version2/actions/workflows/build.yml)


## Sql pre-requisite for execution
- QA Pipeline: data_reset.sql is part of startup script.
  - [FE-Startup script](https://github.com/healthenabled/gdhi-app-version2/blob/main/scripts/start_server.sh)
  - [BE-Startup script](https://github.com/healthenabled/gdhi-service-version2/blob/main/scripts/start_server.sh)
- Showcase Pipeline: data_reset.sql queries has to manually executed 


## [Optional] For running from a remote CI agent
* Install xvfb and run it as a daemon
* Create a ~/.pgpass file with the Postgres DB password
* Add a task in the CI job to run the data_reset.sh file (this task will require the ~/.pgpass file to connect to the database)
* Set an environment variable `DISPLAY=0:0` to let chrome run in a virtual display

