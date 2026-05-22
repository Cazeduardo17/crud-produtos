package com.example.produto.controller;

import com.example.produto.model.Produto;
import com.example.produto.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    // CADASTRAR PRODUTO
    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    // LISTAR PRODUTOS
    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }

    // ATUALIZAR PRODUTO
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id,
                             @RequestBody Produto produtoAtualizado) {

        Produto produto = repository.findById(id).orElse(null);

        if (produto != null) {

            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());

            return repository.save(produto);
        }

        return null;
    }

    // DELETAR PRODUTO
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}