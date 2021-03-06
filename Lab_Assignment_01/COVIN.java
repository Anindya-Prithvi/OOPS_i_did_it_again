package Lab_Assignment_01;

import java.util.ArrayList;
import java.util.Scanner;

import Lab_Assignment_01.assets.Vaccine;
import Lab_Assignment_01.assets.Hospital;
import Lab_Assignment_01.assets.Citizen;

public class COVIN{
    private final String endline = "--------------------------------\n";
    private final String menu = ""
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
    private final String portal_init = "COVIN Portal Initialized...\n";
    private final ArrayList<Vaccine> vaccines; //multiple instances of COVIN
    private final ArrayList<Hospital> hospitals; //multiple instances of COVIN
    private final ArrayList<Citizen> citizens; //multiple instances of COVIN
    private final Scanner sc;

    public COVIN(ArrayList<Vaccine> vaccines, ArrayList<Hospital> hospitals, ArrayList<Citizen> citizens, Scanner sc){
        //if in future there is a vaccine/hospital db which needs to be integrated
        this.vaccines = vaccines;
        this.hospitals = hospitals;
        this.citizens = citizens;
        this.sc = sc;
        System.out.print(portal_init);
    }

    public COVIN(){
        this(new ArrayList<Vaccine>()
            ,new ArrayList<Hospital>()
            ,new ArrayList<Citizen>()
            ,new Scanner(System.in));
    }

    public static void main(String[] args){
        //initialize portal
        COVIN instance = new COVIN();
        //main loop
        instance.event_loop();
    }

    public void event_loop(){//OTHER APPLICATIONS can just create the instance object and run the event loop
        while(true){
            System.out.print(menu);
            if(choice_handler(sc)) break;
        }
        sc.close();
        return;
    }

    private boolean choice_handler(Scanner sc){
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
        switch (_choice_int) {//resolution: All scanners passed to  ref
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