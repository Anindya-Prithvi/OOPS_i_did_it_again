package Lab_Assignment_04;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Hop_n_win{
    static int max_hops = 5;
    static int max_tiles = 20;
    static ArrayList<Tiles> tileCarpet;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hit enter to initialize the game");
        while(true){
            try{
                sc.nextLine();
                break;
            }
            catch(NoSuchElementException e1){
                System.out.println("I assume pressing enter is tougher than adding NULL");
            }
        }
        Player noobie = new Player();
        GenCalc<Object> cc = new GenCalc<Object>();
        initTileCarpet();
        while(noobie.getHops()<=max_hops){
            int jumpLen = noobie.hop();
            if(jumpLen>max_tiles){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddly Puddle\nSplash!");
            }
            else{
                tileCarpet.get(jumpLen-1).land(noobie, jumpLen, cc, sc);
            }
        }
        sc.close();
    }
    private static void initTileCarpet(){
        tileCarpet = new ArrayList<Tiles>();
        tileCarpet.add(new Tiles("Perman"));
        tileCarpet.add(new Tiles("Doraemon"));
        tileCarpet.add(new Tiles("Nobita"));
        tileCarpet.add(new Tiles("Shizuka"));
        tileCarpet.add(new Tiles("Kitretsu"));
        tileCarpet.add(new Tiles("Thomas Engine"));
        tileCarpet.add(new Tiles("Super Mario"));
        tileCarpet.add(new Tiles("Power Rangers"));
        tileCarpet.add(new Tiles("Pikachu"));
        tileCarpet.add(new Tiles("Mocha Bear"));
        tileCarpet.add(new Tiles("Chotta Bheem"));
        tileCarpet.add(new Tiles("Tom"));
        tileCarpet.add(new Tiles("Jerry"));
        tileCarpet.add(new Tiles("Courage"));
        tileCarpet.add(new Tiles("Ash"));
        tileCarpet.add(new Tiles("Misty"));
        tileCarpet.add(new Tiles("Captain America"));
        tileCarpet.add(new Tiles("Thor"));
        tileCarpet.add(new Tiles("Loki"));
        tileCarpet.add(new Tiles("Sylvie"));
        System.out.println("Game is ready");
    }
}

class GenCalc<T>{
    public Object getCorrectAnswer(T first, T second){
        if((first instanceof String)&&(second instanceof String)){
            return ((String) first).concat((String) second);
        }
        if((first instanceof Integer)&&(second instanceof Integer)){
            return ((Integer) first)/((Integer) second);
        }
        return null;
    }

}

class Player{
    Random r1 = new Random();
    final int cap = 5;
    //you
    private int n_hops;
    private final ArrayList<SoftToy> bucket;
    public Player(){
        n_hops = 0;
        bucket = new ArrayList<SoftToy>(cap);
    }
    public void store(SoftToy e){
        bucket.add(e);
    }
    public void toysWon(){
        for(Object i: bucket){
            System.out.println(i);
        }
    }
    public int getHops() {
        return n_hops;        
    }
    public int hop(){
        ++n_hops;
        return r1.nextInt(25) + 1;
    }
}

class SoftToy implements Cloneable{
    private final String toyName;
    public SoftToy(String toyName){
        this.toyName = toyName;
    }
    public String getToyName() {
        return toyName;
    }
    @Override
    protected SoftToy clone() throws CloneNotSupportedException {
        return new SoftToy(toyName);
    }
}

class Tiles{
    final SoftToy st;
    public Tiles(String nameOfToy){
        st = new SoftToy(nameOfToy);
    }

    public void land(Player player, int pos, GenCalc<Object> cc, Scanner sc){
        System.out.printf("You landed on tile %d\n", pos);
        if(pos%2==1){
            while(true){
                try{
                    if(generateQues(cc, sc)){
                        rewardPlayer(player, st);
                        break;
                    }
                    else{
                        tauntPlayer();
                        break;
                    }
                }
                catch (InvalidOptionException e1){
                    System.err.println(e1.getMessage());
                }
            }
        }
        else{
            rewardPlayer(player, st);
        }
    }

    private static void rewardPlayer(Player player, SoftToy st){
        try{
            SoftToy ctoy = st.clone();
            player.store(ctoy);
            System.out.printf("You won a %s soft toy\n", ctoy.getToyName());
        }
        catch(CloneNotSupportedException e1){
            System.out.println("Reward could not be generated.");
        }
    }

    private static boolean generateQues(GenCalc<Object> cc, Scanner sc) throws InvalidOptionException{
        Random r2 = new Random();
        final int bound = 10000;
        System.out.println("Question answer round. Integer or string (input lower case)?");
        String scanned = sc.nextLine();
        if(scanned.equals("integer")){
            final int rand1 = r2.nextInt(bound);
            final int rand2 = r2.nextInt(rand1/2)+1; //to force answer > 0
            System.out.printf("Calculate the result of %d divided by %d (Ceil)\n", rand1, rand2);
            int userRes;
            while(true){
                try {
                    String receivedInput = sc.nextLine();
                    userRes = Integer.parseInt(receivedInput);
                    break;                 
                } catch (NumberFormatException e1) {
                    System.out.println("Please enter an integer.");
                } catch (NoSuchElementException e2){
                    System.out.println("Please enter something.");
                }
            }
            if(userRes == (Integer) cc.getCorrectAnswer(rand1, rand2)){
                return true;
            }
            else{
                return false;
            }
            
        }
        else if(scanned.equals("string")){
            String genFrom = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
            StringBuffer randB = new StringBuffer();
            for(int i=0; i<8; i++) randB.append(genFrom.charAt(r2.nextInt(52)));
            String rand1 = randB.toString().substring(0, 4);
            String rand2 = randB.toString().substring(4, 8);
            System.out.printf("Calculate the concatenation of strings %s and %s\n", rand1, rand2);
            String receivedInput;
            while (true) {
                try{
                    receivedInput = sc.nextLine();
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("Please enter something");
                }
            }
            if(receivedInput.equals(cc.getCorrectAnswer(rand1, rand2).toString())){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new InvalidOptionException("not string or int");
        }
    }

    private static void tauntPlayer(){
        System.out.println("Incorrect answer.\nYou did not win any soft toy.");
    }
}

class InvalidOptionException extends Exception{
    public InvalidOptionException(String message){
        super(message);
    }
}