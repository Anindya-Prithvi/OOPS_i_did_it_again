package Lab_Assignment_03;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public final class Game {
    int points;
	ArrayList<Floor> floors; //14 floors
	Boolean isstarted = false;
	Boolean isfinished = false;
    static Scanner sc = new Scanner(System.in);
    Dice dice;
    public void add_points(int dscore){
        this.points += dscore;
    }
    final Player player;
    public Game(){
        player = new Player(sc);
        dice = new Dice(2);
        this.floors = new ArrayList<Floor>();
        floors.add(new Empty(0));
        floors.add(new Empty(1));
        floors.add(new Elevator(2));
        floors.add(new Empty(3));
        floors.add(new Empty(4));
        floors.add(new NormalSnake(5));
        floors.add(new Empty(6));
        floors.add(new Empty(7));
        floors.add(new NormalLadder(8));
        floors.add(new Empty(9));
        floors.add(new Empty(10));
        floors.add(new KingKobra(11));
        floors.add(new Empty(12));
        floors.add(new Empty(13)); //finish

        player.setPosition(null);
    }

    void play(){
        System.out.println("The game setup is ready");
        while(!isfinished){
            System.out.println("Hit Enter to roll the dice");
            sc.nextLine();
            dice.roll();
            int dout = dice.getFaceValue();
            if(!isstarted){
                if(dout==2){
                    System.out.println("Game cannot start until you get 1");
                    continue;
                }
                else if(dout==1){//start the game
                    isstarted = true;
                    player.setPosition(floors.get(0));
                    floors.get(0).jump(this, player);
                    player.positionV();
                    getTotalPoints();
                    continue;
                }
            }
            int projected = player.getPosition().getLocation() + dout;
            if(projected>13){
                System.out.println("Player cannot move");
                continue;
            }
            Floor assigned = floors.get(projected);
            player.setPosition(assigned);
            player.positionV();  
            int dtravel = assigned.jump(this, player);
            getTotalPoints(); 
            if(dtravel!=0){
                assigned = floors.get(dtravel);
                player.setPosition(assigned);
                dtravel = assigned.jump(this, player);
                player.positionV();
                getTotalPoints(); 

            }
            if(player.getPosition().getLocation()==13){
                isfinished = true;
            }
        }
        gameover();
    }

    private void gameover() {
        System.out.println("Game Over");
        System.out.println(player.getName()
            .concat(" accumulated ")
            .concat(String.valueOf(points))
            .concat(" points")
        );
        System.out.println("---------------------");
    }

    private void getTotalPoints() {
        System.out.println("Total Points ".concat(String.valueOf(points)));
    }

    public static void main(String[] args) {
        Game playnow = new Game();
        playnow.play();
    }
}

class Dice {
    Random rand = new Random();
    private final int numFaces; //maximum face value
    private int faceValue;
    public Dice(int numFaces) {
        this.numFaces = numFaces;
    }
    // Rolls the dice
    public void roll() {
        faceValue = 1+rand.nextInt(numFaces);
        System.out.println("Dice gave ".concat(String.valueOf(faceValue)));
    }
    public int getFaceValue() {
        return faceValue;
    }
}