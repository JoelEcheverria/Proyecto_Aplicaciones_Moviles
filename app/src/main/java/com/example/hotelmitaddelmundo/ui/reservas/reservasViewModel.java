package com.example.hotelmitaddelmundo.ui.reservas;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hotelmitaddelmundo.R;

public class reservasViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //TextView tv;
    public reservasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reservas fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}