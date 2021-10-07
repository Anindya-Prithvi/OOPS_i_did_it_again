package Lab_Assignment_01.assets;

public class Slot {
    private final int day;
    private final Vaccine vaccine;
    private int available_quantity;

    public Slot(int day, Vaccine vaccine, int available_quantity){
        this.day = day;
        this.vaccine = vaccine;
        this.available_quantity = available_quantity;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public int getDay() {
        return day;
    }

    public String getVaccine() {
        return vaccine.getName();
    }

    public void used_vaccine() {
        --available_quantity;
        return;
    }
}
