package com.example.hotelmitaddelmundo.ui.contactos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotelmitaddelmundo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class contactosFragment extends Fragment {
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        private GoogleMap mMap;

        View vista;




        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng sydney = new LatLng(0.03583, -78.14784);
            mMap.addMarker(new MarkerOptions().position(sydney).title("HOTEL MITAD DEL MUNDO"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
            mMap.getUiSettings().setZoomControlsEnabled(true);
        }

    };
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }

    Button boton ;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.activity_contactos, container, false);
        boton= (Button) vista.findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/HotelQuitoBySercotel"));
                startActivity(i);

            }

        });
        Button boton2;
        boton2 = (Button)vista.findViewById(R.id.boton2);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/hotelquito?lang=es"));
                startActivity(i);

            }
        });
        Button boton3;
        boton3 = (Button)vista.findViewById(R.id.boton3);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hotelquito.com.ec/?hl=es"));
                startActivity(i);

            }
        });
        Button btnEnviar;
        btnEnviar = (Button)vista.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getContext(),"MENSAJE ENVIADO", Toast.LENGTH_LONG).show();

            }
        });

        return   vista;
    }

}

