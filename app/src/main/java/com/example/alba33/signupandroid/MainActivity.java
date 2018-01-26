package com.example.alba33.signupandroid;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, name, email, password, tempatlahir, alamat;
    Spinner pekerjaan, kelamin;
    DatePicker dtLahir;
    Button submit, exit;
    TextView halo, Username, Email, Password, tempatLahir, Alamat, Pekerjaan, Kelamin, Lahir;
    Context context = this;

    String item;
    String item2;
    String [] dataPekerjaan = new String[] {
            "Pelajar", "Guru", "PNS"
    };

    String [] dataKelamin = new String[] {
            "Pria", "Wanita", "None"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.etUsername);
        name = (EditText)findViewById(R.id.etNama);
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        tempatlahir = (EditText)findViewById(R.id.etTempatLahir);
        alamat = (EditText)findViewById(R.id.etAlamat);
        dtLahir = (DatePicker)findViewById(R.id.dpTTL);
        submit = (Button)findViewById(R.id.btnSubmit);
        exit = (Button)findViewById(R.id.btnExit);
        halo = (TextView)findViewById(R.id.txtHalo);
        Username = (TextView)findViewById(R.id.txtUsername);
        Email = (TextView)findViewById(R.id.txtEmail);
        Password = (TextView)findViewById(R.id.txtPassword);
        tempatLahir = (TextView)findViewById(R.id.txtTempatLahir);
        Alamat = (TextView)findViewById(R.id.txtAlamat);
        Pekerjaan = (TextView)findViewById(R.id.txtPekerjaan);
        Kelamin = (TextView)findViewById(R.id.txtKelamin);
        Lahir = (TextView)findViewById(R.id.txtLahir);

        //Spinner Pekerjaan
        pekerjaan = (Spinner)findViewById(R.id.spinPekerjaan);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataPekerjaan);
        pekerjaan.setAdapter(adapter);
        pekerjaan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner Kelamin
        kelamin = (Spinner)findViewById(R.id.spinKelamin);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataKelamin);
        kelamin.setAdapter(adapter2);
        kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent2, View view, int position2, long id2) {
                item2 = parent2.getItemAtPosition(position2).toString();

                if (item2 == "None"){
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.activity_prompt);
                    dialog.setTitle("Warning !!");
                    Button btnOK = (Button)dialog.findViewById(R.id.btnOK);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String sUsername = username.getText().toString();
               String sNama = name.getText().toString();
               String sEmail = email.getText().toString();
               String sPassword = password.getText().toString();
               String sTempatLahir = tempatlahir.getText().toString();
               String sAlamat = alamat.getText().toString();

               if (sUsername.isEmpty() && sNama.isEmpty() && sEmail.isEmpty() && sPassword.isEmpty() && sTempatLahir.isEmpty() && sAlamat.isEmpty()) {

                   name.setError("Cant Not Be Empty");
                   username.setError("Cant Not Be Empty");
                   email.setError("Cant Not Be Empty");
                   password.setError("Cant Not Be Empty");
                   tempatlahir.setError("Cant Not Be Empty");
                   alamat.setError("Cant Not Be Empty");

               } else {
                   AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                   alertDialog.setTitle("Confirmation");
                   alertDialog.setMessage("Are You Sure ??")
                           .setCancelable(false)
                           .setPositiveButton("Sudah", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                                   String aUser = name.getText().toString();
                                   String aFullname = username.getText().toString();
                                   String aEmail = email.getText().toString();
                                   String aPass = password.getText().toString();
                                   String aPlace = tempatlahir.getText().toString();
                                   String aAddress = alamat.getText().toString();

                                   halo.setText("Halo " + aUser);
                                   Username.setText(aFullname);
                                   Email.setText(aEmail);
                                   Password.setText(aPass);
                                   tempatLahir.setText(aPlace);
                                   Alamat.setText(aAddress);
                                   Pekerjaan.setText(item);
                                   Kelamin.setText(item2);
                                   Lahir.setText("Date : " + dtLahir.getDayOfMonth() + ""+ dtLahir.getMonth() +""+ dtLahir.getYear());

                                   if (item2 == "L") {
                                       Kelamin.setText("Laki - Laki");
                                   } else if (item2 == "P") {
                                       Kelamin.setText("Perempuan");
                                   }


                               }
                           })
                           .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {
                                   dialogInterface.cancel();
                               }
                           });
                   AlertDialog alert = alertDialog.create();
                   alert.show();
               }
           }
       });
    }
}
