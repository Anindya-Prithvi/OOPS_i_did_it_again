# the compiler: javac for java
 
# compiler flags:
 
# The build target 
default_builder: COVIN BACKPACK
	@echo "done!!"
	
COVIN: 
	@javac -d COVIN Lab_Assignment_01/COVIN.java
	
run_COVIN: COVIN
	@java -cp COVIN Lab_Assignment_01/COVIN

BACKPACK:
	@javac -d BACKPACK Lab_Assignment_02/BACKPACK.java

run_BACKPACK: BACKPACK
	@java -cp BACKPACK Lab_Assignment_02/BACKPACK

clean:
	@rm -rf COVIN
	@rm -rf BACKPACK