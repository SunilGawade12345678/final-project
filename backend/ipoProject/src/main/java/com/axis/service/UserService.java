package com.axis.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.axis.DTO.UserMerchantDTO;
import com.axis.model.ReportingManager;
import com.axis.model.UserMerchant;
import com.axis.model.ValidateUserMerchantData;

public interface UserService {

	
	public void saveAdminFile(MultipartFile file); 
	
	public void saveUserFile(MultipartFile file);

	public List<ReportingManager> getAllInfo();
	public List<UserMerchant> getAllUser();

	public void updateUserMerchant(UserMerchantDTO userMerchantDTO, int id);

	public void addUser(UserMerchant userMerchant);
	public void addAdmin(ReportingManager reportingManager);

	public void addApproveMerchant(ValidateUserMerchantData validateUserMerchantData);
	
	
}
