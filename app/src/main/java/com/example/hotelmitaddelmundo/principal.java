package com.example.hotelmitaddelmundo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
//import com.google.firebase.storage.FirebaseStorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class principal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    public TextView tvCorreoElectronico,profileName;
    public ImageView fotoPerfil;
    private static final int GALLERY_INTENT=1;
    FirebaseStorage storage;
    StorageReference reference;
    private String username;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("usuario");

        /**obtener la navegacion para acceder a sus textView y modificar los datos */
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Obtiene referencia de TextView usado como header.
         fotoPerfil = navigationView.getHeaderView(0).findViewById(R.id.fotoPerfil);
         profileName = navigationView.getHeaderView(0).findViewById(R.id.tvNombreUsuario);
         tvCorreoElectronico = navigationView.getHeaderView(0).findViewById(R.id.tvCorreoElectronico);

         FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hola "+getUsername()+" Bienvenido!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /**Seleccionar foto de perfil*/
        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();
        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,GALLERY_INTENT);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
       // NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menuInicio, R.id.menuServicios, R.id.menuReservas,R.id.menuGaleria, R.id.menuRestaurante,R.id.menuContactos,R.id.menuHotel)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


       // FirebaseStorage storage = FirebaseStorage.getInstance();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //myImageView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
    //myImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

    //Especificar el tamaño de la foto perfil
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(160, 160);
    //leer autenticacion
    public void buscarDatos(String id3, String email){
        String id2 = id3.replace("@","_");
        String id= id2.replace(".","_");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("usuario").child(id);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("username").getValue().toString();
                String apellido = snapshot.child("lastaname").getValue().toString();
                try {
                    String url =snapshot.child("url").getValue().toString();
                    Picasso.get().load(url).error(R.drawable.usuario).into(fotoPerfil);
                    fotoPerfil.setLayoutParams(params);

                }catch (Exception e){
                    fotoPerfil.setImageResource(R.drawable.usuario);
                    fotoPerfil.setLayoutParams(params);
                }
                //Asigna texto a TextView.
                profileName.setText(nombre+" "+apellido);
                tvCorreoElectronico.setText(email);
                Log.e(null, "datos"+nombre+" "+apellido);
                setUsername(nombre);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            String email = currentUser.getEmail();
            String id = currentUser.getEmail();

           buscarDatos(id, email);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        final StorageReference ref = reference.child("images/"+uri.getLastPathSegment());
        UploadTask uploadTask = ref.putFile(uri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }else{
                    Toast.makeText(getApplication(), "Guardado",
                            Toast.LENGTH_SHORT).show();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    assert user != null;
                    String id3= user.getEmail();
                    String id2 = id3.replace("@","_");
                    String id= id2.replace(".","_");
                    Uri downloadUri = task.getResult();
                    actualizar(id, String.valueOf(downloadUri));

                    Log.e(null, String.valueOf(downloadUri));
                } else {
                    Toast.makeText(getApplication(), "No se encontro en link",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    public void actualizar(String id, String url) {
        FirebaseDatabase.getInstance().getReference().child("usuario").child(id).child("url").setValue(url);
        Picasso.get().load(url).error(R.drawable.usuario).into(fotoPerfil);
    }



}