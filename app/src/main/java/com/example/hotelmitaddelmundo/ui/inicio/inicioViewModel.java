package com.example.hotelmitaddelmundo.ui.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class inicioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public inicioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inicio fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}