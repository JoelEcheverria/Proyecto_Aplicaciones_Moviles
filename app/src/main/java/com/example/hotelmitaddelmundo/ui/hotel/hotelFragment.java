package com.example.hotelmitaddelmundo.ui.hotel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelmitaddelmundo.R;

public class hotelFragment extends Fragment {

    private hotelViewModel hotelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hotelViewModel = new ViewModelProvider(this).get(hotelViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hotel, container, false);

        hotelViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), "bienvenido a hotel", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}