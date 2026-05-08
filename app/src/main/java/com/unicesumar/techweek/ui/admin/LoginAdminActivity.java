package com.unicesumar.techweek.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unicesumar.techweek.R;

public class LoginAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        EditText edtUsuario = findViewById(R.id.edt_usuario_admin);
        EditText edtSenha   = findViewById(R.id.edt_senha_admin);
        Button   btnEntrar  = findViewById(R.id.btn_entrar_admin);

        btnEntrar.setOnClickListener(v -> {
            String usuario = edtUsuario.getText().toString().trim();
            String senha   = edtSenha.getText().toString().trim();

            if ("admin".equals(usuario) && "techweek2024".equals(senha)) {
                startActivity(new Intent(this, PainelAdminActivity.class));
                finish();
            } else {
                Toast.makeText(this,
                    "Credenciais invalidas. Use: admin / techweek2024",
                    Toast.LENGTH_LONG).show();
            }
        });
    }
}
