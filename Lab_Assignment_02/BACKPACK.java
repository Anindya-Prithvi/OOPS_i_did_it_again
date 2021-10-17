package Lab_Assignment_02;

import java.util.ArrayList;

import Lab_Assignment_02.assets.Instructor;

public class BACKPACK {
    private final ArrayList<Instructor> instructors;
    private final static String menu = "Welcome to Backpack\n"
            .concat("1. Enter as instructor\n")
            .concat("2. Enter as student\n")
            .concat("3. Exit\n");

    public BACKPACK(ArrayList<Instructor> instructors){
        this.instructors = instructors;
    }

    public static void main(String[] args){
        //convert to launchable like COVIN
        System.out.print(menu);
        
    }
    
}
