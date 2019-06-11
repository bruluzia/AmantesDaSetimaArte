/*  * @author Bruna Luzia Almeida Rodrigues - Acadêmica de Engenharia de Software
    * @author Danielle Lima Maidana Gauna Benites - Acadêmica de Engenharia de Software
    * @author Gabriel Martinez Nunes - Acadêmico de Engenharia de Software
    * @author Piter Martins Rocha - Acadêmico de Engenharia de Software
    *
    * Classe BDHelper
    * Descrição: Classe java para manipulação do banco de dados
    * */
package com.example.amantesdasetimaarte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "amantesdasetimaarte.db";

    private static final String TABLE_USUARIO = "usuario";
    private static final String COLUMN_USUARIO_ID = "id_usuario";
    private static final String COLUMN_USUARIO_NOME = "nome";
    private static final String COLUMN_USUARIO_SOBRENOME = "sobrenome";
    private static final String COLUMN_USUARIO_EMAIL = "email";
    private static final String COLUMN_USUARIO_SENHA = "senha";

    private static final String TABLE_FILME = "filme";
    private static final String COLUMN_FILME_ID = "id_filme";
    private static final String COLUMN_FILME_TITULO = "titulo";
    private static final String COLUMN_FILME_DIRETOR = "diretor";
    private static final String COLUMN_FILME_DURACAO = "duracao";
    private static final String COLUMN_FILME_GENERO = "genero";
    private static final String COLUMN_FILME_IDADE = "idade";
    private static final String COLUMN_FILME_LANCAMENTO = "lancamento";
    private static final String COLUMN_FILME_SINOPSE = "sinopse";
    private static final String COLUMN_FILME_DISTRIBUIDOR = "distribuidor";
    private static final String COLUMN_FILME_NOTA = "nota";

    private static final String TABLE_NOTA = "nota";
    private static final String COLUMN_NOTA_USUARIO = "id_usuario";
    private static final String COLUMN_NOTA_FILME = "id_filme";
    private static final String COLUMN_NOTA_NOTA = "nota";

    private static final String TABLE_FAVORITO = "favorito";
    private static final String COLUMN_FAVORITO_USUARIO = "id_usuario";
    private static final String COLUMN_FAVORITO_FILME = "id_filme";

    private static final String TABLE_LOGADO = "logado";
    private static final String COLUMN_LOGADO_ID = "id_usuario";
    private static final String COLUMN_LOGADO_NOME = "nome";
    private static final String COLUMN_LOGADO_SOBRENOME = "sobrenome";
    private static final String COLUMN_LOGADO_EMAIL = "email";
    private static final String COLUMN_LOGADO_SENHA = "senha";


    private static final String TABLE_USUARIO_CREATE =
            "create table usuario (" +
                    "id_usuario integer primary key autoincrement," +
                    "nome text not null," +
                    "sobrenome text not null," +
                    "email text not null," +
                    "senha text not null);";

    private static final String TABLE_FILME_CREATE =
            "create table filme (" +
                    "id_filme integer primary key autoincrement," +
                    "titulo text not null," +
                    "diretor text not null," +
                    "duracao text not null," +
                    "genero text not null," +
                    "idade text not null," +
                    "lancamento text not null," +
                    "sinopse text not null," +
                    "distribuidor text not null," +
                    "nota text not null);";

    private static final String TABLE_NOTA_CREATE =
            "create table nota (" +
                    "id_usuario integer not null," +
                    "id_filme integer not null," +
                    "nota text not null," +
                    "foreign key(usuario) references usuario(id_usuario)," +
                    "foreign key(filme) references usuario(id_usuario));";

    private static final String TABLE_FAVORITO_CREATE =
            "create table favorito (" +
                    "id_usuario integer not null," +
                    "id_filme integer not null," +
                    "foreign key(id_usuario) references usuario(id_usuario)," +
                    "foreign key(id_filme) references usuario(id_usuario));";

    private static final String TABLE_LOGADO_CREATE =
            "create table logado (" +
                    "id_usuario text not null," +
                    "nome text not null," +
                    "sobrenome text not null," +
                    "email text not null," +
                    "senha text not null);";

    public BDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USUARIO_CREATE);
        db.execSQL(TABLE_FILME_CREATE);
