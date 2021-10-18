package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Scanner;

public class Instructor {//assume instructors have name

    private final String name;
    public Instructor(String name){
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    // INSTRUCTOR MENU
    // 1. Add class material @
    // 2. Add assessments @
    // 3. View lecture materials @
    // 4. View assessments @
    // 5. Grade assessments @
    // 6. Close assessment @
    // 7. View comments
    // 8. Add comments
    // 9. Logout

    public void upload_lecmat(ArrayList<ViewableMaterial> materials, Scanner sc){
        java.util.Date date=new java.util.Date();
        final String menu = ""
                    .concat("1. Add Lecture Slide\n")
                    .concat("2. Add Lecture Video\n");
        System.out.print(menu);
        final int choice = Integer.parseInt(sc.nextLine());
        if(choice==2){
            // Enter topic of video: Lecture 1
            // Enter filename of video: lecture1.mp4
            System.out.print("Enter topic of video: ");
            String topic = sc.nextLine();
            System.out.print("Enter filename of video: ");
            String filename = sc.nextLine();
            if(filename.matches("(.*).mp4")){
                //get time stamp  
                materials.add(new LectureVideo(topic, filename, date, this));
            }
            else System.out.println("REJECTED (file format not mp4)");
        }
        else if(choice==1){
            // Enter topic of slides: Slide 1
            // Enter number of slides: 2
            // Enter content of slides
            // Content of slide 1: Content 1
            // Content of slide 2: Content 2
            System.out.print("Enter topic of slides: ");
            final String topic = sc.nextLine();
            System.out.print("Enter number of slides: ");
            final int no_Slides = Integer.parseInt(sc.nextLine());
            ArrayList<String> slides = new ArrayList<String>();
            System.out.println("Enter content of slides");
            for(int i=0; i<no_Slides; i++){
                System.out.print("Content of slide "+(i+1)+": ");
                slides.add(sc.nextLine());
            }
            materials.add(new LectureSlides(topic, slides, date, this));
        }
        return;
    }

    public void upload_asmnt(ArrayList<GradableMaterial> assessor, Scanner sc){
        final String menu = ""
                    .concat("1. Add Assignment\n")
                    .concat("2. Add Quiz\n");
        System.out.print(menu);
        final int choice = Integer.parseInt(sc.nextLine());
        if(choice==2){
            System.out.print("Enter Quiz question: ");
            String quiz_q = sc.nextLine();
            assessor.add(new Quiz(quiz_q));
        }
        else if(choice==1){
            System.out.print("Enter problem statement: ");
            final String problem_a = sc.nextLine();
            System.out.print("Enter max marks: ");
            final int marks = Integer.parseInt(sc.nextLine());
            assessor.add(new Assignment(marks, problem_a));
        }
        return;

    }

    public void closeasmnt(ArrayList<GradableMaterial> asmnts, Scanner sc){
        int itr = 0;
        for(GradableMaterial i: asmnts){
            ++itr;
            if(i.isclosed()) continue;
            System.out.print("ID: "+itr+" ");
            ((ViewableMaterial) i).view();
        }
        if(itr==0){
            System.out.println("No open assessments available");
            return;
        }
        System.out.print("Enter id of assignment to close: ");
        int choice = Integer.parseInt(sc.nextLine());
        GradableMaterial mat = asmnts.get(choice);
        mat.close();

    }

    public void viewlm(ArrayList<ViewableMaterial> lecmats){
        for(ViewableMaterial i: lecmats){
            i.view();
        }
        return;
    }

    public void viewasnm(ArrayList<GradableMaterial> asmnts){
        for(GradableMaterial i: asmnts){
            ((ViewableMaterial) i).view();
        }
    }

    public void gradeAsmnt(ArrayList<GradableMaterial> asmnts, Scanner sc){
        // List of assessments
        // ID: 0 Assignment: Assignment 1 problem Max Marks: 5
        // ID: 1 Question: Name a language which supports OOPS?
        // Enter ID of assessment to view submissions: 0
        // Choose ID from these ungraded submissions
        // 0. S0
        // 1. S1
        System.out.println("List of assessments");
        int itr = 0;
        for(GradableMaterial i: asmnts){
            System.out.print("ID: "+itr+ " ");
            ((ViewableMaterial) i).view();
            ++itr;
        }
        System.out.print("Enter ID of assessment to view submissions of: ");
        int ases = Integer.parseInt(sc.nextLine());
        GradableMaterial assesment = asmnts.get(ases);
        if(assesment.getSubmitters().size()==0) {
            System.out.println("No submissions left to grade");
            return;
        }
        System.out.println("Choose ID from these ungraded submissions");
        itr = 0;
        for(Submission i: assesment.getSubmitters()){
            ++itr;
            if(i.isGraded()) continue;
            System.out.println(itr+". "+i.submittedby().getName());
        }
        int choice = Integer.parseInt(sc.nextLine());
        Submission sb = assesment.getSubmitters().get(choice);
        sb.grade(this, sc);
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
        comments.add(new Instructor_Com(comment, date, this));        
    }
}
