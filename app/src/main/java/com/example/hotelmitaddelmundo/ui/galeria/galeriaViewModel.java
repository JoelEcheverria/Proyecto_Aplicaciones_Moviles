package com.example.hotelmitaddelmundo.ui.galeria;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class galeriaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public galeriaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is galeria fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}