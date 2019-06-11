/*  * @author Bruna Luzia Almeida Rodrigues - Acadêmica de Engenharia de Software
    * @author Danielle Lima Maidana Gauna Benites - Acadêmica de Engenharia de Software
    * @author Gabriel Martinez Nunes - Acadêmico de Engenharia de Software
    * @author Piter Martins Rocha - Acadêmico de Engenharia de Software
    *
    * Classe InfoFilmes
    * Descrição: Classe java para manipular informações do filme
    * */
package com.example.amantesdasetimaarte;

import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InfoFilme extends AppCompatActivity {

    TextView titulo, diretor, duracao, genero, idade, lancamento, sinopse, distribuidor, nota;
    String id;
    Button btnFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_filme);

        titulo = (TextView)findViewById(R.id.txtTitulo);
        diretor = (TextView)findViewById(R.id.txtDiretor);
        duracao = (TextView)findViewById(R.id.txtDuracao);
        genero = (TextView)findViewById(R.id.txtGenero);
        idade = (TextView)findViewById(R.id.txtIdade);
        lancamento = (TextView)findViewById(R.id.txtLancamento);
        sinopse = (TextView)findViewById(R.id.txtSinopse);
        distribuidor = (TextView)findViewById(R.id.txtDistribuidor);
        nota = (TextView)findViewById(R.id.txtNota);

        Bundle args = getIntent().getExtras();
        id = args.getString("ch_id");
        titulo.setText(args.getString("ch_titulo"));
        diretor.setText(args.getString("ch_diretor"));
        duracao.setText(args.getString("ch_duracao"));
        genero.setText(args.getString("ch_genero"));
        idade.setText(args.getString("ch_idade"));
        lancamento.setText(args.getString("ch_lancamento"));
        sinopse.setText(args.getString("ch_sinopse"));
        distribuidor.setText(args.getString("ch_distribuidor"));
        nota.setText(args.getString("ch_nota"));

        btnFavorito = findViewById(R.id.btnFavorito);

        BDHelper helper = new BDHelper(InfoFilme.this);
        boolean favorito = helper.verificaFavorito(id);

        if(favorito){
            btnFavorito.setText("FAVORITADO");
        }else{
            btnFavorito.setText("ADICIONAR AOS FAVORITOS");
        }
    }

    public void favoritar(View view){
        BDHelper helper = new BDHelper(InfoFilme.this);
        boolean favorito = helper.verificaFavorito(id);

        if(favorito){
            btnFavorito.setText("ADICIONAR AOS FAVORITOS");
            helper.excluirFavorito(id);
            helper.close();
            Toast.makeText(InfoFilme.this, "Filme removido dos favoritos com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            btnFavorito.setText("FAVORITADO");
            helper.adicionaFavorito(id);
            helper.close();
            Toast.makeText(InfoFilme.this, "Filme adicionado aos favoritos com sucesso", Toast.LENGTH_SHORT).show();
        }
    }


}
