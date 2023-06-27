package com.example.livedata.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class HomeViewModel extends ViewModel {
    public static MutableLiveData<Integer> c_num = new MutableLiveData<Integer>(0);
    public static MutableLiveData<Integer> r_num = new MutableLiveData<Integer>(0);
    public static int t_num = 0;
}
