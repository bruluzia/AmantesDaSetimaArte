package com.example.amantesdasetimaarte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.*;
import java.util.ArrayList;

public class ListaFilmes extends AppCompatActivity {

    ListView listaFilmes;
    private ArrayList<Filme> arrayListFilmes;
    private ArrayAdapter<Filme> arrayAdapterFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        listaFilmes = findViewById(R.id.listaFilmes);
        registerForContextMenu(listaFilmes);

        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filmeEnviado = (Filme) arrayAdapterFilmes.getItem(position);
                Bundle param = new Bundle();
                param.putString("ch_id", filmeEnviado.getId_filme());
                param.putString("ch_titulo", filmeEnviado.getTitulo());
                param.putString("ch_diretor", filmeEnviado.getDiretor());
                param.putString("ch_duracao", filmeEnviado.getDuracao());
                param.putString("ch_genero", filmeEnviado.getGenero());
                param.putString("ch_idade", filmeEnviado.getIdade());
                param.putString("ch_lancamento", filmeEnviado.getLancamento());
                param.putString("ch_sinopse", filmeEnviado.getSinopse());
                param.putString("ch_distribuidor", filmeEnviado.getDistribuidor());
                param.putString("ch_nota", filmeEnviado.getNota());

                Intent it = new Intent(ListaFilmes.this, InfoFilme.class);
                it.putExtras(param);
                startActivity(it);
            }
        });
    }

    public void preencheLista() throws IOException {
        BDHelper favoritosBD = new BDHelper(ListaFilmes.this);

        /*if(favoritosBD.verificaBanco()) {
            InputStreamReader filmeInput = new InputStreamReader(getAssets().open("filmes.txt"), "UTF-8");
            BufferedReader filmeBuffer = new BufferedReader(filmeInput);
            String filmeLinha;
            while ((filmeLinha = filmeBuffer.readLine()) != null) {
                String[] f = filmeLinha.split("','");
                Filme filmeLeitura = new Filme(f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], "0");
                favoritosBD.insereFilme(filmeLeitura);
            }
            filmeBuffer.close();
        }*/
        arrayListFilmes = favoritosBD.selectAllFilmes();
        favoritosBD.close();
        if(listaFilmes != null){
            arrayAdapterFilmes = new ArrayAdapter<Filme>(ListaFilmes.this, android.R.layout.simple_list_item_1,
                    arrayListFilmes);
            listaFilmes.setAdapter(arrayAdapterFilmes);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            preencheLista();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
