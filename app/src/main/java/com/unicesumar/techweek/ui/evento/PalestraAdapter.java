package com.unicesumar.techweek.ui.evento;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.entity.Palestra;

import java.util.List;

public class PalestraAdapter extends RecyclerView.Adapter<PalestraAdapter.PalestraViewHolder> {

    public interface OnFavoritoClickListener {
        void onClick(Palestra palestra);
    }

    private final List<Palestra> palestras;
    private final OnFavoritoClickListener listener;

    public PalestraAdapter(List<Palestra> palestras, OnFavoritoClickListener listener) {
        this.palestras = palestras;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PalestraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_palestra, parent, false);
        return new PalestraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalestraViewHolder holder, int position) {
        Palestra palestra = palestras.get(position);
        holder.bind(palestra, listener);
    }

    @Override
    public int getItemCount() {
        return palestras.size();
    }

    /**
     * ViewHolder pattern: evita findViewById repetitivo, melhora performance da RecyclerView.
     */
    static class PalestraViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitulo;
        private final TextView txtPalestrante;
        private final TextView txtHorario;
        private final TextView txtLocal;
        private final TextView txtBio;
        private final ImageButton btnFavorito;

        public PalestraViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txt_titulo_palestra);
            txtPalestrante = itemView.findViewById(R.id.txt_palestrante);
            txtHorario = itemView.findViewById(R.id.txt_horario);
            txtLocal = itemView.findViewById(R.id.txt_local);
            txtBio = itemView.findViewById(R.id.txt_bio);
            btnFavorito = itemView.findViewById(R.id.btn_favorito);
        }

        public void bind(Palestra palestra, OnFavoritoClickListener listener) {
            txtTitulo.setText(palestra.titulo);
            txtPalestrante.setText(palestra.palestrante);
            txtHorario.setText(palestra.horario);
            txtLocal.setText(palestra.local);
            txtBio.setText(palestra.bioPalestrante);

            int icone = palestra.favoritada
                    ? android.R.drawable.btn_star_big_on
                    : android.R.drawable.btn_star_big_off;
            btnFavorito.setImageResource(icone);

            btnFavorito.setOnClickListener(v -> listener.onClick(palestra));
        }
    }
}
