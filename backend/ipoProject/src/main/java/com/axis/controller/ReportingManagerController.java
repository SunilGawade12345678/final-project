package com.axis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.axis.helper.ManagerExcelHelper;
import com.axis.model.ReportingManager;
import com.axis.model.UserMerchant;
import com.axis.model.ValidateUserMerchantData;
import com.axis.service.UserService;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin
public class ReportingManagerController {
	
	@Autowired
	UserService userService;

	@PostMapping("/addAdmin")
	public ResponseEntity<String> addManager(@RequestBody ReportingManager reportingManager){
		userService.addAdmin(reportingManager);
		return new  ResponseEntity<>("User merchant Successfully Created" , HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		if(ManagerExcelHelper.checkExelFormat(file)) {
			userService.saveAdminFile(file);
			return ResponseEntity.ok("file is uploaded Successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excel file Only");
	}
	@GetMapping("/getAll")
	public List<ReportingManager> getAllInfo(){
		return userService.getAllInfo();
	}
	
	@PostMapping("/Approve")
	public ResponseEntity<String> addManager(@RequestBody ValidateUserMerchantData validateUserMerchantData){
		userService.addApproveMerchant(validateUserMerchantData
				);
		return new  ResponseEntity<>("User merchant Successfully save in datatabase" , HttpStatus.CREATED);
	}
}
