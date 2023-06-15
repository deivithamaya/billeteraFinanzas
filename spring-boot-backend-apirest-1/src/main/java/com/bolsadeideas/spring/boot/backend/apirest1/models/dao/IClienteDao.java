package com.bolsadeideas.spring.boot.backend.apirest1.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.spring.boot.backend.apirest1.models.entity.Cliente;
@Repository
public interface IClienteDao extends CrudRepository<Cliente,Long> {

}
