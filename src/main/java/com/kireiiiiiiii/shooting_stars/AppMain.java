/*
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
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

package com.kireiiiiiiii.shooting_stars;

import javax.swing.SwingUtilities;

import com.kireiiiiiiii.shooting_stars.constants.Logs;

import java.io.File;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Main method for
 * 
 */
public class AppMain {

    /////////////////
    // Constatns
    ////////////////

    public static final String APP_NAME = "ShootingStars";

    /////////////////
    // Variables
    ////////////////

    @SuppressWarnings("unused")
    private static Game game;

    /////////////////
    // Main method
    ////////////////

    public static void main(String[] args) {
        Runnable myApp = () -> {
            Logs.log(Logs.APP_START);
            game = new Game();
        };
        SwingUtilities.invokeLater(myApp);
    }

    // ! DEBUG MAIN METHOD
    // public static void main(String[] args) {
    // String cell = getCellValue("dialogue", "dialogue", 0, 0);
    // System.out.println(cell);
    // }

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
        try (InputStream inputStream = AppMain.class.getResourceAsStream(path);
                Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNumber);
            Cell cell = row.getCell(cellNumber);

            if (cell != null) {
                cellValue = cell.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellValue;
    }

}
