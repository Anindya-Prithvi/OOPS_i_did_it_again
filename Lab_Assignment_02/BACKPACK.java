package Lab_Assignment_02;

import java.util.ArrayList;
import java.util.Scanner;

import Lab_Assignment_02.assets.Comment;
import Lab_Assignment_02.assets.GradableMaterial;
import Lab_Assignment_02.assets.Instructor;
import Lab_Assignment_02.assets.Student;
import Lab_Assignment_02.assets.ViewableMaterial;

public class BACKPACK {
    private final ArrayList<Instructor> instructors;
    private final ArrayList<Student> students;
    private final ArrayList<ViewableMaterial> lecmats;
    private final ArrayList<GradableMaterial> asmnts;
    private final ArrayList<Comment> comments;

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

    public BACKPACK(ArrayList<Instructor> instructors, 
                    ArrayList<Student> students,
                    ArrayList<Comment> comments,
                    ArrayList<ViewableMaterial> lecmats,
                    ArrayList<GradableMaterial> asmnts){
        this.instructors = instructors;
        this.lecmats = lecmats;
        this.asmnts = asmnts;
        this.students = students;
        this.comments = comments;
    }

    public void choiceParser(){
        final Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print(menu);
            int login_as = Integer.parseInt(sc.nextLine());
            if(login_as==1){
                //login as inst
                //show instructor list
                System.out.println("Instructors: ");
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
                System.out.println("Students: ");
                int iterator_id = 0;
                for(Student i: students){
                    System.out.println(iterator_id+" - "+ i.getName());
                    ++iterator_id;
                }
                Student inst = students.get(Integer.parseInt(sc.nextLine()));
                while(true){
                    boolean con = studentControls(inst, sc);
                    if(con==false) break;
                }

            }
            else if(login_as==3){
                //exit
                break;
            }
        }
        return;
    }

    private boolean studentControls(Student st, Scanner sc){
        System.out.println("Welcome "+st.getName());
        System.out.print(studentMenu);
        int choice = Integer.parseInt(sc.nextLine());
        // 1. View lecture materials
        // 2. View assessments
        // 3. Submit assessment
        // 4. View grades
        // 5. View comments
        // 6. Add comments
        // 7. Logout
        if(choice == 1){
            st.viewlm(lecmats);
            return true;
        }
        else if(choice == 2){
            st.viewasnm(asmnts);
            return true;
        }
        else if(choice == 3){
            st.submitasnm(asmnts, sc);
            return true;            
        }
        else if(choice == 4){
            st.seegrades(asmnts);
            return true;            
        }
        else if(choice == 5){
            st.viewcom(comments);
            return true;            
        }
        else if(choice == 6){
            st.addcom(comments, sc);
            return true;            
        }
        else if(choice == 7){
            return false;            
        }
        return false;
    }

    private boolean instructorControls(Instructor inst, Scanner sc){
        System.out.println("Welcome "+inst.getName());
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
            inst.viewlm(lecmats);
            return true;
        }
        else if(choice == 4){
            inst.viewasnm(asmnts);
            return true;
        }
        else if(choice == 5){
            inst.gradeAsmnt(asmnts, sc);
            return true;
        }
        else if(choice == 6){
            inst.closeasmnt(asmnts, sc);
            return true;            
        }
        else if(choice == 7){
            inst.viewcom(comments);
            return true;           
        }
        else if(choice == 8){
            inst.addcom(comments, sc);
            return true;
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
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<ViewableMaterial> lecmats = new ArrayList<ViewableMaterial>();
        ArrayList<GradableMaterial> asmnts = new ArrayList<GradableMaterial>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        BACKPACK classroom = new BACKPACK(instructors, students, comments, lecmats, asmnts);

        //add some people (instructors)
        instructors.add(new Instructor("Tony Stark"));
        instructors.add(new Instructor("Bruce Banner"));
        instructors.add(new Instructor("Helen Cho"));
        instructors.add(new Instructor("Andrew N.G."));

        students.add(new Student("Ben Tennyson"));
        students.add(new Student("Ash Ketchum"));
        students.add(new Student("Misty"));
        students.add(new Student("Peter Parker"));

        classroom.choiceParser();        
        
    }
    
}
