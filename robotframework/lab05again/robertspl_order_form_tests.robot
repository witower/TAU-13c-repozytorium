*** Settings ***

Documentation   Tests of robetrs.pl order/inquiry form.

Resource        resource.robot

Suite Setup     Open Browser To Product Page
Suite Teardown  Close Browser
Test Setup      Go To Inquiry Page

#Test Template 	Run with arguments

*** Test Cases ***
Successfull Order
    Fill In Valid Quantity
    Fill In Example Comment
    Click Next Submit
    Step Should Be  2

    Pick Personal Collection
    Pick Valid Delivery Date
    Pick To Pay In Cash
    Pick No Invoice
    Fill In Customer Valid Data
    Click Next Submit
    Step Should Be  3

    Accept Terms
    Click Next Submit
    Step Should Be  4

    Click Continue Link
    Location Should Be Product Page

Delivery Address Show Hide
    Go To Step2

    Delivery Address Should Be Hidden
    Pick Courier Service
    Delivery Address Should Be Visible
    Pick Personal Collection
    Delivery Address Should Be Hidden