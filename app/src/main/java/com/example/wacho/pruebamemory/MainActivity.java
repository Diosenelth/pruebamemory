package com.example.wacho.pruebamemory;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageButton ib0,ib1,ib2,ib3,ib4,ib5,ib6,ib7,ib8,ib9,ib10,ib11,ib12,ib13,ib14,ib15;

    ImageButton[] botonera = new ImageButton[16];

    int imagenes [];

    boolean bloqueo = false;

    int fondo;

    ArrayList<Integer> arraybarajado;

    ImageButton primero;

    int numeroPrimero, numeroSegundo;

    int aciertos=0;
    int puntuacion=0;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarimagenes();
        iniciar();

    }


    public void cargarimagenes(){
        imagenes = new int[] {
                R.drawable.la0,
                R.drawable.la1,
                R.drawable.la2,
                R.drawable.la3,
                R.drawable.la4,
                R.drawable.la5,
                R.drawable.la6,
                R.drawable.la7,
        };
        fondo = R.drawable.fondo;
    }

    public ArrayList<Integer> barajar (int longitud){
        ArrayList Resultado = new ArrayList<>();
        for (int i=0;i<longitud;i++){
            Resultado.add(i % longitud/2);
            Collections.shuffle(Resultado);
        }
        return Resultado;
    }

    public void cargarbotones(){
        ib0 = (ImageButton)findViewById(R.id.ib1);
        botonera[0] = ib0;
        ib1 = (ImageButton)findViewById(R.id.ib2);
        botonera[1] = ib1;
        ib2 = (ImageButton)findViewById(R.id.ib3);
        botonera[2] = ib2;
        ib3 = (ImageButton)findViewById(R.id.ib4);
        botonera[3] = ib3;
        ib4 = (ImageButton)findViewById(R.id.ib5);
        botonera[4] = ib4;
        ib5 = (ImageButton)findViewById(R.id.ib6);
        botonera[5] = ib5;
        ib6 = (ImageButton)findViewById(R.id.ib7);
        botonera[6] = ib6;
        ib7 = (ImageButton)findViewById(R.id.ib8);
        botonera[7] = ib7;
        ib8 = (ImageButton)findViewById(R.id.ib9);
        botonera[8] = ib8;
        ib9 = (ImageButton)findViewById(R.id.ib10);
        botonera[9] = ib9;
        ib10 = (ImageButton)findViewById(R.id.ib11);
        botonera[10] = ib10;
        ib11 = (ImageButton)findViewById(R.id.ib12);
        botonera[11] = ib11;
        ib12 = (ImageButton)findViewById(R.id.ib13);
        botonera[12] = ib12;
        ib13 = (ImageButton)findViewById(R.id.ib14);
        botonera[13] = ib13;
        ib14 = (ImageButton)findViewById(R.id.ib15);
        botonera[14] = ib14;
        ib15 = (ImageButton)findViewById(R.id.ib16);
        botonera[15] = ib15;

        puntuacion++;
    }

    public void comprobar(int i, final ImageButton imgb){
        if(primero==null){
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arraybarajado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero=arraybarajado.get(i);
        }else{
            bloqueo=true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arraybarajado.get(i)]);
            imgb.setEnabled(false);
            numeroSegundo=arraybarajado.get(i);
            if(numeroPrimero==numeroSegundo){//si coincide el valor los dejamos   destapados reiniciamos
                primero=null;
                bloqueo=false;
                aciertos++;
                puntuacion++;
                //textoPuntuacion.setText("Puntuación: " + puntuacion);
                if(aciertos==8){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Has  ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }else{//si NO coincide el valor los volvemos a tapar al cabo de un segundo
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(R.drawable.fondo);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(R.drawable.fondo);
                        primero.setEnabled(true);
                        imgb.setEnabled(true);
                        primero = null;
                        bloqueo = false;
                        if (puntuacion > 0) {
                            puntuacion--;

                        }
                    }
                }, 1000);
            }
        }
    }

    private void iniciar() {
        arraybarajado = barajar(imagenes.length*2);
        cargarbotones();

        for (int i=0; i<botonera.length;i++){
            botonera[i].setScaleType(ImageButton.ScaleType.CENTER_CROP);
            botonera[i].setImageResource(imagenes[arraybarajado.get(i)]);
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

        for (int i=0; i <arraybarajado.size();i++){
            final int j=i;
            botonera[i].setEnabled(true);
            botonera[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo){
                        comprobar( j ,botonera[j]);
                    }
                }
            });
        }


    }

    public void juegopqeueño(View view) {
        Intent intent
                = new Intent(this,juegomedio.class);
        startActivity(intent);
    }
}
