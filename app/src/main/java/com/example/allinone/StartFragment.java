package com.example.allinone;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class StartFragment extends Fragment {

    View view;
    private Button btnReg, btnLog;

    private FirebaseAuth fAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start, container, false);
        btnReg = (Button) view.findViewById(R.id.start_reg_btn);
        btnLog = (Button) view.findViewById(R.id.start_log_btn);

        fAuth = FirebaseAuth.getInstance();

        updateUI();

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        return view;
    }

    private void register(){
        ((MainActivity) requireActivity()).replaceFragments(RegisterFragment.class);
    }

    private void login(){
        ((MainActivity) requireActivity()).replaceFragments(LoginFragment.class);
    }

    private void updateUI(){
        if (fAuth.getCurrentUser() != null){
            ((MainActivity) requireActivity()).replaceFragments(ViewFireNoteFragment.class);
            Toast.makeText(getActivity(), "Welcome "+ Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    //handling on back button pressed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) requireActivity()).replaceFragments(HomeFragment.class);
            }
        });
    }

}