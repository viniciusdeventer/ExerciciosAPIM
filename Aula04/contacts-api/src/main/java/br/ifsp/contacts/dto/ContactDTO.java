package br.ifsp.contacts.dto;

import java.util.List;

public class ContactDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private List<AddressDTO> addresses;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {  
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<AddressDTO> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}