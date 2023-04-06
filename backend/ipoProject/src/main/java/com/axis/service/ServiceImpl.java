package com.axis.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.axis.DTO.UserMerchantDTO;
import com.axis.helper.ManagerExcelHelper;
import com.axis.helper.UserExcelManager;
import com.axis.model.ReportingManager;
import com.axis.model.UserMerchant;
import com.axis.model.ValidateUserMerchantData;
import com.axis.repo.ReportManagerRepo;
import com.axis.repo.UserMerchantRepo;
import com.axis.repo.ValidateRepo;

@Service
public class ServiceImpl implements UserService {
  
	@Autowired
	ReportManagerRepo managerRepo;
	
	@Autowired
	UserMerchantRepo merchantRepo;
	@Autowired
	ValidateRepo validateRepo;
	
	@Override
	public void saveAdminFile(MultipartFile file) {
		try {
			List<ReportingManager> excelData=ManagerExcelHelper.convertExceltToList(file.getInputStream());
			managerRepo.saveAll(excelData);
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
		}
	
		public List<ReportingManager> getAllInfo(){
		return	managerRepo.findAll();
		                       }

		@Override
		public void saveUserFile(MultipartFile file) {
			// TODO Auto-generated method stub
			try {
				List<UserMerchant> excelData=UserExcelManager.convertExceltToList(file.getInputStream());
				merchantRepo.saveAll(excelData);
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		}
          
		public List<UserMerchant> getAllUser(){
			return	merchantRepo.findAll();
			       }
        //
		@Override
		public void updateUserMerchant(UserMerchantDTO u, int id) {
			merchantRepo.findById(id);
		UserMerchant userMerchant	=merchantRepo.findById(id);
			if (userMerchant.getId()==0) {
				userMerchant.setName(u.getName());
				userMerchant.setMarketingName(u.getMarketingName());
				userMerchant.setAddress(u.getAddress());
				userMerchant.setCity(u.getCity());
				userMerchant.setState(u.getState());
				userMerchant.setPincode(u.getPincode());
				userMerchant.setMobileNo(u.getMobileNo());
				userMerchant.setLandlineNo(u.getLandlineNo());
				userMerchant.setPartner(u.getPartner());
				userMerchant.setOwner(u.getOwner());
				userMerchant.setCategory(u.getCategory());
				userMerchant.setMcc(u.getMcc());
				userMerchant.setMccdesc(u.getMccdesc());
				userMerchant.setTypeOfIntegration(u.getTypeOfIntegration());
				userMerchant.setAddMerchantOption(u.getAddMerchantOption());
				userMerchant.setVpa(u.getVpa());
				userMerchant.setGstNumber(u.getGstNumber());
				userMerchant.setPan(u.getPan());
				userMerchant.setMerchantOfficialId(u.getMerchantOfficialId());
				userMerchant.setMerchantWebsite(u.getMerchantWebsite());
				userMerchant.setBankName(u.getBankName());
				userMerchant.setIfscCode(u.getIfscCode());
				userMerchant.setCurrentAccNo(u.getCurrentAccNo());
				userMerchant.setPoolAccNo(u.getPoolAccNo());
				userMerchant.setPoolIfscCode(u.getPoolIfscCode());
				userMerchant.setSym(u.getSym());
				userMerchant.setRole(u.getRole());
				
				merchantRepo.save(userMerchant);
					} 
		}

		@Override
		public void addUser(UserMerchant userMerchant) {
			
			merchantRepo.save(userMerchant);
		}

		@Override
		public void addAdmin(ReportingManager reportingManager) {
			
			managerRepo.save(reportingManager);
		}

		@Override
		public void addApproveMerchant(ValidateUserMerchantData validateUserMerchantData) {
			// TODO Auto-generated method stub
			validateRepo.save(validateUserMerchantData);
		}
		
		
}
