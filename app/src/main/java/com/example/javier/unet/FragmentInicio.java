package com.example.javier.unet;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Javier Moreno on 18/11/2015.
 */
public class FragmentInicio extends Fragment {

    TextView textView_tabla;
    TextView textView_cuanto;
    TextView textView_guarda;
    TextView textView_calcula;
    ImageButton imageButton_tabla;
    ImageButton imageButton_cuanto;
    ImageButton imageButton_guarda;
    ImageButton imageButton_calcula;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentinicio, container, false);

        Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(),
                "font/RobotoCondensed-Bold.ttf");

        textView_tabla = (TextView) view.findViewById(R.id.textView_tabla);
        textView_tabla.setTypeface(roboto);
        textView_cuanto = (TextView) view.findViewById(R.id.textView_cuanto);
        textView_cuanto.setTypeface(roboto);
        textView_guarda = (TextView) view.findViewById(R.id.textView_guarda);
        textView_guarda.setTypeface(roboto);
        textView_calcula = (TextView) view.findViewById(R.id.textView_calcula);
        textView_calcula.setTypeface(roboto);

        imageButton_tabla = (ImageButton) view.findViewById(R.id.imageButtonTabla);
        imageButton_tabla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new FragmentTabla()).commit();
            }
        });

        imageButton_cuanto = (ImageButton) view.findViewById(R.id.imageButtonCuanto);
        imageButton_cuanto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new FragmentCuanto()).commit();
            }
        });

        imageButton_guarda = (ImageButton) view.findViewById(R.id.imageButtonGuarda);
        imageButton_guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new FragmentGuarda()).commit();
            }
        });

        imageButton_calcula = (ImageButton) view.findViewById(R.id.imageButtonCalcula);
        imageButton_calcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new FragmentCalcula()).commit();
            }
        });

        return view;
    }
}
