package com.example.a04_creacionelementosporcodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a04_creacionelementosporcodigo.databinding.ActivityAddAlumnoBinding;
import com.example.a04_creacionelementosporcodigo.modelos.Alumno;

public class AddAlumnoActivity extends AppCompatActivity {

    //  1. Activa el Binding para la Activity
    private ActivityAddAlumnoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Construye el Binding
        binding = ActivityAddAlumnoBinding.inflate(getLayoutInflater());

        // Asocia el Binding al Activity
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alumno alumno = createAlumno();
                if (alumno != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("ALUMNO", alumno);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(AddAlumnoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private Alumno createAlumno() {
        if (binding.txtNombreAddAlumno.getText().toString().isEmpty() || binding.txtApellidosAddAlumno.getText().toString().isEmpty()){
            return null;
        }
        if (binding.spCiclosAddAlumno.getSelectedItemPosition() == 0){
            return null;
        }
        if (!binding.rbGrupoAAddAlumno.isChecked() && !binding.rbGrupoBAddAlumno.isChecked() && !binding.rbGrupoCAddAlumno.isChecked()){
            return null;
        }

        String ciclo = binding.spCiclosAddAlumno.getSelectedItem().toString();
        RadioButton rb = findViewById(binding.rgGruposAddAlumno.getCheckedRadioButtonId());
        char grupo = rb.getText().charAt(0);

        return new Alumno(binding.txtNombreAddAlumno.getText().toString(), binding.txtApellidosAddAlumno.getText().toString(), ciclo, grupo);
    }
}