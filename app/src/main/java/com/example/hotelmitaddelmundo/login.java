package com.example.hotelmitaddelmundo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class login extends AppCompatActivity {

    private EditText etPass, etUsu;

    private Button  btnInit;
    private TextView agregar;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPass = (EditText)findViewById(R.id.etPas);
        etUsu = (EditText)findViewById(R.id.etNam);
        btnInit = (Button) findViewById(R.id.btnInit);
        agregar = (TextView) findViewById(R.id.agregar);
        


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("usuario");
            btnInit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!etUsu.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty()) {
                    signIn(etUsu.getText().toString(), etPass.getText().toString());
                    }
                }
            });



    }
    //entrar
    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(null, "signInWithEmail:success");


                            Intent i = new Intent(getApplication(), principal.class);
                            startActivity(i);
                           // i.putExtra("usuario")

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(null, "signInWithEmail:failure", task.getException());
                            if (task.getException().getMessage().equals("The password is invalid or the user does not have a password.")){
                                Log.e(null, "La clave es invalida o no tiene");
                                Toast.makeText(login.this, "La clave es invalida o no tiene",
                                        Toast.LENGTH_SHORT).show();
                            }else if (task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                                Log.e(null, "Este usuario ha sido borrado o no ha creado una cuenta");
                                Toast.makeText(login.this, "Este usuario ha sido borrado o no ha creado una cuenta",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Log.e(null, "signInWithEmail:failure "+ task.getException().getMessage());
                                Toast.makeText(login.this, "signInWithEmail:failure "+ task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }


                            // updateUI(null);
                        }
                    }
                });

    }
    private static final String LOGTAG = "android-fcm";

    public void registro(View view){
        Intent i = new Intent(this, registrar.class);
        startActivity(i);
       /* String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(LOGTAG, "Token actualizado: " + refreshedToken);

        registerToFirebaseTopic();*/

    }
 private void registerToFirebaseTopic(){
        Log.e("Login","registering: ");
     FirebaseMessaging.getInstance().subscribeToTopic("test").addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
             if (task.isSuccessful()){
                 Log.e("Login","suscribed");
             }else{
                 Log.e("Login","No suscribed: "+task.getException());
             }
         }
     });
 }
    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


      /*  UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();*/

      /*  user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {*/

                     /*   }
                    }
                });*/



}