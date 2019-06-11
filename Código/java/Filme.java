/*  * @author Bruna Luzia Almeida Rodrigues - Acadêmica de Engenharia de Software
    * @author Danielle Lima Maidana Gauna Benites - Acadêmica de Engenharia de Software
    * @author Gabriel Martinez Nunes - Acadêmico de Engenharia de Software
    * @author Piter Martins Rocha - Acadêmico de Engenharia de Software
    *
    * Classe Filmes
    * Descrição: Classe java para manipulação dos dados do filme
    * */
package com.example.amantesdasetimaarte;

public class Filme {

    private String id_filme;
    private String titulo;
    private String diretor;
    private String duracao;
    private String genero;
    private String idade;
    private String lancamento;
    private String sinopse;
    private String distribuidor;
    private String nota;

    public Filme(){

    }

    public Filme(String titulo, String diretor, String duracao, String genero, String idade, String lancamento, String sinopse, String distribuidor, String nota) {
        this.id_filme = id_filme;
        this.titulo = titulo;
        this.diretor = diretor;
        this.duracao = duracao;
        this.genero = genero;
        this.idade = idade;
        this.lancamento = lancamento;
        this.sinopse = sinopse;
        this.distribuidor = distribuidor;
        this.nota = nota;
    }

    public String getId_filme() {
        return id_filme;
    }

    public void setId_filme(String id_filme) {
        this.id_filme = id_filme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }


    @Override
    public String toString() {
        return id_filme + " " + titulo;
    }
}
