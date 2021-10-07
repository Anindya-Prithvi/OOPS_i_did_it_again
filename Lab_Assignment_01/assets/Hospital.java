package Lab_Assignment_01.assets;

public class Hospital {
    private final String pincode; //pincode 002013
    private final String name;

    public Hospital(String pincode, String name){
        this.pincode = pincode;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPincode() {
        return pincode;
    }

    public boolean upload_slot(){
        return true;
    }
}
