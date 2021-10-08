package Lab_Assignment_01;

import java.util.ArrayList;
import java.util.Scanner;

import Lab_Assignment_01.assets.Vaccine;
import Lab_Assignment_01.assets.Hospital;
import Lab_Assignment_01.assets.Citizen;

public class COVIN{
    private static final String endline = "--------------------------------\n";
    private static final String menu = ""
        .concat(endline)
        .concat("1. Add Vaccine\n")
        .concat("2. Register Hospital\n")
        .concat("3. Register Citizen\n")
        .concat("4. Add slot for vaccination\n")
        .concat("5. Book slot for vaccination\n")
        .concat("6. List all slots for a hospital\n")
        .concat("7. Check Vaccination Status\n")
        .concat("8. Exit\n")
        .concat(endline);
    private static ArrayList<Vaccine> vaccines; //multiple instances of COVIN
    private static ArrayList<Hospital> hospitals; //multiple instances of COVIN
    private static ArrayList<Citizen> citizens; //multiple instances of COVIN

    public static void main(String[] args){
        //initialize portal
        vaccines = new ArrayList<Vaccine>();
        Scanner sc = new Scanner(System.in);
        System.out.print("COVIN Portal Initialized...\n");
        //main loop
        while(true){
            System.out.print(menu);
            if(choice_handler(sc.nextLine())) break;
        }
        sc.close();
    }

    public static boolean choice_handler(String choice){
        int _choice_int;
        try {
            _choice_int = Integer.parseInt(choice);
        } catch (Exception e) {
            System.out.println("Invalid choice");
            return false; //i.e. dont exit
        }
        if (_choice_int==8) return true; //exit main loop
        if ((_choice_int>8)||_choice_int<1){
            System.out.println("Invalid choice");
            return false; //i.e. dont exit
        }
        switch (_choice_int) {//resolution: All constructors will have Scanners to make objects
            case 1:
                
                break;
        
            default:
                break;
        }
        return false; //parse choices
    }

    //here we shall make Hospitals and citizens (staker)
}