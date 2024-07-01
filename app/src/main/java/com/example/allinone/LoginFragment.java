package com.example.allinone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

    View view;
    TextInputLayout inputEmail, inputPass;
    private Button btnLogIn;
    TextView forgot_password,phone_auth;
    FirebaseUser user;
    FirebaseAuth mAuth;

    private FirebaseAuth fAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        phone_auth = (TextView) view.findViewById(R.id.phone_auth);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        forgot_password = (TextView) view.findViewById(R.id.forgot_password);
        inputEmail = (TextInputLayout) view.findViewById(R.id.input_log_email);
        inputPass = (TextInputLayout) view.findViewById(R.id.input_log_pass);
        btnLogIn = (Button) view.findViewById(R.id.btn_log);

        fAuth = FirebaseAuth.getInstance();

        phone_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) requireActivity()).replaceFragments(PhoneAuthenticationFragment.class);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View view) {
                                                   //creating alert dialog to display
                                                   final AlertDialog.Builder alertdialog = new AlertDialog.Builder(requireActivity());
                                                   alertdialog.setTitle("Reset Forgotten Password?")
                                                           .setMessage("Email with Password reset link will be sent on this Email-ID. Make sure you have entered correct Email-ID");
                                                   final EditText emailinput = new EditText(getActivity());
                                                   emailinput.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                                                   alertdialog.setView(emailinput);
                                                   alertdialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           //data validation
                                                           String useremail = emailinput.getText().toString().trim();
                                                           Pattern pattern;
                                                           Matcher matcher;
                                                           String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                                                           if (useremail.isEmpty() | !(useremail.matches(emailPattern))) {
                                                               Toast.makeText(getActivity(), "Please enter valid Email Address", Toast.LENGTH_SHORT).show();
                                                               return;
                                                           }
                                                           mAuth.sendPasswordResetEmail(useremail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                               @Override
                                                               public void onSuccess(Void unused) {
                                                                   Toast.makeText(getActivity(), "A Password Reset Email is sent to your Email Address.", Toast.LENGTH_SHORT).show();
                                                                   ((MainActivity) requireActivity()).replaceFragments(LoginFragment.class);

                                                               }
                                                           }).addOnFailureListener(new OnFailureListener() {
                                                               @Override
                                                               public void onFailure(@NonNull @NotNull Exception e) {

                                                                   Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                               }
                                                           });
                                                       }
                                                   }).setNegativeButton("Cancel", null).create().show();

                                               }
                                               });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lEmail = Objects.requireNonNull(inputEmail.getEditText()).getText().toString().trim();
                String lPass = Objects.requireNonNull(inputPass.getEditText()).getText().toString().trim();

                if (!TextUtils.isEmpty(lEmail) && !TextUtils.isEmpty(lPass)) {
                    logIn(lEmail, lPass);
                }

            }
        });
        return view;
    }

    private void logIn(String email, String password){

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Logging in, please wait...");
        progressDialog.show();

        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if (task.isSuccessful()) {

                            ((MainActivity) requireActivity()).replaceFragments(ViewFireNoteFragment.class);

                            Toast.makeText(getActivity(), "Sign in successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "ERROR: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case android.R.id.home:
                ((MainActivity) requireActivity()).replaceFragments(StartFragment.class);
                break;
        }

        return true;
    }

    //handling on back button pressed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity) requireActivity()).replaceFragments(StartFragment.class);
            }
        });
    }
}