# the compiler: javac for java
 
# compiler flags:
 
# The build target 
default_builder: COVIN BACKPACK GAME Hop_n_win
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

Hop_n_win:
	@javac -d Hop_n_Win Lab_Assignment_04/Hop_n_win.java

run_Hop_n_win: Hop_n_win
	@java -cp Hop_n_win Lab_Assignment_04/Hop_n_win

clean:
	@rm -rf COVIN
	@rm -rf BACKPACK
	@rm -rf GAME
	@rm -rf Hop_n_win