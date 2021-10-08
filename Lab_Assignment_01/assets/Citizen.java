package Lab_Assignment_01.assets;

public class Citizen {
    //Each citizen can register themselves only once, and they must provide their name, age, and a unique ID.
    private String name;
    private int age;
    private final int uid;

    public Citizen(String name, int age, int uid){
        this.name = name;
        this.age = age;
        this.uid = uid;
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
