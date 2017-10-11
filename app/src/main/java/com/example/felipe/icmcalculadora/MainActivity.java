package com.example.felipe.icmcalculadora;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton generoM, generoF;
    private Button btnCm, btnKg;
    private EditText talla, peso,edad;
    private TextView rimc, pgrasa, informacion;
    private boolean uTa=true, uPe=true;
    private double imc, grasa;
    private int gen=0;
    private TableRow dSevera, dModerada, dAceptable, normal, preobeso, obesoI,obesoII, obesoIII;
    private TableRow pInsuficienteF,normopesoF, sobrepesoF, obesidadF;
    private TableRow pInsuficienteM,normopesoM, sobrepesoM, obesidadM;
    private TableLayout pGCF,pGCM;
    private ImageView imgCuerpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generoM = (ImageButton) findViewById(R.id.generoM);
        generoF = (ImageButton) findViewById(R.id.generoF);
        btnKg = (Button) findViewById(R.id.btnKg);
        btnCm = (Button) findViewById(R.id.btnCm);
        edad = (EditText) findViewById(R.id.edadu);
        talla = (EditText) findViewById(R.id.tallaU);
        peso = (EditText) findViewById(R.id.pesoU);
        rimc = (TextView) findViewById(R.id.rimc);
        pgrasa = (TextView) findViewById(R.id.pgrasa);
        informacion = (TextView) findViewById(R.id.informacion);
        talla.setHint("cm");
        peso.setHint("kg");

        dSevera = (TableRow) findViewById(R.id.dSevera);
        dModerada = (TableRow) findViewById(R.id.dModerada);
        dAceptable = (TableRow) findViewById(R.id.dAceptable);
        normal = (TableRow) findViewById(R.id.normal);
        preobeso = (TableRow) findViewById(R.id.preobeso);
        obesoI = (TableRow) findViewById(R.id.obesoI);
        obesoII = (TableRow) findViewById(R.id.obesoII);
        obesoIII = (TableRow) findViewById(R.id.obesoIII);

        pInsuficienteF = (TableRow) findViewById(R.id.pInsuficienteF);
        normopesoF = (TableRow) findViewById(R.id.normopesoF);
        sobrepesoF = (TableRow) findViewById(R.id.sobrepesoF);
        obesidadF = (TableRow) findViewById(R.id.obesidadF);
        pGCF = (TableLayout) findViewById(R.id.pGCF);

        pInsuficienteM = (TableRow) findViewById(R.id.pInsuficienteM);
        normopesoM = (TableRow) findViewById(R.id.normopesoM);
        sobrepesoM = (TableRow) findViewById(R.id.sobrepesoM);
        obesidadM = (TableRow) findViewById(R.id.obesidadM);
        pGCM = (TableLayout) findViewById(R.id.pGCM);

        imgCuerpos = (ImageView) findViewById(R.id.cuerpos);


        generoM.setVisibility(View.GONE);
        pGCM.setVisibility(View.GONE);



        edad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcular();
            }
        });

        talla.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcular();
            }
        });

        peso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                calcular();
            }
        });
    }

    private void calcular() {

        if(!edad.getText().toString().isEmpty() && !talla.getText().toString().isEmpty() && !peso.getText().toString().isEmpty()) {

            int ed= Integer.parseInt(edad.getText().toString());
            double ta = Double.parseDouble(talla.getText().toString());
            double pe = Double.parseDouble(peso.getText().toString());

            if(uTa==false){
                ta = ta/0.39370;
            }

            if(uTa==true){
                ta = ta/100;
            }

            if(uPe==false){
                pe = pe/2.2046;
            }

            imc = pe/(ta*ta);

            rimc.setText(String.format("%.2f", imc));

            grasa = (1.20*imc) + (0.23*ed) - (10.8*gen)- (5.4);

            pgrasa.setText(String.format("%.2f", grasa)+"%");

            if(gen==0){//PGC Mujeres
                if(grasa < 20){ // peso insuficiente

                    pInsuficienteF.setBackgroundResource(R.drawable.borde_rojo);
                    normopesoF.setBackgroundResource(R.drawable.transparencia);
                    sobrepesoF.setBackgroundResource(R.drawable.transparencia);
                    obesidadF.setBackgroundResource(R.drawable.transparencia);

                }

                if (grasa >= 20 && grasa <= 30){//Normopeso

                    pInsuficienteF.setBackgroundResource(R.drawable.transparencia);
                    normopesoF.setBackgroundResource(R.drawable.borde_verde_2);
                    sobrepesoF.setBackgroundResource(R.drawable.transparencia);
                    obesidadF.setBackgroundResource(R.drawable.transparencia);

                }

                if (grasa > 30 && grasa <= 35.1){

                    pInsuficienteF.setBackgroundResource(R.drawable.transparencia);
                    normopesoF.setBackgroundResource(R.drawable.transparencia);
                    sobrepesoF.setBackgroundResource(R.drawable.borde_naranja);
                    obesidadF.setBackgroundResource(R.drawable.transparencia);

                }

                if (grasa > 35){

                    pInsuficienteF.setBackgroundResource(R.drawable.transparencia);
                    normopesoF.setBackgroundResource(R.drawable.transparencia);
                    sobrepesoF.setBackgroundResource(R.drawable.transparencia);
                    obesidadF.setBackgroundResource(R.drawable.borde_rojo);

                }

                if (imc < 18 ) imgCuerpos.setImageResource(R.drawable.m1);

                if (imc >= 18 && imc < 25) imgCuerpos.setImageResource(R.drawable.m2);

                if (imc >= 25 && imc <30) imgCuerpos.setImageResource(R.drawable.m3);

                if (imc >= 30) imgCuerpos.setImageResource(R.drawable.m4);

            }

            if(gen==1){//PGC Hombres
                if(grasa < 10){ // peso insuficiente

                    pInsuficienteM.setBackgroundResource(R.drawable.borde_rojo);
                    normopesoM.setBackgroundResource(R.drawable.transparencia);
                    sobrepesoM.setBackgroundResource(R.drawable.transparencia);
                    obesidadM.setBackgroundResource(R.drawable.transparencia);

                }

                if (grasa > 10 && grasa <= 20){//Normopeso

                    pInsuficienteM.setBackgroundResource(R.drawable.transparencia);
                    normopesoM.setBackgroundResource(R.drawable.borde_verde_2);
                    sobrepesoM.setBackgroundResource(R.drawable.transparencia);
                    obesidadM.setBackgroundResource(R.drawable.transparencia);

                }

                if (grasa > 20 && grasa <= 25){

                    pInsuficienteM.setBackgroundResource(R.drawable.transparencia);
                    normopesoM.setBackgroundResource(R.drawable.transparencia);
                    sobrepesoM.setBackgroundResource(R.drawable.borde_naranja);
                    obesidadM.setBackgroundResource(R.drawable.transparencia);

                }

                if (grasa > 25){

                    pInsuficienteM.setBackgroundResource(R.drawable.transparencia);
                    normopesoM.setBackgroundResource(R.drawable.transparencia);
                    sobrepesoM.setBackgroundResource(R.drawable.transparencia);
                    obesidadM.setBackgroundResource(R.drawable.borde_rojo);

                }

                if (imc < 18 ) imgCuerpos.setImageResource(R.drawable.h1);

                if (imc >= 18 && imc < 25) imgCuerpos.setImageResource(R.drawable.h2);

                if (imc >= 25 && imc <30) imgCuerpos.setImageResource(R.drawable.h3);

                if (imc >= 30) imgCuerpos.setImageResource(R.drawable.h4);

            }

            if(imc < 16){ //Delgadez severa
                dSevera.setBackgroundResource(R.drawable.borde_rojo);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.Delgadez_severa));
                informacion.setTextColor(Color.parseColor("#ff0d00"));

            }
            if(imc >= 16 && imc <17){ //Delgadez Moderada
                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.borde_rojo);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.delgadez_moderada));
                informacion.setTextColor(Color.parseColor("#ff0d00"));
            }

            if(imc >=17 && imc < 18.50 ){ //Delgadez Aceptable

                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.borde_rojo);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.delgadez_aceptable));
                informacion.setTextColor(Color.parseColor("#ff0d00"));

            }

            if(imc >= 18.50 && imc < 25){ // Normal

                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                normal.setBackgroundResource(R.drawable.borde_verde);
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.normal));
                informacion.setTextColor(Color.parseColor("#22ff00"));

            }

            if(imc >= 25 && imc < 30){ // Preobeso

                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.borde_naranja);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.preobeso));
                informacion.setTextColor(Color.parseColor("#ff6200"));

            }

            if(imc >= 30 && imc < 35){ // Obeso tipo I

                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.borde_rojo);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.obesoI));
                informacion.setTextColor(Color.parseColor("#ff0d00"));

            }

            if(imc >= 35 && imc < 40){ // Obeso tipo II

                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.borde_rojo);
                obesoIII.setBackgroundResource(R.drawable.transparencia);
                informacion.setText(getResources().getString(R.string.obesoII));
                informacion.setTextColor(Color.parseColor("#ff0d00"));

            }

            if(imc > 40){ // Obeso tipo III

                dSevera.setBackgroundResource(R.drawable.transparencia);
                dModerada.setBackgroundResource(R.drawable.transparencia);
                dAceptable.setBackgroundResource(R.drawable.transparencia);
                normal.setBackgroundColor(Color.parseColor("#6eae2a"));
                preobeso.setBackgroundResource(R.drawable.transparencia);
                obesoI.setBackgroundResource(R.drawable.transparencia);
                obesoII.setBackgroundResource(R.drawable.transparencia);
                obesoIII.setBackgroundResource(R.drawable.borde_rojo);
                informacion.setText(getResources().getString(R.string.ObesoIII));
                informacion.setTextColor(Color.parseColor("#ff0d00"));

            }



            /*grasa = -44.988+(0.503*ed)+(10.689*gen)+(3.172*imc)-(0.026*imc*imc)+(0.181*imc*gen)-(0.02*imc*ed)-(0.005*imc*imc*gen)+(0.00021*imc*imc*ed);*/


            /*Toast toast1 = Toast.makeText(getApplicationContext(), "imc "+String.format("%.2f", grasa), Toast.LENGTH_LONG);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();*/
        }

        else {
            imc = 0;
            grasa = 0;

            rimc.setText("#");
            pgrasa.setText("%");

            informacion.setText("");

            dSevera.setBackgroundResource(R.drawable.transparencia);
            dModerada.setBackgroundResource(R.drawable.transparencia);
            dAceptable.setBackgroundResource(R.drawable.transparencia);
            normal.setBackgroundColor(Color.parseColor("#6eae2a"));
            preobeso.setBackgroundResource(R.drawable.transparencia);
            obesoI.setBackgroundResource(R.drawable.transparencia);
            obesoII.setBackgroundResource(R.drawable.transparencia);
            obesoIII.setBackgroundResource(R.drawable.transparencia);

            pInsuficienteF.setBackgroundResource(R.drawable.transparencia);
            normopesoF.setBackgroundResource(R.drawable.transparencia);
            sobrepesoF.setBackgroundResource(R.drawable.transparencia);
            obesidadF.setBackgroundResource(R.drawable.transparencia);

            pInsuficienteM.setBackgroundResource(R.drawable.transparencia);
            normopesoM.setBackgroundResource(R.drawable.transparencia);
            sobrepesoM.setBackgroundResource(R.drawable.transparencia);
            obesidadM.setBackgroundResource(R.drawable.transparencia);

            if(gen==0){

                imgCuerpos.setImageResource(R.drawable.bm);

            }

            if(gen==1){

                imgCuerpos.setImageResource(R.drawable.bh);

            }

        }
    }

    public void sexo(View view) {

        AlertDialog.Builder sBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_sexo, null);
        sBuilder.setView(mView);
        final AlertDialog dialog = sBuilder.create();
        dialog.show();

        ImageButton btnWomen = (ImageButton) mView.findViewById(R.id.women);
        ImageButton btnMan = (ImageButton) mView.findViewById(R.id.man);

        btnMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generoF.setVisibility(View.GONE);
                generoM.setVisibility(View.VISIBLE);
                pGCF.setVisibility(View.GONE);
                pGCM.setVisibility(View.VISIBLE);
                imgCuerpos.setImageResource(R.drawable.bh);
                dialog.cancel();
                gen = 1;
                calcular();
            }
        });

        btnWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generoF.setVisibility(View.VISIBLE);
                generoM.setVisibility(View.GONE);
                pGCF.setVisibility(View.VISIBLE);
                pGCM.setVisibility(View.GONE);
                imgCuerpos.setImageResource(R.drawable.bm);
                dialog.cancel();
                gen = 0;
                calcular();
            }
        });

    }

    public void uTalla(View view) {

        AlertDialog.Builder tBuilder = new AlertDialog.Builder(MainActivity.this);
        View tView = getLayoutInflater().inflate(R.layout.dialog_talla, null);
        tBuilder.setView(tView);
        final AlertDialog dialog = tBuilder.create();

        final RadioButton in = (RadioButton) tView.findViewById(R.id.in);
        final RadioButton cm = (RadioButton) tView.findViewById(R.id.lb);

        if (talla.getHint().toString() == "in") {
            in.setChecked(true);
            cm.setChecked(false);
        }

        if (talla.getHint().toString() == "cm") {
            in.setChecked(false);
            cm.setChecked(true);
        }

        dialog.show();


        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                talla.setHint("in");
                btnCm.setText("in");
                dialog.cancel();
                uTa = false;
                calcular();
            }
        });

        cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                talla.setHint("cm");
                btnCm.setText("Cm");
                dialog.cancel();
                uTa = true;
                calcular();
            }
        });

    }

    public void uPeso(View view) {

        AlertDialog.Builder uBuilder = new AlertDialog.Builder(MainActivity.this);
        View pView = getLayoutInflater().inflate(R.layout.dialog_peso, null);
        uBuilder.setView(pView);
        final AlertDialog dialog = uBuilder.create();

        RadioButton kg = (RadioButton) pView.findViewById(R.id.kg);
        RadioButton lb = (RadioButton) pView.findViewById(R.id.lb);

        if (peso.getHint().toString() == "lb") {
            lb.setChecked(true);
            kg.setChecked(false);
        }

        if (peso.getHint().toString() == "kg") {
            lb.setChecked(false);
            kg.setChecked(true);
        }

        dialog.show();

        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peso.setHint("lb");
                btnKg.setText("Lb");
                dialog.cancel();
                uPe = false;
                calcular();
            }
        });

        kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peso.setHint("kg");
                btnKg.setText("Kg");
                dialog.cancel();
                uPe = true;
                calcular();
            }
        });
    }

    public void acercade(View view){

        AlertDialog.Builder uBuilder = new AlertDialog.Builder(MainActivity.this);
        View aView = getLayoutInflater().inflate(R.layout.acercade, null);
        uBuilder.setView(aView);
        final AlertDialog dialog = uBuilder.create();
        dialog.show();

        ImageButton casa = (ImageButton) aView.findViewById(R.id.casa);

        casa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    public void facebook(View view){

        Uri uri = Uri.parse("https://www.facebook.com/afelipebs");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void google(View view){

        Uri uri = Uri.parse("https://plus.google.com/u/0/109645678402557890394");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
