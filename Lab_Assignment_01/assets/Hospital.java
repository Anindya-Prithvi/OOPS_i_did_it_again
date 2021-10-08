package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Hospital {
    private static int id_int;

    private final char[] pincode; //pincode 002013
    private final String name;
    private final char[] huid; //000000

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

    public char[] getHuid() {
        return huid;
    }

    public void addslots() {
        //maybe a slot array is passed here
        //count slots then add each
        //TODO
        //hospitals might need access to edit their slots (IRL)
    }
}
