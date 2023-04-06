package com.axis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.axis.DTO.UserMerchantDTO;
import com.axis.exception.IdNotFoundException;
import com.axis.exception.InformationNotFound;
import com.axis.helper.ManagerExcelHelper;
import com.axis.helper.UserExcelManager;
import com.axis.model.UserMerchant;
import com.axis.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserMerchantController {

	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserMerchant userMerchant){
		userService.addUser(userMerchant);
		return new  ResponseEntity<>("User merchant Successfully Created" , HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserList")
	public ResponseEntity<List<UserMerchant>> listOfUserMerchant() throws InformationNotFound{
		try {
			List<UserMerchant> list =userService.getAllUser();
			
			return new ResponseEntity<List<UserMerchant>>(list, HttpStatus.OK);
		} catch (Exception e) {
			throw new InformationNotFound("user Information not Found");
		}
	}
	
	//update employee by id
		@PutMapping("/update/{id}")
		public String updateMerchantDetails( @RequestBody UserMerchantDTO userMerchantDTO ,@PathVariable int id) throws IdNotFoundException {
			try {
				userService.updateUserMerchant(userMerchantDTO ,id);
				return "Id no "+id+"udated succesfully";
			} catch (Exception e) {
				throw new IdNotFoundException(id);
			}
		
		}
	
	//excel to database 
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		if(UserExcelManager.checkExelFormat(file)) {
			userService.saveUserFile(file);
			return ResponseEntity.ok("file is uploaded Successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file Only");
	}

}
