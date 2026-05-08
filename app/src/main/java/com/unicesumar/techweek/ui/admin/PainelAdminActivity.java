package com.unicesumar.techweek.ui.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.database.AppDatabase;
import com.unicesumar.techweek.data.entity.Participante;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * RF07 — Painel Administrativo
 * Exibe lista de inscritos, total e filtro Coffee Break
 */
public class PainelAdminActivity extends AppCompatActivity {

    private RecyclerView recyclerInscritos;
    private ParticipanteAdminAdapter adapter;
    private TextView txtTotal, txtTotalCoffee;
    private AppDatabase db;
    private boolean mostandoCoffee = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_admin);

        db = AppDatabase.getInstance(this);

        recyclerInscritos = findViewById(R.id.recycler_inscritos);
        recyclerInscritos.setLayoutManager(new LinearLayoutManager(this));

        txtTotal = findViewById(R.id.txt_total_inscritos);
        txtTotalCoffee = findViewById(R.id.txt_total_coffee);

        Button btnTodos = findViewById(R.id.btn_ver_todos);
        Button btnCoffee = findViewById(R.id.btn_ver_coffee);
        Button btnLogout = findViewById(R.id.btn_logout);

        btnTodos.setOnClickListener(v -> carregarTodos());
        btnCoffee.setOnClickListener(v -> carregarCoffeeBreak());
        btnLogout.setOnClickListener(v -> finish());

        carregarTodos();
    }

    private void carregarTodos() {
        mostandoCoffee = false;
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Participante> lista = db.participanteDao().listarTodos();
            int total = db.participanteDao().contarTotal();
            int totalCoffee = db.participanteDao().contarCoffeeBreak();
            runOnUiThread(() -> {
                txtTotal.setText("Total de inscritos: " + total);
                txtTotalCoffee.setText("Coffee Break: " + totalCoffee);
                adapter = new ParticipanteAdminAdapter(lista);
                recyclerInscritos.setAdapter(adapter);
            });
        });
    }

    private void carregarCoffeeBreak() {
        mostandoCoffee = true;
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Participante> lista = db.participanteDao().listarCoffeeBreak();
            runOnUiThread(() -> {
                adapter = new ParticipanteAdminAdapter(lista);
                recyclerInscritos.setAdapter(adapter);
            });
        });
    }
}
