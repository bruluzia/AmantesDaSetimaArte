/*  * @author Bruna Luzia Almeida Rodrigues - Acadêmica de Engenharia de Software
    * @author Danielle Lima Maidana Gauna Benites - Acadêmica de Engenharia de Software
    * @author Gabriel Martinez Nunes - Acadêmico de Engenharia de Software
    * @author Piter Martins Rocha - Acadêmico de Engenharia de Software
    *
    * Classe CadastroUsuario
    * Descrição: arquivo java para manipulação do cadastro do usuário
    * */
package com.example.amantesdasetimaarte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {

    BDHelper helper = new BDHelper(this);
    private EditText edtNome, edtSobrenome, edtEmail, edtSenha;
    Button btnCadastra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        edtNome = findViewById(R.id.id_NomeCadastro);
        edtSobrenome = findViewById(R.id.id_SobrenomeCadastro);
        edtEmail = findViewById(R.id.id_EmailCadastro);
        edtSenha = findViewById(R.id.id_SenhaCadastro);
        btnCadastra = findViewById(R.id.id_BotaoCadastrar);

        Bundle args = getIntent().getExtras();
        if(args != null){
            btnCadastra.setText("EDITAR");
            String nome = args.getString("ch_nome");
            String sobrenome = args.getString("ch_sobrenome");
            String email = args.getString("ch_email");

            edtNome.setText(nome);
            edtSobrenome.setText(sobrenome);
            edtEmail.setText(email);
        }
    }

    public void cadastrarUsuario(View view){
        String nome = edtNome.getText().toString();
        String sobrenome = edtSobrenome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();


        if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty()|| senha.isEmpty()){
            Toast toast = Toast.makeText(CadastroUsuario.this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            boolean emailCadastrado = helper.validarEmail(email);

            if(emailCadastrado){
                Toast toast = Toast.makeText(CadastroUsuario.this,
                        "Email já cadastrado, logue com este email ou insira outro endereço",
                        Toast.LENGTH_SHORT);
                toast.show();
            }else{
                if(senha.length() < 5){
                    Toast toast = Toast.makeText(CadastroUsuario.this,
                            "A senha deve ser maior do que 5 caracteres",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    if(btnCadastra.getText().toString().equalsIgnoreCase("EDITAR")){
                        Usuario u = new Usuario(nome, sobrenome, email, senha);
                        Bundle args = getIntent().getExtras();
                        u.setId_usuario(args.getString("ch_id"));
                        helper.alterarUsuario(u);
                        helper.close();
                        Toast toast = Toast.makeText(CadastroUsuario.this, "Usuário editado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }else{
                        Usuario u = new Usuario(nome, sobrenome, email, senha);
                        helper.insereUsuario(u);
                        helper.close();
                        Toast toast = Toast.makeText(CadastroUsuario.this, "Cadastro realizado com sucesso",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }
                }
            }
        }
    }


}
