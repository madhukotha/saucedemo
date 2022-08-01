package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcel {
	
	@DataProvider
	public String[][] readData() throws IOException
	{
		File excelFile=new File("./src/test/resources/logindata.xlsx");
		System.out.println(excelFile.exists());
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int noofRows = sheet.getPhysicalNumberOfRows();
		int noofColumns = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[noofRows-1][noofColumns];
		for (int i=0;i<noofRows-1;i++)
		{
			for(int j=0;j<noofColumns;j++)
			{
				data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}
