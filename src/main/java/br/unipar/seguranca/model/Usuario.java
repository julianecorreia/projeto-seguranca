package br.unipar.seguranca.model;

public class Usuario {

    private String nome;
    private String usuario;
    private String senha;

    //TODO:
    //persistir no banco de dados 3 usuários distintos
    //conseguir logar com qualquer um deles
    //passo 1: quais dependências preciso? postrgres, data jpa
    //passo 2: preciso gravar um usuario (CRUD completo)
    //passo 3: editar userDetailsService para receber a lista de usuario do bd

    //depois:
    //adicionar perfis de acesso para esses usuários já existentes
}
