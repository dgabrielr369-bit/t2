package com.dgrojasaguirre.etirojasd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.dgrojasaguirre.etirojasd.DataAcces.DAOEstudiante;
import com.dgrojasaguirre.etirojasd.Models.Estudiante;
import com.google.android.material.textfield.TextInputEditText;

public class ActividadLogin extends AppCompatActivity {

    private TextInputEditText txtDniLogin, txtPasswordLogin;
    private Button btnIngresar, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtDniLogin = findViewById(R.id.txtDniLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnIngresar.setOnClickListener(view -> {
            ingresarSistema();
        });

        btnRegistrar.setOnClickListener(view -> {
            Intent oIntento = new Intent(this, ActividadRegistro.class);
            startActivity(oIntento);
        });
    }

    private void ingresarSistema() {
        if (validarcampos()) {
            return;
        }
        String vdni = txtDniLogin.getText().toString().trim();
        String vpass = txtPasswordLogin.getText().toString().trim();

        DAOEstudiante oDAO = new DAOEstudiante(this);
        String nombreCompleto = oDAO.buscar(vdni, vpass);

        if (!nombreCompleto.isEmpty()) {
            Intent oIntento = new Intent(this, MainActivity.class);
            oIntento.putExtra("nombre_estudiante", nombreCompleto);
            startActivity(oIntento);
        } else {
            Toast.makeText(this, "DNI o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarcampos() {
        txtDniLogin.setError(null);
        txtPasswordLogin.setError(null);

        String vdni = txtDniLogin.getText().toString().trim();
        String vpass = txtPasswordLogin.getText().toString().trim();

        if (vdni.isEmpty()) {
            txtDniLogin.setError("Campo DNI es obligatorio");
            txtDniLogin.requestFocus();
            return true;
        }

        Estudiante oE = new Estudiante(vdni, "", "", "", vpass);
        if (!oE.validarDni()) {
            txtDniLogin.setError("El DNI debe tener 8 dígitos");
            txtDniLogin.requestFocus();
            return true;
        }

        if (vpass.isEmpty()) {
            txtPasswordLogin.setError("Campo Contraseña es obligatorio");
            txtPasswordLogin.requestFocus();
            return true;
        }

        return false;
    }
}
