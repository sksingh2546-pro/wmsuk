

package com.wmsweb.dispatchPlan;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(origins = "*")
public class DispatchPlanController {
    @PostMapping({"/uploadDisptchPlan"})
    public String uploadDispatchData(@RequestParam("file") MultipartFile multipartFile) {
        Workbook workbook = null;
        try {
            workbook = (Workbook) new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        CellReference cellReference = new CellReference("B3");
        int count = 0;
        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            ++count;
            System.out.println("");
            System.out.println("");
            System.out.println(row.getRowNum());
            while (cells.hasNext()) {
                XSSFCell cell = (XSSFCell) cells.next();
                try {
                    System.out.println(cell.getColumnIndex());
                    System.out.println(cell.getCellType());
                    if (!cell.getCellType().equals((Object) CellType.STRING)) {
                        continue;
                    }
                    System.out.println(cell.getStringCellValue());
                } catch (Exception e2) {
                    System.out.println("Exception :  " + e2);
                }
            }
            if (count == 3) {
                break;
            }
        }
        return "successfull";
    }
}
