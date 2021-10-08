package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Citizen {
    //Each citizen can register themselves only once, and they must provide their name, age, and a unique ID.
    private String name;
    private int age;
    private final char[] uid;
    private String cvs = "REGISTERED";
    private String given = null; //vaccine name
    private int doses; //doses given
    private Integer due = null;

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
            String name = sc.next();
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
        if(validate(newcitizen)) {
            citizens.add(newcitizen);
            System.out.println(citizen_info(newcitizen));
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

    private static boolean validate(Citizen citizen){
        if(citizen.uid.length!=12) return false;
        if(citizen.age<18){
            System.out.println("Only 18 above are allowed");
            return false;
        }
        return true;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return new String(uid);
    }

    public void lookup(){
        System.out.println("Looking");
        return;
    }

    public static void book_slot(ArrayList<Hospital> hospitals, ArrayList<Citizen> citizens, java.util.Scanner sc){
        //may add error handling (not req) if out of vacc
        //slot.used_vaccine();
        //TODO
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
        System.out.println(vaccination_status(patient));
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
            if(j.getUid().equals(new String(uid))){
                return j;
            }
        }
        return null;
    }
}
