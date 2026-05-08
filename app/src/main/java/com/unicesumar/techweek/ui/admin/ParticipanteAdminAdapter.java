package com.unicesumar.techweek.ui.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unicesumar.techweek.R;
import com.unicesumar.techweek.data.entity.Participante;

import java.util.List;

public class ParticipanteAdminAdapter extends RecyclerView.Adapter<ParticipanteAdminAdapter.ViewHolder> {

    private final List<Participante> lista;

    public ParticipanteAdminAdapter(List<Participante> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_participante_admin, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Participante p = lista.get(position);
        holder.txtNome.setText(p.nome);
        holder.txtRa.setText("RA: " + p.ra);
        holder.txtCurso.setText(p.curso + " — " + p.serie);
        holder.txtCoffee.setText(p.coffeeBreak ? "☕ Coffee Break" : "");
        holder.txtPresenca.setText(p.presencaConfirmada ? "✅ Presença confirmada" : "⏳ Pendente");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtRa, txtCurso, txtCoffee, txtPresenca;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txt_admin_nome);
            txtRa = itemView.findViewById(R.id.txt_admin_ra);
            txtCurso = itemView.findViewById(R.id.txt_admin_curso);
            txtCoffee = itemView.findViewById(R.id.txt_admin_coffee);
            txtPresenca = itemView.findViewById(R.id.txt_admin_presenca);
        }
    }
}
