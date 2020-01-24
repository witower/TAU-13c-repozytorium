*** Settings ***

Documentation 	Tests to determine which figures could be constructed from given side lengths.
...
... 			Supports: Triangles and Quadrangles.
... 			The test suites requires to be launched in the same directory as the program figure.py.
...				Command represents the arguments passed to figure.py script.
...
...				For the sake of readability phrase "passed as arguments" is ommited in the endigs of each test case name.

Resource 		resource.robot
Test Template 	Run with arguments
Library 		OperatingSystem

*** Test Cases ***						Command			Expected

No arguments							/				nie rozpoznano

One argument 							1				nie rozpoznano

Two arguments 							1 2				nie rozpoznano

Five arguments 							1 2 3 4 5		nie rozpoznano

Underscores  			 				_ __ ___ ____	nie rozpoznano

Dollars									$ $$ $$$ $$$$	nie rozpoznano

Curlies									\{ \} \$\{ \}\{		nie rozpoznano

Letters									a A b B			nie rozpoznano

Letter and three valid arguments		1 a 1 1			nie rozpoznano

Zeros									0 0 0			nie rozpoznano

Zero and three valid arguments			1 0 1 1			nie rozpoznano

Negatives								-1 -1 -1		nie rozpoznano

Negative and three valid arguments		1 -1 1 1		nie rozpoznano

Floats									4.456 4.456 4.456 4.456		kwadrat

E notation								4e3 4e3 4e3 4e3		kwadrat

Huge number								4e64 4e64 4e64 4e64		kwadrat

Equilateral triangle					1 1 1			trojkat rownoboczny

Polygonal triangle					 	2 2.5 4			trojkat roznoboczny

Isosceles triangle						1 2 2			trojkat rownoramienny

Square              					1 1 1 1			kwadrat

Quadrangle		                		1 2 2 1			prostokat