package service;

import java.util.ArrayList;
import java.util.List;

import model.produto.Produto;
import model.produto.RendaFixa;
import model.produto.RendaVariavel;

public class ProdutoService {
    private final List<Produto> produtos = new ArrayList<>();
    
    public void cadastrar(Produto produto) {
        if (produto == null)
            throw new IllegalArgumentException("Produto não pode ser nulo.");

        produtos.add(produto);
    }

    public List<Produto> listar() {
        return List.copyOf(produtos);
    }

    public Produto buscarPorId(int id) {
        return produtos.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean remover(int id) {
        Produto produto = buscarPorId(id);

        if (produto == null)
            return false;

        return produtos.remove(produto);
    }

    public boolean atualizarRendaFixa(int id, String nome, String descricao, double rendimento, int carencia) {
		Produto produto = buscarPorId(id);
			
		if (!(produto instanceof RendaFixa rendaFixa))
			return false;
		
		rendaFixa.setNome(nome);
		rendaFixa.setDescricao(descricao);
		rendaFixa.setRendimentoMensalFixo(rendimento);
		rendaFixa.setPeriodoCarencia(carencia);
		
		return true;
	}
    
    public boolean atualizarRendaVariavel(int id, String nome, String descricao, double rendimento) {
		Produto produto = buscarPorId(id);
		
		if (!(produto instanceof RendaVariavel rendaVariavel))
		return false;
		
		rendaVariavel.setNome(nome);
		rendaVariavel.setDescricao(descricao);
		rendaVariavel.setRendimentoMensalVariavel(rendimento);
		
		return true;
	}
}


