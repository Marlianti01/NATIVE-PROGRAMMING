package com.example.imageexperiment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private boolean isBackgroundOne =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        Button button = findViewById(R.id.imageButton);
        Button switchBackgroundButton = findViewById(R.id.switchBackgroundButton);
        switchBackgroundButton.setOnClickListener(v -> {
            if (isBackgroundOne){
                layout.setBackgroundResource(R.drawable.background2);
            }
            else {
                layout.setBackgroundResource(R.drawable.background1);
            }
            isBackgroundOne = !isBackgroundOne;
        });
    }
}