package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyNameFragment extends Fragment {
    private Button bt_tocal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleBt();
    }

    private void findViews(View view) {
        bt_tocal = view.findViewById(R.id.bt_tocal);
    }
    private void handleBt() {
        bt_tocal.setOnClickListener(view->{
            Navigation.findNavController(view).navigate(R.id.action_myNameFragment_to_calculatorFragment);
        });
    }
}