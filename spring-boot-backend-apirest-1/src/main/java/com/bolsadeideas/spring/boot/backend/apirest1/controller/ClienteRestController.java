package com.bolsadeideas.spring.boot.backend.apirest1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.spring.boot.backend.apirest1.models.entity.Cliente;
import com.bolsadeideas.spring.boot.backend.apirest1.models.service.IClienteService;


@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	

	/*@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}*/
	
	@GetMapping("/clientes")
	public ResponseEntity<?> index(){
		Map<String,Object> respuestas = new HashMap<>();
		try {
			respuestas.put("clientes", this.clienteService.findAll());
		}catch(DataAccessException e) {
			respuestas.put("mensaje", "ha ocurrido un error al consultar en la base de datos");
			respuestas.put("Error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuestas,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		respuestas.put("mensaje","todos fueon encontrados");
		return new ResponseEntity<Map<String,Object>>(respuestas,HttpStatus.OK);
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		//return clienteService.findById(id);
		Cliente cliente = null;
		Map<String,Object> respuestas = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
			respuestas.put("cliente",cliente);
		}
		catch(DataAccessException e) {
			respuestas.put("mensaje", "ha ocurrido un error al consultar en la base de datos");
			respuestas.put("Error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuestas,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(cliente==null) {
			respuestas.put("mensaje", "el cliente".concat(id.toString()).concat("no se encuentra"));
			return new ResponseEntity<Map<String,Object>>(respuestas,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Map<String,Object>>(respuestas,HttpStatus.OK);
	}
	
	@PostMapping("clientes")
	public ResponseEntity<?> save(@RequestBody Cliente cliente) {
		Cliente clienteNuevo = null;
		Map<String,Object> respuesta = new HashMap<>();
		try{
			clienteNuevo = this.clienteService.save(cliente);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "Error al crear el cliente");
			respuesta.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
		respuesta.put("mensaje","Cliente creado con exito");
		respuesta.put("cliente",clienteNuevo );
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> upDate(@RequestBody Cliente cliente,@PathVariable Long id) {
		Map<String,Object> resp = new HashMap<>();
		Cliente clienteNuevo = null;
		
		try {
			Cliente clienteAActualizar  = clienteService.findById(id);
			if(clienteAActualizar==null) {
				resp.put("error", "cliente null");
				return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.NOT_FOUND);
			}
			
			clienteAActualizar.setNombre(cliente.getNombre());
			clienteAActualizar.setApellido(cliente.getApellido());
			clienteAActualizar.setEmail(cliente.getEmail());
			clienteAActualizar.setCreateAt(cliente.getCreateAt());
			
			clienteNuevo=clienteService.save(clienteAActualizar);
			resp.put("mensaje","el cliente fue actualizado");
			resp.put("cliente", clienteNuevo);
		}catch(DataAccessException e){
			resp.put("mensaje", "Error al actualizar el cliente");
			resp.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Map<String,Object>>(resp,HttpStatus.OK);
	}
	
	@DeleteMapping("cliente/{id}")

	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String,Object> respuesta = new HashMap<>();
		try {
			clienteService.delete(id);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "Error al eliminar el cliente el cliente");
			respuesta.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		respuesta.put("mensaje", "eliminado con exito");
		return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
	}
}
