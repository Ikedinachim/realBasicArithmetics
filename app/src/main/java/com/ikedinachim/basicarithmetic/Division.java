package com.ikedinachim.basicarithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

public class Division extends AppCompatActivity {
    TextView sumView, points, result, hiscore;
    Button button0, button1, button2, button3, goButton, playAgain;
    ProgressBar timerProgressBar;
    int score, numOfQuestions, locationOfCorrectAns;
    int currentProgressTimer = 30;
    int max = 30;
    RelativeLayout relativeLayout;
    ArrayList<String> numbers;
    Random random;

    Handler handler;
    SharedPreferences store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);
        sumView = findViewById(R.id.sumView);
        points = findViewById(R.id.points);
        button0 = findViewById(R.id.button0);
        goButton = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        result = findViewById(R.id.result);
        AdView adView;
        random = new Random();
        hiscore = findViewById(R.id.hiScore);
        handler = new Handler();
        playAgain = findViewById(R.id.play);
        numbers = new ArrayList<>();
        timerProgressBar = findViewById(R.id.spinnerTimer);
        relativeLayout = findViewById(R.id.relativeLayout);
        timerProgressBar.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                numOfQuestions = 0;
                points.setText("00");
                result.setText("");

                relativeLayout.setVisibility(View.VISIBLE);
                goButton.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.INVISIBLE);
                int[] numbs = generateNumbers(numOfQuestions);
                timerProgressBar.setVisibility(View.VISIBLE);
                sumView.setText(Integer.toString(numbs[0]) + "/" + Integer.toString(numbs[1]));
                generateQuestions(numbs[0] / numbs[1]);
                hiscore.setText("Best:" + Integer.toString(retrieveHighScore()));
                button0.setEnabled(true);
                button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timerProgressBar.setMax(max);
                        timerProgressBar.setProgress(currentProgressTimer);
                        currentProgressTimer -= 1;
                        if (currentProgressTimer != 0) {
                            new Handler().postDelayed(this, 2000);
                        } else {
                            playAgain.setVisibility(View.VISIBLE);
                            button0.setEnabled(false);
                            button1.setEnabled(false);
                            button2.setEnabled(false);
                            button3.setEnabled(false);
                            timerProgressBar.setVisibility(View.INVISIBLE);
//                    timerProgressBar.setMax(30);//fj
//                    timerProgressBar.setProgress(30);//hj
                            result.setText(getString(R.string.time_up));
                        }
                    }
                }, 1000);
            }
        });
        MobileAds.initialize(this, "ca-app-pub-9738106405881817~3349816055");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }

