package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Date;

public interface ViewableMaterial {
    public void view();    
}

final class LectureVideo implements ViewableMaterial{
    private final String topic;
    private final String filename;
    private final Date date;
    private final Instructor instructor;
    public LectureVideo(String topic, String filename, Date date, Instructor instructor){
        //construct Videos (error handle not req ahahah)
        this.topic = topic;
        this.filename = filename;
        this.date = date;
        this.instructor = instructor;
    }

    @Override
    public void view(){
        // Title of video: Lecture 1
        // Video file: lecture1.mp4
        // Date of upload: Thu Oct 14 23:25:39 IST 2021
        // Uploaded by: I0
        System.out.println(""
            .concat("Title of video: ").concat(this.topic).concat("\n")
            .concat("Video file: ").concat(this.filename).concat("\n")
            .concat("Date of upload: ").concat(date.toString()).concat("\n")
            .concat("Uploaded by: ").concat(instructor.getName().concat("\n"))
        );
        return;
    }
}

final class LectureSlides implements ViewableMaterial{
    private final String topic;
    private final ArrayList<String> slides;
    private final Instructor instructor;
    private final Date date;
    public LectureSlides(String topic, ArrayList<String> slides, Date date, Instructor instructor){
        //construct the slides
        this.topic = topic;
        this.slides = slides;
        this.instructor = instructor;
        this.date = date;
    }

    @Override
    public void view(){
        // Title: Slide 1
        // Slide 1: Content 1
        // Slide 2: Content 2
        // Number of slides: 2
        // Date of upload: Thu Oct 14 23:25:25 IST 2021
        // Uploaded by: I0
        System.out.println("Title: ".concat(this.topic));
        int i=1;
        for(String slide: slides){
            System.out.println("Slide "+i+":"+" "+slide);
            ++i;
        }
        System.out.println("Number of slides: "+slides.size());
        System.out.println("Date of upload: ".concat(this.date.toString()));
        System.out.println("Uploaded by: "+instructor.getName().concat("\n"));

    }
}