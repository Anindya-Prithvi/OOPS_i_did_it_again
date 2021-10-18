package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Date;

public interface ViewableMaterial {
    public void view();    
}

class LectureVideo implements ViewableMaterial{
    public LectureVideo(String topic, String filename, Date date){
        //construct Videos (error handle not req ahahah)
    }

    @Override
    public void view(){
        System.out.println("Shows the lecvid.mp4");
    }
}

class LectureSlides implements ViewableMaterial{
    public LectureSlides(String topic, ArrayList<String> slides){
        //construct the slides
    }

    @Override
    public void view(){
        System.out.println("Show slide");
    }
}