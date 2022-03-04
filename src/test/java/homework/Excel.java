package homework;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public  class Excel {
    private static final String greeting = "Создано тестовым ПО";
    private static final String sheetDefaultName = "Отчет";
    private static final int start_raw_data_position = 4;

    public static void map_to_exel(String storageName, HashMap<String,Integer> storageMap) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(storageName.concat(".xlsx"));
        XSSFCellStyle style_name = createCellStyle(book);




// создания страниц
        XSSFSheet sheet_1 = book.createSheet(sheetDefaultName);
// создание строк
        sheet_1.autoSizeColumn(0);
        sheet_1.autoSizeColumn(1);

        XSSFRow row_ofStorageName = sheet_1.createRow(0);
        XSSFCell cell_ofStorageName = row_ofStorageName.createCell(0);
        cell_ofStorageName.setCellValue(storageName);
        cell_ofStorageName.setCellStyle(style_name);

        XSSFRow row_ofStorageGreeting = sheet_1.createRow(2);
        XSSFCell cell_ofStorageGreeting = row_ofStorageGreeting.createCell(0);
        cell_ofStorageGreeting.setCellValue(greeting);

        int i = 0;
        for (Map.Entry <String, Integer> entry : storageMap.entrySet()) {

            XSSFRow row = sheet_1.createRow((short) start_raw_data_position+i+1);
            XSSFCell cell_1 = row.createCell(0, CellType.STRING);
            XSSFCell cell_2 = row.createCell(1, CellType.NUMERIC);
            if (i==0){
               XSSFRow named_row = sheet_1.createRow(start_raw_data_position);
                named_row.createCell(0).setCellValue("Название товара");
                named_row.createCell(1).setCellValue("Вес (ск.ед)");
            }
            cell_1.setCellValue(entry.getKey());
            cell_2.setCellValue(entry.getValue());
            i++;
        }

        // Закрытие
        book.write(fileOut);
        fileOut.close();

//
//        sheet_1.autoSizeColumn(1);
//        XSSFRow row = sheet_1.createRow((short)0);
//        row.setHeightInPoints(80.0f);
// создание и форматирование ячеек


//        XSSFCell cell = row.createCell(0);
//        cell.setCellType(CellType.NUMERIC);
//        cell.setCellValue(1.2);
//
//        cell = row.createCell(1);
//        cell.setCellType(CellType.STRING);
//        cell.setCellValue("Строковое представление");
//
//        cell = row.createCell(2);
//        cell.setCellType(CellType.FORMULA);
//        cell.setCellValue("SUM(B3:B5)");
    }

    private static XSSFCellStyle createCellStyle (XSSFWorkbook book)
    {
        BorderStyle thin  = BorderStyle.THIN;
        short       black = IndexedColors.BLACK.getIndex();

        XSSFCellStyle style = book.createCellStyle();

        style.setWrapText(true);
        style.setAlignment        (HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setBorderTop        (thin);
        style.setBorderBottom     (thin);
        style.setBorderRight      (thin);
        style.setBorderLeft       (thin);

        style.setTopBorderColor   (black);
        style.setRightBorderColor (black);
        style.setBottomBorderColor(black);
        style.setLeftBorderColor  (black);

        // Создание шрифта
        XSSFFont font = book.createFont();

        font.setFontHeightInPoints((short)24);
        font.setFontName("Candles");
        font.setItalic(true);
        font.setStrikeout(false);
// цвет шрифта
        font.setColor(new XSSFColor(new java.awt.Color(150,50,40)));
// Создание стиля с определением в нем шрифта
        style = book.createCellStyle();
        style.setFont(font);

// Создание ячейки с определением ее стиля
//        XSSFRow row = sheet.createRow(0);
//        XSSFCell cell = row.createCell(1);
//        cell.setCellValue("Тестовый шрифт");
//        cell.setCellStyle(style);

        return style;
    }
}
