package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Citizen {
    //Each citizen can register themselves only once, and they must provide their name, age, and a unique ID.
    private String name;
    private int age;
    private final char[] uid;
    private String cvs = "REGISTERED"; //current vac status
    private String given = null; //vaccine name
    private int doses; //doses given
    private int due;

    public Citizen(String name, int age, char[] uid){
        this.name = name;
        this.age = age;
        this.uid = uid;
    }

    public static void add_citizen(ArrayList<Citizen> citizens, java.util.Scanner sc){
        // Citizen Name: Marrion
        // Age: 23
        // Unique ID: 123456789000
        // Citizen Name: Marrion, Age: 23, Unique ID: 123456789000
        Citizen newcitizen;
        try {
            System.out.print("Citizen Name: ");
            String name = sc.nextLine();
            System.out.print("Age: ");
            int age = sc.nextInt();
            System.out.print("Unique ID: ");
            char[] uid = sc.next().toCharArray();
            sc.nextLine();
            //sc.close();
            newcitizen = new Citizen(name, age, uid);
        } catch (Exception e) {
            sc.nextLine();//clear stdin
            System.out.println("Exception Occured/ Invalid input");
            return;
        }
        System.out.println(citizen_info(newcitizen));
        if(validate(newcitizen, citizens)) {
            citizens.add(newcitizen);
        }else{
            System.out.println("Validation failed");
        }
    }

    private static String citizen_info(Citizen citizen){
        // Citizen Name: Marrion, Age: 23, Unique ID: 123456789000
        return "Citizen Name: "
            .concat(citizen.name)
            .concat(", Age: ")
            .concat(String.valueOf(citizen.age))
            .concat(", Unique ID: ")
            .concat(new String(citizen.uid));
    }

    private static boolean validate(Citizen citizen, ArrayList<Citizen> citizens){
        for(Citizen j: citizens){
            if((new String(j.uid)).equals(new String(citizen.uid))) return false;
        }
        if(citizen.uid.length!=12) return false;
        if(citizen.age<18){
            System.out.println("Only 18 above are allowed");
            return false;
        }
        return true;
    }

    public int get_doses(){
        return doses;
    }

    public void set_given(String vaccine){
        this.given = vaccine;
    }

    public void update_doses(){
        this.doses ++;
    }

    public void set_cvs(String vs){
        this.cvs = vs;
    }

    public void set_due(int days){
        this.due = days;
    }

    public String get_name(){
        return name;
    }

    private static void lookup(ArrayList<Hospital> hospitals, Citizen patient, java.util.Scanner sc){
        System.out.print(""
            .concat("1. Search by area\n")
            .concat("2. Search by Vaccine\n")
            .concat("3. Exit\n")
            .concat("Enter option: "))        
        ;

        try {
            Slot booked;
            int choice = sc.nextInt();
            if((choice==1)||(choice==2)) {
                booked = (choice==1)?Hospital.search_by_pin(hospitals, sc)
                                    :Hospital.search_by_vaccine(hospitals, sc);
                if(booked==null){System.out.println("No slot booked");sc.nextLine();return;}
                if(booked.getDay()<patient.due){
                    System.out.println("Not eligible for vaccine");
                    sc.nextLine(); 
                    return;
                }
                if(!patient.cvs.equals("REGISTERED")){
                    if(!patient.given.equals(booked.getVaccine())){
                        System.out.println("No vaccine cocktails, pls");
                        sc.nextLine();
                        return;
                    }
                    if(patient.cvs.equals("FULLY VACCINATED")){
                        System.out.println("Vaccine overdone disallowed:)");
                        sc.nextLine();
                        return;
                    }
                }
                booked.used_vaccine(patient);
                sc.nextLine();
                choice = 3; //everything must come to an end
            }
            else if(choice==3){
                sc.nextLine();
                return;
            }
            else{
                sc.nextLine();
                System.out.println("Invalid choice, exiting");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            sc.nextLine();
        }
        
        // Enter option: 1
        // Enter PinCode: 110091
        // 123456 Medistar
        // Enter hospital id: 123456
        // 0-> Day: 1 Available Qty:5 Vaccine:Covax
        // 1-> Day: 2 Available Qty:5 Vaccine:Covi
        // Choose Slot: 0
        // Marrion vaccinated with Covax
        return;
    }

    public static void book_slot(ArrayList<Hospital> hospitals, ArrayList<Citizen> citizens, java.util.Scanner sc){
        // Enter patient Unique ID: 123456789000
        try {
            System.out.print("Enter patient Unique ID: ");
            char[] uid = sc.next().toCharArray();
            sc.nextLine();
            Citizen patient = search(citizens, uid);
            if(patient==null){System.out.println("Patient not found"); return;}
            else{
                //check CVS TOO

                lookup(hospitals, patient, sc);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input/ Error");
            sc.nextLine();
            return;
        }
        return;
    }

    public static void check_vaccination_status(ArrayList<Citizen> citizens, java.util.Scanner sc){
        // Enter Patient ID: 123456789000
        System.out.print("Enter Patient ID: ");
        char[] uid = sc.next().toCharArray();
        sc.nextLine();//clear stdin
        Citizen patient = search(citizens, uid);
        if(patient==null) {
            System.out.println("Does not exist");
            return;
        }
        // PARTIALLY VACCINATED
        if(patient.cvs.equals("PARTIALLY VACCINATED")){
            System.out.println(vaccination_status(patient));
        }
        else{
            System.out.println(patient.cvs);
        }
        return;
        // Vaccine Given: Covax
        // Number of Doses given: 1
        // Next Dose due date: 3
    }

    private static String vaccination_status(Citizen patient){
        return ""
            .concat(patient.cvs) //current vacstatus
            .concat("\nVaccine Given: "+patient.given)
            .concat("\nNumber of Doses given: "+patient.doses)
            .concat("\nNext Dose due date: "+patient.due)
        ;
    }

    private static Citizen search(ArrayList<Citizen> citizens, char[] uid){
        for(Citizen j: citizens){
            if((new String(j.uid)).equals(new String(uid))){
                return j;
            }
        }
        return null;
    }
}
