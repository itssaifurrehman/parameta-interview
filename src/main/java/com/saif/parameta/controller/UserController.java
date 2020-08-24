package com.saif.parameta.controller;

import java.util.List;

import com.saif.parameta.exception.UserManagementServiceException;
import com.saif.parameta.model.UserDTO;
import com.saif.parameta.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user/controller")
public class UserController {

	@Autowired
	private UserService userService;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) throws Exception {

		UserDTO response = userService.create(userDTO);

		return new ResponseEntity<UserDTO>(response, HttpStatus.CREATED);
	}

	@CrossOrigin
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws Exception {

		UserDTO response = userService.update(userDTO);

		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/{DocumentNumber}")
	public ResponseEntity<UserDTO> getById(@PathVariable("DocumentNumber") String DocumentNumber)
			throws UserManagementServiceException {

		UserDTO response = userService.getById(DocumentNumber);

		return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<UserDTO>> get() throws UserManagementServiceException {

		List<UserDTO> response = userService.get();

		return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
	}
}
