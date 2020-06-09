package com.base.utils;

import com.lowagie.text.Document;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * 文档操作
 */
public class DocumentUtil {

    private static DocumentUtil instance;

    public DocumentUtil() {
    }
    public static DocumentUtil getInstance(){
        if(instance == null){
            synchronized (DocumentUtil.class){
                if(instance == null){
                    instance = new DocumentUtil();
                }
            }
        }
        return instance;
    }




    /**
     *  itext 操作
     * @param path
     * @param document
     * @return
     */
    public  String itextHtmlToWord (String path, Document document){
        return null;
    }



    /**
     * POI操作
     * @return
     */
    public String POIHtmlToWord(String path, MultipartFile file) throws ParserConfigurationException {
        try {
            File path1 = new File(ResourceUtils.getURL("classpath:").getPath()+file.getOriginalFilename()+".html");
            if(!path1.exists()){
                path1.mkdir();
            }
            org.w3c.dom.Document document = getDocument(file);
            if(document == null){
                return null;
            }

            WordToHtmlConverter wth = new WordToHtmlConverter(document);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    //获取document
    public org.w3c.dom.Document getDocument(MultipartFile file){
        try {
            HWPFDocument worddoc = new HWPFDocument(file.getInputStream());
            org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }



     /*   try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(),"utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) !=null){
                stringBuffer.append(line);
            }
            InputStream inputStream = new ByteArrayInputStream(stringBuffer.toString().getBytes());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(inputStream);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return null;
    }

}
