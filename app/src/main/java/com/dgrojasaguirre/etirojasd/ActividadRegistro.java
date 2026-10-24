package com.dgrojasaguirre.etirojasd;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dgrojasaguirre.etirojasd.DataAcces.DAOEstudiante;
import com.dgrojasaguirre.etirojasd.Models.Estudiante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class ActividadRegistro extends AppCompatActivity {

    private TextInputEditText txtDni, txtNombres, txtApellidos, txtCorreo, txtPassword;
    private FloatingActionButton btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtDni = findViewById(R.id.txtDni);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtPassword);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(view -> {
            guardarObjetoEstudiante();
        });
    }

    private void guardarObjetoEstudiante() {
        if (validarcampos()) {
            return;
        }
        String vdni = txtDni.getText().toString().trim();
        String vnombres = txtNombres.getText().toString().trim();
        String vapellidos = txtApellidos.getText().toString().trim();
        String vcorreo = txtCorreo.getText().toString().trim();
        String vpass = txtPassword.getText().toString().trim();

        Estudiante oE = new Estudiante(vdni, vnombres, vapellidos, vcorreo, vpass);
        DAOEstudiante oDAOEstudiante = new DAOEstudiante(this);

        if (oDAOEstudiante.guardar(oE)) {
            Toast.makeText(this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "No se ha registrado", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarcampos() {
        txtDni.setError(null);
        txtNombres.setError(null);
        txtApellidos.setError(null);
        txtCorreo.setError(null);
        txtPassword.setError(null);

        String vdni = txtDni.getText().toString().trim();
        String vnombres = txtNombres.getText().toString().trim();
        String vapellidos = txtApellidos.getText().toString().trim();
        String vcorreo = txtCorreo.getText().toString().trim();
        String vpass = txtPassword.getText().toString().trim();

        if (vdni.isEmpty()) {
            txtDni.setError("Campo DNI es obligatorio");
            txtDni.requestFocus();
            return true;
        }

        Estudiante oE = new Estudiante(vdni, vnombres, vapellidos, vcorreo, vpass);
        if (!oE.validarDni()) {
            txtDni.setError("El DNI debe tener 8 dígitos");
            txtDni.requestFocus();
            return true;
        }

        if (vnombres.isEmpty()) {
            txtNombres.setError("Campo Nombres es obligatorio");
            txtNombres.requestFocus();
            return true;
        }

        if (vapellidos.isEmpty()) {
            txtApellidos.setError("Campo Apellidos es obligatorio");
            txtApellidos.requestFocus();
            return true;
        }

        if (vcorreo.isEmpty()) {
            txtCorreo.setError("Campo Correo es obligatorio");
            txtCorreo.requestFocus();
            return true;
        }

        if (vpass.isEmpty()) {
            txtPassword.setError("Campo Contraseña es obligatorio");
            txtPassword.requestFocus();
            return true;
        }

        return false;
    }
}
