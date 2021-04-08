package com.example.hotelmitaddelmundo.ui.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelmitaddelmundo.R;
import com.example.hotelmitaddelmundo.login;

public class inicioFragment extends Fragment {

    private inicioViewModel inicioViewModel;
    private Button boton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inicioViewModel = new ViewModelProvider(this).get(inicioViewModel.class);
        View root = inflater.inflate(R.layout.activity_inicio, container, false);
        boton = root.findViewById(R.id.btnSalir);

        //en caso de hacer botones con eventos
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }


        });

        //evento de cambio
        inicioViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), "bienvenido a inicio", Toast.LENGTH_SHORT).show();

            }
        });
        return root;

    }

    //muestra funcional de los fragement
    public void salir(){
        Intent i = new Intent(getActivity(),login.class);
        startActivity(i);
        Toast.makeText(getActivity(), "boton funcionando correctamente", Toast.LENGTH_SHORT).show();
    }
}