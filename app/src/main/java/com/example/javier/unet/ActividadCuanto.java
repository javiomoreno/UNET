package com.example.javier.unet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javier.clases.TablaConversion;

public class ActividadCuanto extends AppCompatActivity {

    TextView textViewPrimerParcial, textViewSegundoParcial, textViewTercerParcial;
    TextInputLayout textInputLayoutPorcentaje1,textInputLayoutPorcentaje2, textInputLayoutPorcentaje3;
    EditText editTextPorcentaje1, editTextPorcentaje2, editTextPorcentaje3;
    TextInputLayout textInputLayoutNota1,textInputLayoutNota2, textInputLayoutNota3;
    EditText editTextNota1, editTextNota2, editTextNota3;
    LinearLayout linearLayoutButtons;
    Button calcular;

    boolean bandera = false;
    int opcionSeleccionada;

    TablaConversion obj = new TablaConversion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_cuanto_layout);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.titulo_cuanto);
        actionBar.setDisplayHomeAsUpEnabled(true);

        textViewPrimerParcial = (TextView) findViewById(R.id.textView5);
        textViewSegundoParcial = (TextView) findViewById(R.id.textView6);
        textViewTercerParcial = (TextView) findViewById(R.id.textView7);
        textInputLayoutPorcentaje1 = (TextInputLayout) findViewById(R.id.inputLayoutPorcentaje1);
        textInputLayoutPorcentaje2 = (TextInputLayout) findViewById(R.id.inputLayoutPorcentaje2);
        textInputLayoutPorcentaje3 = (TextInputLayout) findViewById(R.id.inputLayoutPorcentaje3);
        editTextPorcentaje1 = (EditText) findViewById(R.id.porcentaje1);
        editTextPorcentaje2 = (EditText) findViewById(R.id.porcentaje2);
        editTextPorcentaje3 = (EditText) findViewById(R.id.porcentaje3);
        textInputLayoutNota1 = (TextInputLayout) findViewById(R.id.inputLayoutNota1);
        textInputLayoutNota2 = (TextInputLayout) findViewById(R.id.inputLayoutNota2);
        textInputLayoutNota3 = (TextInputLayout) findViewById(R.id.inputLayoutNota3);
        editTextNota1 = (EditText) findViewById(R.id.nota1);
        editTextNota2 = (EditText) findViewById(R.id.nota2);
        editTextNota3 = (EditText) findViewById(R.id.nota3);
        linearLayoutButtons = (LinearLayout) findViewById(R.id.linearLayoutButtons);
        calcular = (Button) findViewById(R.id.buttonCalcular);
        SeleccionarCantidadParciales();

    }

    public void onClickLimpiar(View v){
        finish();
        Intent i = new Intent(ActividadCuanto.this, ActividadCuanto.class);
        startActivity(i);
    }

    public void onClickCalcular(View v)
    {
        int suma_porcentaje = 0;
        double resultado = 0;

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        if (opcionSeleccionada == 0){
            if (editTextPorcentaje1.getText().toString().equals("") || editTextNota1.getText().toString().equals("")){
                Toast.makeText(this, "Todos los Campos son Obligatorios", Toast.LENGTH_SHORT).show();
            }
            else{
                if (Integer.parseInt(editTextPorcentaje1.getText().toString()) > 0 && Integer.parseInt(editTextPorcentaje1.getText().toString()) <= 100){
                    if (Integer.parseInt(editTextNota1.getText().toString()) >= 0 && Integer.parseInt(editTextNota1.getText().toString()) <= 100){
                        resultado = CalcularNota1(Float.parseFloat(editTextPorcentaje1.getText().toString()), Integer.parseInt(editTextNota1.getText().toString()));
                        double porcentaje= (100 - (Float.parseFloat(editTextPorcentaje1.getText().toString()))) / 100;
                        MostrarResultados(resultado, porcentaje);
                    }
                    else{
                        Toast.makeText(this, "La nota deben estar entre 0 y 100", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "El porcentaje debe ser entre 0 y 100", Toast.LENGTH_SHORT).show();
                }
            }
        }

        else if(opcionSeleccionada == 1){
            if (editTextPorcentaje1.getText().toString().equals("") || editTextNota1.getText().toString().equals("")
                    || editTextPorcentaje2.getText().toString().equals("") || editTextNota2.getText().toString().equals("")){
                Toast.makeText(this, "Todos los Campos son Obligatorios", Toast.LENGTH_SHORT).show();
            }
            else{
                suma_porcentaje = (Integer.parseInt(editTextPorcentaje1.getText().toString()) + Integer.parseInt(editTextPorcentaje2.getText().toString()));
                if (suma_porcentaje > 0 && suma_porcentaje < 100){
                    if ((Integer.parseInt(editTextNota1.getText().toString()) >= 0 && Integer.parseInt(editTextNota1.getText().toString()) <= 100)
                            && (Integer.parseInt(editTextNota2.getText().toString()) >= 0 && Integer.parseInt(editTextNota2.getText().toString()) <= 100) ){
                        resultado = CalcularNota2(Float.parseFloat(editTextPorcentaje1.getText().toString()), Float.parseFloat(editTextPorcentaje2.getText().toString()), Integer.parseInt(editTextNota1.getText().toString()), Integer.parseInt(editTextNota2.getText().toString()));
                        double porcentaje= (100 - (Float.parseFloat(editTextPorcentaje1.getText().toString()) + Float.parseFloat(editTextPorcentaje2.getText().toString()))) / 100;
                        MostrarResultados(resultado, porcentaje);
                    }
                    else{
                        Toast.makeText(this, "Las notas deben estar entre 0 y 100", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "La suma de los porcentajes debe ser entre 0 y 100", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            if (editTextPorcentaje1.getText().toString().equals("") || editTextNota1.getText().toString().equals("")
                    || editTextPorcentaje2.getText().toString().equals("") || editTextNota2.getText().toString().equals("")
                    || editTextPorcentaje3.getText().toString().equals("") || editTextNota3.getText().toString().equals("")){
                Toast.makeText(this, "Todos los Campos son Obligatorios", Toast.LENGTH_SHORT).show();
            }
            else{
                suma_porcentaje = (Integer.parseInt(editTextPorcentaje1.getText().toString()) + Integer.parseInt(editTextPorcentaje2.getText().toString()) + Integer.parseInt(editTextPorcentaje3.getText().toString()));
                if (suma_porcentaje > 0 && suma_porcentaje < 100){
                    if ((Integer.parseInt(editTextNota1.getText().toString()) >= 0 && Integer.parseInt(editTextNota1.getText().toString()) <= 100)
                            && (Integer.parseInt(editTextNota2.getText().toString()) >= 0 && Integer.parseInt(editTextNota2.getText().toString()) <= 100)
                            && (Integer.parseInt(editTextNota3.getText().toString()) >= 0 && Integer.parseInt(editTextNota3.getText().toString()) <= 100)){
                        resultado = CalcularNota3(Float.parseFloat(editTextPorcentaje1.getText().toString()), Float.parseFloat(editTextPorcentaje2.getText().toString()), Float.parseFloat(editTextPorcentaje3.getText().toString()), Integer.parseInt(editTextNota1.getText().toString()), Integer.parseInt(editTextNota2.getText().toString()), Integer.parseInt(editTextNota3.getText().toString()));
                        double porcentaje= (100 - (Float.parseFloat(editTextPorcentaje1.getText().toString()) + Float.parseFloat(editTextPorcentaje2.getText().toString()) + Float.parseFloat(editTextPorcentaje3.getText().toString()))) / 100;
                        MostrarResultados(resultado, porcentaje);
                    }
                    else{
                        Toast.makeText(this, "Las notas deben estar entre 0 y 100", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "La suma de los porcentajes debe ser entre 0 y 100", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void MostrarResultados(double sumatoria, double porcentaje) {

        double porce = Math.rint(porcentaje*100)/100;
        double aux1, aux2;
        String mensaje1 = null, mensaje2 = null, mensaje3 = null, mensaje = "";

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("Aceptar", null);
        alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        if(sumatoria < 4.5)
        {
            aux1 = (4.5 - sumatoria);
            aux2 = Math.rint(aux1*100)/100;
            aux1 = aux2 / porce;
            aux2 = Math.rint(aux1*10)/10;
            if(obj.DevolverNota((float) aux2) == -1)
            {
                alertDialog.setTitle("Estas Fuera de escala..!!");
                alertDialog.setMessage("Llevas Acumulado: " + sumatoria + " pts.\n\"Ya no puedes pasar la materia.\"");
            }
            else
            {
                alertDialog.setTitle("Aun la puedes Pasar..!!");
                if(obj.DevolverNota((float) aux2) == 0)
                    mensaje = "Para el 5 menos de 7 pts.\n";
                else
                    mensaje += "Para el 5: "+obj.DevolverNota((float) aux2)+" pts.\n";
                if(DejarlaMasAlta(5.5, sumatoria, porce))
                {
                    aux1 = (5.5 - sumatoria);
                    aux2 = Math.rint(aux1*100)/100;
                    aux1 = aux2 / porce;
                    aux2 = Math.rint(aux1*10)/10;
                    mensaje += "Para el 6: "+obj.DevolverNota((float) aux2)+" pts.\n";
                }
                if(DejarlaMasAlta(6.5, sumatoria, porce))
                {
                    aux1 = (6.5 - sumatoria);
                    aux2 = Math.rint(aux1*100)/100;
                    aux1 = aux2 / porce;
                    aux2 = Math.rint(aux1*10)/10;
                    mensaje += "Para el 7: "+obj.DevolverNota((float) aux2)+" pts.\n";
                }
                alertDialog.setMessage("Llevas Acumulado: "+sumatoria+" pts.\n"+mensaje);
            }
        }
        else if(sumatoria >= 4.5 && sumatoria < 5.5)
        {
            alertDialog.setTitle("Ya La Pasaste..!!");
            aux1 = (5.5 - sumatoria);
            aux2 = Math.rint(aux1*100)/100;
            aux1 = aux2 / porce;
            aux2 = Math.rint(aux1*10)/10;
            if(obj.DevolverNota((float) aux2) == 0)
                mensaje = "Para el 6 menos de 7 pts.\n";
            else
                mensaje = "Para el 6: "+obj.DevolverNota((float) aux2)+" pts.\n";
            if(DejarlaMasAlta(6.5, sumatoria, porce))
            {
                aux1 = (6.5 - sumatoria);
                aux2 = Math.rint(aux1*100)/100;
                aux1 = aux2 / porce;
                aux2 = Math.rint(aux1*10)/10;
                mensaje += "Para el 7: "+obj.DevolverNota((float) aux2)+" pts.\n";
            }
            if(DejarlaMasAlta(7.5, sumatoria, porce))
            {
                aux1 = (7.5 - sumatoria);
                aux2 = Math.rint(aux1*100)/100;
                aux1 = aux2 / porce;
                aux2 = Math.rint(aux1*10)/10;
                mensaje += "Para el 8: "+obj.DevolverNota((float) aux2)+" pts.";
            }
            alertDialog.setMessage("Llevas Acumulado: "+sumatoria+" pts.\n"+mensaje);
        }
        else if(sumatoria >= 5.5 && sumatoria < 6.5)
        {
            alertDialog.setTitle("Ya La Pasaste..!!");
            aux1 = (6.5 - sumatoria);
            aux2 = Math.rint(aux1*100)/100;
            aux1 = aux2 / porce;
            aux2 = Math.rint(aux1*10)/10;
            if(obj.DevolverNota((float) aux2) == 0)
                mensaje = "Para el 7 menos de 7 pts.\n";
            else
                mensaje = "Para el 7: "+obj.DevolverNota((float) aux2)+" pts.\n";
            if(DejarlaMasAlta(7.5, sumatoria, porce))
            {
                aux1 = (7.5 - sumatoria);
                aux2 = Math.rint(aux1*100)/100;
                aux1 = aux2 / porce;
                aux2 = Math.rint(aux1*10)/10;
                mensaje += "Para el 8: "+obj.DevolverNota((float) aux2)+" pts.\n";
            }
            if(DejarlaMasAlta(8.5, sumatoria, porce))
            {
                aux1 = (8.5 - sumatoria);
                aux2 = Math.rint(aux1*100)/100;
                aux1 = aux2 / porce;
                aux2 = Math.rint(aux1*10)/10;
                mensaje += "Para el 9: "+obj.DevolverNota((float) aux2)+" pts.";
            }
            alertDialog.setMessage("Llevas Acumulado: "+sumatoria+" pts.\n"+mensaje);
        }
        else if(sumatoria >= 6.5 && sumatoria < 7.5)
        {
            alertDialog.setTitle("Ya La Pasaste..!!");
            aux1 = (7.5 - sumatoria);
            aux2 = Math.rint(aux1*100)/100;
            aux1 = aux2 / porce;
            aux2 = Math.rint(aux1*10)/10;
            if(obj.DevolverNota((float) aux2) == 0)
                mensaje = "Para el 8 menos de 7 pts.\n";
            else
                mensaje = "Para el 8: "+obj.DevolverNota((float) aux2)+" pts.\n";
            if(DejarlaMasAlta(8.5, sumatoria, porce))
            {
                aux1 = (8.5 - sumatoria);
                aux2 = Math.rint(aux1*100)/100;
                aux1 = aux2 / porce;
                aux2 = Math.rint(aux1*10)/10;
                mensaje += "Para el 9: "+obj.DevolverNota((float) aux2)+" pts.\n";
            }
            alertDialog.setMessage("Llevas Acumulado: "+sumatoria+" pts.\n"+mensaje1+"\n"+mensaje);
        }
        else if(sumatoria >= 7.5 && sumatoria < 8.5)
        {
            alertDialog.setTitle("Ya La Pasaste..!!");
            aux1 = (8.5 - sumatoria);
            aux2 = Math.rint(aux1*100)/100;
            aux1 = aux2 / porce;
            aux2 = Math.rint(aux1*10)/10;
            if(obj.DevolverNota((float) aux2) == 0)
                mensaje = "Para el 9 menos de 7 pts.";
            else
                mensaje = "Para el 9: "+obj.DevolverNota((float) aux2)+" pts.";
            alertDialog.setMessage("Llevas Acumulado: "+sumatoria+" pts.\n"+mensaje);
        }

        AlertDialog dialog = alertDialog.create();
        alertDialog.show();

        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(calcular.getWindowToken(), 0);
    }

    private boolean DejarlaMasAlta(double doub, double sumatoria, double porcen)
    {
        double aux1, aux2;

        aux1 = (doub - sumatoria);
        aux2 = Math.rint(aux1*100)/100;
        aux1 = aux2 / porcen;
        aux2 = Math.rint(aux1*10)/10;
        if(obj.DevolverNota((float) aux2) != -1 )
            return true;
        else
            return false;
    }

    private double CalcularNota1(float por_1, int not_1)
    {
        double porce_1, sumatoria;
        porce_1 = por_1 / 100;

        sumatoria = (porce_1 * obj.DevolverNumero(not_1));
        return Math.rint(sumatoria*100)/100;
    }

    private double CalcularNota2(float por_1, float por_2, int not_1, int not_2)
    {
        double porce_1, porce_2, sumatoria;
        porce_1 = por_1 / 100;
        porce_2 = por_2 / 100;

        sumatoria = (porce_1 * obj.DevolverNumero(not_1)) + (porce_2 * obj.DevolverNumero(not_2));
        return Math.rint(sumatoria*100)/100;
    }

    private double CalcularNota3(float por_1, float por_2, float por_3, int not_1, int not_2, int not_3)
    {
        double porce_1, porce_2, porce_3, sumatoria;
        porce_1 = por_1 / 100;
        porce_2 = por_2 / 100;
        porce_3 = por_3 / 100;

        sumatoria = (porce_1 * obj.DevolverNumero(not_1)) + (porce_2 * obj.DevolverNumero(not_2)) + (porce_3 * obj.DevolverNumero(not_3));
        return Math.rint(sumatoria*100)/100;
    }

    public void SeleccionarCantidadParciales(){
        final String[] items = {"2 Parciales", "3 Parciales", "4 Parciales"};

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Seleccione Cantidad de Parciales.")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        bandera = true;
                        opcionSeleccionada = item;
                    }
                });


        alertDialog.setPositiveButton("Aceptar", null);
        alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (bandera) {
                    dialog.dismiss();
                    textViewPrimerParcial.setVisibility(View.VISIBLE);
                    textInputLayoutPorcentaje1.setVisibility(View.VISIBLE);
                    textInputLayoutNota1.setVisibility(View.VISIBLE);
                    editTextPorcentaje1.setVisibility(View.VISIBLE);
                    editTextNota1.setVisibility(View.VISIBLE);
                    linearLayoutButtons.setVisibility(View.VISIBLE);
                    linearLayoutButtons.setY(150);

                    if (opcionSeleccionada == 1){
                        textViewSegundoParcial.setVisibility(View.VISIBLE);
                        textInputLayoutPorcentaje2.setVisibility(View.VISIBLE);
                        textInputLayoutNota2.setVisibility(View.VISIBLE);
                        editTextPorcentaje2.setVisibility(View.VISIBLE);
                        editTextNota2.setVisibility(View.VISIBLE);
                        linearLayoutButtons.setY(320);
                    }
                    else if (opcionSeleccionada == 2){
                        textViewSegundoParcial.setVisibility(View.VISIBLE);
                        textInputLayoutPorcentaje2.setVisibility(View.VISIBLE);
                        textInputLayoutNota2.setVisibility(View.VISIBLE);
                        editTextPorcentaje2.setVisibility(View.VISIBLE);
                        editTextNota2.setVisibility(View.VISIBLE);
                        textViewTercerParcial.setVisibility(View.VISIBLE);
                        textInputLayoutPorcentaje3.setVisibility(View.VISIBLE);
                        textInputLayoutNota3.setVisibility(View.VISIBLE);
                        editTextPorcentaje3.setVisibility(View.VISIBLE);
                        editTextNota3.setVisibility(View.VISIBLE);
                        linearLayoutButtons.setY(500);
                    }

                } else {
                    finish();
                    Toast.makeText(ActividadCuanto.this, "Debe Seleccionar una opcion de la Lista", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            }
        });

        AlertDialog dialogIcon = alertDialog.create();
        dialogIcon.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
