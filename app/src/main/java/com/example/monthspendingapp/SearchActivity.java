package com.example.monthspendingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private TextView tvLugar;
    private TextView tvPrecio;
    private EditText etIDSearch;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tvLugar = findViewById(R.id.tvLugar);
        tvPrecio = findViewById(R.id.tvPrecio);
        etIDSearch = findViewById(R.id.etIDSearch);
        tvDescripcion = findViewById(R.id.tvDescripcion);

    }


    /**
     * Searchs the ID value, and shows in the activity the values of that registry.
     * @param view
     */
    public void search(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"facturas",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int _id = Integer.parseInt(etIDSearch.getText().toString());
        Cursor fila = db.rawQuery("select descripcion,precio,lugar from facturas where id = " + _id,null);
        if(isID()){
            if(fila.moveToFirst()){
                tvDescripcion.setText(fila.getString(0));
                tvPrecio.setText(fila.getString(1));
                tvLugar.setText(fila.getString(2));
                Toast.makeText(this, "Encontrado con éxito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se ha encontrado ningún resultado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ID no válido", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Checks if the ID is properly written
     * @return true - if ID is properly filled, false otherwise
     */
    public boolean isID() {
        return etIDSearch.getText().toString().matches("^[0-9]+$");
    }
}