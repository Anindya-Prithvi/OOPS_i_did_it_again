# the compiler: javac for java
CC = javac
 
# compiler flags:
CFLAGS  = 
 
# The build target 
default_builder:
	@echo -n "making ."
	@rm -r COVIN
	@$(CC) Lab_Assignment_01/*.java -d COVIN
	@echo .
	@echo "done!!"
	
run COVIN:
	@java -cp COVIN Lab_Assignment_01.COVIN

clean:
	@rm -rf *.class
	#just empties the builds, nothing more