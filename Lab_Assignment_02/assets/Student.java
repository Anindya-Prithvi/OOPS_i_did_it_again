package Lab_Assignment_02.assets;

import java.util.ArrayList;

public class Student {
    // 1. View lecture materials
    // 2. View assessments
    // 3. Submit assessment
    // 4. View grades
    // 5. View comments
    // 6. Add comments
    // 7. Logout
    private final String name;
    public Student(String name){
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public void viewlm(ArrayList<ViewableMaterial> lecmat){
        for(ViewableMaterial i: lecmat){
            i.view();
        }
        return;
    }

    public void viewasnm(ArrayList<GradableMaterial> asmnts){
        for(GradableMaterial i: asmnts){
            ((ViewableMaterial) i).view();
        }
    }
    
}
