package com.unicesumar.techweek.ui.evento;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.database.AppDatabase;
import com.unicesumar.techweek.data.entity.Palestra;
import com.unicesumar.techweek.ui.admin.LoginAdminActivity;
import com.unicesumar.techweek.ui.cadastro.CadastroActivity;
import com.unicesumar.techweek.ui.faq.FaqActivity;
import com.unicesumar.techweek.ui.projetos.CadastroProjetoActivity;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PalestraAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        // Setar toolbar manualmente como ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Tech Week 2024");
        }

        recyclerView = findViewById(R.id.recycler_palestras);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fabCadastro = findViewById(R.id.fab_cadastro);
        fabCadastro.setOnClickListener(v ->
            startActivity(new Intent(this, CadastroActivity.class))
        );

        configurarBottomNav();
        carregarPalestras();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPalestras();
    }

    private void carregarPalestras() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Palestra> palestras = db.palestraDao().listarTodas();
            runOnUiThread(() -> {
                adapter = new PalestraAdapter(palestras, palestra -> {
                    // Toggle favorito em background thread
                    Executors.newSingleThreadExecutor().execute(() -> {
                        db.palestraDao().atualizarFavorito(palestra.id, !palestra.favoritada);
                        runOnUiThread(() -> {
                            palestra.favoritada = !palestra.favoritada;
                            adapter.notifyDataSetChanged();
                            String msg = palestra.favoritada ? "Palestra favoritada!" : "Favorito removido.";
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                        });
                    });
                });
                recyclerView.setAdapter(adapter);
            });
        });
    }

    private void configurarBottomNav() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_inicio) {
                return true;
            } else if (id == R.id.nav_cadastro) {
                startActivity(new Intent(this, CadastroActivity.class));
                return true;
            } else if (id == R.id.nav_projetos) {
                startActivity(new Intent(this, CadastroProjetoActivity.class));
                return true;
            } else if (id == R.id.nav_faq) {
                startActivity(new Intent(this, FaqActivity.class));
                return true;
            } else if (id == R.id.nav_mapa) {
                abrirMapa();
                return true;
            }
            return false;
        });
    }

    private void abrirMapa() {
        // Endereço da UniCesumar Londrina
        String uri = "https://maps.google.com/?q=UniCesumar+Londrina+Paraná";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_admin) {
            startActivity(new Intent(this, LoginAdminActivity.class));
            return true;
        } else if (id == R.id.action_checkin) {
            startActivity(new Intent(this, ConfirmarPresencaActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
