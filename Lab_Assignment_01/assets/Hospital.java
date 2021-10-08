package Lab_Assignment_01.assets;

public class Hospital {
    private static int id_int;

    private final char[] pincode; //pincode 002013
    private final String name;
    private final char[] huid; //000000

    public Hospital(char[] pincode, String name, char[] huid){
        this.pincode = pincode;
        this.name = name;
        this.huid = update_huid();
        if(validate()){++id_int;}
    }

    public boolean validate(){
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
