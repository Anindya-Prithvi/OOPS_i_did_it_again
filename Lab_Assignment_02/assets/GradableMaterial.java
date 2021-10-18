package Lab_Assignment_02.assets;

import java.util.ArrayList;

public interface GradableMaterial {
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

}

class Quiz implements ViewableMaterial, GradableMaterial{
    final int marks;
    final String question;
    public Quiz(String ques){
        this.marks = 1;
        this.question = ques;
    }

    @Override
    public void view(){
        System.out.println("Add logic to view");
    }

}

class Submission{
    //filename
    //subn=mitter object
    //object 
    //isgraded?
    //what grade?
    //who
}
