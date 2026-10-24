package com.dgrojasaguirre.etirojasd.DataAcces;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dgrojasaguirre.etirojasd.Models.Estudiante;

public class DAOEstudiante {
    private String nombreBD;
    private int version;
    private Activity actividad;
    private BDHelper oHelper;

    public DAOEstudiante(Activity actividad) {
        nombreBD = "BDEstudiante";
        version = 1;
        this.actividad = actividad;
        oHelper = new BDHelper(actividad, nombreBD, null, version);
    }

    public boolean guardar(Estudiante oE) {
        SQLiteDatabase oBD = oHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues oValue = new ContentValues();
            oValue.put("dni", oE.getDni());
            oValue.put("nombres", oE.getNombres());
            oValue.put("apellidos", oE.getApellidos());
            oValue.put("correo", oE.getCorreo());
            oValue.put("contrasena", oE.getContrasena());

            long fila = oBD.insert("Estudiante", null, oValue);
            oBD.close();

            if (fila > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String buscar(String dni, String contrasena) {
        String nombreCompleto = "";
        SQLiteDatabase oBD = oHelper.getReadableDatabase();
        String sql = "SELECT * FROM Estudiante WHERE dni=? AND contrasena=?";
        Cursor oRegistros = oBD.rawQuery(sql, new String[]{dni, contrasena});

        if (oRegistros.moveToFirst()) {
            String vnombres = oRegistros.getString(2);
            String vapellidos = oRegistros.getString(3);
            nombreCompleto = vapellidos + " " + vnombres;
        }
        oRegistros.close();
        oBD.close();
        return nombreCompleto;
    }
}
