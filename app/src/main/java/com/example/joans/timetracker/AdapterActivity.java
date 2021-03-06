package com.example.joans.timetracker;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;



public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.TascaViewHolder> {

    private List<DadesActivitat> items;
    private OnTascaClickedListener onTascaClickedListener;


    public AdapterActivity() {
        items = new ArrayList<>();
    }
    //
    @Override
    public TascaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TascaViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_adapter, parent, false));
    }

    /**
     * Función que fija las imagenes a las actividades, dependiendo si son proyecto,
     * tarea, si está corriendo o no.
      */
    @Override
    public void onBindViewHolder(final TascaViewHolder holder, final int position) {
        final DadesActivitat dadesActivitat = items.get(position);
        if(dadesActivitat.isTasca()){
            holder.typeButton.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.tasklogo));
            if(dadesActivitat.isCronometreEngegat()) {
                holder.tascaPlay.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.pause));

            }else if(!dadesActivitat.isCronometreEngegat()){
                holder.tascaPlay.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.play));

            }
        }else{
            holder.typeButton.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.folder));
            holder.tascaPlay.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.color.transparent));
        }
        holder.tascaSettings.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.mipmap.info));
        holder.tascaTitol.setText(dadesActivitat.getNom());
        holder.tascaTimer.setText(dadesActivitat.toString());

        holder.tascaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTascaClickedListener.onPlayClicked(holder.getAdapterPosition());

            }
        });

        holder.tascaSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTascaClickedListener.onInfoClicked(holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTascaClickedListener.onItemClicked(holder.getAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void clear() {
        items.clear();
    }

    public void setItems(ArrayList<DadesActivitat> items) {
        this.items = items;
    }

    /**
     * Linkeamos evento del item de la lista al del adaptador
     * @param onTascaClickedListener Evento click
     */
    public void setOnTascaClickedListener(OnTascaClickedListener onTascaClickedListener) {
        this.onTascaClickedListener = onTascaClickedListener;
    }

    /**
     * Cambia los datos por los introducidos
     */
    public class TascaViewHolder extends RecyclerView.ViewHolder {
        TextView tascaTitol;
        ImageButton tascaPlay;
        ImageButton tascaSettings;
        ImageButton typeButton;
        TextView tascaTimer;

        public TascaViewHolder(View itemView) {
            super(itemView);
            tascaSettings = (ImageButton) itemView.findViewById(R.id.settingsButt);
            typeButton = (ImageButton) itemView.findViewById(R.id.typeButton);
            tascaPlay = (ImageButton) itemView.findViewById(R.id.imageButton);
            tascaTitol = (TextView) itemView.findViewById(R.id.actionInfo);
            tascaTimer = (TextView) itemView.findViewById(R.id.durCount);

        }
    }

    public interface OnTascaClickedListener {
        void onItemClicked(int pos);
        void onPlayClicked(int pos);
        void onInfoClicked(int pos);
    }
}