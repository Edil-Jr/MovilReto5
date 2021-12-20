package com.example.reto2.Adaptadores;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.example.reto2.R;
import com.example.reto2.model.Producto;

import java.util.ArrayList;


public class AdaptRviewfavoritos extends RecyclerView.Adapter <AdaptRviewfavoritos.ViewHolder>{
    public AdaptRviewfavoritos(ArrayList<Producto> mData) {
        this.mData = mData;
    }

    private ArrayList<Producto> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ViewGroup parent;
    private int viewType;
    private ListView viewfavoritos;


    // data is passed into the constructor
    public AdaptRviewfavoritos(Context context, ArrayList<Producto> data) {


        this.mInflater = LayoutInflater.from(context);

        this.mData=(data);

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.itemlist , parent, false);

        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.asignardatos(mData.get(position));
       
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre;
        TextView descripcion;
        TextView precio;
        ImageView image1;

        ViewHolder(View itemView) {
            super(itemView);

            this.nombre = (TextView) itemView.findViewById(R.id.nombreP1);
            this.descripcion = (TextView) itemView.findViewById(R.id.nombreP1);
            this.precio = (TextView) itemView.findViewById(R.id.precioP1);
            this.image1 = (ImageView) itemView.findViewById(R.id.imagP1);

            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        public void asignardatos(Producto producto) {

            this.image1.setImageResource(producto.getImagen());
            this.nombre.setText(producto.getName());
            this.descripcion.setText(producto.getDescription());
            this.precio.setText(producto.getPrice().toString());

        }
    }

    // convenience method for getting data at click position
    public Producto getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;


    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}


