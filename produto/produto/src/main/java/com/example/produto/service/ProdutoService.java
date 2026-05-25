package com.example.produto.service;

import com.example.produto.dto.ProdutoDTO;
import com.example.produto.model.Produto;
import com.example.produto.repository.ProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrar(ProdutoDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());

        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        Optional<Produto> produto = repository.findById(id);
        return produto.orElse(null);
    }

    public Produto atualizar(Long id, ProdutoDTO dto) {

        Produto produto = repository.findById(id).orElse(null);

        if (produto != null) {

            produto.setNome(dto.getNome());
            produto.setPreco(dto.getPreco());
            produto.setCategoria(dto.getCategoria());

            return repository.save(produto);
        }

        return null;
    }

    public boolean deletar(Long id) {

        Produto produto = repository.findById(id).orElse(null);

        if (produto != null) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}