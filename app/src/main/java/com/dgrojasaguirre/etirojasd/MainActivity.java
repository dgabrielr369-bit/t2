package com.dgrojasaguirre.etirojasd;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView lbBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lbBienvenido = findViewById(R.id.lbBienvenido);
        String nombre = getIntent().getStringExtra("nombre_estudiante");
        if (nombre != null && !nombre.isEmpty()) {
            lbBienvenido.setText("Bienvenido " + nombre);
        }

        BottomNavigationView oNavegacion = findViewById(R.id.botonNavegacion);
        oNavegacion.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itemInicio) {
                return true;
            }
            if (item.getItemId() == R.id.itemSalir) {
                finishAffinity();
                return true;
            }
            return false;
        });
    }
}
