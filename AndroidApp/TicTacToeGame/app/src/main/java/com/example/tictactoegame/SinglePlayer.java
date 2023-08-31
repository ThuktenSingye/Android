package com.example.tictactoegame;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;

import java.util.Random;
import android.graphics.*;
public class SinglePlayer extends AppCompatActivity {
//    get all the button
    Button sBtn1, sBtn2, sBtn3,sBtn4,sBtn5,sBtn6,sBtn7,sBtn8,sBtn0;
    Button restart_btn ;
    TextView player_turn;
//    define player
    int PLAYER_Computer = 0;
    int PLAYER_Human = 1;
    int active_player = PLAYER_Computer;
    boolean game_ended = false;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1}; // empty cell at the beginning

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         all the single player logic  #computer
//        initialized all the component
        player_turn = (TextView) findViewById(R.id.player_name);
        sBtn0 = (Button) findViewById(R.id.box_s0);
        sBtn1 = (Button) findViewById(R.id.box_s1);
        sBtn2 = (Button) findViewById(R.id.box_s2);
        sBtn3 = (Button) findViewById(R.id.box_s3);
        sBtn4 = (Button) findViewById(R.id.box_s4);
        sBtn5 = (Button) findViewById(R.id.box_s5);
        sBtn6 = (Button) findViewById(R.id.box_s6);
        sBtn7 = (Button) findViewById(R.id.box_s7);
        sBtn8 = (Button) findViewById(R.id.box_s8);

        restart_btn = (Button) findViewById(R.id.restartS);
        sBtn0.setOnClickListener(this);
//        sBtn0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // check if game has ended
//                if (game_ended)
//                    return;
//                Button click = findViewById(view.getId());
//                int clickTag = Integer.parseInt(view.getTag().toString());
//
//                if (filledPos[clickTag] != -1){ // if button is already click do nothing
//                    return;
//                }
//                filledPos[clickTag] = active_player;
//
//                if (active_player == PLAYER_Computer){
//                    computerPlayer();
//                }else{
//
//                }
//            }
//        });


        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                call the restart game function
                restartGame();
            }
        });
//        first play begin th computer
        computerPlayer();


    }
    @Override
    public void onClick(){

    }
    private void computerPlayer(){
//        generate random numebr
        int random_num = new Random().nextInt(filledPos.length);
        int buttonClick = filledPos[random_num]; // this is the button click by the computer
        // now set the button text to O if its -1
        if (buttonClick == -1){
            if(random_num == 0){ // first button click
                sBtn0.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 1){ // first button click
                sBtn1.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 2){ // first button click
                sBtn2.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 3){ // first button click
                sBtn3.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 4){ // first button click
                sBtn4.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 5){ // first button click
                sBtn5.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 6){ // first button click
                sBtn6.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 7){ // first button click
                sBtn7.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 8){ // first button click
                sBtn8.setText("O");
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }

        }else{
            computerPlayer(); // if button is already click, allow computer to click the button again
        }
        // check winner
        checkWinner();
        // check draw
        checkDraw();
    }
    private void checkDraw(){
        int count = 0;
        for (int i=0; i<=8; i++){
            if (filledPos[i] != -1){
                count++;
            }
        }
        if (count == 9){ // meaning all cell are filled
            // check winner first
            if (checkWinner()){
                return;
            }
            showWinner("Game is draw"); //
            game_ended= true;
        }
    }
    private boolean checkWinner(){

    }
    private void restartGame(){

    }
    private void showWinner(String text){

    }
}
