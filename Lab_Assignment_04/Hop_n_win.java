package Lab_Assignment_04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Hop_n_win{
    private final static int max_hops = 5;
    private final static int max_tiles = 20;
    private static ArrayList<Tiles> tileCarpet;
    private final static String[] numberNames = {"zeroeth", "first", "second", "third", "fourth", "fifth"};
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        getReturn("Hit enter to initialize the game", sc);
        Player noobie = new Player();
        GenCalc<Object> cc = new GenCalc<Object>();
        Boolean flag = true;
        try{
            initTileCarpet(20);
        }
        catch (FileNotFoundException e0){
            System.err.println("This error is not recoverable, exiting. toyNames File was not found");
            flag = false;
        }
        catch (NoSuchElementException e1){
            System.err.println("toyNames File does not have enough entries. Error is not recoverable.");
            flag = false;
        }

        //Initialization over
        //Start main game logic

        while((noobie.getHops()<max_hops)&&flag){
            getReturn("Hit enter for your "
                    .concat(numberNames[noobie.getHops()+1])
                    .concat(" jump"), sc);
            int jumpLen = noobie.hop();
            if(jumpLen>max_tiles){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddly Puddle\nSplash!");
            }
            else{
                tileCarpet.get(jumpLen-1).land(noobie, jumpLen, cc, sc);
            }
        }
        
        //Game finish
        sc.close();
        if(flag) noobie.toysWon(); //display toys
    }
    private static void initTileCarpet(int totalNum) throws FileNotFoundException, NoSuchElementException{
        tileCarpet = new ArrayList<Tiles>();
        //read from file
        File toyNames = new File("./Lab_Assignment_04/assets/toyNames");
        Scanner rfs = new Scanner(toyNames);
        for(int i = 0; i<totalNum; i++){
            tileCarpet.add(new Tiles(rfs.nextLine()));
        }
        rfs.close();
        System.out.println("Game is ready");
    }

    private static void getReturn(String message, Scanner sc){
        System.out.printf("%s",message);
        while(true){
            try{
                sc.nextLine();
                break;
            }
            catch(NoSuchElementException e1){
                //can fix but unwanted resource leak
                //it's the fault of the user.
                System.out.println("I assume pressing enter is tougher than adding NULL. Restart program");
            }
        }
    }
}

class GenCalc<T>{
    public Object getCorrectAnswer(T first, T second) throws UnexpectedTypeException{
        if((first instanceof String)&&(second instanceof String)){
            return ((String) first).concat((String) second);
        }
        if((first instanceof Integer)&&(second instanceof Integer)){
            return ((Integer) first)/((Integer) second);
        }
        throw new UnexpectedTypeException("Wrong usage of calc");
    }
}

class Player{
    private final Random r1 = new Random();
    private final int cap = 5;
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
        System.out.println("Soft toys won by you are:");
        for(SoftToy i: bucket){
            System.out.printf("%s, ",i.getToyName());
        }
        System.out.println();//flush
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
    private final SoftToy st;
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
                catch (NoSuchElementException e0){
                    System.out.println("I assume pressing enter is tougher than adding NULL. Restart program");
                }
                catch (InvalidOptionException e1){
                    System.err.println(e1.getMessage());
                }
                catch (UnexpectedTypeException e2){
                    System.err.println("Terminate program and check arguments.");
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
                    System.out.println("I assume pressing enter is tougher than adding NULL. Restart program");
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
                    System.out.println("I assume pressing enter is tougher than adding NULL. Restart program");
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
            throw new InvalidOptionException("Invalid option, please write string/integer");
        }
    }

    private static void tauntPlayer(){
        System.out.println("Incorrect answer.\nYou did not win any soft toy.");
    }
}

class InvalidOptionException extends RuntimeException{
    public InvalidOptionException(String message){
        super(message);
    }
}

class UnexpectedTypeException extends RuntimeException{
    public UnexpectedTypeException(String message){
        super(message);
    }

}