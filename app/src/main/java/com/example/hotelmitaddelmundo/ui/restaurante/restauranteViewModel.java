package com.example.hotelmitaddelmundo.ui.restaurante;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class restauranteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public restauranteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is restaurante fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}