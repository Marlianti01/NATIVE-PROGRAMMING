package com.example.myfirstappjava;
import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        textView.setTextColor(Color.RED);
        Button button = findViewById(R.id.button);
        Button buttonNavigate = findViewById(R.id.buttonNavigate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button was clicked!", Toast.LENGTH_SHORT).show();
                textView.setText("Button Clicked!");


            }
        });

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "Hello From MainActivity");
                startActivity(intent);

            }

        }

        );

    }
}
