/*
 * Author: Matěj Šťastný
 * Date created: 9/8/2024
 * Github link: https://github.com/kireiiiiiiii/ShootingStars
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.kireiiiiiiii.shooting_stars.tools;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * Util class to handle {@code .xlsx} spreadsheet files.
 * 
 */
public class SpreadsheetUtil {

    /**
     * Returns the contents of an {@code .xlsx} spreadsheet cell as a {@code String}
     * value.
     * 
     * @param fileName   - name of the spreadsheet file, {@code .xlsx} will be
     *                   appended
     * @param sheetName  - name of the target sheet name.
     * @param rowNumber  - row number, first row is 0.
     * @param cellNumber - the index of the target cell in the row, first cell is 0.
     * @return a {@code String} value of the cell, {@code null} if empty.
     */
    public static String getCellValue(String fileName, String sheetName, int rowNumber, int cellNumber) {
        String cellValue = null;
        String path = File.separator + "spreadsheets" + File.separator + fileName + ".xlsx";
        try (InputStream inputStream = SpreadsheetUtil.class.getResourceAsStream(path);
                Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                return null;
            }
            Row row = sheet.getRow(rowNumber);
            if (row == null) {
                return null;
            }
            Cell cell = row.getCell(cellNumber);

            if (cell != null) {
                cellValue = cell.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellValue;
    }

    public static ArrayList<String> getRowValues(String fileName, String sheetName, int rowNumer) {
        ArrayList<String> row = new ArrayList<String>();
        boolean foundEmpty = false;
        int currCell = 0;
        while (!foundEmpty) {
            String cell = getCellValue(fileName, sheetName, rowNumer, currCell);
            foundEmpty = cell == null;
            if (cell != null) {
                row.add(cell);
            }
            currCell++;
        }
        return row;
    }

    public static ArrayList<String> getColumnValues(String fileName, String sheetName, int columnNumber) {
        System.out.println(fileName + " " + sheetName + " " + columnNumber); // TODO DEBUG
        ArrayList<String> collum = new ArrayList<String>();
        boolean foundEmpty = false;
        int currCell = 0;
        while (!foundEmpty) {
            System.out.println(currCell); // TODO DEBUG
            String cell = getCellValue(fileName, sheetName, currCell, columnNumber);
            foundEmpty = cell == null;
            if (cell != null) {
                collum.add(cell);
            }
            currCell++;
        }
        System.out.println(collum.size() + " " + collum); // TODO DEBUG
        return collum;
    }

}
