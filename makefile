# the compiler: javac for java
 
# compiler flags:
 
# The build target 
default_builder: COVIN BACKPACK GAME
	@echo "done!!"
	
COVIN: 
	@javac -d COVIN Lab_Assignment_01/COVIN.java
	
run_COVIN: COVIN
	@java -cp COVIN Lab_Assignment_01/COVIN

BACKPACK:
	@javac -d BACKPACK Lab_Assignment_02/BACKPACK.java

run_BACKPACK: BACKPACK
	@java -cp BACKPACK Lab_Assignment_02/BACKPACK

GAME:
	@javac -d GAME Lab_Assignment_03/Game.java

run_GAME: GAME
	@java -cp GAME Lab_Assignment_03/Game

clean:
	@rm -rf COVIN
	@rm -rf BACKPACK