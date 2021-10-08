package Lab_Assignment_01.assets;

import java.util.ArrayList;

public class Vaccine {
    private final String name;
    private final int num_doses;
    private final int gap_doses;

    public Vaccine(String name, int num_doses, int gap_doses){
        this.name = name;
        this.num_doses = num_doses;
        this.gap_doses = gap_doses;
    }

    public static void add_vaccine(ArrayList<Vaccine> vaccines){
        
    // Vaccine Name: Covax
    // Number of Doses: 2
    // Gap between Doses: 2
    // Vaccine Name: Covax, Number of Doses: 2, Gap Between Doses: 2
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Vaccine newvaccine;
        try {
            System.out.print("Vaccine Name: ");
            String name = sc.next();
            System.out.print("Number of Doses: ");
            int num_doses = sc.nextInt();
            System.out.print("Gap between Doses: ");
            int gap_doses = sc.nextInt();
            newvaccine = new Vaccine(name, num_doses, gap_doses);
        } catch (Exception e) {
            System.out.println("Exception Occured/ Invalid input");
            return;
        }
        if(validate(newvaccine)) vaccines.add(newvaccine);
    }

    public int getGap_doses() {
        return gap_doses;
    }

    private static boolean validate(Vaccine newvaccine) {
        return true;
    }

    public String getName() {
        return name;
    }

    public int getNum_doses() {
        return num_doses;
    }
}
