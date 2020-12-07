package com.example.monthspendingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {
    private EditText editTextTextMultiLine;
    private EditText editTextNumberDecimal;
    private Spinner locationSelector;
    ArrayList<LatLng> locationLats;
    private final LatLng LA_LAGUNA = new LatLng(28.480386332229276, -16.31707181320373);
    private final LatLng SANTA_CRUZ = new LatLng(28.459487258125794, -16.252550005678547);
    private final LatLng LA_OROTAVA = new LatLng(28.39353824071403, -16.520846650813613);
    private final LatLng PUERTO_CRUZ = new LatLng(28.41415654533631, -16.553357134043328);
    private final LatLng BUENA_VISTA = new LatLng(28.370558862659212, -16.848310336493924);
    private final LatLng GUIA_ISORA = new LatLng(28.209560902766952, -16.77568383927963);
    private final LatLng ADEJE = new LatLng(28.127602679297468, -16.742850242896626);
    private final LatLng GRANADILLA = new LatLng(28.118935157787234, -16.577358641392763);
    private final LatLng GUIMAR = new LatLng(28.319751518924367, -16.408424103578888);
    private final LatLng CANDELARIA = new LatLng(28.3557200849151, -16.371055425807842);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        initLocations();
    }

    public LatLng getLocationLatLng(int locationIndex){
        return locationLats.get(locationIndex);
    }

    public void initLocations(){
        locationSelector = findViewById(R.id.spinnerLocations);
        locationLats = new ArrayList<>();
        locationLats.add(ADEJE);
        locationLats.add(BUENA_VISTA);
        locationLats.add(CANDELARIA);
        locationLats.add(GRANADILLA);
        locationLats.add(GUIA_ISORA);
        locationLats.add(GUIMAR);
        locationLats.add(LA_LAGUNA);
        locationLats.add(LA_OROTAVA);
        locationLats.add(PUERTO_CRUZ);
        locationLats.add(SANTA_CRUZ);
    }

    public void addBill(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"facturas",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String desc = editTextTextMultiLine.getText().toString();
        double price = Double.parseDouble(editTextNumberDecimal.getText().toString());
        String location = locationSelector.getSelectedItem().toString();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("descripcion",desc);
        contentvalues.put("precio",price);
        contentvalues.put("lugar",location);
        if(areFieldsValid()){
            if(db.insert("facturas",null,contentvalues) != -1){
                Toast.makeText(this, "Se ha añadido con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"No se ha podido añadir el registro", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Algunos campos no se han rellenado bien", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean areFieldsFilled(){
        return !editTextNumberDecimal.getText().toString().equals("") &&
                !editTextTextMultiLine.getText().toString().equals("");
    }

    public boolean isPrice(){
        return editTextNumberDecimal.getText().toString().matches("^[0-9]+[.]?[0-9]{0,2}$");
    }

    public boolean areFieldsValid(){
        return areFieldsFilled() && isPrice();
    }
}