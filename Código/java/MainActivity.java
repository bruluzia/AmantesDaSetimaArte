package com.example.amantesdasetimaarte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrar(View view){
        Intent it = new Intent(this, CadastroUsuario.class);
        startActivity(it);
    }

    public void editar(View view){
        BDHelper helper = new BDHelper(MainActivity.this);
        Usuario u = helper.buscaUsuarioLogado();
        Bundle param = new Bundle();
        param.putString("ch_id", u.getId_usuario());
        param.putString("ch_nome", u.getNome());
        param.putString("ch_sobrenome", u.getSobrenome());
        param.putString("ch_email", u.getEmail());
        param.putString("ch_senha", u.getSenha());
        Intent it = new Intent(this, CadastroUsuario.class);
        it.putExtras(param);
        startActivity(it);
    }

    public void login(View view){
        Intent it = new Intent(this, LoginUsuario.class);
        startActivity(it);
    }

    public void listaFavoritos(View view){
        Intent it = new Intent(this, ListaFavoritos.class);
        startActivity(it);
    }

    public void listaFilmes(View view){
        Intent it = new Intent(this, ListaFilmes.class);
        startActivity(it);
    }


}
