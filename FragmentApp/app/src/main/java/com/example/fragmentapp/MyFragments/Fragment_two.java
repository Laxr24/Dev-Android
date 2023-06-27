package com.example.fragmentapp.MyFragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentapp.DataModel;
import com.example.fragmentapp.MainActivity;
import com.example.fragmentapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_two#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_two extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button back;

    public Fragment_two() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_two.
     */
    // TODO: Rename and change types and number of parameters
    public Fragment_two newInstance(String param1, String param2) {
        Fragment_two fragment = new Fragment_two();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_two, container, false);

        TextView counterTxt = fragView.findViewById(R.id.counterTxt);

        DataModel.counter.observe(getActivity(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                counterTxt.setText(""+aLong/1000);
            }
        });
        back = fragView.findViewById(R.id.button2);
        back.setOnClickListener(v->{
            Log.i("msg", "Back button clicked with "+Thread.currentThread().getName());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container_view, new Fragment_one())
                    .commit();
        });
        return fragView;
    }
}