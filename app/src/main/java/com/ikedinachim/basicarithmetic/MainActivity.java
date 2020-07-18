package com.ikedinachim.basicarithmetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private Button btnAddition, btnSubtraction, btnDivision,btnMultiplication, btnAddSubtract;
    private TextView about;
    private AdView madView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddition = findViewById(R.id.btnAddition);
        btnSubtraction = findViewById(R.id.btnSubtraction);
        btnDivision = findViewById(R.id.btnDivision);
        btnAddSubtract = findViewById(R.id.btnAddSubtract);
        btnMultiplication = findViewById(R.id.btnMultiplication);

        MobileAds.initialize(this, "ca-app-pub-9738106405881817~3349816055");
        madView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        madView.loadAd(adRequest);
    }

    public void btnAddition(View view) {
        Intent addition = new Intent(this,Addition.class);
        startActivity(addition);
    }

    public void btnSubtraction(View view) {
        Intent subtraction = new Intent(this, Subtraction.class);
        startActivity(subtraction);
    }

    public void btnDivision(View view) {
        startActivity(new Intent(this,Division.class));
    }

    public void btnMultiplication(View view) {
        startActivity(new Intent(this, Multiplication.class));
    }

    public void btnAddSubtract(View view) {
        startActivity(new Intent(this, Addition_Subtraction.class));
    }
}