package com.example.javier.unet;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javier.clases.TablaConversion;

public class ActividadTabla extends AppCompatActivity {


    EditText campo;
    TextView valor_tabla;
    TablaConversion obj = new TablaConversion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_tabla_layout);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.titulo_tabla);
        actionBar.setDisplayHomeAsUpEnabled(true);

        campo = (EditText) findViewById(R.id.editTextCampo);
        valor_tabla = (TextView) findViewById(R.id.textViewValor);

    }

    public void onClickConvertir(View v)
    {
        valor_tabla.setText("");
        String textoOriginal = campo.getText().toString();
        if(isNumeric(textoOriginal))
        {
            if(Integer.parseInt(textoOriginal) >= 0 && Integer.parseInt(textoOriginal) <= 100)
            {
                float resultado = obj.DevolverNumero(Integer.parseInt(textoOriginal));

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Tabla de Conversión");
                alertDialog.setMessage("El Resultado es: " + resultado);
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("Aceptar", null);
                alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                alertDialog.show();


                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(campo.getWindowToken(), 0);
            }
            else
                Toast.makeText(this, "Debe ser un número entre 0 y 100", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "El Texto Ingresado no es un Número ", Toast.LENGTH_SHORT).show();
    }

    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
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
