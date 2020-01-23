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
    Title Should Be    Robert's Outdoor Equipment

Submit To Inquiry Page
    Click Button    css:[type="submit"]
    Location Should Contain     p=_zamowienie&i=_neutron_plus
    Step Should Be   1

Click Next Submit
    Click Element    css:[type="submit"][name="SubmitNext"]

Step Should Be
    [Arguments]     ${expected}
    Element Attribute Value Should Be   name:step   value   ${expected}

Go To Step2
    Fill In Valid Quantity
    Fill In Example Comment
    Click Next Submit
    Step Should Be  2

Fill In Valid Quantity
    Input Text  name:krok1-qty-required     2

Fill In Example Comment
    Input Text  name:krok1-info1    Example comment

Pick Personal Collection
    Select Radio Button     krok2-sposob_dostawy-required       _formularz_zamowienia_sposob_dostawy_odbior_osobisty

Pick Valid Delivery Date
    Click Element   id:datepicker
    Click Element   css:.ui-datepicker-calendar [data-handler="selectDay"]

Pick To Pay In Cash
    Select Radio Button     krok2-rodzaj_platnosci-required     _formularz_zamowienia_rodzaj_platnosci_przy_odbiorze

Pick No Invoice
    Select Radio Button     krok2-dokument_zakupu-required      _formularz_zamowienia_dokument_zakupu_paragon

Fill In Customer Valid Data
    Input Text  name:krok2-imie-required        Johnny
    Input Text  name:krok2-nazwisko-required    Test
    Input Text  name:krok2-email-required       johnny@test.com
    Input Text  name:krok2-emailv-required      johnny@test.com
    Input Text  name:krok2-telefon-required     123456789

Accept Terms
    Select Checkbox     css:[type="checkbox"][name="akceptuje_regulamin-required"]

Click Continue Link
    Click Element   link:Click here to go back to the page before starting inquiry.

Location Should Be Product Page
    Location Should Be  ${START URL}

