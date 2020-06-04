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
     * @author zjw
     * @return boolean 是否通过认证许可
     */
    private static boolean getLicense()
    {
        boolean result = false;
        try (InputStream is = Word2PdfUtils.class.getClassLoader().getResourceAsStream(LICENCE_NAME))
        {
            License aposeLic = new License();
            assert is != null;
            aposeLic.setLicense(is);
            result = true;
        }
        catch (Exception e)
        {
            log.error("验证License失败:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * {@code doc2pdf} doc转pdf
     * <p>doc文件转成pdf文件</p>
     * @author zjw
     * @param inPath doc文件的路径
     * @param outPath pdf文件路径
     * @throws Exception 异常
     */
    public static void doc2pdf(String inPath, String outPath)
            throws Exception
    {
//         验证License
//        if (!getLicense()) {
//            return;
//        }
        FileOutputStream os = null;
        try
        {
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
        }
        catch (Exception e)
        {
            log.error("转换失败:" + e.getMessage());
            throw new Exception(e);
        }
        finally
        {
            if (null != os)
            {
                os.close();
            }
        }
    }

    public static  void  main(String [] args){
        try {

            doc2pdf("E:\\test\\word05262.doc","E:\\test\\word05262.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
