package model.produto;

import util.IdGenerator;

public abstract class Produto {

    private final int id;
    private String nome;
    private String descricao;

    protected Produto(String nome, String descricao) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido.");

        if (descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Descrição inválida.");

        this.id = IdGenerator.generate(Produto.class);
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido.");

        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank())
            throw new IllegalArgumentException("Descrição inválida.");

        this.descricao = descricao;
    }

    public abstract double calcularRendimento(double valorAplicado, int dias);

    public abstract String getTipoProduto();
}