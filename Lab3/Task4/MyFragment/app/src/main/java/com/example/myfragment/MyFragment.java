package com.example.myfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myfragment.MainActivity;

import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    private static final String TAG = "MyFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View view = inflater.inflate(R.layout.fragment_my, container,false);

        Button sendButton = view.findViewById(R.id.button_send);
        sendButton.setOnClickListener(v ->{
            if(getActivity() instanceof MainActivity){
                ((MainActivity) getActivity()).sendDataToFragment2("Hello from fragment1");
            }
        });

        return view;
    }

    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}

