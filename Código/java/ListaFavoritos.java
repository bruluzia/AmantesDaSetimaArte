package com.example.amantesdasetimaarte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListaFavoritos extends AppCompatActivity {

    ListView listaFavoritos;
    private ArrayList<Filme> arrayListFavoritos;
    private ArrayAdapter<Filme> arrayAdapterFavoritos;
    int idExcluir, idCancelar;
    private Filme filme = new Filme();
    private boolean a = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_favoritos);
        listaFavoritos = findViewById(R.id.listaFavoritos);
        registerForContextMenu(listaFavoritos);
        try {
            preencheLista();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listaFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Filme filmeEnviado = (Filme) arrayAdapterFavoritos.getItem(i);
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

                Intent it = new Intent(ListaFavoritos.this, InfoFilme.class);
                it.putExtras(param);
                startActivity(it);
            }
        });

        listaFavoritos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                filme = arrayAdapterFavoritos.getItem(position);
                return false;
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        MenuItem mExcluir = menu.add(Menu.NONE, idExcluir, 1, "Excluir");
        MenuItem mCancelar = menu.add(Menu.NONE, idCancelar, 2, "Cancelar");

        mExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                long retornoDB;
                BDHelper helper = new BDHelper(ListaFavoritos.this);
                retornoDB = helper.excluirFavorito(filme.getId_filme());
                if(retornoDB == -1)
                    Toast.makeText(ListaFavoritos.this, "Erro ao excluir favorito", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(ListaFavoritos.this, "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                    try {
                        preencheLista();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }



    public void preencheLista() throws IOException {
        BDHelper favoritosBD = new BDHelper(ListaFavoritos.this);

        if(favoritosBD.verificaBanco()) {
            InputStreamReader filmeInput = new InputStreamReader(getAssets().open("filmes.txt"), "UTF-8");
            BufferedReader filmeBuffer = new BufferedReader(filmeInput);
            String filmeLinha;
            while ((filmeLinha = filmeBuffer.readLine()) != null) {
                String[] f = filmeLinha.split("','");
                Filme filmeLeitura = new Filme(f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], "0");
                favoritosBD.insereFilme(filmeLeitura);
            }
            filmeBuffer.close();
        }

        arrayListFavoritos = favoritosBD.selectAllFavoritos();
        favoritosBD.close();
        if(listaFavoritos != null){
            arrayAdapterFavoritos = new ArrayAdapter<Filme>(ListaFavoritos.this, android.R.layout.simple_list_item_1,
                    arrayListFavoritos);
            listaFavoritos.setAdapter(arrayAdapterFavoritos);
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