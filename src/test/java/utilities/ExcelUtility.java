package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fi;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public ExcelUtility(String path)
	{
		this.path=path;
	}

	// Method to get number of rows in the sheet
	public int getRowCount(String sheetName) throws IOException 
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;		
	}
	
	// Method to get number of columns (cells) in a particular row
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	// Method to get data from a cell
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	// Method to read pet data from the Excel sheet and return it as a List of Object arrays
	public List<Object[]> readPetData(String sheetName) throws IOException {
		List<Object[]> petData = new ArrayList<>();
		
		// Open Excel file
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		
		// Loop through all rows (starting from row 1 to skip the header)
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			
			// Get Pet ID (numeric), Pet Name (string), and Pet Status (string)
			int petId = (int) row.getCell(0).getNumericCellValue(); // Pet ID
			String petName = row.getCell(1).getStringCellValue();   // Pet Name
			String petStatus = row.getCell(2).getStringCellValue(); // Pet Status
			
			// Add the row data to the list
			petData.add(new Object[] {petId, petName, petStatus});
		}
		
		// Close the workbook and file input stream
		workbook.close();
		fi.close();
		
		return petData;
	}
}
