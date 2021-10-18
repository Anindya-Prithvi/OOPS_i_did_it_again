package Lab_Assignment_02;

import java.util.ArrayList;
import java.util.Scanner;

import Lab_Assignment_02.assets.GradableMaterial;
import Lab_Assignment_02.assets.Instructor;
import Lab_Assignment_02.assets.ViewableMaterial;

public class BACKPACK {
    private final ArrayList<Instructor> instructors;
    private final ArrayList<ViewableMaterial> lecmats;
    private final ArrayList<GradableMaterial> asmnts;

    private final static String instructorMenu = "INSTRUCTOR MENU\n"
        .concat("1. Add class material\n")
        .concat("2. Add assessments\n")
        .concat("3. View lecture materials\n")
        .concat("4. View assessments\n")
        .concat("5. Grade assessments\n")
        .concat("6. Close assessment\n")
        .concat("7. View comments\n")
        .concat("8. Add comments\n")
        .concat("9. Logout\n");

    private final static String studentMenu = "STUDENT MENU\n"
        .concat("1. View lecture materials\n")
        .concat("2. View assessments\n")
        .concat("3. Submit assessment\n")
        .concat("4. View grades\n")
        .concat("5. View comments\n")
        .concat("6. Add comments\n")
        .concat("7. Logout\n");

    private final static String menu = "Welcome to Backpack\n"
            .concat("1. Enter as instructor\n")
            .concat("2. Enter as student\n")
            .concat("3. Exit\n");

    public BACKPACK(ArrayList<Instructor> instructors, ArrayList<ViewableMaterial> lecmats,
                    ArrayList<GradableMaterial> asmnts){
        this.instructors = instructors;
        this.lecmats = lecmats;
        this.asmnts = asmnts;
    }

    public void choiceParser(){
        final Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print(menu);
            int login_as = Integer.parseInt(sc.nextLine());
            if(login_as==1){
                //login as inst
                //show instructor list
                int iterator_id = 0;
                for(Instructor i: instructors){
                    System.out.println(iterator_id+" - "+ i.getName());
                    ++iterator_id;
                }
                Instructor inst = instructors.get(Integer.parseInt(sc.nextLine()));
                while(true){
                    boolean con = instructorControls(inst, sc);
                    if(con==false) break;
                }
            }
            else if(login_as==2){
                //login as student
                //show student list
                System.out.print(studentMenu);
            }
            else if(login_as==3){
                //exit
                break;
            }
        }
        return;
    }

    private boolean instructorControls(Instructor inst, Scanner sc){
        System.out.print(instructorMenu);
        int choice = Integer.parseInt(sc.nextLine());
        // 1. Add class material
        // 2. Add assessments
        // 3. View lecture materials
        // 4. View assessments
        // 5. Grade assessments
        // 6. Close assessment
        // 7. View comments
        // 8. Add comments
        // 9. Logout
        //9 choices
        if(choice == 1){
            inst.upload_lecmat(lecmats, sc);
            return true;
        }
        else if(choice == 2){
            inst.upload_asmnt(asmnts, sc);
            return true;
        }
        else if(choice == 3){
            
        }
        else if(choice == 4){
            
        }
        else if(choice == 5){
            
        }
        else if(choice == 6){
            
        }
        else if(choice == 7){
            
        }
        else if(choice == 8){
            
        }
        else if(choice == 9){
            return false;            
        }
        return false; //default
    }

    public static void main(String[] args){
        //convert to launchable like COVIN
        //create insts and stud dummy
        // create object and call parser from that
        // choiceParser();
        ArrayList<Instructor> instructors = new ArrayList<Instructor>();
        ArrayList<ViewableMaterial> lecmats = new ArrayList<ViewableMaterial>();
        ArrayList<GradableMaterial> asmnts = new ArrayList<GradableMaterial>();
        BACKPACK classroom = new BACKPACK(instructors, lecmats, asmnts);

        //add some people (instructors)
        instructors.add(new Instructor("Tony Stark"));
        instructors.add(new Instructor("Bruce Banner"));
        instructors.add(new Instructor("Helen Cho"));
        instructors.add(new Instructor("Andrew N.G."));
        classroom.choiceParser();        
        
    }
    
}
