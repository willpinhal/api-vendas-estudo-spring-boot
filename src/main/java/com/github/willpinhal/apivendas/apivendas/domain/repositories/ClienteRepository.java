package com.github.willpinhal.apivendas.apivendas.domain.repositories;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> findByNomeLike(@Param("nome") String nome);

    boolean existsByNomeLike(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
