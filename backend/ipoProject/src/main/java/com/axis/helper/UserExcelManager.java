package com.axis.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.axis.model.ReportingManager;
import com.axis.model.UserMerchant;

public class UserExcelManager {

	//check whether it is xsml file
			public static boolean checkExelFormat(MultipartFile file) {
				String contentType= file.getContentType();
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
					return true;
				}else {
					return false;
				}
				}
			
			//excel to list of Manager
			public static List<UserMerchant> convertExceltToList(InputStream is){
				List<UserMerchant> list = new ArrayList<UserMerchant>();
				
				try {
					XSSFWorkbook workbook	=new XSSFWorkbook(is);
				XSSFSheet sheet	= workbook.getSheet("user");
					
				int rowNumber=0;
				Iterator<Row> itr=sheet.iterator();
				while(itr.hasNext()) {
					Row row =itr.next();
					//for avoid first row
					if(rowNumber==0) {
						rowNumber++;
						continue;
					}
				Iterator<Cell>	cells=row.iterator();
				
				int cid=0;
				UserMerchant userMerchantData= new UserMerchant();
				while(cells.hasNext()) {
					Cell cell=cells.next();
					switch(cid) {
					case 0:
						
						userMerchantData.setName(cell.getStringCellValue());
						break;
					case 1:
						userMerchantData.setMarketingName(cell.getStringCellValue());
						break;
					case 2:
						userMerchantData.setAddress(cell.getStringCellValue());
						break;
					case 3:
						userMerchantData.setCity(cell.getStringCellValue());
						break;
					case 4:
						userMerchantData.setState(cell.getStringCellValue());
						break;
					case 5:
					userMerchantData.setPincode((int)cell.getNumericCellValue());
						break;
					case 6:
						userMerchantData.setMobileNo((long)cell.getNumericCellValue());
						break;
					case 7:
						userMerchantData.setLandlineNo(cell.getStringCellValue());
						break;
					case 8:
					userMerchantData.setPartner(cell.getStringCellValue());
					break;
				case 9:
					userMerchantData.setCategory(cell.getStringCellValue());
					break;
					case 10:
				userMerchantData.setMcc((int)cell.getNumericCellValue());
						break;
				case 11:
			     userMerchantData.setMccdesc(cell.getStringCellValue());
		        	break;
				case 12:
					userMerchantData.setTypeOfIntegration(cell.getStringCellValue());
					break;
//					case 13:
//						userMerchantData.setCity(cell.getStringCellValue());
//						break;
					case 13:
						userMerchantData.setVpa(cell.getStringCellValue());
						break;
					case 14:
						userMerchantData.setGstNumber(cell.getStringCellValue());
						break;
					case 15:
						userMerchantData.setPan(cell.getStringCellValue());
						break;
					case 16:
						userMerchantData.setMerchantOfficialId(cell.getStringCellValue());
						break;
						
					case 17:
						userMerchantData.setMerchantWebsite(cell.getStringCellValue());
						break;
						
					case 18:
						userMerchantData.setBankName(cell.getStringCellValue());
						break;
						
					case 19:
						userMerchantData.setIfscCode(cell.getStringCellValue());
						break;
						
					case 20:
						userMerchantData.setCurrentAccNo((long)cell.getNumericCellValue());
						break;
						
					case 21:
						userMerchantData.setPoolAccNo((long)cell.getNumericCellValue());
						break;
						
					case 22:
						userMerchantData.setPoolIfscCode(cell.getStringCellValue());
						break;
						
					case 23:
						userMerchantData.setSym(cell.getStringCellValue());
						break;
						
						
						default:
							break;
					}
					cid++;
				}
				list.add(userMerchantData);
				}
				
				} catch (Exception e) {
				 e.printStackTrace();
				}
				return list;
			}
}
