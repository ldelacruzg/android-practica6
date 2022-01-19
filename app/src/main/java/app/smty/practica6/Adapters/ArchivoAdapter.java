package app.smty.practica6.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.smty.practica6.Models.Archivo;
import app.smty.practica6.R;

public class ArchivoAdapter extends RecyclerView.Adapter<ArchivoAdapter.ArchivoVH> {
    int layout;
    List<Archivo> archivoList;
    OnItemClickListener onItemClickListener;

    public ArchivoAdapter(int layout, List<Archivo> archivoList, OnItemClickListener onItemClickListener) {
        this.layout = layout;
        this.archivoList = archivoList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ArchivoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
        return new ArchivoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArchivoVH holder, int position) {
        holder.bind(archivoList.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return archivoList.size();
    }

    public class ArchivoVH extends RecyclerView.ViewHolder {
        TextView textViewName, textViewDate;
        ImageView imageViewPDF;

        public ArchivoVH(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            imageViewPDF = itemView.findViewById(R.id.imageViewPDF);
        }

        public void bind(Archivo archivo, OnItemClickListener onItemClickListener) {
            textViewName.setText(archivo.getNombre());
            textViewDate.setText(archivo.getFecha());
            imageViewPDF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(archivo, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Archivo archivo, int position);
    }
}
