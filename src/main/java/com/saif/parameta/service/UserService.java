package com.saif.parameta.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saif.parameta.exception.UserManagementServiceErrorCodes;
import com.saif.parameta.exception.UserManagementServiceException;
import com.saif.parameta.model.UserDTO;
import com.saif.parameta.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public static final Pattern VALID_DATE_REGEX = Pattern
			.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");

	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public UserDTO getById(String documentNumber) throws UserManagementServiceException {

		Optional<UserDTO> response = userRepo.findByDocumentNumber(documentNumber);

		if (Objects.isNull(response) || !response.isPresent()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.EMPTY_DATABASE);
		}

		return response.get();

	}

	public List<UserDTO> get() throws UserManagementServiceException {

		List<UserDTO> serviceResponse = new ArrayList<UserDTO>();

		userRepo.findAll().forEach(UserDTO -> serviceResponse.add(UserDTO));

		return serviceResponse;
	}

	public UserDTO create(UserDTO userDTO) throws UserManagementServiceException {

		Optional<UserDTO> response = userRepo.findByDocumentNumber(userDTO.getDocumentNumber());

		if (response.isPresent()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.USER_ALREADY_EXISTS);
		}

		validateNames(userDTO);
		validateDocuments(userDTO);
		validateCargoAndSalary(userDTO);
		validateDates(userDTO);

		Date currentDate = new Date(Calendar.getInstance().getTime().getTime());

		userDTO.setCreationTime(currentDate);

		return userRepo.save(userDTO);
	}

	public UserDTO update(UserDTO userDTO) throws UserManagementServiceException {

		Optional<UserDTO> response = userRepo.findByDocumentNumber(userDTO.getDocumentNumber());

		if (Objects.isNull(response) || !response.isPresent()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.EMPTY_DATABASE);
		}

		validateDocuments(userDTO);
		validateNames(userDTO);
		validateCargoAndSalary(userDTO);
		validateDates(userDTO);

		UserDTO oldRecord = response.get();

		oldRecord.setFirstName(userDTO.getFirstName());
		oldRecord.setLastName(userDTO.getLastName());
		oldRecord.setDocumentType(userDTO.getDocumentNumber());
		oldRecord.setCargo(userDTO.getCargo());
		oldRecord.setCompanyJoiningDate(userDTO.getCompanyJoiningDate());
		oldRecord.setDateOfBirth(userDTO.getDateOfBirth());
		oldRecord.setSalary(userDTO.getSalary());

		return userRepo.save(oldRecord);

	}

	public void validateDates(UserDTO userDTO) throws UserManagementServiceException {

		Matcher matcher = VALID_DATE_REGEX.matcher(userDTO.getCompanyJoiningDate().toString());

		if (!matcher.matches()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.CRITERIA_NOT_FOUND);
		}

		matcher = VALID_DATE_REGEX.matcher(userDTO.getDateOfBirth().toString());

		if (!matcher.matches()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.CRITERIA_NOT_FOUND);
		} else {
			ageCalculator(userDTO);
		}
	}

	public void validateNames(UserDTO userDTO) throws UserManagementServiceException {

		if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.EMPTY_NAME);
		}
		if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty())
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.EMPTY_NAME);

	}

	public void validateDocuments(UserDTO userDTO) throws UserManagementServiceException {
		if (userDTO.getDocumentType() == null || userDTO.getDocumentType().isEmpty()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.DOCUMENT_TYPE_NOT_FOUND);
		}
		if (userDTO.getDocumentNumber() == null || userDTO.getDocumentNumber().isEmpty()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.DOCUMENT_NUMBER_NOT_FOUND);
		}
	}

	public void validateCargoAndSalary(UserDTO userDTO) throws UserManagementServiceException {
		if (userDTO.getCargo() == null || userDTO.getCargo().isEmpty()) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.CARGO_NOT_FOUND);
		}
		if (userDTO.getSalary() == null || Double.isNaN(userDTO.getSalary())) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.SALARY_NOT_FOUND);
		}
	}

	public void ageCalculator(UserDTO userDTO) throws UserManagementServiceException {
		int dateofBirth = Integer.parseInt(formatter.format(userDTO.getDateOfBirth()));
		int currentDate = Integer.parseInt(formatter.format(System.currentTimeMillis()));
		int age = (currentDate - dateofBirth) / 10000;
		if (age < 18) {
			throw new UserManagementServiceException(UserManagementServiceErrorCodes.UNDER_AGE);
		}
	}

}
