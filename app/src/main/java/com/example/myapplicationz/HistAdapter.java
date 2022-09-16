package com.example.myapplicationz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import model.Coisas_hist;
import model.Hist;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.HistViewHolder> {
    private final List<Hist> hists;

    public HistAdapter(List<Hist> hists)  {
        this.hists = hists;
    }

    @NonNull
    @Override
    public HistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.itens, parent, false
        );
        return new HistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistViewHolder holder, int position) {
        Hist hist = hists.get(position);
        holder.bind(hist);

    }

    @Override
    public int getItemCount() {
        return hists.size();
    }

    public List<Hist> getHists(){
        return hists;
    }

    class HistViewHolder extends RecyclerView.ViewHolder {

        TextView hist_ferramenta;
        TextView hist_entrada;
        TextView hist_saida;
        TextView hist_data;
        ImageView hist_icone;

        public HistViewHolder(@NonNull View itemView) {
            super(itemView);
            hist_ferramenta = itemView.findViewById(R.id.hist_ferramenta);
            hist_entrada = itemView.findViewById(R.id.hist_entrada);
            hist_saida = itemView.findViewById(R.id.hist_saida);
            hist_data = itemView.findViewById(R.id.hist_data);
            hist_icone = itemView.findViewById(R.id.hist_icone);
        }

        public void bind(Hist hist) {
            hist_ferramenta.setText(hist.getFerramenta());
            hist_entrada.setText(hist.getDadosentrada());
            hist_saida.setText(hist.getDadossaida());
            hist_data.setText(hist.getData());
            hist_icone.setBackgroundResource(hist.getIcone());
        }
    }


}
