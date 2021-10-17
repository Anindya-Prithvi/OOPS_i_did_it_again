package Lab_Assignment_02.assets;

import java.util.ArrayList;
import java.util.Scanner;

public class Instructor {
    static int id_ctr = 0;

    private final int inst_id;
    public Instructor(){
        this.inst_id = id_ctr;

        ++id_ctr;
    }
    
    public String getInst_id() {
        return "I".concat(String.valueOf(inst_id));
    }

    public void upload_lecmat(ArrayList<LectureMaterial> materials, Scanner sc){
        final String menu = "\n"
                    .concat("1. Add Lecture Slide\n")
                    .concat("2. Add Lecture Video\n");
        System.out.print(menu);
        final int choice = Integer.parseInt(sc.nextLine());
        if(choice==2){
            String filename = sc.nextLine();
            if(filename.matches("(.*).mp4")){
                //get time stamp
                java.util.Date date=new java.util.Date();  
                materials.add(new LectureVideo(filename, date));
            }
        }
        else if(choice==1){
            // Enter topic of slides: Slide 1
            // Enter number of slides: 2
            // Enter content of slides
            // Content of slide 1: Content 1
            // Content of slide 2: Content 2
            System.out.print("Enter topic of slides: ");
            final String topic = sc.nextLine();
            final int no_Slides = Integer.parseInt(sc.nextLine());
            ArrayList<String> slides = new ArrayList<String>();
            System.out.println("Enter content of slides");
            for(int i=0; i<no_Slides; i++){
                System.out.print("Content of slide "+i+": ");
                slides.add(sc.nextLine());
            }
            materials.add(new LectureSlides(topic, slides));
        }
        return;
    }

    public void upload_asmnt(){
        
    }
}
