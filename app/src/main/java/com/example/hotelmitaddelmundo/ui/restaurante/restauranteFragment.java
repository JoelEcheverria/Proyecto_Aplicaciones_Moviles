package com.example.hotelmitaddelmundo.ui.restaurante;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelmitaddelmundo.R;
import com.example.hotelmitaddelmundo.login;

public class restauranteFragment extends Fragment {

    private restauranteViewModel restauranteViewModel;
    private Button boton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        restauranteViewModel = new ViewModelProvider(this).get(restauranteViewModel.class);
        View root = inflater.inflate(R.layout.activity_restaurante, container, false);
        boton = root.findViewById(R.id.btnSalir);



        //evento de cambio
        restauranteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), "bienvenido a restaurante", Toast.LENGTH_SHORT).show();

            }
        });
        return root;

    }


}