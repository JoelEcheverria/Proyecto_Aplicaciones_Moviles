package com.example.hotelmitaddelmundo.ui.galeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hotelmitaddelmundo.R;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;


public class galeriaFragment extends Fragment {
    View vista;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.activity_galeria, container, false);
        ImageCarousel carousel = vista.findViewById(R.id.carousel);
        ImageCarousel carouselPisina = vista.findViewById(R.id.carouselPisina);
        ImageCarousel carouselRestaurant = vista.findViewById(R.id.carouselRestaurant);

        List<CarouselItem> list = new ArrayList<>();

        //PARA PEGAR URLS
        /*list.add(
                new CarouselItem(
                        "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080",
                        "Photo by Aaron Wu on Unsplash"
                )
        );*/

        //CAROUSEL HABITACIONES
        list.add(
                new CarouselItem(R.drawable.unohabitacion, "Habitación Doble")
        );
        list.add(
                new CarouselItem(R.drawable.doshabitacion, "Habitación Doble")
        );
        list.add(
                new CarouselItem(R.drawable.treshabitacion, "Habitación Simple")
        );
        list.add(
                new CarouselItem(R.drawable.cuatrohabitacion, "Habitación Simple")
        );


        //CAROUSEL PISINA

        List<CarouselItem> listPisina = new ArrayList<>();
        listPisina.add(
                new CarouselItem(R.drawable.cincopisina, "Pisina Temperada")
        );
        listPisina.add(
                new CarouselItem(R.drawable.seispisina, "Pisina Temperada")
        );

        //CAROUSEL RESTAURANT
        List<CarouselItem> listRestaurant = new ArrayList<>();
        listRestaurant.add(
                new CarouselItem(R.drawable.sieteres, "Restaurant")
        );
        listRestaurant.add(
                new CarouselItem(R.drawable.ochores, "Restaurant")
        );
        carousel.addData(list);
        carouselPisina.addData(listPisina);
        carouselRestaurant.addData(listRestaurant);



        return vista;
    }
}