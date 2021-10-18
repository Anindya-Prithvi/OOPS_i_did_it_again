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

    public void upload_lecmat(ArrayList<ViewableMaterial> materials, Scanner sc){
        final String menu = "\n"
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
                java.util.Date date=new java.util.Date();  
                materials.add(new LectureVideo(topic, filename, date));
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

    public void upload_asmnt(ArrayList<GradableMaterial> assessor, Scanner sc){
        final String menu = "\n"
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
}
