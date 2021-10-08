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
        System.out.print("COVIN Portal Initialized...\n"
        .concat(endline));
        System.out.print(menu);
        

    }

    //here we shall make Hospitals and citizens (staker)
}