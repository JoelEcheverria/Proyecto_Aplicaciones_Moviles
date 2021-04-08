package com.example.hotelmitaddelmundo.ui.servicios;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmitaddelmundo.R;
import com.example.hotelmitaddelmundo.activity.DetailActivity;
import com.example.hotelmitaddelmundo.adaptador.RecyclerAdapter;
import com.example.hotelmitaddelmundo.model.ItemList;

import java.util.ArrayList;
import java.util.List;

public class serviciosFragment extends Fragment implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {

    private serviciosViewModel serviciosViewModel;
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        serviciosViewModel =
                new ViewModelProvider(this).get(serviciosViewModel.class);
        View root = inflater.inflate(R.layout.activity_main, container, false);
        rvLista = root.findViewById(R.id.rvLista);
        svSearch = root.findViewById(R.id.svSearch);

        initValues();
        initListener();

        serviciosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(getActivity(), "bienvenido a servicios", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }


    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("GIMNASIO CONCERTADO", "Póngase en forma en el Wellness & Spa y acceda al completo gimnasio y a las clases de aeróbic dirigidas.", R.drawable.gimnasioservicios));
        itemLists.add(new ItemList("SERVICIO DE LAVANDERÍA", "Disponga de nuestro servicio de lavandería cuando lo necesite.", R.drawable.lavanderiaserivicios));
        itemLists.add(new ItemList("SALÓN DE BELLEZA", "Cuídese con nuestros exclusivos tratamientos corporales.", R.drawable.salonbellezaservicios));
        itemLists.add(new ItemList("JARDÍN", "Pasee por el hermoso y extenso jardín del hotel.", R.drawable.jardinservicios));
        itemLists.add(new ItemList("PISCINA EXTERIOR CLIMATIZADA", "Libérese del estrés en nuestra extensa piscina climatizada.", R.drawable.piscinaservicios));
        itemLists.add(new ItemList("SAUNA", "Olvídese de las tensiones en nuestra zona wellness.", R.drawable.saunaservicios));
        itemLists.add(new ItemList("WIFI SIN COSTO ADICIONAL", "Conéctese al servicio de wifi sin costo adicional en todas las instalaciones del hotel", R.drawable.wifiservicios));
        itemLists.add(new ItemList("RECEPCIÓN 24 HORAS", "¿Le ayudamos? Estamos encantados de atenderle las 24h", R.drawable.recepcionservicios));
        itemLists.add(new ItemList("DISCOTECA",  "¿Le apetece bailar? Nuestra animada discoteca le espera con el mejor ambiente.", R.drawable.discotecaservicios));
        itemLists.add(new ItemList("PARQUEADERO SIN COSTO ADICIONAL", "Aparque en el exclusivo parqueadero de cortesía. Disponemos de estacionamiento para autobuses.", R.drawable.parqueaderoservicios));
        itemLists.add(new ItemList("SERVICIO DE MASAJES", "Disfrute de masajes con expertos extranjeros.", R.drawable.masajeriaservicios));
        itemLists.add(new ItemList("SERVICIO RESTAURANTE", "Platos a la carta y marisquería.", R.drawable.restauranteservicios));

        return itemLists;
    }

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }





}