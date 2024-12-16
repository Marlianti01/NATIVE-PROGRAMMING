package com.example.smsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText phoneNumberEditText = findViewById(R.id.phone_number);
        EditText messageEditText = findViewById(R.id.message_text);
        Button sendButton = findViewById(R.id.send_button);

        sendButton.setOnClickListener(v -> {
           String phoneNumber = phoneNumberEditText.getText().toString();
           String message = messageEditText.getText().toString();

           if (!phoneNumber.isEmpty() && !message.isEmpty()){
               Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
               smsIntent.setData(Uri.parse("smsto:"+phoneNumber));
               smsIntent.putExtra("sms_body", message);
               startActivity(smsIntent);
           } else {
               Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show();
           }
        });
    }
}