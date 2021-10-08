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

    public static void add_vaccine(ArrayList<Vaccine> vaccines, java.util.Scanner sc){        
    // Vaccine Name: Covax
    // Number of Doses: 2
    // Gap between Doses: 2
    // Vaccine Name: Covax, Number of Doses: 2, Gap Between Doses: 2
        //sc.closed in COVIN
        Vaccine newvaccine;
        try {
            System.out.print("Vaccine Name: ");
            String name = sc.next();
            System.out.print("Number of Doses: ");
            int num_doses = sc.nextInt();
            System.out.print("Gap between Doses: ");
            int gap_doses = sc.nextInt();
            sc.nextLine();
            //sc.close();
            newvaccine = new Vaccine(name, num_doses, gap_doses);
        } catch (Exception e) {
            System.out.println("Exception Occured/ Invalid input");
            return;
        }
        if(validate(newvaccine)) {
            vaccines.add(newvaccine);
            System.out.println(vaccine_info(newvaccine));
        }
    }

    private static String vaccine_info(Vaccine newvaccine){
        return "Vaccine name: "
            .concat(newvaccine.name)
            .concat(", Number of doses: ")
            .concat(String.valueOf(newvaccine.num_doses))
            .concat(", Gap Between doses: ")
            .concat(String.valueOf(newvaccine.gap_doses));
    }

    public int getGap_doses() {
        return gap_doses;
    }

    private static boolean validate(Vaccine vaccine) {
        return true;
    }

    public String getName() {
        return name;
    }

    public int getNum_doses() {
        return num_doses;
    }

    public static Vaccine chooseVaccine(ArrayList<Vaccine> vaccines, java.util.Scanner sc){
        if(vaccines.size()==0){System.out.println("No Vaccines added");return null;}
        int i = 0;
        System.out.println("Select Vaccine");
        for(Vaccine j:vaccines){
            System.out.println(i+". "+j.getName());
            ++i;
        }
        try {
            int index = Integer.parseInt(sc.nextLine());
            return vaccines.get(index);
        } catch (Exception e) {
            System.out.println("Erronous input");
            return null;
        }
    }
}
