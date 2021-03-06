package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Hospital {
    private static int id_int;

    private final char[] pincode; //pincode 002013
    private final String name;
    private final char[] huid; //000000

    private final ArrayList<Slot> slots = new ArrayList<Slot>();

    public Hospital(char[] pincode, String name){
        this.pincode = pincode;
        this.name = name;
        this.huid = update_huid();
    }

    public static void add_hospital(ArrayList<Hospital> hospitals, java.util.Scanner sc){
        // Hospital Name: Medistar
        // PinCode: 110091
        // Hospital Name: Medistar, PinCode: 110091, Unique ID: 123456
        // sc closed in COVIN
        Hospital newhospital;
        try {
            System.out.print("Hospital Name: ");
            String name = sc.nextLine();
            System.out.print("Pincode: ");
            char[] pincode = sc.next().toCharArray();
            ;
            //sc.close();
            newhospital = new Hospital(pincode, name);
        } catch (Exception e) {
            System.out.println("Exception Occured/ Invalid input");
            return;
        }
        if(validate(newhospital, hospitals)) {
            hospitals.add(newhospital);
            System.out.println(hospital_info(newhospital));
        }
        else{
            System.out.println("Validation failed");
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

    private static boolean validate(Hospital hospital, ArrayList<Hospital> hospitals){
        for(Hospital j: hospitals){
            if(j.name.equals(hospital.name)&&(new String(j.pincode)).equals(new String(hospital.pincode))) return false;
        }
        if(hospital.pincode.length!=6) return false;
        ++Hospital.id_int;
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

    // public String getName() {
    //     return name;
    // }

    // public char[] getPincode() {
    //     return pincode;
    // }

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
            if(hospital_newslot==null){System.out.println("Not found");; return;}
            System.out.print("Enter number of Slots to be added: ");
            int i = sc.nextInt(); //iterator
            if(i<1){; return;}//not an error
            while(i-->0){
                System.out.print("Enter Day Number: ");
                int day = sc.nextInt();
                System.out.print("Enter Quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine();//clear stdin
                Vaccine selected = Vaccine.chooseVaccine(vaccines, sc);
                if(selected==null) return;
                Slot new_slot = new Slot(day, selected, quantity);
                hospital_newslot.slots.add(new_slot);
                new_slot.show_slot(hospital_newslot);
            }

        } catch (Exception e) {
            ;
            System.out.println("Error occured");
            return;
        }
        return;
        
    }

    private static Hospital search_by_huid(ArrayList<Hospital> hospitals, char[] huid){
        for(Hospital j: hospitals){
            if((new String(j.huid)).equals(new String(huid))){
                return j;
            }
        }
        return null;
    }

    public static Slot search_by_pin(ArrayList<Hospital> hospitals, java.util.Scanner sc){
        // Enter PinCode: 110091
        // 123456 Medistar
        // Enter hospital id: 123456

        // 0-> Day: 1 Available Qty:5 Vaccine:Covax
        // 1-> Day: 2 Available Qty:5 Vaccine:Covi
        // Choose Slot: 0

        System.out.print("Enter Pincode: ");
        char[] pincode = sc.next().toCharArray();
        for(Hospital j: hospitals){
            if((new String(j.pincode)).equals(new String(pincode))){
                System.out.println(new String(j.huid) +" "+ j.name);
            }
        }
        System.out.print("Enter Hospital ID: ");
        char[] huid = sc.next().toCharArray();
        Hospital vaccine_camp = search_by_huid(hospitals, huid);
        if(vaccine_camp==null){return null;}
        if(vaccine_camp.slots.size()==0){System.out.println("No slots available");return null;}
        int num=0;
        for(Slot j: vaccine_camp.slots){
            System.out.print(num+"-> "); j.show_slot_by_huid();
            num++;
        }
        System.out.print("Choose Slot: ");
        Slot slot_choice = vaccine_camp.slots.get(sc.nextInt());
        if(slot_choice.getAvailable_quantity()==0){return null;}
        return slot_choice;
    }

    public static Slot search_by_vaccine(ArrayList<Hospital> hospitals, java.util.Scanner sc){
        // Enter Vaccine name: Covax
        // 123456 Medistar
        // 111111 HealthCenter
        // Enter hospital id: 123456
        // No slots available
        // Choose Slot: 0

        //INCONSISTENCY IN INSTRUCTIONS page3/8 5.
        //assumed correct: task 5 page3/8

        System.out.print("Enter Vaccine name: ");
        String vaccine_name = sc.next();
        ArrayList<Slot> available = new ArrayList<Slot>();
        int num=0;
        for(Hospital j: hospitals){
            for(Slot k: j.slots){
                if((k.getVaccine().equals(vaccine_name))){
                    ++num;
                    available.add(k);
                    System.out.println(new String(j.huid)+" "+new String(search_by_huid(hospitals, j.huid).name));
                }
            }
        }
        if(num==0){System.out.println("No hospital has this vaccine");return null;}
        System.out.print("Enter Hospital ID: ");
        char[] huid = sc.next().toCharArray();
        Hospital vaccine_camp = search_by_huid(hospitals, huid);
        if(vaccine_camp==null){return null;}
        if(vaccine_camp.slots.size()==0){System.out.println("No slots available");return null;}
        num=0;
        int fallback=0;
        for(Slot j: vaccine_camp.slots){
            if(j.getVaccine().equals(vaccine_name)) {
                System.out.print(num+"-> "); j.show_slot_by_huid(); ++fallback;
            }
            num++;
        }
        if(fallback==0){System.out.println("No slots available"); return null;}
        System.out.print("Choose Slot: ");
        Slot slot_choice = vaccine_camp.slots.get(sc.nextInt());
        if(slot_choice.getAvailable_quantity()==0){System.out.println("No vaccines left"); return null;}
        return slot_choice;
    }

    public static void list_slots(ArrayList<Hospital> hospitals, java.util.Scanner sc){
        // Enter Hospital Id: 123456
        // Day: 1 Vaccine: Covax Available Qty: 5
        // Day: 2 Vaccine: Covi Available Qty: 5
        try {
            System.out.print("Enter Hospital Id: ");
            char[] huid = sc.next().toCharArray();
            ;//clear stdin
            Hospital hospital = search_by_huid(hospitals, huid);
            if(hospital==null){System.out.println("Hospital not found"); return;}
            for(Slot i : hospital.slots){
                i.show_slot_by_huid();
            }
            if(hospital.slots.size()==0){System.out.println("No Slots were found.");}
        } catch (Exception e) {
            System.out.println("Erroneous input");
            return;
        }
        return;
    }
}
