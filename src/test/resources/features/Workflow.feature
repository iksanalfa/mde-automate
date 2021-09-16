Feature: JALIN_ITMX History

  Scenario Outline: Go To Workflow Manager - Run History
    Given User login using <username> and <password>
    When User Click Main Menu <mainmenu> and Sub Menu <submenu>
    And User Choose Workflow <workflowname> want to be processed
    And User Click <button> button
    Then User get run history information for workflow <workflowname>

    Examples: 
      | username | password | mainmenu | submenu | workflowname | button |
      | Sigmauser-4 | S1gm41234* | Workflow Manager | Workflows | JALIN_ITMX_EOD | Run History |