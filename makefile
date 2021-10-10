# the compiler: javac for java
 
# compiler flags:
 
# The build target 
default_builder: COVIN
	@echo "done!!"
	
COVIN: 
	@javac -d COVIN Lab_Assignment_01/COVIN.java
	
run_COVIN: COVIN
	@java -cp COVIN Lab_Assignment_01/COVIN

clean:
	@rm -rf COVIN