package com.bolsadeideas.spring.boot.backend.apirest1.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bolsadeideas.spring.boot.backend.apirest1.models.dao.IClienteDao;
import com.bolsadeideas.spring.boot.backend.apirest1.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest1.models.service.IClienteService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@WebMvcTest(ClienteRestController.class)
public class ClienteRestControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IClienteService clienteService;
	
	
	
	@Test
	void testShow() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("Deivith");
		cliente.setApellido("Amaya");
		cliente.setEmail("email");
		
		Mockito.when(clienteService.findById((long)1)).thenReturn(cliente);
		
		mockMvc.perform(get("/api/cliente/1")).andExpect(status().isOk());	
				
	}
	
	@Test
	void testIndex() throws Throwable  {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		cliente.setNombre("Deivith");
		cliente.setApellido("Amaya");
		cliente.setEmail("email");
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente);
		
		Mockito.when(clienteService.findAll()).thenReturn(clientes);
		
		System.out.println(mockMvc.perform(get("/api/clientes"))
					.andExpect(status().isOk())
				);	
		
		
		
		
	}

}
