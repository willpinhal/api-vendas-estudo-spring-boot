package com.github.willpinhal.apivendas.apivendas.controllers;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Produto;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity save(@RequestBody Produto produto){

        Produto produtoSalvo = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @GetMapping("{id}")
    public ResponseEntity getProdutoByID(@PathVariable Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()){
            return ResponseEntity.ok(produto);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Produto produto){

        return produtoRepository.findById(id).map(p -> {
            produto.setId(p.getId());
            produtoRepository.save(produto);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){

        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()){
            produtoRepository.delete(produtoExistente.get());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity findProduto(Produto produto){

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(produto, exampleMatcher);

        List<Produto> produtoList = produtoRepository.findAll(example);
        return ResponseEntity.ok(produtoList);
    }
}
