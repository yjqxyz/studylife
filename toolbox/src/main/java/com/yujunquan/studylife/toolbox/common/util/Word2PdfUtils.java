package com.yujunquan.studylife.toolbox.common.util;

import com.aspose.words.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author yujunquan
 * @Description 描述
 * @createTime 2020-05-26 17:10:33
 */
@Slf4j
public class Word2PdfUtils {

    /**
     * 许可证的配置
     */
    private static final String LICENCE_NAME = "aspose-words-license.xml";

    /**
     * {@code getLicense} 获取认证许可
     * <p>若不验证则转化出的pdf文档会有水印产生</p>
     *
     * @return boolean 是否通过认证许可
     * @author zjw
     */
    private static boolean getLicense() {
        boolean result = false;
        try (InputStream is = Word2PdfUtils.class.getClassLoader().getResourceAsStream(LICENCE_NAME)) {
            License aposeLic = new License();
            assert is != null;
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            log.error("验证License失败:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * {@code doc2pdf} doc转pdf
     * <p>doc文件转成pdf文件</p>
     *
     * @param inPath  doc文件的路径
     * @param outPath pdf文件路径
     * @throws Exception 异常
     * @author zjw
     */
    public static void doc2pdf(String inPath, String outPath)
            throws Exception {
        // 验证License
        if (!getLicense()) {
            log.error("获取证书失败，会有水印，注意");
            // return;
        }
        FileOutputStream os = null;
        try {
            long old = System.currentTimeMillis();
            // 新建一个空白pdf文档
            File file = new File(outPath);
            os = new FileOutputStream(file);
            // inPath是将要被转化的word文档
            Document doc = new Document(inPath);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            doc.save(os, SaveFormat.PDF);
            // EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            // 转化用时
            log.info("共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            log.error("转换失败:" + e.getMessage());
            throw new Exception(e);
        } finally {
            if (null != os) {
                os.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
//            doc2pdf("E:\\test\\src.doc", "E:\\test\\src-18.5.0.pdf");
            // 动态修改达到执行不同逻辑目的
            int editFlag = 0        ;
            int version = 185    ;

            if(editFlag == 0){// 没有编辑过
                processByversion("src"+version);
            }else if(editFlag == 1){
                processByversion("edit"+version);
            }else {
                processByversion("xml2word");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void processByversion(String version){
        switch (version){
            case "src185":
                doc2pdfsrc185();
                break;
            case "src195":
                doc2pdfsrc195();
                break;
            case "src205":
                doc2pdfsrc205();
                break;
            case "edit185":
                doc2pdfedit185();
                break;
            case "edit195":
                doc2pdfedit195();
                break;
            case "edit205":
                doc2pdfedit205();
                break;
            case "xml2word":
                xml2doc2pdf();
                break;
            default:
                break;
        }
    }
    static void xml2doc2pdf(){
        try {
            doc2pdf("E:\\test\\src-edit - 副本xml2word.doc", "E:\\test\\src-edit - 副本xml2word-18.5.0.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void doc2pdfsrc185(){
        try {
            doc2pdf("E:\\test\\src2.doc", "E:\\test\\src-18.5.0.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void doc2pdfsrc195(){
        try {
            doc2pdf("E:\\test\\src.doc", "E:\\test\\src-19.5.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void doc2pdfsrc205(){
        try {
            doc2pdf("E:\\test\\src.doc", "E:\\test\\src-20.5.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void doc2pdfedit185(){
        try {
            doc2pdf("E:\\test\\src-edit.doc", "E:\\test\\src-edit-18.5.0.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void doc2pdfedit195(){
        try {
            doc2pdf("E:\\test\\src-edit.doc", "E:\\test\\src-edit-19.5.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void doc2pdfedit205(){
        try {
            doc2pdf("E:\\test\\src-edit.doc", "E:\\test\\src-edit-20.5.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
