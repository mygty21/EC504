1.Environment Setup
	i. Install Java1.8
	ii. Download the libraries (./jogl-2.0) and setup JOGL in Eclipse:
		ref: https://jogamp.org/wiki/index.php/Setting_up_a_JogAmp_project_in_your_favorite_IDE
		1) Set up a Java project in eclipse and include all the source files(can exclude TestAVLTree.java,TestFHeap.java and TestMinHeap.java if the IDE shows error on these files, which are programs running JUnit test, can be compiled through command line, see JUnit_Setup.txt)
		2) Select Window->Preferences from the menu to open up the Preferences panel.
		3) From the panel on the left, select Java->Build Path->User Libraries. Click "New..." on the right hand side
		4) Type "jogl-2.0" as the library name and click "OK"
		5) Then the new library's name will appear in the white area. Click on it, and click "Add External Jars.."
		6) In the popup window, go to the path where the ./jogl-2.0 is stored. Select all the files in jogl-2.0/jar/ and click "Open".
		7) Click "OK to close the panel"
		8) Right-click your project in the Package Explorer and click "Properties".
		9) Select "Java Build Path" and click the "Libraries" tab.
		10) Click "Add Library...", select "User Library", click "Next", check "jogl-2.0", and click "Finish".
		*May need to specify the Main class. Can be done by 'Run->Run Configurations->Java Application->Main class' and set Main.java as Main class

2.Run the application
	i. Run the application using 'java Main.class' in the output directory
	ii. Select options based on the menu
	iii. If selecting options 1-3, after running the program, an HTML file will be generated in the same directory
3.Unit test
	i) See JUnit_Setup.txt for setting up
	ii) Compile the unittest program in command line, e.g. 'javac TestAVLTree.java'. With the compiled .class files, run the test suite by 'java TestAVLTree'