//    public void playAgain(View view) {
//        score = 0;
//        numOfQuestions = 0;
//        points.setText("00");
//      //  max = 30;
//       // currentProgressTimer = 30;
//        timerProgressBar.setVisibility(View.VISIBLE);
//        relativeLayout.setVisibility(View.VISIBLE);
//        playAgain.setVisibility(View.INVISIBLE);
//        goButton.setVisibility(View.INVISIBLE);
//        int[] numbs = generateNumbers(numOfQuestions);
//        sumView.setText(Integer.toString(numbs[0])+"+"+Integer.toString(numbs[1]));
//        generateQuestions(numbs[0] + numbs[1]);
//        hiscore.setText("Best:"+Integer.toString(retrieveHighScore()));
//        button0.setEnabled(true);
//        button1.setEnabled(true);
//        button2.setEnabled(true);
//        button3.setEnabled(true);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                currentProgressTimer -= 1;
//                timerProgressBar.setMax(max);
//                timerProgressBar.setProgress(currentProgressTimer);
//                if (currentProgressTimer != 0) {
//                    new Handler().postDelayed(this, 1000);
//                }else {
//                    playAgain.setVisibility(View.VISIBLE);
//                    button0.setEnabled(false);
//                    button1.setEnabled(false);
//                    button2.setEnabled(false);
//                    button3.setEnabled(false);
//                    timerProgressBar.setVisibility(View.INVISIBLE);
//                    result.setText(getString(R.string.time_up));
//                    timerProgressBar.setMax(30);//fj
//                    timerProgressBar.setProgress(30);
//                    score = 0;
//                    numOfQuestions = 0;
//                }
//            }
//        },1000);
//    }

    public void chooseanswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))) {

            int q = random.nextInt(3);
            if (q == 0) {
                result.setText(getString(R.string.great));
            }
            if (q == 1) {
                result.setText(getString(R.string.awesome));
            }
            if (q == 2) {
                result.setText(getString(R.string.nice));
            }
            score++;
            numOfQuestions++;
            if (score > retrieveHighScore()) {
                storeHighScore(score);

            }


            points.setText(Integer.toString(score));

            hiscore.setText("Best:" + Integer.toString(retrieveHighScore()));

            int[] numbs = generateNumbers(numOfQuestions);
            sumView.setText(Integer.toString(numbs[0]) + "/" + Integer.toString(numbs[1]));
            generateQuestions(numbs[0] / numbs[1]);

        } else {

            int q = random.nextInt(3);
            if (q == 0) {
                result.setText(R.string.sorry);
            }
            if (q == 1) {
                result.setText(R.string.nope);
            }
            if (q == 2) {
                result.setText(R.string.wrong);
            }
            playAgain.setVisibility(View.VISIBLE);
            button0.setEnabled(false);
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            timerProgressBar.setVisibility(View.GONE);


            //     Toast.makeText(this, "max:"+timerProgressBar.getMax()+"progress"+timerProgressBar.getProgress(), Toast.LENGTH_LONG).show();

        }

    }

    public void begin(View view) {
        score = 0;
        numOfQuestions = 0;
        points.setText("00");
        result.setText("");

        relativeLayout.setVisibility(View.VISIBLE);
        goButton.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        int[] numbs = generateNumbers(numOfQuestions);
        timerProgressBar.setVisibility(View.VISIBLE);
        sumView.setText(Integer.toString(numbs[0]) + "/" + Integer.toString(numbs[1]));
        generateQuestions(numbs[0] / numbs[1]);
        hiscore.setText("Best:" + Integer.toString(retrieveHighScore()));
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timerProgressBar.setMax(max);
                timerProgressBar.setProgress(currentProgressTimer);
                currentProgressTimer -= 0.5;
                if (currentProgressTimer != 0) {
                    new Handler().postDelayed(this, 1000);
                } else {
                    playAgain.setVisibility(View.VISIBLE);
                    button0.setEnabled(false);
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    timerProgressBar.setVisibility(View.INVISIBLE);
//                    timerProgressBar.setMax(30);//fj
//                    timerProgressBar.setProgress(30);//hj
                    result.setText(getString(R.string.time_up));
                }
            }
        }, 1000);

    }

    public int[] generateNumbers(int numOfQues) {
        int num1;
        int num2;
        int[] numbers;
        if (max != 30) {
            max = 30;
        }
        if (currentProgressTimer != 30) {
            currentProgressTimer = 30;
        }
        if (numOfQues <= 5) {
            num1 = random.nextInt(1)+20;
            num2 = random.nextInt(10)+1;
            while (num1 % num2 != 0){
                num2 = random.nextInt(10)+1;

            }
            currentProgressTimer = 30;
            max = 30;
            timerProgressBar.setMax(max);
            timerProgressBar.setProgress(currentProgressTimer);
            numbers = new int[]{num1, num2};
            return numbers;
        }
        if (numOfQues <= 10) {
            num1 = random.nextInt(21) + 19; // random number from 21 to 21+19
            num2 = random.nextInt(16) + 5;
            while (num1 % num2 != 0){
                num2 = random.nextInt(16)+5;

            }
            currentProgressTimer = 25;
            max = 25;
            timerProgressBar.setMax(max);
            timerProgressBar.setProgress(currentProgressTimer);
            numbers = new int[]{num1, num2};
            return numbers;
        }
        if (numOfQues <= 15) {
            num1 = random.nextInt(40) + 20;
            num2 = random.nextInt(29) + 2;
            while (num1 % num2 != 0){
                num2 = random.nextInt(29)+2;

            }
            currentProgressTimer = 20;
            max = 20;
            timerProgressBar.setMax(max);
            timerProgressBar.setProgress(currentProgressTimer);
            numbers = new int[]{num1, num2};
            return numbers;
        }
        if (numOfQues <= 20) {
            num1 = random.nextInt(60) + 60;
            num2 = random.nextInt(29) + 2;
            while (num1 % num2 != 0){
                num2 = random.nextInt(29)+2;

            }
            currentProgressTimer = 15;
            max = 15;
            timerProgressBar.setMax(max);
            timerProgressBar.setProgress(currentProgressTimer);
            numbers = new int[]{num1, num2};
            return numbers;
        }
        if (numOfQues <= 25) {
            num1 = random.nextInt(120) + 80;
            num2 = random.nextInt(41) + 20;
            while (num1 % num2 != 0){
                num2 = random.nextInt(41)+20;

            }
            currentProgressTimer = 10;
            max = 10;
            timerProgressBar.setMax(max);
            timerProgressBar.setProgress(currentProgressTimer);
            numbers = new int[]{num1, num2};
            return numbers;
        }
        if (numOfQues <= 30) {
            num1 = random.nextInt(200) + 50;
            num2 = random.nextInt(81) + 20;
            while (num1 % num2 != 0){
                num2 = random.nextInt(81)+20;

            }
            currentProgressTimer = 5;
            max = 5;
            timerProgressBar.setMax(max);
            timerProgressBar.setProgress(currentProgressTimer);
            numbers = new int[]{num1, num2};
            return numbers;
        }//else
        currentProgressTimer = 3;
        max = 3;
        timerProgressBar.setMax(max);
        timerProgressBar.setProgress(currentProgressTimer);

        return new int[]{random.nextInt(250) + 250, random.nextInt(105) + 20};
    }

    public void generateQuestions(int correctAns) {
        numbers.clear();

        locationOfCorrectAns = random.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAns) {
                numbers.add(Integer.toString(correctAns));
            } else {
                int incorrectAns = random.nextInt(correctAns + 50) + (correctAns - 50);
                if (incorrectAns < 0) {
                    incorrectAns = -1 * incorrectAns;
                }
                while (incorrectAns == correctAns) {
                    random.nextInt(correctAns + 50);
                }
                numbers.add(Integer.toString(incorrectAns));
            }
        }
        button0.setText(numbers.get(0));
        button1.setText(numbers.get(1));
        button2.setText(numbers.get(2));
        button3.setText(numbers.get(3));

    }

    public void storeHighScore(int highScore) {

        store = getSharedPreferences("DIVISION", 0);
        SharedPreferences.Editor editor = store.edit();
        editor.putInt("DIVISION", highScore);
        editor.commit();

    }

    public int retrieveHighScore() {
        store = getSharedPreferences("DIVISION", 0);
        int hscore = store.getInt("DIVISION", 0); //0 is the default value
        return hscore;
    }
}
