package com.dgrojasaguirre.etirojasd.DataAcces;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    private String tabla_Estudiante = "CREATE TABLE Estudiante(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "dni VARCHAR(8) NOT NULL," +
            "nombres VARCHAR(50) NOT NULL," +
            "apellidos VARCHAR(50) NOT NULL," +
            "correo VARCHAR(80) NOT NULL," +
            "contrasena VARCHAR(50) NOT NULL)";

    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_Estudiante);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Estudiante");
        db.execSQL(tabla_Estudiante);
    }
}
