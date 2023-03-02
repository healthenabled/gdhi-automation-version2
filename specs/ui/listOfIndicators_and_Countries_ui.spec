# User views the list of indicator questions and countries page
Tags: ui
This scenario verifies the lis of indicators questions

* Given when the User opens the Brower

## Verify the lis of indicators questions is displayed as in database
* User opens the application url in a browser
* The default map view appears
* User navigates to the list of indicators page
* User should see below list of indicators
    | Indicators                         |
    | leadership and governance          |
    | strategy and investment            |
    | legislation, policy, and compliance|
    | workforce                          |
    | standards and interoperability     |
    | infrastructure                     |
    | services and applications          |

## Verify the list of Countries questions is displayed as in database
* User opens the application url in a browser
* The default map view appears
* User navigates to the list of countries page
* User should see the list of Countries

* Close the Browser