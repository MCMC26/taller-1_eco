package com.example.mila.taller1_tcp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private EditText nombre;
    private EditText ip;
    private Button fusion;
    private Comunicacion ref;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = Comunicacion.getRef();
        ref.addObserver(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.colorAccent);

        nombre = findViewById(R.id.edt_nombre_main);
        ip = findViewById(R.id.edt_ip_main);
        fusion = findViewById(R.id.btn_conectar_main);

        fusion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String nombreU = nombre.getText().toString();
                final String direccion = ip.getText().toString();
                Intent i = new Intent(MainActivity.this, Control.class);
                startActivity(i);
            }
        });
    }
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {

                       ref = Comunicacion.getRef(/*direccion);
                        try {
                            Thread.sleep(500);
                            ref.enviar("Crear: :"+nombreU);
                            ref.enviar("IP"+direccion);

                            Intent i = new Intent(MainActivity.this, Control.class);
                            i.putExtra("ip", direccion);
                            startActivity(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();

            }
        });
    }
*/

    @Override
    public void update(Observable o, Object arg) {

    }
}