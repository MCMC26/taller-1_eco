package com.example.mila.taller1_tcp;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Observable;
import java.util.Observer;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class Control extends AppCompatActivity implements Observer {

    private Comunicacion ref;
    private JoystickView analogo;
    private  ImageButton d;
    private  ImageButton r;
    View view;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        view =this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.colorAccent);
        ref = Comunicacion.getRef();
        ref.addObserver(this);

        analogo = findViewById(R.id.jv_analogo_control);
        r =findViewById(R.id.btn_recargar_control);
        d =findViewById(R.id.btn_disparar_control);



        analogo.setOnMoveListener(new JoystickView.OnMoveListener() {
            public void onMove(int angle, int strength) {
                String msg = "Mover: :"+angle+": :"+strength;
                ref.enviar(msg);
            }
        });


        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ref.enviar("D");
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ref.enviar("R");
            }
        });

    }

    @Override
    public void update(Observable o, Object arg) {

    }}
