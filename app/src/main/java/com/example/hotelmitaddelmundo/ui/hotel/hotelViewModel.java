package com.example.hotelmitaddelmundo.ui.hotel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class hotelViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public hotelViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is hotel fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}