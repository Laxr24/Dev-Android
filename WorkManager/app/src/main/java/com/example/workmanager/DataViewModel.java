package com.example.workmanager;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    public MutableLiveData<String> name = new MutableLiveData<String>("");
}
