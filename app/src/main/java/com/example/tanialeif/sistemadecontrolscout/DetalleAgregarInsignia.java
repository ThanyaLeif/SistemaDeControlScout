package com.example.tanialeif.sistemadecontrolscout;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ChainHead;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class DetalleAgregarInsignia extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private StorageReference mStorageRef;
    private StorageTask mUploadTask;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    ImageView imgInsignia;
    Button btnSeleccionarImagenInsignia;
    Spinner spnNivel;
    FloatingActionButton btnAgregarInsignia;
    EditText txtNombre, txtDetalle;
    CheckBox txbCorporalidad, txbCreatividad, txbCaracter, txbAfectividad, txbSociabilidad, txbEspiritualidad;

    Boolean isEdit;

    final Context self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_agregar_insignia);
        inicializarFirebase();

        imgInsignia = (ImageView)findViewById(R.id.imgInsignia);
        btnSeleccionarImagenInsignia = (Button)findViewById(R.id.btnSeleccionarImagenInsignia);
        spnNivel = (Spinner) findViewById(R.id.spnNivelInsignia);
        btnAgregarInsignia = (FloatingActionButton) findViewById(R.id.btnAgregarInsignia);
        txtNombre = (EditText) findViewById(R.id.txtNombreInsignia);
        txtDetalle = (EditText) findViewById(R.id.txtDescripcionInsignia);
        txbCorporalidad = (CheckBox) findViewById(R.id.txbInsigniaCorporalidad);
        txbCreatividad = (CheckBox) findViewById(R.id.txbInsigniaCreatividad);
        txbCaracter = (CheckBox) findViewById(R.id.txbInsigniaCaracter);
        txbAfectividad = (CheckBox) findViewById(R.id.txbInsigniaAfectividad);
        txbSociabilidad = (CheckBox) findViewById(R.id.txbInsigniaSociabilidad);
        txbEspiritualidad = (CheckBox) findViewById(R.id.txbInsigniaEspiritualidad);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNivel.setAdapter(adapter);
        spnNivel.setOnItemSelectedListener(this);

        btnSeleccionarImagenInsignia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSeleccionadorImagen();
            }
        });

        btnAgregarInsignia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insignia insignia = new Insignia();
                insignia.uid = UUID.randomUUID().toString();
                insignia.nombre = txtNombre.getText().toString();
                insignia.descripcion = txtDetalle.getText().toString();
                insignia.corporalidad = txbCorporalidad.isChecked();
                insignia.creatividad = txbCreatividad.isChecked();
                insignia.caracter = txbCaracter.isChecked();
                insignia.afectividad = txbAfectividad.isChecked();
                insignia.sociabilidad = txbSociabilidad.isChecked();
                insignia.espiritualidad = txbEspiritualidad.isChecked();
                insignia.nivel = spnNivel.getSelectedItem().toString();

                if(validar()){
                    insertarInsignia(insignia);
                }
                else {
                    Toast.makeText(DetalleAgregarInsignia.this, "Datos faltantes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        isEdit = getIntent().getBooleanExtra("isEdit",false);
        if(isEdit){
            llenarDatos();
        }
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private boolean validar(){
        boolean correcto = true;
        if(txtNombre.getText().toString().trim().length() == 0 || txtDetalle.getText().toString().trim().length() == 0){
            correcto = false;
        }
        return correcto;
    }

    private void llenarDatos(){
        Bundle bundle = getIntent().getExtras();
        Insignia insignia = (Insignia) bundle.getSerializable("insignia");
        txtNombre.setText(insignia.nombre);
        txtDetalle.setText(insignia.descripcion);
        txbCorporalidad.setChecked(insignia.corporalidad);
        txbCreatividad.setChecked(insignia.creatividad);
        txbCaracter.setChecked(insignia.caracter);
        txbEspiritualidad.setChecked(insignia.espiritualidad);
        txbAfectividad.setChecked(insignia.espiritualidad);
        txbSociabilidad.setChecked(insignia.sociabilidad);

        Picasso.get().load(insignia.urlImagen).into(imgInsignia);
    }

    private void insertarInsignia(final Insignia insignia){

        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." +
                    obtenerExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            insignia.urlImagen = taskSnapshot.getDownloadUrl().toString();

                            databaseReference.child("Insignias").child(insignia.uid).setValue(insignia);
                            txtNombre.setText("");
                            txtDetalle.setText("");
                            txbCorporalidad.setChecked(false);
                            txbCreatividad.setChecked(false);
                            txbCaracter.setChecked(false);
                            txbAfectividad.setChecked(false);
                            txbSociabilidad.setChecked(false);
                            txbEspiritualidad.setChecked(false);

                            Toast.makeText(self, "Se " + (isEdit ? "actualizó" : "agregó") +" a " + insignia.nombre + " exitosamente.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(self, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(self, "Imagen no seleccionada", Toast.LENGTH_SHORT).show();
        }


    }

    private void abrirSeleccionadorImagen(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();
            imgInsignia.setImageURI(mImageUri);
        }
    }

    private String obtenerExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void subirImagen(){
        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." +
                obtenerExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(self, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(self, "Imagen no seleccionada", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
