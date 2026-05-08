package com.unicesumar.techweek.ui.evento;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.database.AppDatabase;
import com.unicesumar.techweek.data.entity.Palestra;
import com.unicesumar.techweek.data.entity.Participante;
import com.unicesumar.techweek.data.entity.Presenca;
import com.unicesumar.techweek.util.Validators;

import java.util.concurrent.Executors;

/**
 * RF06 — Confirmar Presença na Palestra.
 * Lógica: o admin/palestrante exibe um código único por palestra.
 * O participante informa seu RA + o código para confirmar presença.
 */
public class ConfirmarPresencaActivity extends AppCompatActivity {

    private EditText edtRa, edtCodigo;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_presenca);

        db = AppDatabase.getInstance(this);
        edtRa = findViewById(R.id.edt_ra_checkin);
        edtCodigo = findViewById(R.id.edt_codigo_checkin);
        Button btnConfirmar = findViewById(R.id.btn_confirmar_presenca);

        btnConfirmar.setOnClickListener(v -> confirmarPresenca());
    }

    private void confirmarPresenca() {
        String ra = edtRa.getText().toString().trim();
        String codigo = edtCodigo.getText().toString().trim().toUpperCase();

        if (!Validators.raValido(ra)) {
            edtRa.setError("RA inválido. Deve ter 8 dígitos.");
            return;
        }
        if (!Validators.codigoCheckinValido(codigo)) {
            edtCodigo.setError("Código inválido. Formato: TW2024-01");
            return;
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            Participante participante = db.participanteDao().buscarPorRa(ra);
            if (participante == null) {
                runOnUiThread(() -> Toast.makeText(this,
                        "RA não encontrado. Cadastre-se primeiro!", Toast.LENGTH_LONG).show());
                return;
            }

            Palestra palestra = db.palestraDao().buscarPorCodigo(codigo);
            if (palestra == null) {
                runOnUiThread(() -> Toast.makeText(this,
                        "Código de palestra inválido.", Toast.LENGTH_LONG).show());
                return;
            }

            int jaConfirmou = db.presencaDao().jaConfirmou(participante.id, palestra.id);
            if (jaConfirmou > 0) {
                runOnUiThread(() -> Toast.makeText(this,
                        "Presença já confirmada nesta palestra!", Toast.LENGTH_LONG).show());
                return;
            }

            Presenca presenca = new Presenca(participante.id, palestra.id);
            db.presencaDao().inserir(presenca);
            db.participanteDao().confirmarPresenca(participante.id);

            runOnUiThread(() -> {
                Toast.makeText(this,
                        "✅ Presença confirmada! Obrigado, " + participante.nome + "!",
                        Toast.LENGTH_LONG).show();
                finish();
            });
        });
    }
}
