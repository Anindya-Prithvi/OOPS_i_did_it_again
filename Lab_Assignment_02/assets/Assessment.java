package Lab_Assignment_02.assets;

import java.util.ArrayList;

public interface Assessment {
    public void view();
}

class Assignment implements Assessment{
    final int marks;
    final ArrayList<Submission> submitters;
    boolean isclosed = false;
    public Assignment(int marks, String problem){
        this.marks = marks;
        this.submitters = new ArrayList<Submission>();
    }

    @Override
    public void view(){
        System.out.println("Add logic to view");
    }

}

class Quiz implements Assessment{
    final int marks;
    public Quiz(String ques){
        this.marks = 1;
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