//        db.execSQL(TABLE_NOTA_CREATE);
        db.execSQL(TABLE_FAVORITO_CREATE);
        db.execSQL(TABLE_LOGADO_CREATE);

        this.db = db;
        /*try {
            populateDB();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITO);
        this.onCreate(db);
    }

    public void insereUsuario(Usuario u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO_NOME, u.getNome());
        values.put(COLUMN_USUARIO_SOBRENOME, u.getSobrenome());
        values.put(COLUMN_USUARIO_EMAIL, u.getEmail());
        values.put(COLUMN_USUARIO_SENHA, u.getSenha());
        db.insert(TABLE_USUARIO, null, values);
        db.close();
    }

    public int alterarUsuario(Usuario u){
        int retornoDB;
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO_ID, u.getId_usuario());
        values.put(COLUMN_USUARIO_NOME, u.getNome());
        values.put(COLUMN_USUARIO_SOBRENOME, u.getSobrenome());
        values.put(COLUMN_USUARIO_EMAIL, u.getEmail());
        values.put(COLUMN_USUARIO_SENHA, u.getSenha());
        String[] args = {String.valueOf(u.getId_usuario())};
        retornoDB = db.update(TABLE_USUARIO, values, COLUMN_USUARIO_ID + "=?", args);
        db.close();

        db = getWritableDatabase();
        ContentValues valuesLogado = new ContentValues();
        valuesLogado.put(COLUMN_LOGADO_ID, u.getId_usuario());
        valuesLogado.put(COLUMN_LOGADO_NOME, u.getNome());
        valuesLogado.put(COLUMN_LOGADO_SOBRENOME, u.getSobrenome());
        valuesLogado.put(COLUMN_LOGADO_EMAIL, u.getEmail());
        valuesLogado.put(COLUMN_LOGADO_SENHA, u.getSenha());
        String[] argsLogado = {String.valueOf(u.getId_usuario())};
        retornoDB = db.update(TABLE_USUARIO, values, COLUMN_LOGADO_ID + "=?", args);
        db.close();


        return retornoDB;

    }

    public boolean validarEmail(String email){
        db = this.getReadableDatabase();
        String query = "select email from " + TABLE_USUARIO;
        Cursor cursor = db.rawQuery(query, null);
        String emailCadastrado;
        if(cursor.moveToFirst()){
            do{
                emailCadastrado = cursor.getString(0);
                if(emailCadastrado.equals(email)){
                    return true;
                }
            }while(cursor.moveToNext());
        }

        return false;
    }

    public void insereFilme(Filme filme){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FILME_TITULO, filme.getTitulo());
        values.put(COLUMN_FILME_DIRETOR, filme.getDiretor());
        values.put(COLUMN_FILME_DURACAO, filme.getDuracao());
        values.put(COLUMN_FILME_GENERO, filme.getGenero());
        values.put(COLUMN_FILME_IDADE, filme.getIdade());
        values.put(COLUMN_FILME_LANCAMENTO, filme.getLancamento());
        values.put(COLUMN_FILME_SINOPSE, filme.getSinopse());
        values.put(COLUMN_FILME_DISTRIBUIDOR, filme.getDistribuidor());
        values.put(COLUMN_FILME_NOTA, filme.getNota());
        db.insert(TABLE_FILME, null, values);
        db.close();
    }

    public boolean verificaBanco(){
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_FILME;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return false;
        }
        return true;
    }

    public ArrayList<Filme> selectAllFilmes(){
        String[] columns = {
                COLUMN_FILME_ID,
                COLUMN_FILME_TITULO,
                COLUMN_FILME_DIRETOR,
                COLUMN_FILME_DURACAO,
                COLUMN_FILME_GENERO,
                COLUMN_FILME_IDADE,
                COLUMN_FILME_LANCAMENTO,
                COLUMN_FILME_SINOPSE,
                COLUMN_FILME_DISTRIBUIDOR,
                COLUMN_FILME_NOTA};
        Cursor cursor = getWritableDatabase().query(TABLE_FILME, columns, null, null,
                null, null, "upper(titulo)", null);
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        while(cursor.moveToNext()){
            Filme f = new Filme(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9));
            f.setId_filme(cursor.getString(0));
            listaFilmes.add(f);
        }
        return  listaFilmes;
    }

    public ArrayList<Filme> selectAllFavoritos(){
        String[] columns = {
                COLUMN_FILME_ID,
                COLUMN_FILME_TITULO,
                COLUMN_FILME_DIRETOR,
                COLUMN_FILME_DURACAO,
                COLUMN_FILME_GENERO,
                COLUMN_FILME_IDADE,
                COLUMN_FILME_LANCAMENTO,
                COLUMN_FILME_SINOPSE,
                COLUMN_FILME_DISTRIBUIDOR,
                COLUMN_FILME_NOTA};

        String query = "select * from " + TABLE_FAVORITO + " a INNER JOIN " + TABLE_FILME +
                " b on a.id_filme = b.id_filme where a.id_usuario =?";

        Usuario u = buscaUsuarioLogado();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(u.getId_usuario())});
        ArrayList<Filme> listaFavoritos = new ArrayList<>();
        while(cursor.moveToNext()){
            Filme f = new Filme(
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
            cursor.getString(11));
                    f.setId_filme(cursor.getString(2));
            listaFavoritos.add(f);
        }

        return listaFavoritos;
    }

    public int loginUsuario(String email, String senha){
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_USUARIO;
        Cursor cursor = db.rawQuery(query, null);
        String emailCadastrado, senhaCadastrada;
        if(cursor.moveToFirst()){
            do{
                emailCadastrado = cursor.getString(3);
                senhaCadastrada = cursor.getString(4);
                if(emailCadastrado.equals(email) && senhaCadastrada.equals(senha)){
                    db = this.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_LOGADO_ID, cursor.getString(0));
                    values.put(COLUMN_LOGADO_NOME, cursor.getString(1));
                    values.put(COLUMN_LOGADO_SOBRENOME, cursor.getString(2));
                    values.put(COLUMN_LOGADO_EMAIL, cursor.getString(3));
                    values.put(COLUMN_LOGADO_SENHA, cursor.getString(4));
                    db.insert(TABLE_LOGADO, null, values);
                    db.close();
                    return 0;
                }else if(emailCadastrado.equals(email) && !senhaCadastrada.equals(senha)){
                    return 1;
                }
            }while(cursor.moveToNext());
        }

        return -1;
    }

    public void adicionaFavorito(String id){
       db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
       Usuario u = buscaUsuarioLogado();
       values.put(COLUMN_FAVORITO_FILME, id);
       values.put(COLUMN_FAVORITO_USUARIO, u.getId_usuario());
       db.insert(TABLE_FAVORITO, null, values);
       db.close();
    }

    public int excluirFavorito(String id){
        int retornoDB;
        db = this.getWritableDatabase();
        Usuario u = buscaUsuarioLogado();
        String[] args = {String.valueOf(u.getId_usuario()), String.valueOf(id)};
        retornoDB = db.delete(TABLE_FAVORITO, COLUMN_FAVORITO_USUARIO + "=? AND " + COLUMN_FAVORITO_FILME + "=?", args);
        db.close();
        return retornoDB;
    }

    public boolean verificaFavorito(String id){
        db = this.getReadableDatabase();
        String query = "select * from " + TABLE_FAVORITO;
        Cursor cursor = db.rawQuery(query, null);
        Usuario u = buscaUsuarioLogado();
        String id_usuario;
        String id_filme;
        if(cursor.moveToFirst()){
            do{
                id_usuario = cursor.getString(0);
                id_filme = cursor.getString(1);
                if(id_usuario.equals(u.getId_usuario()) && id_filme.equals(id)){
                    return true;
                }
            }while(cursor.moveToNext());
        }

        return false;
    }

    public Usuario buscaUsuarioLogado(){
        db = getReadableDatabase();
        Usuario u = new Usuario();
        String query = "select * from " + TABLE_LOGADO;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            u.setId_usuario(cursor.getString(0));
            u.setNome(cursor.getString(1));
            u.setSobrenome(cursor.getString(2));
            u.setEmail(cursor.getString(3));
            u.setSenha(cursor.getString(4));
        }

        return u;
    }

}
