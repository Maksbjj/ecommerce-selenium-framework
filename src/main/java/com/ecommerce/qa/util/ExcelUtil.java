package com.ecommerce.qa.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class ExcelUtil {

    Workbook workbook = null;
    Sheet sheet = null;

    public ExcelUtil(String path) {
        try {
            workbook = Workbook.getWorkbook(new File(path));
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

    public String getCellValue(int column,int row) {
       return  workbook.getSheet(0).getCell(column,row).getContents();
    }


}
