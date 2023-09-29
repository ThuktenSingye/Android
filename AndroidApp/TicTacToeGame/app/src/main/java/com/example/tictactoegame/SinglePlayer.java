package com.example.tictactoegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;

import java.util.Random;
import android.graphics.*;
import android.os.Handler;
import android.os.Looper;
public class SinglePlayer extends AppCompatActivity implements View.OnClickListener{

    // Inside your SinglePlayer class
    private Handler computerHandler = new Handler(Looper.getMainLooper());
    private final int COMPUTER_DELAY = 1000;
    //    get all the button
    Button sBtn1, sBtn2, sBtn3,sBtn4,sBtn5,sBtn6,sBtn7,sBtn8,sBtn0;
    Button restart_btn , back_btn;
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
        setContentView(R.layout.single_player);
//         all the single player logic  #computer
//        initialized all the component
        player_turn = (TextView) findViewById(R.id.player_name);
        restart_btn = (Button) findViewById(R.id.restartS);
        sBtn0 = (Button) findViewById(R.id.box_s0);
        sBtn1 = (Button) findViewById(R.id.box_s1);
        sBtn2 = (Button) findViewById(R.id.box_s2);
        sBtn3 = (Button) findViewById(R.id.box_s3);
        sBtn4 = (Button) findViewById(R.id.box_s4);
        sBtn5 = (Button) findViewById(R.id.box_s5);
        sBtn6 = (Button) findViewById(R.id.box_s6);
        sBtn7 = (Button) findViewById(R.id.box_s7);
        sBtn8 = (Button) findViewById(R.id.box_s8);
        back_btn = (Button) findViewById(R.id.backS);
        sBtn0.setOnClickListener(this);
        sBtn1.setOnClickListener(this);
        sBtn2.setOnClickListener(this);
        sBtn3.setOnClickListener(this);
        sBtn4.setOnClickListener(this);
        sBtn5.setOnClickListener(this);
        sBtn6.setOnClickListener(this);
        sBtn7.setOnClickListener(this);
        sBtn8.setOnClickListener(this);

        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                call the restart game function
                restartGame();
            }
        });
//        if click back button move navigate back to player
        back_btn.setOnClickListener((v)->{
            startActivity(new Intent(SinglePlayer.this, Player.class));
        });
//        first play begin th computer
        computerPlayer();

    }
    @Override
    public void onClick(View view){


        if(game_ended){
            return;

        }

        // getting cliked button
        Button clickBtn = findViewById(view.getId());
        // getting tag of click button
        int clickTag = Integer.parseInt(view.getTag().toString());
        Toast.makeText(getApplicationContext(), String.valueOf(clickTag), Toast.LENGTH_LONG).show();

        // if user click on already click button , do nothing
        if(filledPos[clickTag] != -1){
            // do nothing
            return;
        }
        // else we will set below
        filledPos[clickTag] = active_player;
        if(active_player == PLAYER_Computer){
            computerPlayer();
        }else{
            clickBtn.setText("X");
            clickBtn.setBackgroundColor(Color.GREEN);
            clickBtn.setTextColor(Color.WHITE);
            clickBtn.setTextSize(18);
            player_turn.setText("Player Computer Turn");
            computerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerPlayer();
                }
            }, COMPUTER_DELAY);
        }
        // check for winner
        checkWinner();

        // check draw
        checkDraw();

    }
    private void computerPlayer(){
//        generate random numebr
        int random_num = new Random().nextInt(filledPos.length);
        int buttonClick = filledPos[random_num]; // this is the button click by the computer
        // now set the button text to O if its -1
        if (buttonClick == -1){
            if(random_num == 0){ // first button click
                sBtn0.setText("O");
                sBtn0.setTextSize(18);
                sBtn0.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn0.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 1){ // first button click
                sBtn1.setText("O");
                sBtn1.setTextSize(18);
                sBtn1.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn1.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 2){ // first button click
                sBtn2.setText("O");
                sBtn2.setTextSize(18);
                sBtn2.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn2.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 3){ // first button click
                sBtn3.setText("O");
                sBtn3.setTextSize(18);
                sBtn3.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn3.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 4){ // first button click
                sBtn4.setText("O");
                sBtn4.setTextSize(18);
                sBtn4.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn4.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 5){ // first button click
                sBtn5.setText("O");
                sBtn5.setTextSize(18);
                sBtn5.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn5.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 6){ // first button click
                sBtn6.setText("O");
                sBtn6.setTextSize(18);
                sBtn6.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn6.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 7){ // first button click
                sBtn7.setText("O");
                sBtn7.setTextSize(18);
                sBtn7.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn7.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            if(random_num == 8){ // first button click
                sBtn8.setText("O");
                sBtn8.setTextSize(18);
                sBtn8.setTextColor(Color.WHITE);
                filledPos[random_num]= PLAYER_Computer;
                sBtn8.setBackgroundColor(Color.BLUE);
                player_turn.setText("PLAYER Human Turn");
            }
            active_player = PLAYER_Human;

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
//        initialize the winner condition
        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for(int i=0;i<8;i++){
            int value0 = winningPosition[i][0];
            int value1 = winningPosition[i][1];
            int value2 = winningPosition[i][2];

            if (filledPos[value0] != -1){ // check if button is click
                if(filledPos[value0]== filledPos[value1] && filledPos[value1]==filledPos[value2]){ // check winning condition
                    game_ended = true;
                    if(filledPos[value0]==PLAYER_Computer){
                        showWinner("Winner is Player O");
                    }else{
                        showWinner("Winner is Player X");
                    }
                    return true;
                }
            }
        }
        return false;
    }
    private void restartGame(){
        // calling the same intent again
        Intent intent = new Intent(getApplicationContext(), SinglePlayer.class);
        startActivity(intent);
        finish();

    }
    private void showWinner(String winner){
//
        new AlertDialog.Builder(this)
                .setTitle(winner)
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                }).show();

    }
}
