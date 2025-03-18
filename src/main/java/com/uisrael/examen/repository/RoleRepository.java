/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.uisrael.examen.repository;

import com.uisrael.examen.entities.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Oscar
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String defaultRole);
    
}
