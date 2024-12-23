package com.example.sensorexperimentapp;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor accelerometer, proximitySensor, lightSensor;
    private Sensor rotationVectorSensor;
    private TextView orientationData;
    private SensorManager sensorManager;
    private TextView accelerometerData, proximityData, lightData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, rotationVectorSensor,SensorManager.SENSOR_DELAY_NORMAL);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        accelerometerData = findViewById(R.id.accerometerData);
        proximityData = findViewById(R.id.proximityData);
        lightData = findViewById(R.id.lightData);
        orientationData = findViewById(R.id.orientationData);


        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelerometerData.setText("Accelerometer Data: X= " + x + ", Y= " + y + ", Z= " + z);
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximityData.setText("Proximity Data: " + event.values[0]);
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightData.setText("Light Data: " + event.values[0]);
        }

        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            float[] rotationMatrix = new float [9];
            SensorManager.getOrientation(rotationMatrix, event.values);

            float[] orientation = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientation);

            orientationData.setText("Orientation: "+
                    "Azimuth=" + Math.toDegrees(orientation[0])+
                    ", Pith=" + Math.toDegrees(orientation[1])+
                    ", Roll=" + Math.toDegrees(orientation[2]));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No action needed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister sensor listeners to avoid memory leaks
        sensorManager.unregisterListener(this);
    }
}
