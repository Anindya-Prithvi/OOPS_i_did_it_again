package Lab_Assignment_01.assets;

public class Slot {
    private final char[] hospital_id;
    private final int day;
    private final Vaccine vaccine;
    private int available_quantity;

    public Slot(int day, Vaccine vaccine, int available_quantity, char[] hospital_id){
        this.day = day;
        this.vaccine = vaccine;
        this.available_quantity = available_quantity;
        this.hospital_id = hospital_id;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public int getDay() {
        return day;
    }

    // public String getVaccine() {
    //     return vaccine.getName();
    // }

    public void used_vaccine(Citizen citizen) {
        // private String cvs = "REGISTERED"; //current vac status
        // private String given = null; //vaccine name
        // private int doses; //doses given
        // private Integer due = null;
        --available_quantity;
        citizen.set_given(vaccine.getName());
        citizen.update_doses();
        if(citizen.get_doses()==vaccine.getNum_doses()){
            citizen.set_cvs("FULLY VACCINATED");
        }
        else{
            citizen.set_due(vaccine.getGap_doses()+day);
            citizen.set_cvs("PARTIALLY VACCINATED");
        }
        //Marrion vaccinated with Covax
        System.out.println(citizen.get_name()+" vaccinated with "+vaccine.getName());
        return;
    }

    // public char[] getHospital_id() {
    //     return hospital_id;
    // }

    public void show_slot(){
        //Slot added by Hospital 123456 for Day: 1, Available Quantity: 5 of Vaccine Covax
        System.out.println("Slot added by Hospital "
        .concat(new String(hospital_id))
        .concat(" for Day: ")
        .concat(String.valueOf(day))
        .concat(", Available Quantity: ")
        .concat(String.valueOf(available_quantity))
        .concat(", of Vaccine ")
        .concat(vaccine.getName())
        );
    }

    public void show_slot_by_huid(){
        //Day: 1 Vaccine: Covax Available Qty: 5
        System.out.println(""
        .concat("Day: ")
        .concat(String.valueOf(day))
        .concat(" Vaccine: ")
        .concat(vaccine.getName())
        .concat(" Qty: ")
        .concat(String.valueOf(available_quantity))
        );
    }
}
