package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Scanner;

public interface GradableMaterial {
    public void submit(Student st, Scanner sc);
    public ArrayList<Submission> getSubmitters();
    public boolean isclosed();
    public void close();
}

class Assignment implements ViewableMaterial, GradableMaterial{
    private boolean closed = false;
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
        // Assignment: Assignment 1 problem Max Marks: 5
        System.out.println("Assignment: "
            .concat(this.question)
            .concat(" Max Marks: ")
            .concat(String.valueOf(this.marks))        
        );
    }

    @Override
    public void submit(Student st, Scanner sc){
        System.out.print("Enter filename of Assignment: ");
        String submitted = sc.nextLine();
        if(submitted.matches("(.*).zip")){
            Submission sb = new Submission(st, submitted, marks);
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

    @Override
    public boolean isclosed(){
        return this.closed;
    }

    @Override
    public void close(){
        this.closed = true;
    }

}

class Quiz implements ViewableMaterial, GradableMaterial{
    private boolean closed = false;
    private final int marks;
    private final String question;
    final ArrayList<Submission> submitters;
    public Quiz(String ques){
        this.marks = 1;
        this.question = ques;
        this.submitters = new ArrayList<Submission>();
    }

    @Override
    public void view(){
        System.out.println("Question: "
            .concat(this.question)
        );
    }

    @Override
    public void submit(Student st, Scanner sc){
        System.out.print("Question: "+this.question+" ");
        String ans = sc.nextLine();
        Submission sb = new Submission(st, ans, marks);
        submitters.add(sb);
    }

    @Override
    public ArrayList<Submission> getSubmitters(){
        return this.submitters;
    }

    @Override
    public boolean isclosed(){
        return this.closed;
    }

    @Override
    public void close(){
        this.closed = true;
    }

}

class Submission{
    private final Student st;
    private final String filename;
    private boolean isgraded = false;
    private Instructor grader = null;
    private int marks = 0;
    private int max_marks;

    public Submission(Student st, String filename, int max_marks){
        this.st = st;
        this.filename = filename;
        this.max_marks = max_marks;
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

    public void grade(Instructor inst, Scanner sc){
        this.grader = inst;
        System.out.println("Submission: "+this.filename);
        System.out.println("-------------------------------");
        System.out.println("Max marks: "+this.max_marks);
        System.out.print("Marks scored: ");
        this.marks = Integer.parseInt(sc.nextLine());
        this.isgraded = true;
        // Submission: assign1_s0.zip
        // -------------------------------
        // Max Marks: 5
        // Marks scored: 5
        // Welcome I1
        // {INSTRUCTOR MENU}
    }
}
