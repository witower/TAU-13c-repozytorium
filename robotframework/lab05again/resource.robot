*** Settings ***

Documentation     Resources for robertspl_order_form_tests

Library    SeleniumLibrary

*** Variable ***

${START URL}    https://roberts.pl/index.php?l=en&p=_katalog&i=_neutron_plus
${BROWSER}      chrome
${DELAY}        0

*** Keywords ***
Open Browser To Product Page
    Open Browser    ${START URL}    ${BROWSER}
    Set Selenium Speed    ${DELAY}
    Product Page Should Be Open

Product Page Should Be Open
    Title Should Be    Robert's Outdoor Equipment

Submit To Inquiry Page
    Click First Submit
    Location Should Contain     p=_zamowienie&i=_neutron_plus
    Step Should Be   1

Click First Submit
    Click Button    css:[type="submit"]

Step Should Be
    [Arguments]     ${expected}
    Element Attribute Value Should Be   name:step   value   ${expected}

