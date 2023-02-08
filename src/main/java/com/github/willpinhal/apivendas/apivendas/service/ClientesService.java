package com.github.willpinhal.apivendas.apivendas.service;

import com.github.willpinhal.apivendas.apivendas.model.Cliente;
import com.github.willpinhal.apivendas.apivendas.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository clientesRepository;

    @Autowired
    public ClientesService(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        clientesRepository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){

    }
}
