package Lab_Assignment_01.assets;

public class slot {
    private final int day;
    private final Vaccine vaccine;
    private int available_quantity;

    public slot(int day, Vaccine vaccine, int available_quantity){
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
}
