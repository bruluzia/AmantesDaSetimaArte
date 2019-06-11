package com.example.amantesdasetimaarte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUsuario extends AppCompatActivity {

    BDHelper helper = new BDHelper(this);
    private EditText edtEmail, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        edtEmail = findViewById(R.id.id_email);
        edtSenha = findViewById(R.id.id_senha);
    }

    public void realizarLogin(View view){
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        int login = helper.loginUsuario(email, senha);

        if(login == 0){
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }else if(login > 0){
            Toast toast = Toast.makeText(LoginUsuario.this, "Senha inserida incorreta, tente novamente",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(LoginUsuario.this, "Email inválido, cadastre-se para continuar",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void realizarCadastro(View view){
        Intent it = new Intent(this, CadastroUsuario.class);
        startActivity(it);
    }
}
