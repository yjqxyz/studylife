package com.yujunquan.studylife.util;

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
     * 从Excel文件得到二维数组，每个sheet的第一行为标题
     * @param file Excel文件
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String[][] getData(File file) throws FileNotFoundException,
            IOException {
        return getData(file, 1);
    }

    /**
     * 从Excel文件得到二维数组
     * 
     * @param file Excel文件
     * @param ignoreRows 忽略的行数，通常为每个sheet的标题行数
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
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
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
                        // 注意：一定要设成这个，否则可能会出现乱码
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
                            // 导入时如果为公式生成的数据则无值
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
                throw new Exception("获取客户信息发生异常，请重新导入！");
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
     * 从Excel文件得到二维数组
     * 
     * @param file Excel文件
     * @param ignoreRows 忽略的行数，通常为每个sheet的标题行数
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
        // 打开HSSFWorkbook
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
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
                        // 注意：一定要设成这个，否则可能会出现乱码
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
                            // 导入时如果为公式生成的数据则无值
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
     * 行复制功能 
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
     * 复制单元格 
     * @param srcCell 
     * @param distCell 
     * @param copyValueFlag  true则连同cell的内容一起复制 
     */  
    public static void copyCell(HSSFWorkbook wb, HSSFCell srcCell, HSSFCell distCell,
                                boolean copyValueFlag) {
        //样式  
        distCell.setCellStyle(srcCell.getCellStyle());  
        //评论  
        if (srcCell.getCellComment() != null) {  
            distCell.setCellComment(srcCell.getCellComment());  
        }  
        // 不同数据类型处理  
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
     * 设置某些列的值只能输入预制的数据,显示下拉框. 
     * @param sheet  要设置的sheet. 
     * @param textlist  下拉框显示的内容 
     * @param firstRow  开始行 
     * @param endRow    结束行 
     * @param firstCol  开始列 
     * @param endCol    结束列 
     * @return 设置好的sheet. 
     */ 
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
                                              String[] textlist, int firstRow, int endRow, int firstCol,
                                              int endCol){
        // 加载下拉列表内容  
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);  
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);  
        // 数据有效性对象  
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
        // 打开HSSFWorkbook
        XSSFWorkbook wb = new XSSFWorkbook(in);
        XSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
        	XSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
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
                        // 注意：一定要设成这个，否则可能会出现乱码
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
                            // 导入时如果为公式生成的数据则无值
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
                throw new Exception("获取客户信息发生异常，请重新导入！");
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
        // 打开HSSFWorkbook
    	try{
    	    fs = new POIFSFileSystem(in);
    	}catch (Exception e) {
    		return getDataFromExcel2007(file,ignoreRows);
		}
    	 HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFCell cell = null;
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            HSSFSheet st = wb.getSheetAt(sheetIndex);
            // 第一行为标题，不取
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
                        // 注意：一定要设成这个，否则可能会出现乱码
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
                            // 导入时如果为公式生成的数据则无值
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
                throw new Exception("获取客户信息发生异常，请重新导入！");
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
     * 对象转流
     *
     * @param list      传入的数据
     * @param sheetName 工作表名字
     * @param title     表头
     *                  key-为英文表头 表要javabean对应所以要遵循如下规则
     *                  根据带路径或不带路径的属性名获取属性值,即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     *                  value-对应中文在excel中产生的表头名
     * @return ByteArrayInputStream 字节流对象
     * @throws Exception 传入数据为空
     */
    public ByteArrayInputStream list2ExcelStream(List list, String sheetName,
                                                 LinkedHashMap<String, String> title) throws Exception {
        byte[] data = null;
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        // 首先判断list里面是不是有值
        if (list == null || list.size() == 0) {
            // 如果空或者大小为0就直接return 或者抛出异常
            // return;
            throw new Exception("传入的数据为空或者大小为零");
        }
        try {

            out = new ByteArrayOutputStream();
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet(sheetName);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中
            fillVeticalSheet(sheet, list, title);
            sheet.autoSizeColumn(0);//第一列 报案号单独自适应
            for (int i = 1; i < title.size(); i++) {
                sheet.autoSizeColumn(i);//设置自动适应宽度
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 15 / 10);// 解决自动设置列宽中文失效的问题
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
     * 设置表头 数据
     *
     * @param sheet 工作表对象
     * @param list  存放对象的list
     * @param title 中英英文对应的表头数据
     */
    private  void fillVeticalSheet(Sheet sheet, List<Object> list, LinkedHashMap<String, String> title) {
        if (null == list || null == title) {
            return;
        }
        // 定义存放英文字段名和中文字段名称的数组
        String[] enFields = new String[title.size()];
        String[] cnFields = new String[title.size()];
        // 填充数组
        int count = 0;
        for (Map.Entry<String, String> entry : title.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
        // 填充表头
        Row row = sheet.createRow(0);
        for (int j = 0; j < cnFields.length; j++) {
            Cell cell = row.createCell(j);
            cell.setCellValue(cnFields[j]);
        }
        // 填充数据
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
     * 根据带路径或不带路径的属性名获取属性值,即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.
     * name等
     *
     * @param fieldNameSequence 对象名
     * @param o                 对象
     * @return 对象的值
     */
    private Object getFieldValueByNameSequence(String fieldNameSequence, Object o) {

        Object value = null;
        try {
            // 将fieldNameSequence进行拆分
            String[] attributes = fieldNameSequence.split("\\.");
            if (attributes.length == 1) {
                value = PropertyUtils.getProperty(o, fieldNameSequence);

            } else {
                // 根据属性名获取属性对象
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
     * 把文件输出到本地
     */
    private void write2LocalFile (){
        //  输出到本地  test begin
            /*ByteArrayInputStream byteArrayInputStream = new ExcelUtil().list2ExcelStream(prplAcciOneKeyRegistDtos,"result",title);
             // 打开目的输出流，不存在则会创建 输出到本地试试，等内容正确就OK了
            OutputStream outputStream = new FileOutputStream("d:\\testExcel\\out.xls");
            int ch = 0;
            try {
                while ((ch = byteArrayInputStream.read()) != -1) {
                    outputStream.write(ch);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                //关闭输入流等（略）
                outputStream.close();
                byteArrayInputStream.close();
            }*/
        //  输出到本地  test begin
    }


}