package com.unicesumar.techweek.ui.faq;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.unicesumar.techweek.R;

/**
 * RF05 — FAQ com accordion simples (expandir/recolher ao tocar)
 */
public class FaqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        configurarAcordeon(R.id.card_faq1, R.id.txt_resp1);
        configurarAcordeon(R.id.card_faq2, R.id.txt_resp2);
        configurarAcordeon(R.id.card_faq3, R.id.txt_resp3);
        configurarAcordeon(R.id.card_faq4, R.id.txt_resp4);
        configurarAcordeon(R.id.card_faq5, R.id.txt_resp5);
        configurarAcordeon(R.id.card_faq6, R.id.txt_resp6);
    }

    private void configurarAcordeon(int cardId, int respostaId) {
        CardView card = findViewById(cardId);
        TextView resposta = findViewById(respostaId);
        card.setOnClickListener(v -> {
            if (resposta.getVisibility() == View.GONE) {
                resposta.setVisibility(View.VISIBLE);
            } else {
                resposta.setVisibility(View.GONE);
            }
        });
    }
}
