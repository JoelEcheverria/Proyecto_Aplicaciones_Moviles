package com.example.hotelmitaddelmundo.ui.servicios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class serviciosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public serviciosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is servicios fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}