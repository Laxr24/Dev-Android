package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BFragment extends Fragment {


    public BFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_b, container, false);
        TextView txt = thisView.findViewById(R.id.idB);
        Button btn = thisView.findViewById(R.id.goThere);

        txt.setText("Hello by controller B");

        btn.setOnClickListener(v->{
            btn.setText("Go back now");
            Intent go = new Intent(getContext(), secondActivity.class);
            startActivity(go);
        });
        return thisView;
    }
}