package Lab_Assignment_01;

import java.util.ArrayList;
import java.util.Scanner;
public class COVIN{
    private static final String endline = "--------------------------------\n";
    private static final String menu = ""
        .concat("1. Add Vaccine\n")
        .concat("2. Register Hospital\n")
        .concat("3. Register Citizen\n")
        .concat("4. Add slot for vaccination\n")
        .concat("5. Book slot for vaccination\n")
        .concat("6. List all slots for a hospital\n")
        .concat("7. Check Vaccination Status\n")
        .concat("8. Exit\n")
        .concat(endline);
    public static void main(String[] args){
        //initialize portal
        Scanner sc = new Scanner(System.in);
        System.out.print("COVIN Portal Initialized...\n".concat(endline));
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
        return false; //parse choices
    }

    //here we shall make Hospitals and citizens (staker)
}