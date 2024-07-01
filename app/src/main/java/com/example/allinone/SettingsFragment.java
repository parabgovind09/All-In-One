package com.example.allinone;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    View view;
    TextView update_email,update_password,log_out,delete_account;
    FirebaseUser user;
    FirebaseAuth mAuth;
    EditText et_password,et_email;
    String userpassword, useremail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        update_email = (TextView) view.findViewById(R.id.update_email);
        update_password = (TextView) view.findViewById(R.id.update_password);
        log_out = (TextView) view.findViewById(R.id.log_out);
        delete_account = (TextView) view.findViewById(R.id.delete_account);

        update_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_userEmail();

            }
        });

        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reset the user password
                update_userPassword();
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                ((MainActivity) requireActivity()).replaceFragments(StartFragment.class);

            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating alert dialog box
                final AlertDialog.Builder alertdialog = new AlertDialog.Builder(requireActivity());
                alertdialog.setTitle("Delete Account Permanently?")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                assert user != null;
                                user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Toast.makeText(getActivity(),"Account Deleted Permanently",Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();
                                        ((MainActivity) requireActivity()).replaceFragments(StartFragment.class);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {

                                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel",null)
                        .create().show();
            }
        });

        return view;
    }

    public void update_userPassword()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Please enter new password");
        et_password = new EditText(getActivity());
        builder.setView(et_password);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //data extraction
//                String getPassword = et_password.getText().toString().trim();
//                et_password.setError(null);
//                if(getPassword.isEmpty() | userpassword.length()<=6){
//                    Toast.makeText(getActivity(),"Enter A Strong Password",Toast.LENGTH_SHORT).show();
//                    return;
//                }

                userpassword = et_password.getText().toString();
                finalUpdatePassword(userpassword);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                //
            }
        });
        builder.show();

    }

    public void update_userEmail()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Please enter new email address");
        et_email = new EditText(getActivity());
        builder.setView(et_email);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //data extraction
//                String getPassword = et_password.getText().toString().trim();
//                et_password.setError(null);
//                if(getPassword.isEmpty() | userpassword.length()<=6){
//                    Toast.makeText(getActivity(),"Enter A Strong Password",Toast.LENGTH_SHORT).show();
//                    return;
//                }

                useremail = et_email.getText().toString();
                finalUpdateEmail(useremail);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                //
            }
        });
        builder.show();

    }

    public void finalUpdatePassword(String finalupdateparameter){
        user.updatePassword(finalupdateparameter).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(getActivity(),"Password Updated", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).replaceFragments(LoginFragment.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finalUpdateEmail(String finalupdateparameter){
        user.updateEmail(useremail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(getActivity(),"Email-ID Updated",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //inflating are menu file
        inflater.inflate(R.menu.fire_main_menu, menu);
        //below line toget our menu item
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.main_new_note_btn:
                ((MainActivity) requireActivity()).replaceFragments(NewFireNoteFragment.class);
                break;
            case android.R.id.home:
                ((MainActivity) requireActivity()).replaceFragments(ViewFireNoteFragment.class);
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
                ((MainActivity) requireActivity()).replaceFragments(ViewFireNoteFragment.class);
            }
        });
    }
}