package com.example.hotelmitaddelmundo.ui.contactos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class contactosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public contactosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contactos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}