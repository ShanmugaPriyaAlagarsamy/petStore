package utilities;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="petstoreData")
	public String [][] getData() throws IOException
	{
		String path=".\\testdata\\petData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)  
		{		
			for(int j=0;j<totalcols;j++)  
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  
			}
		}
	return logindata;
				
	}
	
	@DataProvider(name = "petIdProvider")
    public Object[][] getPetId() {
        return new Object[][]{
            {"501"},
            {"502"}
        };
    }

    @DataProvider(name = "updatePetProvider")
    public Object[][] updatePetData() {
        return new Object[][]{
            {"501", "JimmyUpdated", "sold", "Dog", "Friendly"},
            {"502", "MaxUpdated", "available", "Cat", "Playful"}
        };
    }

}
