package model.cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.conta.Conta;
import util.IdGenerator;

public abstract class Cliente {

    private final int id;
    private String nome;
    private String email;
    private final List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String email) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido.");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email inválido.");

        this.id = IdGenerator.generate(Cliente.class);
        this.nome = nome;
        this.email = email;
    }

    public void adicionarConta(Conta conta) {
        if (conta == null)
            throw new IllegalArgumentException("Conta não pode ser nula.");

        contas.add(conta);
    }

    public void removerConta(Conta conta) {
        if (conta == null)
            throw new IllegalArgumentException("Conta não pode ser nula.");

        if (contas.size() <= 1)
            throw new IllegalStateException("Cliente deve possuir pelo menos uma conta.");

        contas.remove(conta);
    }

    public boolean possuiContas() {
        return !contas.isEmpty();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome inválido.");

        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email inválido.");

        this.email = email;
    }

    public abstract String getTipoPessoa();

    public abstract String getDocumento();

    public abstract void setDocumento(String documento);

    public abstract double getTaxaServicoInvestimentoAutomatico();
}