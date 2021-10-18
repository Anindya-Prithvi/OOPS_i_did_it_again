package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Scanner;

public interface GradableMaterial {
    public void submit(Student st, Scanner sc);
    public ArrayList<Submission> getSubmitters();
}

class Assignment implements ViewableMaterial, GradableMaterial{
    final int marks;
    final String question;
    final ArrayList<Submission> submitters;
    boolean isclosed = false;
    public Assignment(int marks, String problem){
        this.marks = marks;
        this.question = problem;
        this.submitters = new ArrayList<Submission>();
    }

    @Override
    public void view(){
        System.out.println("Add logic to view");
    }

    @Override
    public void submit(Student st, Scanner sc){
        System.out.print("Enter filename of Assignment: ");
        String submitted = sc.nextLine();
        if(submitted.matches("(.*).zip")){
            Submission sb = new Submission(st, submitted);
            submitters.add(sb);
        }
        else{
            System.out.println("REJECTED (File format not zip)");
        }
        return;
    }

    @Override
    public ArrayList<Submission> getSubmitters(){
        return this.submitters;
    }

}

class Quiz implements ViewableMaterial, GradableMaterial{
    final int marks;
    final String question;
    final ArrayList<Submission> submitters;
    public Quiz(String ques){
        this.marks = 1;
        this.question = ques;
        this.submitters = new ArrayList<Submission>();
    }

    @Override
    public void view(){
        System.out.println("Add logic to view");
    }

    @Override
    public void submit(Student st, Scanner sc){
        System.out.print(this.question);
        String ans = sc.nextLine();
        Submission sb = new Submission(st, ans);
        submitters.add(sb);
    }

    @Override
    public ArrayList<Submission> getSubmitters(){
        return this.submitters;
    }

}

class Submission{
    private final Student st;
    private final String filename;
    private boolean isgraded = false;
    private Instructor grader = null;
    private int marks = 0;

    public Submission(Student st, String filename){
        this.st = st;
        this.filename = filename;
    }

    public boolean isGraded(){
        return isgraded;
    }

    public Student submittedby(){
        return this.st;
    }

    public void showscore(){
        // Submission: assign1_s0.zip
        // Marks scored: 5
        // Graded by: I1
        if(grader!=null){
            System.out.println("Submission: ".concat(this.filename));
            System.out.println("Marks scored: ".concat(String.valueOf(marks)));
            System.out.println("Graded by: ".concat(grader.getName()));
        }

    }
    //filename
    //subn=mitter object
    //object 
    //isgraded?
    //what grade?
    //who
}
