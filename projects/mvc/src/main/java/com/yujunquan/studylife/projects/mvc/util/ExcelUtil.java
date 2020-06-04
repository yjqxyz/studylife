package com.yujunquan.studylife.projects.mvc.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExcelUtil {
    
    /**
     * ��Excel�ļ��õ���ά���飬ÿ��sheet�ĵ�һ��Ϊ����
     * @param file Excel�ļ�
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file) throws FileNotFoundException,
            IOException {
        return getData(file, 1);
    }

    /**
     * ��Excel�ļ��õ���ά����
     * 
     * @param file Excel�ļ�
     * @param ignoreRows ���Ե�������ͨ��Ϊÿ��sheet�ı�������
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file, int ignoreRows)
            throws FileNotFoundException, IOException {
        ArrayList<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        try{
        // ��HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // ��һ��Ϊ���⣬��ȡ
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // ע�⣺һ��Ҫ��������������ܻ��������
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0.####").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y"
                                    : "N");
                            break;
                        default:
                            value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = StringUtils.trim(StringUtils.trim(value));
                    values[columnIndex]=values[columnIndex].replaceAll(String.valueOf((char)160)," ");
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(values[columnIndex]);
                    values[columnIndex] = m.replaceAll("");
                    hasValue = true;
                }

                if (hasValue) {
                    result.add(values);
                }
            }
        }
        }catch(RecordFormatException rfe){
            try {
                throw new Exception("��ȡ�ͻ���Ϣ�����쳣�������µ��룡");
            } catch (Exception e) {
                e.printStackTrace();
            }
            rfe.printStackTrace();
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }
    
    /**
     * ��Excel�ļ��õ���ά����
     * 
     * @param file Excel�ļ�
     * @param ignoreRows ���Ե�������ͨ��Ϊÿ��sheet�ı�������
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getDataForDot2(File file, int ignoreRows)
            throws FileNotFoundException, IOException {
        ArrayList<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        // ��HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // ��һ��Ϊ���⣬��ȡ
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // ע�⣺һ��Ҫ��������������ܻ��������
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0.00").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y"
                                    : "N");
                            break;
                        default:
                            value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        break;
                    }
                    values[columnIndex] = StringUtils.trim(value);
                    values[columnIndex]=values[columnIndex].replaceAll(String.valueOf((char)160)," ");
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(values[columnIndex]);
                    values[columnIndex] = m.replaceAll("");
                    hasValue = true;
                }

                if (hasValue) {
                    result.add(values);
                }
            }
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }
    
    /** 
     * �и��ƹ��� 
     * @param fromRow 
     * @param toRow 
     */  
    public static void copyRow(HSSFWorkbook wb, HSSFRow fromRow, HSSFRow toRow, boolean copyValueFlag){
        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
            HSSFCell tmpCell = (HSSFCell) cellIt.next();
            HSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb,tmpCell, newCell, copyValueFlag);  
        }  
    }  
    /** 
     * ���Ƶ�Ԫ�� 
     * @param srcCell 
     * @param distCell 
     * @param copyValueFlag  true����ͬcell������һ���� 
     */  
    public static void copyCell(HSSFWorkbook wb, HSSFCell srcCell, HSSFCell distCell,
                                boolean copyValueFlag) {
        //��ʽ  
        distCell.setCellStyle(srcCell.getCellStyle());  
        //����  
        if (srcCell.getCellComment() != null) {  
            distCell.setCellComment(srcCell.getCellComment());  
        }  
        // ��ͬ�������ʹ���  
        int srcCellType = srcCell.getCellType();  
        distCell.setCellType(srcCellType);  
        if (copyValueFlag) {
            if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
                if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());  
                } else {  
                    distCell.setCellValue(srcCell.getNumericCellValue());  
                }  
            } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
                // nothing21  
            } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());  
            } else { // nothing29  
            }  
        }  
    }  
    
    /** 
     * ����ĳЩ�е�ֵֻ������Ԥ�Ƶ�����,��ʾ������. 
     * @param sheet  Ҫ���õ�sheet. 
     * @param textlist  ��������ʾ������ 
     * @param firstRow  ��ʼ�� 
     * @param endRow    ������ 
     * @param firstCol  ��ʼ�� 
     * @param endCol    ������ 
     * @return ���úõ�sheet. 
     */ 
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
                                              String[] textlist, int firstRow, int endRow, int firstCol,
                                              int endCol){
        // ���������б�����  
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);  
        // ����������Ч�Լ������ĸ���Ԫ����,�ĸ������ֱ��ǣ���ʼ�С���ֹ�С���ʼ�С���ֹ��  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);  
        // ������Ч�Զ���  
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);  
        sheet.addValidationData(data_validation_list);  
        return sheet;  
    }
    
    public static String[][] getDataFromExcel2007(File file, int ignoreRows)
    		throws FileNotFoundException, IOException {

        ArrayList<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        try{
        // ��HSSFWorkbook
        XSSFWorkbook wb = new XSSFWorkbook(in);
        XSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
        	XSSFSheet st = wb.getSheetAt(sheetIndex);
            // ��һ��Ϊ���⣬��ȡ
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
            	XSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // ע�⣺һ��Ҫ��������������ܻ��������
                        switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0.##").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case XSSFCell.CELL_TYPE_FORMULA:
                            // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y"
                                    : "N");
                            break;
                        default:
                            value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        continue;
                    }
                    values[columnIndex] = StringUtils.trim(StringUtils.trim(value));
                    values[columnIndex]=values[columnIndex].replaceAll(String.valueOf((char)160)," ");
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(values[columnIndex]);
                    values[columnIndex] = m.replaceAll("");
                    hasValue = true;
                }

                if (hasValue) {
                    result.add(values);
                }
            }
        }
        }catch(RecordFormatException rfe){
            try {
                throw new Exception("��ȡ�ͻ���Ϣ�����쳣�������µ��룡");
            } catch (Exception e) {
                e.printStackTrace();
            }
            rfe.printStackTrace();
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;   
    }
    
    public static String[][] getDataNew(File file, int ignoreRows)
            throws FileNotFoundException, IOException {
        ArrayList<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        POIFSFileSystem fs = null;
        try{
        // ��HSSFWorkbook
    	try{
    	    fs = new POIFSFileSystem(in);
    	}catch (Exception e) {
    		return getDataFromExcel2007(file,ignoreRows);
		}
    	 HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // ��һ��Ϊ���⣬��ȡ
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                HSSFRow row = st.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                int tempRowSize = row.getLastCellNum() + 1;
                if (tempRowSize > rowSize) {
                    rowSize = tempRowSize;
                }
                String[] values = new String[rowSize];
                Arrays.fill(values, "");
                boolean hasValue = false;
                for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                    String value = "";
                    cell = row.getCell(columnIndex);
                    if (cell != null) {
                        // ע�⣺һ��Ҫ��������������ܻ��������
                        switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                if (date != null) {
                                    value = new SimpleDateFormat("yyyy-MM-dd")
                                            .format(date);
                                } else {
                                    value = "";
                                }
                            } else {
                                value = new DecimalFormat("0.##").format(cell
                                        .getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            // ����ʱ���Ϊ��ʽ���ɵ���������ֵ
                            if (!cell.getStringCellValue().equals("")) {
                                value = cell.getStringCellValue();
                            } else {
                                value = cell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = (cell.getBooleanCellValue() == true ? "Y"
                                    : "N");
                            break;
                        default:
                            value = "";
                        }
                    }
                    if (columnIndex == 0 && value.trim().equals("")) {
                        continue;
                    }
                    values[columnIndex] = StringUtils.trim(StringUtils.trim(value));
                    values[columnIndex]=values[columnIndex].replaceAll(String.valueOf((char)160)," ");
                    Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                    Matcher m = p.matcher(values[columnIndex]);
                    values[columnIndex] = m.replaceAll("");
                    hasValue = true;
                }

                if (hasValue) {
                    result.add(values);
                }
            }
        }
        }catch(RecordFormatException rfe){
            try {
                throw new Exception("��ȡ�ͻ���Ϣ�����쳣�������µ��룡");
            } catch (Exception e) {
                e.printStackTrace();
            }
            rfe.printStackTrace();
        }
        in.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
    }

    /**
     * ����ת��
     *
     * @param list      ���������
     * @param sheetName ����������
     * @param title     ��ͷ
     *                  key-ΪӢ�ı�ͷ ��Ҫjavabean��Ӧ����Ҫ��ѭ���¹���
     *                  ���ݴ�·���򲻴�·������������ȡ����ֵ,�����ܼ�����������userName�ȣ��ֽ��ܴ�·��������������student.department.name��
     *                  value-��Ӧ������excel�в����ı�ͷ��
     * @return ByteArrayInputStream �ֽ�������
     * @throws Exception ��������Ϊ��
     */
    public ByteArrayInputStream list2ExcelStream(List list, String sheetName,
                                                 LinkedHashMap<String, String> title) throws Exception {
        byte[] data = null;
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        // �����ж�list�����ǲ�����ֵ
        if (list == null || list.size() == 0) {
            // ����ջ��ߴ�СΪ0��ֱ��return �����׳��쳣
            // return;
            throw new Exception("���������Ϊ�ջ��ߴ�СΪ��");
        }
        try {

            out = new ByteArrayOutputStream();
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet(sheetName);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);//����
            fillVeticalSheet(sheet, list, title);
            sheet.autoSizeColumn(0);//��һ�� �����ŵ�������Ӧ
            for (int i = 1; i < title.size(); i++) {
                sheet.autoSizeColumn(i);//�����Զ���Ӧ���
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 15 / 10);// ����Զ������п�����ʧЧ������
            }
            wb.write(out);
            data = out.toByteArray();
            in = new ByteArrayInputStream(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return in;
    }

    /**
     * ���ñ�ͷ ����
     *
     * @param sheet ���������
     * @param list  ��Ŷ����list
     * @param title ��ӢӢ�Ķ�Ӧ�ı�ͷ����
     */
    private  void fillVeticalSheet(Sheet sheet, List<Object> list, LinkedHashMap<String, String> title) {
        if (null == list || null == title) {
            return;
        }
        // ������Ӣ���ֶ����������ֶ����Ƶ�����
        String[] enFields = new String[title.size()];
        String[] cnFields = new String[title.size()];
        // �������
        int count = 0;
        for (Map.Entry<String, String> entry : title.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
        // ����ͷ
        Row row = sheet.createRow(0);
        for (int j = 0; j < cnFields.length; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(cnFields[j]);
        }
        // �������
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            row = sheet.createRow(i + 1);
            for (int j = 0; j < enFields.length; j++) {
                Object objValue = getFieldValueByNameSequence(enFields[j], item);
                if (objValue instanceof String) {
                    row.createCell(j).setCellValue((String) objValue);
                } else if (objValue instanceof Double) {
                    row.createCell(j).setCellValue((Double) objValue);
                } else if (objValue instanceof RichTextString) {
                    row.createCell(j).setCellValue((RichTextString) objValue);
                } else if (objValue instanceof Date) {
                    row.createCell(j).setCellValue((Date) objValue);
                } else if (objValue instanceof Boolean) {
                    row.createCell(j).setCellValue((Boolean) objValue);
                }
            }
        }
    }

    /**
     * ���ݴ�·���򲻴�·������������ȡ����ֵ,�����ܼ�����������userName�ȣ��ֽ��ܴ�·��������������student.department.
     * name��
     *
     * @param fieldNameSequence ������
     * @param o                 ����
     * @return �����ֵ
     */
    private Object getFieldValueByNameSequence(String fieldNameSequence, Object o) {

        Object value = null;
        try {
            // ��fieldNameSequence���в��
            String[] attributes = fieldNameSequence.split("\\.");
            if (attributes.length == 1) {
                value = PropertyUtils.getProperty(o, fieldNameSequence);

            } else {
                // ������������ȡ���Զ���
                Object fieldObj = PropertyUtils.getProperty(o, attributes[0]);

                String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
                value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
            }

            if (value instanceof Date) {
                value = new SimpleDateFormat("yyyy-MM-dd").format(value).toString();
            }

            // if (value.toString().endsWith(".0000")) {
            // String txt =value.toString();
            // value = txt.substring(0, txt.lastIndexOf("."));
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * ���ļ����������
     */
    private void write2LocalFile (){
        //  ���������  test begin
            /*ByteArrayInputStream byteArrayInputStream = new ExcelUtil().list2ExcelStream(prplAcciOneKeyRegistDtos,"result",title);
             // ��Ŀ�����������������ᴴ�� ������������ԣ���������ȷ��OK��
            OutputStream outputStream = new FileOutputStream("d:\\testExcel\\out.xls");
            int ch = 0;
            try {
                while ((ch = byteArrayInputStream.read()) != -1) {
                    outputStream.write(ch);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                //�ر��������ȣ��ԣ�
                outputStream.close();
                byteArrayInputStream.close();
            }*/
        //  ���������  test begin
    }


}