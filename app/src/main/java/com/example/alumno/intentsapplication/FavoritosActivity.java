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

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Busco a todos los favoritos de la base de datos.
        List<Favoritos> favoritos = Favoritos.listAll(Favoritos.class);

        FavoritosAdapter adaptador = new FavoritosAdapter(this, R.layout.favorito_item, favoritos);

        ListView listaOpciones = (ListView)findViewById(R.id.listaDeFavoritos);

        listaOpciones.setAdapter(adaptador);


        listaOpciones.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> padre, View view, int posicion, long id) {

        FavoritosAdapter adaptador = (FavoritosAdapter) padre.getAdapter();

        Favoritos favorito_seleccionado = adaptador.getItem(posicion);

        Toast.makeText(this, (CharSequence) favorito_seleccionado, Toast.LENGTH_SHORT).show();


        /*Intent miIntent = getIntent();
        miIntent.putExtra("FAVORITO_ELEGIDO", favorito_elegido.getUrl());

        setResult(RESULT_OK, miIntent);
        finish();*/
    }

}
