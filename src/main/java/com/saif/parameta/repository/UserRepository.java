package com.saif.parameta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saif.parameta.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {

	Optional<UserDTO> findByDocumentNumber(String documentNumber);
}
