package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    // 1. View lecture materials
    // 2. View assessments
    // 3. Submit assessment
    // 4. View grades
    // 5. View comments
    // 6. Add comments
    // 7. Logout
    private final String name;
    public Student(String name){
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public void viewlm(ArrayList<ViewableMaterial> lecmat){
        for(ViewableMaterial i: lecmat){
            i.view();
        }
        return;
    }

    public void viewasnm(ArrayList<GradableMaterial> asmnts){
        for(GradableMaterial i: asmnts){
            ((ViewableMaterial) i).view();
        }
    }

    public void submitasnm(ArrayList<GradableMaterial> asmnts, Scanner sc){
        System.out.println("Pending Assignments");
        int itr = 0;
        for(GradableMaterial i: asmnts){
            System.out.print("ID: "+itr);
            ((ViewableMaterial) i).view();
            ++itr;
        }
        System.out.print("Enter ID of assessment: ");
        int choice = Integer.parseInt(sc.nextLine());

        GradableMaterial sub = asmnts.get(choice);
        sub.submit(this, sc);
        
    }

    public void seegrades(ArrayList<GradableMaterial> asmnts){
        System.out.println("Graded submissions");
        for(GradableMaterial i: asmnts){
            for(Submission sb: i.getSubmitters()){
                if(sb.submittedby()==this){
                    if(sb.isGraded()){
                        sb.showscore();
                    }
                }
            }
        }
        System.out.println("----------------");
        System.out.println("Ungraded submissions");
        for(GradableMaterial i: asmnts){
            for(Submission sb: i.getSubmitters()){
                if(sb.submittedby()==this){
                    if(!sb.isGraded()){
                        sb.showscore();
                    }
                }
            }
        }
        // Graded submissions
        // Submission: assign1_s0.zip
        // Marks scored: 5
        // Graded by: I1
        // ----------------------------
        // Ungraded submissions

    }
    
}
