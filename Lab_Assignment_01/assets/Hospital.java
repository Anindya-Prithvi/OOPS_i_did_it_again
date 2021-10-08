package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Hospital {
    private static int id_int;

    private final char[] pincode; //pincode 002013
    private final String name;
    private final char[] huid; //000000

    ArrayList<Slot> slots = new ArrayList<Slot>();

    public Hospital(char[] pincode, String name){
        this.pincode = pincode;
        this.name = name;
        this.huid = update_huid();
        if(validate(this)){++id_int;}
    }

    public static void add_hospital(ArrayList<Hospital> hospitals, java.util.Scanner sc){
        // Hospital Name: Medistar
        // PinCode: 110091
        // Hospital Name: Medistar, PinCode: 110091, Unique ID: 123456
        // sc closed in COVIN
        Hospital newhospital;
        try {
            System.out.print("Hospital Name: ");
            String name = sc.next();
            System.out.print("Pincode: ");
            char[] pincode = sc.next().toCharArray();
            sc.nextLine();
            //sc.close();
            newhospital = new Hospital(pincode, name);
        } catch (Exception e) {
            System.out.println("Exception Occured/ Invalid input");
            return;
        }
        if(validate(newhospital)) {
            hospitals.add(newhospital);
            System.out.println(hospital_info(newhospital));
        }
    }

    private static String hospital_info(Hospital hospital){
        // Hospital Name: Medistar, PinCode: 110091, Unique ID: 123456
        return "Hospital Name: "
            .concat(hospital.name)
            .concat(", PinCode: ")
            .concat(new String(hospital.pincode))
            .concat(", Unique ID: ")
            .concat(new String(hospital.huid));
    }

    private static boolean validate(Hospital hospital){
        return true;
    }

    private char[] update_huid(){
        String temp = String.valueOf(id_int); //temp should have atmost 6 digits
        int temp_length = temp.length();
        for(int i=0; i<6-temp_length; i++){
            temp = 0+temp;
        }
        char[] huid = temp.toCharArray();
        return huid;
    }

    public String getName() {
        return name;
    }

    public char[] getPincode() {
        return pincode;
    }

    public String getHuid() {
        return new String(huid);
    }

    public static void add_slots(ArrayList<Hospital> hospitals, ArrayList<Vaccine> vaccines, java.util.Scanner sc) {
        // Enter Hospital ID: 123456
        // Enter number of Slots to be added: 2
        // Enter Day Number: 1
        // Enter Quantity: 5
        // Select Vaccine
        // 0. Covax
        // 1. Covi
        try {
            System.out.print("Enter Hospital ID: ");
            char[] huid = sc.next().toCharArray();
            Hospital hospital_newslot = search_by_huid(hospitals, huid);
            if(hospital_newslot==null){System.out.println("Not found"); return;}
            System.out.print("Enter number of Slots to be added: ");
            int i = sc.nextInt(); //iterator
            while(i-->0){
                System.out.print("\nEnter Day Number: ");
                int day = sc.nextInt();
                System.out.print("\nEnter Quantity: ");
                int quantity = sc.nextInt();
                Vaccine selected = Vaccine.chooseVaccine(vaccines, sc);
                if(selected==null) return;
                Slot new_slot = new Slot(day, selected, quantity, huid);
                hospital_newslot.slots.add(new_slot);
                new_slot.show_slot();
            }

        } catch (Exception e) {
            System.out.println("Error occured");
            return;
        }
        return;
        
    }

    private static Hospital search_by_huid(ArrayList<Hospital> hospitals, char[] huid){
        for(Hospital j: hospitals){
            if(j.getHuid().equals(new String(huid))){
                return j;
            }
        }
        return null;
    }

    public static void list_slots(ArrayList<Hospital> hospitals, java.util.Scanner sc){
        //TODO
    }
}
