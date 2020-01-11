*** Settings ***

Documentation     A resource fil with reusable keywords and variables.

Library 	OperatingSystem

*** Variable ***

${app} =	${CURDIR}${/}figure.py

*** Keywords ***

Run with arguments
	[Arguments]		${command}		${expected}
	${rc}	${output} = 	Run and Return RC and Output 	${app} ${command}
	Should Be Equal 	${output} 	${expected}