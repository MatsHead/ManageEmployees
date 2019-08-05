package pl.matshead.examapp.excelHandlerPackage;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.matshead.examapp.model.Employee;
import pl.matshead.examapp.static_values.CellFormat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesExcelHandler {
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    private WorkbookSettings wbSettings;
    private static WritableCellFormat writableCellFormatGreen;
    private static WritableCellFormat writableCellFormatBlue;


    static {

            WritableFont writableFont = new WritableFont(WritableFont.createFont("Arial")
                    ,WritableFont.DEFAULT_POINT_SIZE
                    ,WritableFont.BOLD
                    ,false
                    ,UnderlineStyle.NO_UNDERLINE
                    ,Colour.GREEN);

            WritableFont writableFont_2 = new WritableFont(WritableFont.createFont("Arial")
                    ,WritableFont.DEFAULT_POINT_SIZE
                    ,WritableFont.NO_BOLD
                    ,false
                    ,UnderlineStyle.NO_UNDERLINE
                    ,Colour.LIGHT_BLUE);

        writableCellFormatBlue = new WritableCellFormat(writableFont);
    }

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write(List<Employee> employees) throws IOException, WriteException {
        File file = new File(inputFile);
        wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet, employees);
        createContent(excelSheet, employees);

        workbook.write();
        workbook.close();
    }


    private void createLabel(WritableSheet sheet, List<Employee> employees)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write a header
        addCaption(sheet, 0, 0, "EMPLOYEES");
        sheet.mergeCells(0, 0, employees.size()+1,  0);
    }

    private void createContent(WritableSheet sheet, List<Employee> employees ) throws WriteException{
        int rowIterator = 0;
        // write info about employees
        for (int i = 0; i<employees.size(); i++){
            if (i == 0 ) {
                // if there is a first row, write labels for them first
                addLabelForEmployeesAttributes(sheet, 0, ++rowIterator);
            }
            addText(sheet, 0, ++rowIterator, employees.get(i));
        }
        addCaption(sheet, 0, employees.size()+2, "Employees amount: ");
        sheet.mergeCells(0, employees.size()+2, 3, employees.size()+2);
        addCaption(sheet,4, employees.size()+2, String.valueOf(employees.size()));
    }
    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws WriteException {
        Label label;
        label = new Label(column, row, s, CellFormat.NORMAL_CENTER.getWritableCellFormat());
        sheet.addCell(label);
    }

    private void addLabelForEmployeesAttributes(WritableSheet sheet, int column, int row)
            throws WriteException {
        List<Label> labelList = new ArrayList<>();
        labelList.add( new Label(column++, row, "ID", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "NAME", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "SURNAME", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "PERSONAL_ID", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "COUNTRY", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "CITY", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "STREET", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "FLAT", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "HOUSE", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "ZIP_CODE", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "EMAIL", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "POSITION", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column++, row, "SALARY", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        labelList.add( new Label(column, row, "HAS CHILDREN?", CellFormat.GREEN_LEFT.getWritableCellFormat()));
        for (Label label : labelList) {
            sheet.addCell(label);
        }

    }
    private void addText(WritableSheet sheet, int columnStart, int row, Employee employee) throws WriteException,
            RowsExceededException {
            List<Label> labelList = new ArrayList<>();
            labelList.add( new Label(columnStart++, row, String.valueOf(employee.getId()), CellFormat.DARK_BLUE_LEFT.getWritableCellFormat()));
            labelList.add( new Label(columnStart++, row, employee.getName()));
            labelList.add( new Label(columnStart++, row, employee.getSurname()));
            labelList.add( new Label(columnStart++, row, employee.getPersonalId()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getCountry()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getCity()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getStreet()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getFlat()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getHouse()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getZipCode()));
            labelList.add( new Label(columnStart++, row, employee.getAddress().getEmail()));
            labelList.add( new Label(columnStart++, row, employee.getPosition() != null ?  employee.getPosition().name() : ""));
            labelList.add( new Label(columnStart++, row, String.valueOf(employee.getSalary())));
            labelList.add( new Label(columnStart, row, String.valueOf(employee.isChildren())));

        for (Label label : labelList) {
            sheet.addCell(label);
        }
    }

}
