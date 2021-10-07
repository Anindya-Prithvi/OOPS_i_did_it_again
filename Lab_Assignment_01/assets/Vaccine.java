package Lab_Assignment_01.assets;

public class Vaccine {
    private final String name;
    private final int num_doses;
    private final int gap_doses;

    public Vaccine(String name, int num_doses, int gap_doses){
        this.name = name;
        this.num_doses = num_doses;
        this.gap_doses = gap_doses;
    }

    public int getGap_doses() {
        return gap_doses;
    }

    public String getName() {
        return name;
    }

    public int getNum_doses() {
        return num_doses;
    }
}
