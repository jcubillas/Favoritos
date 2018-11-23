package com.example.alumno.intentsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class FavoritosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Favoritos> favoritos;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        favoritos = Favoritos.listAll(Favoritos.class);

        generarLista();
    }

    @Override
    public void onItemClick(AdapterView<?> padre, View view, int posicion, long id) {

        FavoritosAdapter favoritoAdaptador = (FavoritosAdapter) padre.getAdapter();

        Favoritos favoritoElegido = favoritoAdaptador.getItem(posicion);

        //Toast.makeText(this, (CharSequence) favoritoElegido, Toast.LENGTH_SHORT).show();

        Intent favoritosIntent = getIntent();
        favoritosIntent.putExtra("FAVORITO_ELEGIDO", favoritoElegido.getUrl());

        setResult(RESULT_OK, favoritosIntent);
        finish();
    }

    public void generarLista() {
        FavoritosAdapter adaptador = new FavoritosAdapter(this, R.layout.favorito_item, favoritos);
        ListView listaOpciones = findViewById(R.id.listaDeFavoritos);
        listaOpciones.setAdapter(adaptador);
        listaOpciones.setOnItemClickListener(this);
    }
    public void eliminarFavorito(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();

        final int position = listView.getPositionForView(parentRow);

        Favoritos favoritoElegido = favoritos.get(position);
        favoritoElegido.delete();

        favoritos = Favoritos.listAll(Favoritos.class);

        generarLista();
    }
}
