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
    private static Scanner sc;
    public static void main(String[] args){
        //initialize portal
        vaccines = new ArrayList<Vaccine>();
        hospitals = new ArrayList<Hospital>();
        citizens = new ArrayList<Citizen>();
        sc = new Scanner(System.in);
        System.out.print("COVIN Portal Initialized...\n");
        //main loop
        while(true){
            System.out.print(menu);
            if(choice_handler(sc)) break;
        }
        sc.close();
    }

    private static boolean choice_handler(Scanner sc){
        String choice;
        try {
            choice = sc.nextLine();            
        } catch (Exception e) {
            System.out.println("Unprecedented EOF");
            return true;
        }
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
        switch (_choice_int) {//resolution: All scanners passed to static ref
            case 1:
                Vaccine.add_vaccine(vaccines, sc);
                break;

            case 2:
                Hospital.add_hospital(hospitals, sc);
                break;

            case 3:
                Citizen.add_citizen(citizens, sc);
                break;

            case 4:
                Hospital.add_slots(hospitals, vaccines, sc);
                break;

            case 5:
                Citizen.book_slot(hospitals, citizens, sc);
                break;

            case 6:
                Hospital.list_slots(hospitals, sc);
                break;
            
            case 7:
                Citizen.check_vaccination_status(citizens, sc);
                break;
        
            default:
                break;
        }
        sc.nextLine(); //master STDIN clear
        return false; //parse choices
    }

}