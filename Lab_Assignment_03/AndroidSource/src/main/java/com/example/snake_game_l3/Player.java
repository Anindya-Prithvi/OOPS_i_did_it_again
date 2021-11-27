package com.example.snake_game_l3;

public final class Player {
    private final String name;
    private Floor position;
    public Player(){
        System.out.println("Enter the player name and hit enter");
        this.name = "Jeslyn";
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

    public void positionV(){
        System.out.println("Player position Floor-".concat(String.valueOf(this.position.getLocation())));
    }
    
}
