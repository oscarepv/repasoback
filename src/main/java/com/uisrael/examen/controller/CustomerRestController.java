/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.uisrael.examen.controller;

import com.uisrael.examen.dto.CustomerDto;
import com.uisrael.examen.entities.Customer;
import com.uisrael.examen.entities.Role;
import com.uisrael.examen.repository.CustomerRepository;
import com.uisrael.examen.repository.RoleRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Oscar
 */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping()
    @CrossOrigin(origins = "http://localhost:5173")
    public List<Customer> list() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> get(@PathVariable long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            //coment
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody CustomerDto input) {
        Optional<Customer> optionalcustomer = customerRepository.findById(id);
        Optional<Role> optionalrole = roleRepository.findById(input.getRole());

        if (optionalrole == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (optionalcustomer.isPresent()) {
            Customer newcustomer = optionalcustomer.get();
            newcustomer.setName(input.getName());
            newcustomer.setPhone(input.getPhone());
            newcustomer.setRole(optionalrole.get());
            Customer save = customerRepository.save(newcustomer);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> post(@RequestBody CustomerDto input) {
        Optional<Role> optionalrole = roleRepository.findById(input.getRole());

        if (optionalrole == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Customer newcustomer = new Customer();
        newcustomer.setName(input.getName());
        newcustomer.setPhone(input.getPhone());
        newcustomer.setRole(optionalrole.get());
        Customer save = customerRepository.save(newcustomer);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<?> delete(@PathVariable long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
