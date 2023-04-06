package com.axis.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.axis.model.ReportingManager;

public class ManagerExcelHelper {

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
		public static List<ReportingManager> convertExceltToList(InputStream is){
			List<ReportingManager> list = new ArrayList<ReportingManager>();
			
			try {
				XSSFWorkbook workbook	=new XSSFWorkbook(is);
			XSSFSheet sheet	= workbook.getSheet("data");
				
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
			ReportingManager reportingManagerlData= new ReportingManager();
			while(cells.hasNext()) {
				Cell cell=cells.next();
				switch(cid) {
				case 0:
					reportingManagerlData.setName(cell.getStringCellValue());
					break;
				case 1:
					reportingManagerlData.setAddress(cell.getStringCellValue());
					break;
				case 2:
					reportingManagerlData.setRole(cell.getStringCellValue());
					break;
				case 3:
					reportingManagerlData.setMobileNo((long)cell.getNumericCellValue());
					break;
					default:
						break;
				}
				cid++;
			}
			list.add(reportingManagerlData);
			}
			
			} catch (Exception e) {
			 e.printStackTrace();
			}
			return list;
		}
	
}
