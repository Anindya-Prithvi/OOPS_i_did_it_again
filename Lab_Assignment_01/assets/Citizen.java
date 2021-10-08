package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Citizen {
    //Each citizen can register themselves only once, and they must provide their name, age, and a unique ID.
    private String name;
    private int age;
    private final char[] uid;

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
        return true;
    }


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }

    public void lookup(){
        System.out.println("Looking");
        return;
    }

    public boolean validate(){
        //if valid //multi UID //age
        return true;
    }

    public void book_slot(Slot slot){
        //may add error handling (not req) if out of vacc
        slot.used_vaccine();
    }
}
