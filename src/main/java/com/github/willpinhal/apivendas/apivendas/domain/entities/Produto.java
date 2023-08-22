package com.github.willpinhal.apivendas.apivendas.domain.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "O campo descrição é obrigatório.")
    private String descricao;

    @NotNull(message = "O campo preço é obrigatório.")
    @Column(name = "preco_unitario")
    private BigDecimal preco;

   }
