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
        int itr = 0;
        for(GradableMaterial i: asmnts){
            if(i.isclosed()) continue;
            ++itr;
            ((ViewableMaterial) i).view();
        }
        if(itr==0) System.out.println("No open assessments available");
        return;
    }

    public void submitasnm(ArrayList<GradableMaterial> asmnts, Scanner sc){
        System.out.println("Pending Assessments");
        int itr = 0;
        int id_tr = 0;
        for(GradableMaterial i: asmnts){
            ++itr;
            if(i.isclosed()) continue;
            boolean subm = false;
            for(Submission j: i.getSubmitters()) if(j.submittedby()==this) subm = true;
            if(!subm) ++id_tr;
            else continue;
            System.out.print("ID: "+(itr-1)+" ");
            ((ViewableMaterial) i).view();
        }
        if(id_tr==0) {
            System.out.println("No Pending Assessments");
            return;
        }
        System.out.print("Enter ID of assessment: ");
        int choice = Integer.parseInt(sc.nextLine());

        GradableMaterial sub = asmnts.get(choice);
        if(sub.isclosed()) {
            System.out.println("Very smart? LOL, submissions closed.");
            return; //No bypassing ahahahaha lol
        }
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

    public void viewcom(ArrayList<Comment> comments){
        for(Comment i: comments){
            i.showcomment();
        }

    }
    
    public void addcom(ArrayList<Comment> comments, Scanner sc){
        System.out.print("Enter comment: ");
        final String comment = sc.nextLine();
        final java.util.Date date=new java.util.Date();
        comments.add(new Student_Com(comment, date, this));        
    }
}
