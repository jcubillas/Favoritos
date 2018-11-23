package com.example.alumno.intentsapplication;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    final static int FAVORITO_ELEGIDO = 0;
    WebView pageView;
    EditText pageText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pageText = findViewById(R.id.inputURL);
        pageView = findViewById(R.id.vistaPaginaWeb);
    }

    public void verFavoritos(View v){
        Intent favoritosIntent = new Intent(HomeActivity.this, FavoritosActivity.class);
        startActivityForResult(favoritosIntent, FAVORITO_ELEGIDO);
    }

    public void agregarFavorito(View v){
        String url = pageText.getText().toString();

        final EditText txtUrl = new EditText(this);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        if(url.isEmpty()) {
            Toast.makeText(HomeActivity.this, "Por favor, escribe la URL que deseas agregar a favoritos.", Toast.LENGTH_SHORT).show();
        }

        else {
            alertDialog.setTitle("Agregar a favoritos")
                    .setMessage("Elija un nombre para esta URL favorita.")
                    .setView(txtUrl)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String nombreURL = txtUrl.getText().toString();
                            if(nombreURL.isEmpty()) {
                                Toast.makeText(HomeActivity.this, "Por favor, escribe un nombre para tu URL favorita.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                guardarFavorito(nombreURL);
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) { }
                    })
                    .show();
        }
    }

    public void verPagina(View v){
        String url = pageText.getText().toString();
        pageView.loadUrl(url);
        pageView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return false;
            }
        });
    }

    public void guardarFavorito(String nombre) {
        String URL = pageText.getText().toString();
        String nombreFavorito = nombre;

        Favoritos favorito = new Favoritos(nombreFavorito,URL);
        favorito.save();
        Toast.makeText(this, "Has agregado exitosamente el favorito: " + nombreFavorito, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK)
        {
            String url = data.getExtras().getString("FAVORITO_ELEGIDO");

            if(requestCode == FAVORITO_ELEGIDO)
                pageText.setText(url);

            verPagina(pageView);
        }
    }
}
