*** Settings ***

Documentation   Tests of robetrs.pl order/inquiry form.

Resource        resource.robot

Suite Setup     Open Browser To Product Page
Suite Teardown  Close Browser
Test Setup      Submit To Inquiry Page

#Test Template 	Run with arguments

*** Test Cases ***
Fill in step1 with valid values
    Input Text  name:krok1-qty-required     2
    Input Text  name:krok1-info1            Example comment
    Click First Submit
    
