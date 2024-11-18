package com.example.myfragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            MyFragment myFragment = new MyFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, myFragment);
            transaction.commit();

            Fragment2 fragment2 = new Fragment2();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment2_container, fragment2, "fragment2")
                    .commit();

        }
    }

    public void sendDataToFragment2(String data){
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentByTag("fragment2");
        if (fragment2 != null){
            fragment2.updateData(data);
        }
    }
}