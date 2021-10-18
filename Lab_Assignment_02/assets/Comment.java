package Lab_Assignment_02.assets;

import java.util.Date;

public interface Comment {
    public void showcomment();
}

class Student_Com implements Comment{
    private final String comment;
    private final Date date;
    private final Student commentor;

    public Student_Com(String comment, Date date, Student commentor){
        this.comment = comment;
        this.date = date;
        this.commentor = commentor;
    }

    @Override
    public void showcomment(){
        System.out.println(this.comment
            .concat(" - ")
            .concat(commentor.getName())
        );
        System.out.println(this.date);
        System.out.println(); //extra flush

    }
}

class Instructor_Com implements Comment{
    private final String comment;
    private final Date date;
    private final Instructor commentor;

    public Instructor_Com(String comment, Date date, Instructor commentor){
        this.comment = comment;
        this.date = date;
        this.commentor = commentor;
    }

    @Override
    public void showcomment(){
        System.out.println(this.comment
            .concat(" - ")
            .concat(commentor.getName())
        );
        System.out.println(this.date);
        System.out.println(); //extra flush

    }
}
