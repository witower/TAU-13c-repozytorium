*** Settings ***

Documentation     Resources for robertspl_order_form_tests

Library    SeleniumLibrary

*** Variable ***

${START URL}    https://roberts.pl/index.php?l=en&p=_katalog&i=_neutron_plus
${BROWSER}      chrome
${DELAY}        0

*** Keywords ***
Open Browser To Product Page
    Open Browser    about:blank    ${BROWSER}
    Set Selenium Speed    ${DELAY}

Go To Inquiry Page
    Go To   ${START URL}
    Title Should Be    Robert's Outdoor Equipment
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

Delivery Address Should Be Hidden
    Element Attribute Value Should Be   id:answer2   style     display: none;

Pick Courier Service
    Select Radio Button     krok2-sposob_dostawy-required       _formularz_zamowienia_sposob_dostawy_kurier

Delivery Address Should Be Visible
    Element Attribute Value Should Be   id:answer2   style     display: block;

Clear Email Fields
    Input Text  name:krok2-email-required   \
    Input Text  name:krok2-emailv-required   \

Fill In Email Field
    [Arguments]     ${value}
    Input Text  name:krok2-email-required   ${value}
    
Fill In Repeat Email Field
    [Arguments]     ${value}
    Input Text  name:krok2-emailv-required   ${value}
    
Field Should Have Error
    [Arguments]     ${field}
    Page Should Contain Element     xpath://input[@name='${field}']/parent::*[contains(@class, 'form_error')]   Field error expected in ${field}

Email Field Should Have Error
    Field Should Have Error  krok2-email-required

Repeat Email Field Should Have Error
    Field Should Have Error  krok2-emailv-required

Email Field Should Not Have Error
    Run Keyword And Expect Error    STARTS: Field error expected in      Email Field Should Have Error

Repeat Email Field Should Not Have Error
    Run Keyword And Expect Error    STARTS: Field error expected in      Repeat Email Field Should Have Error