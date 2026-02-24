package service;

import java.util.ArrayList;
import java.util.List;

import model.cliente.Cliente;

public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();

    public void cadastrar(Cliente cliente) {
        if (cliente == null)
            throw new IllegalArgumentException("Cliente não pode ser nulo.");

        clientes.add(cliente);
    }

    public List<Cliente> listar() {
        return List.copyOf(clientes);
    }

    public Cliente buscarPorId(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public Cliente buscarPorDoc(String documento) {
    	return clientes.stream()
    			.filter(c -> c.getDocumento().equals(documento))
    			.findFirst()
    			.orElse(null);
    }

    public boolean remover(int id) {
        Cliente cliente = buscarPorId(id);

        if (cliente == null)
            return false;

        return clientes.remove(cliente);
    }

    public boolean atualizar(int id, String nome, String email, String documento) {
        Cliente cliente = buscarPorId(id);

        if (cliente == null)
            return false;

        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setDocumento(documento);
        return true;
    }
}