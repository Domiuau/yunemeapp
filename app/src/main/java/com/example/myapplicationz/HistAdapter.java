package com.example.myapplicationz;

import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import model.Hist;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.HistViewHolder> {
    private final List<Hist> hists;
    static boolean u;
    AdapterView.OnItemClickListener onItemClickListener;

    public void setPosition(int position) {
        this.position = position;
    }

    int position;


    //aqui estão umas coisas que eu não entendi tudo, vi um cara fazendo e adaptei para o meu
    //adaptador responsavel por gerenciar a recyclerview do historico



    public HistAdapter(List<Hist> hists)  {
        this.hists = hists;

    }

    @NonNull
    @Override
    public HistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (u == true){

            view = LayoutInflater.from(parent.getContext()).inflate(

                    R.layout.itens, parent, false

            );
        } else {
             view = LayoutInflater.from(parent.getContext()).inflate(

                    R.layout.favoritositens, parent, false
             );
        }

        return new HistViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull HistViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Hist hist = hists.get(position);
        holder.bind(hist);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                System.out.println(holder.getAdapterPosition() + " fdsfdsdfg");
                //ferramentastela.removeritem(position);
                ferramentastela fer = new ferramentastela();
                //fer.setPosition(position);

                fer.setPosition(holder.getAdapterPosition());
                fer.deletarbanco();



            }
        });

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
            hist_icone = itemView.findViewById(R.id.hist_icone);

            if (u == true){

                hist_saida = itemView.findViewById(R.id.hist_saida);
                hist_data = itemView.findViewById(R.id.hist_data);
                hist_entrada = itemView.findViewById(R.id.hist_entrada);
            }


        }

        public void bind(Hist hist) {
            hist_ferramenta.setText(hist.getFerramenta());
            hist_icone.setBackgroundResource(hist.getIcone());

            try {

                hist_saida.setText(hist.getDadossaida());
                hist_entrada.setText(hist.getDadosentrada());
                hist_data.setText(hist.getData());

            } catch (Exception e){

            }


        }
    }




}
