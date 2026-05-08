package com.unicesumar.techweek.ui.cadastro;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.database.AppDatabase;
import com.unicesumar.techweek.data.entity.Participante;
import com.unicesumar.techweek.util.Validators;

import java.util.concurrent.Executors;

/**
 * RF02 — Cadastro de Participante
 * RF08 — Coffee Break (checkbox integrado)
 */
public class CadastroActivity extends AppCompatActivity {

    private EditText edtNome, edtRa;
    private Spinner spinnerCurso, spinnerSerie;
    private CheckBox checkCoffeeBreak;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        db = AppDatabase.getInstance(this);

        edtNome = findViewById(R.id.edt_nome);
        edtRa = findViewById(R.id.edt_ra);
        spinnerCurso = findViewById(R.id.spinner_curso);
        spinnerSerie = findViewById(R.id.spinner_serie);
        checkCoffeeBreak = findViewById(R.id.check_coffee_break);

        configurarSpinners();

        Button btnCadastrar = findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(v -> realizarCadastro());
    }

    private void configurarSpinners() {
        String[] cursos = {"ADS", "Sistemas de Informação", "Ciência da Computação",
                           "Engenharia de Software", "Redes de Computadores"};
        String[] series = {"1º Semestre", "2º Semestre", "3º Semestre",
                           "4º Semestre", "5º Semestre", "6º Semestre"};

        spinnerCurso.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, cursos));
        spinnerSerie.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, series));
    }

    private void realizarCadastro() {
        String nome = edtNome.getText().toString().trim();
        String ra = edtRa.getText().toString().trim();
        String curso = spinnerCurso.getSelectedItem().toString();
        String serie = spinnerSerie.getSelectedItem().toString();
        boolean coffeeBreak = checkCoffeeBreak.isChecked();

        // Validações
        if (!Validators.nomeValido(nome)) {
            edtNome.setError("Nome deve ter ao menos 3 caracteres.");
            return;
        }
        if (!Validators.raValido(ra)) {
            edtRa.setError("RA inválido. Deve ter 8 dígitos numéricos.");
            return;
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            // Verificar se RA já está cadastrado
            Participante existente = db.participanteDao().buscarPorRa(ra);
            if (existente != null) {
                runOnUiThread(() -> edtRa.setError("RA já cadastrado!"));
                return;
            }

            Participante novo = new Participante(nome, ra, curso, serie, coffeeBreak);
            db.participanteDao().inserir(novo);

            runOnUiThread(() -> {
                Toast.makeText(this,
                        "✅ Cadastro realizado com sucesso, " + nome + "!",
                        Toast.LENGTH_LONG).show();
                finish();
            });
        });
    }
}
