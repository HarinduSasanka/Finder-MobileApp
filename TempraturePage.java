package com.s22010176.finderapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.location.LocationManager;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TempraturePage extends AppCompatActivity implements SensorEventListener {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemparatureSensorAvailable;
    private LocationManager locationManager;
    private float lastKnownTemperature= Float.NaN;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.temprature_page);


        textView=  findViewById(R.id.textView);
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null){
            tempSensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        }else{
            textView.setText("Temparature Sensor Is Not Available");
            isTemparatureSensorAvailable=false;
        }
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText(event.values[0]+ " ºC " );

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onResume(){
        super.onResume();
        if (isTemparatureSensorAvailable){
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);

        }
    }
    protected void onPause(){
        super.onPause();
        if(isTemparatureSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }


}