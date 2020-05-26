package com.training.dataproviders;

import java.util.List;
import org.testng.annotations.DataProvider;
import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public static Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public static Object[][] getExcelData(){
		String fileName ="C:\\Sandip Das\\Training\\Reskill Programming\\ReSkill - Selenium\\ExcelTestData\\RetailRegistrationData.xlsx"; 
		
		List<List<Object>> retVal= ApachePOIExcelRead.getExcelContent(fileName);
		System.out.println("size" + retVal.size());
		
		Object[][] result = new Object[retVal.size()][retVal.size()];
		int count=0;
		
		for(List<Object> temp : retVal){
			if(temp!=null){
				Object[] obj = new Object[12];
				System.out.println(temp.get(0));
				System.out.println(temp.get(1));
				System.out.println(temp.get(2));
				System.out.println(temp.get(3));
				System.out.println(temp.get(4));
				System.out.println(temp.get(5));
				System.out.println(temp.get(6));
				System.out.println(temp.get(7));
				System.out.println(temp.get(8));
				System.out.println(temp.get(9));
				System.out.println(temp.get(10));
				System.out.println(temp.get(11));
				//System.out.println(temp.get(11));
				
				obj[0]= temp.get(0);
				obj[1]= temp.get(1);
				obj[2]= temp.get(2);
				obj[3]= temp.get(3);
				obj[4]= temp.get(4);
				obj[5]= temp.get(5);
				obj[6]= temp.get(6);
				obj[7]= temp.get(7);
				obj[8]= temp.get(8);
				obj[9]= temp.get(9);
				obj[10]= temp.get(10);
				obj[11]= temp.get(11);
				 
				 
				
				result[count ++]= obj;
			}
		}
		return result;
		
		
	}
	
	@DataProvider(name = "xls-inputs")
	public static Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:\\\\Sandip Das\\\\Training\\\\Reskill Programming\\\\ReSkill - Selenium\\\\ExcelTestData\\\\RetailRegistrationData.xlsx", "Sheet1"); 
	}
}
