package Lab_Assignment_03;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class Game {
    private int points;
	private ArrayList<Floor> floors; //14 floors
	private Boolean isstarted = false;
	private Boolean isfinished = false;
    private final static Scanner sc = new Scanner(System.in);
    private final Dice dice;
    private final Player player;

    public Game(){
        //set up game
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

    protected void add_points(int dscore){
        this.points += dscore;
    }

    private void play(){
        System.out.println("The game setup is ready");
        while(!isfinished){
            await_roll();
            dice.roll();
            int dout = dice.getFaceValue();

            if(!isstarted){//checks if game started, also mocks player
                startmessageprompt(dout);
                continue;
            }

            int projected = player.getPosition().getLocation() + dout;
            if(projected>=floors.size()){
                System.out.println("Player cannot move");
                continue;
            }

            moveplayer(projected);
            showgamestate();
            
            if(player.getPosition().getLocation()==13){
                isfinished = true;
            }
        }
        gameover();
        System.out.println("Press Enter to exit");
        sc.nextLine();
    }

    private void showgamestate() {
        for(Floor i: floors){
            if(i.getLocation()==player.getPosition().getLocation()){
                System.out.print("####\t");
            }
            else{
                System.out.print("----\t");
            }
        }
        System.out.println();
        for(Floor i: floors){
            System.out.print(i.getLocation()+"\t");
        }
        System.out.println();
        for(Floor i: floors){
            if(i.getLocation()==player.getPosition().getLocation()){
                System.out.print("####\t");
            }
            else{
                System.out.print("----\t");
            }
        }
        System.out.println();
    }

    private void moveplayer(int projected) {
        Floor assigned = floors.get(projected);
        player.setPosition(assigned);
        player.positionV();  
        int dtravel = assigned.jump(this, player);
        getTotalPoints(); 
        while(dtravel!=0){
            showgamestate();
            assigned = floors.get(dtravel);
            player.setPosition(assigned);
            dtravel = assigned.jump(this, player);
            player.positionV();
            getTotalPoints(); 
        }
    }

    private void startmessageprompt(int dout) {
        if(dout==2){
            System.out.println("Game cannot start until you get 1");
            return;
        }
        else if(dout==1){//start the game
            isstarted = true;
            player.setPosition(floors.get(0));
            floors.get(0).jump(this, player);
            player.positionV();
            getTotalPoints();
            showgamestate();
            return;
        }
    }

    private void await_roll() {
        System.out.print("Hit Enter to roll the dice");//newline suppressor
        sc.nextLine();
    }

    private void gameover() {
        System.out.println("Game Over");
        System.out.println(player.getName()
            .concat(" accumulated ")
            .concat(String.valueOf(points))
            .concat(" points")
        );
        System.out.println("---------------------");
        File fall = new File("./Lab_Assignment_03/assets/finish.wav");

        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(fall);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (Exception e){
            System.out.println("File not found..");
        }

    }

    private void getTotalPoints() {
        System.out.println("Total Points ".concat(String.valueOf(points)));
    }

    public static void main(String[] args) {
        Game playnow = new Game();
        playnow.play();
    }
}

final class Dice {
    private final Random rand = new Random();
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