package com.github.willpinhal.apivendas.apivendas.domain.repositories;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
