Feature: Article CRUD 

#Login Process
Scenario: User Login
Given User is on login Page
When User enters "p.rajalakshmi1@tcs.com" and "Password3"
Then User should be on Home page

#Add article using Scenario Outline
Scenario Outline: Create Article
Given User should be Article Page
When User Create Article "<title>" and "<desc>" and "<body>" and "<tag>"
Then Article must be Created

Examples:
| title | desc | body | tag |
| TestRP17 | Selenium1 | Java with Selenium | RP |

#Update with Parametreization / Data Table as Maps
Scenario: Update an Article
Given Article must be Created
When User Update an Article
| title |
| TestRP17 |
Then Article Should be Updated

#Delete with Parametreization / Data Table as Maps
Scenario: Update an Article
Given Article Should be Updated
When User Delete an Article
| title |
| TestRP17 |
Then Article Should be Deleted