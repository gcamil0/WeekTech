package com.unicesumar.techweek.ui.projetos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.database.AppDatabase;
import com.unicesumar.techweek.data.entity.Projeto;
import com.unicesumar.techweek.util.Validators;

import java.util.concurrent.Executors;

/**
 * RF09 — Cadastro de Projeto
 */
public class CadastroProjetoActivity extends AppCompatActivity {

    private EditText edtNomeAutor, edtRaAutor, edtNomeProjeto, edtDescricao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_projeto);

        db = AppDatabase.getInstance(this);

        edtNomeAutor = findViewById(R.id.edt_nome_autor);
        edtRaAutor = findViewById(R.id.edt_ra_autor);
        edtNomeProjeto = findViewById(R.id.edt_nome_projeto);
        edtDescricao = findViewById(R.id.edt_descricao_projeto);

        Button btnCadastrar = findViewById(R.id.btn_cadastrar_projeto);
        btnCadastrar.setOnClickListener(v -> cadastrarProjeto());
    }

    private void cadastrarProjeto() {
        String nomeAutor = edtNomeAutor.getText().toString().trim();
        String ra = edtRaAutor.getText().toString().trim();
        String nomeProjeto = edtNomeProjeto.getText().toString().trim();
        String descricao = edtDescricao.getText().toString().trim();

        if (!Validators.nomeValido(nomeAutor)) {
            edtNomeAutor.setError("Nome inválido.");
            return;
        }
        if (!Validators.raValido(ra)) {
            edtRaAutor.setError("RA inválido. Deve ter 8 dígitos.");
            return;
        }
        if (Validators.campoVazio(nomeProjeto)) {
            edtNomeProjeto.setError("Informe o nome do projeto.");
            return;
        }
        if (Validators.campoVazio(descricao)) {
            edtDescricao.setError("Informe a descrição do projeto.");
            return;
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            // Um participante só pode cadastrar um projeto
            Projeto existente = db.projetoDao().buscarPorRa(ra);
            if (existente != null) {
                runOnUiThread(() -> edtRaAutor.setError("Você já cadastrou um projeto!"));
                return;
            }

            Projeto projeto = new Projeto(nomeAutor, ra, nomeProjeto, descricao);
            db.projetoDao().inserir(projeto);

            runOnUiThread(() -> {
                Toast.makeText(this,
                        "✅ Projeto \"" + nomeProjeto + "\" cadastrado com sucesso!",
                        Toast.LENGTH_LONG).show();
                finish();
            });
        });
    }
}
