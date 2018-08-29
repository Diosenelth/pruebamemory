package com.example.wacho.pruebamemory;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class juegomedio extends AppCompatActivity {

    Handler handler = new Handler();

    ImageButton ib0, ib1, ib2, ib3, ib4, ib5, ib6, ib7;

    ImageButton[] botonera = new ImageButton[8];

    int[] Imagenes;

    int fondo;

    ImageButton primero;

    boolean bloqueo = false;

    int aciertos;

    int puntaje;

    int numeroPrimero, numeroSegundo;

    ArrayList<Integer> arraybarajado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegomedio);
        cargarimagenes();
        iniciar();
    }

    private void cargarimagenes() {
        Imagenes = new int[]{
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
        };

        fondo = R.drawable.fondo;
    }

    public ArrayList<Integer> barajar(int longitud) {
        ArrayList resultado = new ArrayList();
        for (int i = 0; i < longitud; i++) {
            resultado.add(i % longitud / 2);
            Collections.shuffle(resultado);
        }
        return resultado;
    }

    public void cargarbotones() {
        ib0 = (ImageButton) findViewById(R.id.ibu1);
        botonera[0] = ib0;
        ib1 = (ImageButton) findViewById(R.id.ibu2);
        botonera[1] = ib1;
        ib2 = (ImageButton) findViewById(R.id.ibu3);
        botonera[2] = ib2;
        ib3 = (ImageButton) findViewById(R.id.ibu4);
        botonera[3] = ib3;
        ib4 = (ImageButton) findViewById(R.id.ibu5);
        botonera[4] = ib4;
        ib5 = (ImageButton) findViewById(R.id.ibu6);
        botonera[5] = ib5;
        ib6 = (ImageButton) findViewById(R.id.ibu7);
        botonera[6] = ib6;
        ib7 = (ImageButton) findViewById(R.id.ibu8);
        botonera[7] = ib7;
    }

    public void comprobar(int i, final ImageButton imb) {
        if (primero == null) {
            primero = imb;
            primero.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            primero.setImageResource(Imagenes[arraybarajado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arraybarajado.get(i);
        } else {
            bloqueo = true;
            imb.setScaleType(ImageButton.ScaleType.CENTER_CROP);
            imb.setImageResource(Imagenes[arraybarajado.get(i)]);
            imb.setEnabled(false);
            numeroSegundo = arraybarajado.get(i);
        if (numeroPrimero == numeroSegundo) {
            primero = null;
            bloqueo = false;
            aciertos++;
            puntaje++;
        if (aciertos == 5) {
            Toast.makeText(getApplicationContext(), "Has ganado", Toast.LENGTH_SHORT).show();
            }
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    primero.setScaleType(ImageButton.ScaleType.CENTER_CROP);
                    primero.setImageResource(R.drawable.fondo);
                    imb.setScaleType(ImageButton.ScaleType.CENTER_CROP);
                    imb.setImageResource(R.drawable.fondo);
                    primero.setEnabled(true);
                    imb.setEnabled(true);
                    primero = null;
                    bloqueo = false;
                    if (puntaje > 0) {
                        puntaje--;
                    }
                }
            }, 1000);
        }
    }
}

    private void iniciar() {
        arraybarajado = barajar(Imagenes.length*2);
        cargarbotones();

        for (int i=0; i<botonera.length;i++){
            botonera[i].setScaleType(ImageButton.ScaleType.CENTER_CROP);
            botonera[i].setImageResource(Imagenes[arraybarajado.get(i)]);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<botonera.length;i++){
                    botonera[i].setScaleType(ImageButton.ScaleType.CENTER_CROP);
                    botonera[i].setImageResource(fondo);
                }
            }
        },1000);

        for (int i=0;i<arraybarajado.size();i++){
            final int j = i;
            botonera[i].setEnabled(true);
            botonera[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!bloqueo){
                        comprobar(j,botonera[j]);
                    }
                }
            });
        }
        aciertos++;
        puntaje++;
    }

}
