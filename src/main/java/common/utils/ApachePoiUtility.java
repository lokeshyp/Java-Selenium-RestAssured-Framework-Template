package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ApachePoiUtility {

/**
 * 
 * @param fileName
 * @param sheetName
 * @param row
 * @param cell
 * @return
 * @throws Throwable
 * @throws IOException
 */
	public String readExcel(String fileName, String sheetName, int row, int cell) throws Throwable, IOException {

		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			return WorkbookFactory.create(file).getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();

		} catch (Exception error) {
			throw new Exception("Error occured in readExcel");
		}

	}
	
	/**
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param cellValue
	 * @return
	 * @throws Throwable
	 */
	public boolean writeToExcel(String fileName, String sheetName,int cell, Object cellValue, String citi)throws Throwable {
		
		try {
			FileInputStream file = new FileInputStream(new File(fileName));

			Workbook wb = WorkbookFactory.create(file);
			Sheet sheet = wb.getSheet(sheetName);
			
			
			for( int i =0; i<= sheet.getLastRowNum(); i++) {
				
				if(sheet.getRow(i).getCell(0).getStringCellValue().equals(citi)) {
					sheet.getRow(i).createCell(cell).setCellValue(cellValue.toString());
				}else {
					System.out.println("city not found "+ citi);
				}
				
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
			wb.write(fileOutputStream);
			return true;

		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
		
	}
	
}


