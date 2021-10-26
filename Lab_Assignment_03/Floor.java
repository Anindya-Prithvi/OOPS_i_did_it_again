package Lab_Assignment_03;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public abstract class Floor {
    protected int location;
    protected int getLocation() {
        return location;
    }
    protected void give_point(Game game, int dscore){
        game.add_points(dscore);
    }
    protected void displaymessage(Player player, String floor_name){
        System.out.println(
            player.getName()
            .concat(" has reached ")
            .concat(floor_name)
        );
    }
    abstract int jump(Game game, Player player);
}

final class Empty extends Floor {
    public Empty(int location){
        this.location = location;
    }
    private final String floor_name = "An Empty Floor";
    @Override
    protected int jump(Game game, Player player){
        give_point(game, 1);
        displaymessage(player, floor_name);
        return 0;
    }
}

abstract class Snake extends Floor{
    void Sset_position(){//maybe play demote music
        File fall = new File("./Lab_Assignment_03/assets/snake_bite.wav");

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
}

final class NormalSnake extends Snake{
    private final String floor_name = "A Normal Snake Floor";
    public NormalSnake(int location){
        this.location = location;
    }

    @Override
    int jump(Game game, Player player) {
        give_point(game, -2);
        displaymessage(player, floor_name);
        Sset_position();
        return 1;
    }
    
}

final class KingKobra extends Snake{
    private final String floor_name = "A King Kobra Floor";
    public KingKobra(int location){
        this.location = location;
    }

    @Override
    int jump(Game game, Player player) {
        give_point(game, -4);
        displaymessage(player, floor_name);
        Sset_position();
        return 3;        
    }
    
}

abstract class Ladder extends Floor{
    void Lset_position(){//maybe play promot
        File fall = new File("./Lab_Assignment_03/assets/promote.wav");

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

}

final class NormalLadder extends Ladder{
    private final String floor_name = "A Normal Ladder Floor";
    public NormalLadder(int location){
        this.location = location;
    }

    @Override
    int jump(Game game, Player player) {
        give_point(game, 2);
        displaymessage(player, floor_name);
        Lset_position();
        return 12;        
    }
    
}

final class Elevator extends Ladder{
    private final String floor_name = "An Elevator Floor";
    public Elevator(int location){
        this.location = location;
    }

    @Override
    int jump(Game game, Player player) {
        give_point(game, 4);
        displaymessage(player, floor_name);
        Lset_position();
        return 10;
    }
    
}
