package com.example.fragmentapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataModel extends ViewModel {
    public static MutableLiveData<Long> counter = new MutableLiveData<>();
}
