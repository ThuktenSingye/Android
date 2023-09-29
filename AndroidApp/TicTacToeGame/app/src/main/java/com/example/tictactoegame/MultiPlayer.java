package com.example.tictactoegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MultiPlayer extends AppCompatActivity implements View.OnClickListener {
    Button mBtn1, mBtn2, mBtn3,mBtn4,mBtn5,mBtn6,mBtn7,mBtn8,mBtn0;
    Button restart_btn , back_btn;
    TextView player_turn;
    //    define player
    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int active_player = PLAYER_O;
    boolean game_ended = false;

    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1}; // empty cell at the beginning
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_player);
        player_turn = (TextView) findViewById(R.id.turn);
        restart_btn = (Button) findViewById(R.id.restartM);
        mBtn0 = (Button) findViewById(R.id.box_m1);
        mBtn1 = (Button) findViewById(R.id.box_m2);
        mBtn2 = (Button) findViewById(R.id.box_m3);
        mBtn3 = (Button) findViewById(R.id.box_m4);
        mBtn4 = (Button) findViewById(R.id.box_m5);
        mBtn5 = (Button) findViewById(R.id.box_m6);
        mBtn6 = (Button) findViewById(R.id.box_m7);
        mBtn7 = (Button) findViewById(R.id.box_m8);
        mBtn8 = (Button) findViewById(R.id.box_m9);
        back_btn= (Button) findViewById(R.id.backM);
        mBtn0.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
        mBtn8.setOnClickListener(this);
        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                call the restart game function
                restartGame();
            }
        });
//        back to player activity
        back_btn.setOnClickListener((v)->{
            startActivity(new Intent(MultiPlayer.this, Player.class));
        });
    }
    @Override
    public void onClick(View view) {
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
        if(active_player == PLAYER_O){
            clickBtn.setText("O");
            clickBtn.setBackgroundColor(Color.RED);
            clickBtn.setTextSize(18);
            clickBtn.setTextColor(Color.WHITE);
            active_player = PLAYER_X;
            player_turn.setText("Player X-Turn");
        }else{
            clickBtn.setText("X");
            clickBtn.setBackgroundColor(Color.GREEN);
            clickBtn.setTextColor(Color.WHITE);
            active_player = PLAYER_O;
            clickBtn.setTextSize(18);
            player_turn.setText("Player O Turn");

        }
        // check for winner
        checkWinner();

        // check draw
        checkDraw();

    }
    public void restartGame(){
        active_player = PLAYER_O;

        //Setting the all the values to default
        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};

        //Setting all the Text on the buttons to empty
        mBtn0.setText("");
        mBtn1.setText("");
        mBtn2.setText("");
        mBtn3.setText("");
        mBtn4.setText("");
        mBtn5.setText("");
        mBtn6.setText("");
        mBtn7.setText("");
        mBtn8.setText("");

        //setting the default color for buttons
        mBtn0.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn1.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn2.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn3.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn4.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn5.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn6.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn7.setBackgroundColor(Color.parseColor("#BFBFBF"));
        mBtn8.setBackgroundColor(Color.parseColor("#BFBFBF"));

        //showing the text as player O's turn
        player_turn.setText("Player O Turn");

        //making the game active again
        game_ended = false;

    }
    public void checkDraw(){
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
    public boolean checkWinner(){
        int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for(int i=0;i<8;i++){
            int value0 = winningPosition[i][0];
            int value1 = winningPosition[i][1];
            int value2 = winningPosition[i][2];

            if (filledPos[value0] != -1){ // check if button is click
                if(filledPos[value0]== filledPos[value1] && filledPos[value1]==filledPos[value2]){ // check winning condition
                    game_ended = true;
                    if(filledPos[value0]==PLAYER_O){
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
    public void showWinner(String winner){
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
