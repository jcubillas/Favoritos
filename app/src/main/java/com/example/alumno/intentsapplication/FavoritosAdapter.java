package com.example.alumno.intentsapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FavoritosAdapter extends ArrayAdapter<Favoritos> {

    Activity context;
    List<Favoritos> favoritos;

    public FavoritosAdapter(@NonNull Activity context, int idListItem, @NonNull List<Favoritos> favoritos) {
        super(context, idListItem, favoritos);
        this.context = context;
        this.favoritos = favoritos;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View favorito_item = inflater.inflate(R.layout.favorito_item, null);

        TextView nombreItem = favorito_item.findViewById(R.id.nombreItem);

        TextView URLItem = favorito_item.findViewById(R.id.URLItem);

        Favoritos favorito = getItem(position);

        nombreItem.setText(favorito.getNombre());
        URLItem.setText(favorito.getUrl());

        return favorito_item;
    }

}