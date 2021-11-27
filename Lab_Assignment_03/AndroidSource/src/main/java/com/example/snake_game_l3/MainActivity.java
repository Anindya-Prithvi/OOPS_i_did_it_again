package com.example.snake_game_l3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button use = findViewById(R.id.play_button);
        final Game[] newgame = {new Game()};
        use.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView score = findViewById(R.id.score);
                        score.setText(String.valueOf(newgame[0].getPoints()));

                        TextView tv = findViewById(R.id.status);
                        TextView st = findViewById(R.id.statusG);

                        if(newgame[0].isfinished){
                            tv.setText("Game Over!");
                            return;
                        }
                        int playerprog = newgame[0].play();
                        String progress = "";
                        //display dice
                        TextView diceval = findViewById(R.id.diceval);
                        diceval.setText("Dice Rolled: "+String.valueOf(newgame[0].dice.getFaceValue()));
                        Boolean flag = false;
                        for(int i=0; i<14; i++){
                            if(i>playerprog) flag = true;
                            if(!flag) progress = progress.concat("!");
                            if(flag) progress = progress.concat(".");
                        }
                        if(playerprog==-1){
                            tv.setText("Roll 1!");

                        }
                        else if(playerprog==13){
                            tv.setText("Game Over");
                        }
                        else {
                            tv.setText(String.valueOf(playerprog));
                        }
                        st.setText(progress);
                        String tost = newgame[0].rdm;
                        if(tost!=null){
                            Toast.makeText(getApplicationContext(),tost,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Button resetb = findViewById(R.id.reset_button);
        resetb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newgame[0] = new Game();
                TextView st = findViewById(R.id.statusG);
                st.setText("..............");
                TextView tv = findViewById(R.id.status);
                tv.setText("Roll 1!");
                TextView diceval = findViewById(R.id.diceval);
                diceval.setText("");
                TextView score = findViewById(R.id.score);
                score.setText("0");

            }
        });
    }


}