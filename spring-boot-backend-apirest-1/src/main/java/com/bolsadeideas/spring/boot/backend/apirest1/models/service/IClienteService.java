package com.bolsadeideas.spring.boot.backend.apirest1.models.service;

import java.util.List;

import com.bolsadeideas.spring.boot.backend.apirest1.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public Cliente findById(Long id);
}
