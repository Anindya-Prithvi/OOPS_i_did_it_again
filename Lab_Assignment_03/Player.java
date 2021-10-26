package Lab_Assignment_03;

import java.util.Scanner;

public final class Player {
    private final String name;
    private Floor position;
    public Player(Scanner sc){
        this.name = sc.nextLine();
    }

    public Floor getPosition(){
        return this.position;
    }

    public void setPosition(Floor floor){
        this.position = floor;
    }

    public String getName(){
        return name;
    }
    
}
