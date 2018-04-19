package com.felixkalu.tictactoebyfelix;
//editted from github website to test and be sure that things are working well as they should!
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

//added this line from android studio to ensure that this pulls through

public class MainActivity extends AppCompatActivity {

    // 0= xx, 1 = oo;

    int activePlayer = 0;
    boolean gameIsActive = true; //to keep track of if the game is active or not
    int[] gameState = {3,3,3,3,3,3,3,3,3}; //this array would be used in the code to ensure that a tapped box can not be tapped again.
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {2,4,6}, {0,4,8}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 3 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.xx);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.oo);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotationBy(1080f).setDuration(1000);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[1]] != 3) {

                    gameIsActive = false;
                    String winner = "O";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "X";

                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    if (winner == "X") {
                        winnerMessage.setText(winner + "  Has Won!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setBackgroundColor(Color.RED);

                        layout.setVisibility(view.VISIBLE);
                        layout.animate().rotation(360f).setDuration(3000);

                    }
                    if (winner == "O") {

                        winnerMessage.setText(winner + "  Has Won!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setBackgroundColor(Color.BLUE);

                        layout.setVisibility(view.VISIBLE);
                        layout.animate().rotation(360f).setDuration(3000);

                    }


                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {

                        if (counterState == 3) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(" It's a Draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setBackgroundColor(Color.CYAN);


                        layout.setVisibility(view.VISIBLE);
                        layout.animate().rotation(360f).setDuration(3000);
                    }
                }
            }
        }
    }


        public void playAgain (View view) {

            gameIsActive =true;

            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            layout.setVisibility(view.INVISIBLE);

            activePlayer = 0;

            for(int i=0; i<gameState.length; i++){
                gameState[i] = 3;
            }
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

            for(int i=0 ; i<gridLayout.getChildCount(); i++){
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0); //This line of code resets all the imageviews on the gridLayout and removes all pictures there
            }

        }

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        // this line is used to check that this project can be updated from my windows project after cloning straight into android studio 3
        //not i can do whatevery i want with with github even though i am testing this

    //this is another one to test another branch while i still practice with my android studio and github


}
