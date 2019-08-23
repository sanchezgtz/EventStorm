package com.tahsan.eventstorm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tahsan.eventstorm.R;
import com.tahsan.eventstorm.pojo.Evento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventoListAdapter  extends RecyclerView.Adapter<EventoListAdapter.EventoViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Evento item);
    }

    private ArrayList<Evento> mEventos = new ArrayList<>();
    private Context mContext;
    private final OnItemClickListener mlistener;

    public EventoListAdapter(Context context, ArrayList<Evento> eventos, OnItemClickListener listener) {
        mContext = context;
        mEventos = eventos;
        mlistener = listener;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.evento_item, parent, false);
        EventoViewHolder viewHolder = new EventoViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento e = mEventos.get(position);
        holder.descripcion.setText(e.getNombreEvento());
        Picasso.get().load(R.drawable.img_capoerira).into(holder.imagen);
        holder.bind(e, mlistener);
    }

    /**/
    @Override
    public int getItemCount() {
        return mEventos.size();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {

        private TextView descripcion;
        private ImageView imagen;
        public CardView layout;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.tv_nombre_item);
            imagen = itemView.findViewById(R.id.img_event_item);
            layout = itemView.findViewById(R.id.cardview_item);
        }

        public void bind(final Evento item, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
