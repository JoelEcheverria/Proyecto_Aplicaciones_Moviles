package com.example.hotelmitaddelmundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class registrar extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etNameL,etApell,etPass,etcPass,etEmail;
    private Button btnRegistrar;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        etNameL = (EditText)findViewById(R.id.etNameL);
        etApell = (EditText)findViewById(R.id.etApell);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        etcPass = (EditText)findViewById(R.id.etcPass);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("usuario");
        //myRef.setValue("Hello, World!"); establecer en la referencia
        // Read from the database



        //se activa cuando se adjunta el objeto de escucha y cada vez que cambian los datos, incluidos los secundarios
       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(null, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(null, "Failed to read value.", error.toException());
            }
        });*/


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String
                        pass = etPass.getText().toString(),
                        cpass = etcPass.getText().toString(),
                        email = etEmail.getText().toString();
               if (pass.equals(cpass)  &&  !email.isEmpty() && !pass.isEmpty()) {
                           names = etNameL.getText().toString();
                           lastanames = etApell.getText().toString();
                           emails = etEmail.getText().toString();
                            id3 = etEmail.getText().toString();
                   createAccount(email, pass);


                }else{
                   AlertDialog.Builder builder = new AlertDialog.Builder(registrar.this);
                   builder.setIcon(R.mipmap.ic_launcher)
                           .setTitle("ERROR")
                           .setMessage("Verifique si los datos son correctos")
                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dialog.dismiss();
                               }
                           });
                   AlertDialog alertDialog = builder.create();
                   alertDialog.show();
               }

            }
        });

        //datos();
    }

    public String names,  lastanames,  emails, id3;

    //crear usuario
    public Task<AuthResult> createAccount(String email, String password) {

        Task<AuthResult> autenticado = mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(registrar.this, "Autenticación exitosa",
                                    Toast.LENGTH_SHORT).show();
                            Log.d(null, "createUserWithEmail:success");

                             //FirebaseUser user = mAuth.getCurrentUser();
                           // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            //Agregar usuario a la base de datos
                            String id2 = id3.replace("@","_");
                            String id= id2.replace(".","_");
                            User user = new User();
                            user.setEmail(email);
                            user.setId(id);
                            user.setLastaname(lastanames);
                            user.setUsername(names);
                            user.setUrl("null");
                            myRef.child(user.getId()).setValue(user);
                            Log.d(null, email+lastanames+names);
                            finish();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(registrar.this);
                            builder.setIcon(R.mipmap.ic_launcher)
                                    .setTitle("ERROR")
                                    .setMessage("ERROR: "+task.getException())
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            // If sign in fails, display a message to the user.
                            Log.w(null, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(registrar.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return autenticado;
    }





    /*Actualizar informacion
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
        .setDisplayName("Jane Q. User")
        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
        .build();

user.updateProfile(profileUpdates)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "User profile updated.");
                }
            }
        });*/


    //


}