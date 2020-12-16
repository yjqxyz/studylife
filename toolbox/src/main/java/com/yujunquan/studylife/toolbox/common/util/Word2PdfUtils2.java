package com.yujunquan.studylife.toolbox.common.util;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author yujunquan
 * @Description 描述
 * @createTime 2020-06-07 20:16:59
 */
@Slf4j
public class Word2PdfUtils2 {

    /**
     * @Description: 验证License
     * @Param: []
     * @return: boolean
     * @Author: Jet.Chen
     * @Date: 2019/4/8 11:52
     */
    public static boolean checkLicense() throws Exception {
        boolean result = false;
        try {
            InputStream is = com.aspose.words.Document.class.getResourceAsStream("/com.aspose.words.lic_2999.xml");
            if (is == null) return false;
            License asposeLicense = new License();
            asposeLicense.setLicense(is);
//            System.out.println("Aspose isLicensed: " + asposeLicense.isLicensed());
            result = true;
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public static void doc2pdf(String inPath, String outPath) throws Exception {
        // 验证License 否则转出的pdf文档有水印
        if (!checkLicense()) {
            log.error("获取证书失败，会有水印，注意");
            // throw new Exception("com.aspose.words lic ERROR!");
        }
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath);
            os = new FileOutputStream(file);
            if (!System.getProperty("os.name").toLowerCase().startsWith("windows")) {
                // linux 需要配置字体库
                FontSettings.getDefaultInstance().setFontsFolder("/data/crm/fonts", false);
            }
            // 读原始文档
            Document doc = new Document(inPath);
            // 转 pdf
            doc.save(os, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            // 转化用时
            log.info("共耗时：" + ((now - old) / 1000.0) + "秒");
        }  catch (Exception e) {
            log.error("转换失败:" + e.getMessage());
            throw new Exception(e);
        } finally {
            if (null != os) {
                os.close();
            }
        }
    }

    static void doc2pdfByEditFlagAndVersion(String editFlag,String version){
        try {
            doc2pdf("E:\\test\\"+editFlag+".doc", "E:\\test\\"+editFlag+version+".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
//            doc2pdf("E:\\test\\src.doc", "E:\\test\\src-18.5.0.pdf");
            // 动态修改达到执行不同逻辑目的
            String editFlag = "src"      ;
//            String editFlag = "src-edit"      ;
            String version = "20.5.0"    ;

            doc2pdfByEditFlagAndVersion(editFlag,version);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
