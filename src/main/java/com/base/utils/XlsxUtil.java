package com.base.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.cells.WorksheetCollection;
import com.aspose.slides.*;
import com.aspose.words.*;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *  工具类
 * @ClassName XlsxUtil,wordUtil
 * @Description
 * @创建时间: 2019/5/25  17:19
 * @创建人: 小猴子
 */
public class XlsxUtil {
    final static String path=XlsxUtil.class.getResource("/").getPath()+"downloadFile/";
    static InputStream license;

    /**
     * 获取license
     *
     * @return
     */
    public static boolean getLicensePPT() {
        boolean result = false;
        try {
            String pathUrl=XlsxUtil.class.getResource("").getPath();
            license = new FileInputStream(pathUrl+"license.xml");// 凭证文件
            com.aspose.slides.License aposeLic =new com.aspose.slides.License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获取license
     *
     * @return
     */
    public static boolean getLicenseExcel() {
        boolean result = false;
        try {
            String pathUrl=XlsxUtil.class.getResource("").getPath();
            license = new FileInputStream(pathUrl+"license.xml");// 凭证文件
           com.aspose.cells.License aposeLic =new com.aspose.cells.License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  批量导入
     * @param file 文件流
     * @param clazz 类
     * @param title1 属性名1 对应第一列
     * @param title2 属性名2 对应第二列
     * @param title3 属性名3 对应第三列
     * @param title4 ..
     * @param title5 ..
     * @return 类集合
     * @throws IOException
     */
    public static List readFile(MultipartFile file, Class clazz, String title1, String title2, String title3, String title4, String title5) throws IOException {
        String className = clazz.getName();
        List list = new ArrayList<>();
        try {
            Workbook wb = null;
            if (file.getOriginalFilename().endsWith(".xlsx")) {
                wb = new XSSFWorkbook(file.getInputStream());
            } else if (file.getOriginalFilename().endsWith(".xls")) {
                wb = new HSSFWorkbook(file.getInputStream());
            } else {
                return null;
            }
            Sheet sheet = wb.getSheetAt(0);
            if (sheet != null) {
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //解决数字和字符串转换的问题 明天
                    String value1 = "";
                    String value2 = "";
                    String value3 = "";
                    String value4 = "";
                    String value5 = "";
                    Cell c1 = row.getCell(0);
                    if (c1 != null) {
                        c1.setCellType(Cell.CELL_TYPE_STRING);
                        value1 = c1.getStringCellValue();
                        /*value1 = value1.replace("&#160;"," ");
                        value1 = value1.replace("&#183;","·");*/
                    }
                    Cell c2 = row.getCell(1);
                    if (c2 != null) {
                        c2.setCellType(Cell.CELL_TYPE_STRING);
                        value2 = c2.getStringCellValue();
                        /*value2 = value2.replace("&#160;"," ");
                        value2 = value2.replace("&#183;","·");*/
                    }
                    Cell c3 = row.getCell(2);
                    if (c3 != null) {
                        c3.setCellType(Cell.CELL_TYPE_STRING);
                        value3 = c3.getStringCellValue();
                        /*value3 = value3.replace("&#160;"," ");
                        value3 = value3.replace("&#183;","·");*/
                    }
                    Cell c4 = row.getCell(3);
                    if (c4 != null) {
                        c4.setCellType(Cell.CELL_TYPE_STRING);
                        value4 = c4.getStringCellValue();
                       /* value4 = value4.replace("&#160;"," ");
                        value5 = value4.replace("&#183;","·");*/
                    }
                    Cell c5 = row.getCell(4);
                    if (c5 != null) {
                        c5.setCellType(Cell.CELL_TYPE_STRING);
                        value5 = c5.getStringCellValue();
                        /*value5 = value5.replace("&#160;"," ");
                        value5 = value5.replace("&#183;","·");*/
                    }

                    Field[] fields = clazz.getDeclaredFields();
                    Class aClass = Class.forName(className);
                    Object object = aClass.newInstance();
                    for (Field field : fields) {
                        boolean flog=false;
                        if(field.getName()==title1){
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), object.getClass());
                            Method setMethod = propertyDescriptor.getWriteMethod();
                            if (setMethod != null) {
                                setMethod.invoke(object, value1);//invoke是执行set方法
                            }
                        }else if (null !=title2 && field.getName()==title2){
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), object.getClass());
                            Method setMethod = propertyDescriptor.getWriteMethod();
                            if (setMethod != null) {
                                setMethod.invoke(object, value2);//invoke是执行set方法
                            }
                        }else if (null !=title3 && field.getName()==title3){
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), object.getClass());
                            Method setMethod = propertyDescriptor.getWriteMethod();
                            if (setMethod != null) {
                                setMethod.invoke(object, value3);//invoke是执行set方法
                            }
                        }else if (null !=title4 && field.getName()==title4){
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), object.getClass());
                            Method setMethod = propertyDescriptor.getWriteMethod();
                            if (setMethod != null) {
                                setMethod.invoke(object, value4);//invoke是执行set方法
                            }
                        }else if (null !=title5 && field.getName()==title5){
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), object.getClass());
                            Method setMethod = propertyDescriptor.getWriteMethod();
                            if (setMethod != null) {
                                setMethod.invoke(object, value5);//invoke是执行set方法
                            }
                        }
                    }
                    list.add(object);
                }
                return list;
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * word 转html 存oss
     * @param in 文件流
     * @return
     * @throws Exception
     */
    public static String showWordString( InputStream in) throws Exception{
        Document doc = new Document(in);
        HtmlSaveOptions hso = new HtmlSaveOptions(SaveFormat.HTML);
        hso.setExportLanguageInformation(false);
        hso.setExportImagesAsBase64(true);//base6输出标签
        hso.setScaleImageToShapeSize(true);
        hso.setUseHighQualityRendering(true);//是否高保真图片
        hso.setExportHeadersFootersMode(ExportHeadersFootersMode.NONE);//是否有页面页脚
        hso.setPrettyFormat(false);//是否实际格式输出

        hso.setExportDocumentProperties(true);
        //hso.setExportFontResources(true);
        //hso.setExportFontsAsBase64();
        ByteArrayOutputStream  is = new ByteArrayOutputStream();
        doc.save(is, hso);
        String html=is.toString("UTF-8");
        html=html.trim();
        html=html.replaceAll("", "");
        in.close();
        is.close();
        System.gc();
        return html;
    }
/*    public static class HandleImageSaving implements IImageSavingCallback {
        public void imageSaving(final ImageSavingArgs args) throws Exception {
            if (args.getImageFileName().endsWith(".jpeg"))
                args.setImageFileName(args.getImageFileName().replace(".png", ".jpeg"));
        }
    }*/
    /**
     * word 转html 存oss
     * @param in 文件流
     * @return
     * @throws Exception
     */
    public static String showWordHtml( InputStream in) throws Exception{
        Document doc = new Document(in);
        HtmlSaveOptions hso = new HtmlSaveOptions(SaveFormat.HTML);
        hso.setExportLanguageInformation(false);
        hso.setExportImagesAsBase64(true);//base6输出标签
        hso.setScaleImageToShapeSize(true);
        hso.setUseHighQualityRendering(true);//是否高保真图片
        hso.setExportHeadersFootersMode(ExportHeadersFootersMode.NONE);//是否有页面页脚
        hso.setPrettyFormat(false);//是否实际格式输出
        //ByteArrayOutputStream  is = new ByteArrayOutputStream();
        String html=path+ UUID.randomUUID() +".html";
        doc.save(html, hso);
        return html;
    }

    /**
     * 展示表格 生成html
     * @param in 输入文件流
     * @return 返回路径
     */
    public static String showExcelHtml (InputStream in){
        String html = "";
        try {
            if(!getLicenseExcel()){
                return null;
            }
            com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(in);
            com.aspose.cells.HtmlSaveOptions hso =new com.aspose.cells.HtmlSaveOptions(com.aspose.cells.SaveFormat.HTML);
          /*  hso.setExportHiddenWorksheet(true);//是否导出隐藏内容
            hso.setExportActiveWorksheetOnly(true);//导出全部
            hso.setExportGridLines(true);//表格线
            hso.setExportImagesAsBase64(true);*/
            hso.setExportHiddenWorksheet(true);//是否导出隐藏内容
            hso.setExportActiveWorksheetOnly(false);//导出全部
            hso.setExportGridLines(true);//表格线
            hso.setExportImagesAsBase64(true);
            hso.setCreateDirectory(true);//目录不存在自动创建


            hso.setAttachedFilesUrlPrefix("bailong");

            html=path+ UUID.randomUUID() +".html";
            workbook.save(html,hso);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    /**
     * 展示表格 生成html流
     * @param in
     * @return 返回html字符串流
     */
    /*public static String showExcelString (InputStream in){
        String html = "";
        try {
            if(!getLicenseExcel()){
                return null;
            }
            com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(in);
            WorksheetCollection sheets = workbook.getWorksheets();


            com.aspose.cells.HtmlSaveOptions hso =new com.aspose.cells.HtmlSaveOptions(com.aspose.cells.SaveFormat.HTML);
            hso.setExportHiddenWorksheet(true);//是否导出隐藏内容
            hso.setExportActiveWorksheetOnly(false);//导出全部
            hso.setExportGridLines(true);//表格线
            hso.setExportImagesAsBase64(true);
            hso.setCreateDirectory(true);//目录不存在自动创建
            //hso.setExportSingleTab(true);
            //hso.setAttachedFilesUrlPrefix("bailong");

            sheets.forEach(sheet->{

                System.out.print(sheet);
            });

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.save(out,hso);
            html=out.toString("UTF-8");
            html=html.trim();
            html=html.replaceAll("", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }*/
    /**
     * PPT展示 生成html
     * @param in
     * @return
     */
    public static String showPPTHtml(InputStream in){

        if(!getLicensePPT()){
            return null;
        }
        String html="";
        html=path+ UUID.randomUUID() +".html";
        Presentation ppt =new Presentation(in);
        ppt.save(html, com.aspose.slides.SaveFormat.Html);
        return html;
    }

    /**
     * PPT展示 生成html流
     * @param in
     * @return 返回html字符串流
     */
    public static String showPPTString(InputStream in){
        if(!getLicensePPT()){
            return null;
        }
        String html="";
        Presentation ppt =new Presentation(in);
        ByteArrayOutputStream is = new ByteArrayOutputStream();


        ppt.save(is, com.aspose.slides.SaveFormat.Html);
        html=is.toString();
        html=html.trim();
        html=html.replaceAll("", "");
        return html;
    }

    /**
     * PPT展示 生成html流
     * @param in
     * @return 返回文件夹名字,size,缩略图为m文件夹名
     */
    public static String showPPTPhotos(InputStream in) {
        long start = System.currentTimeMillis();
        if(!getLicensePPT()){
            return null;
        }
        long p = System.currentTimeMillis() - start;
        System.out.println("破解:"+ p);

        //LoadOptions loadOptions = new LoadOptions(2);
        //loadOptions.setDefaultSymbolFont("UTF-8");

        //loadOptions.setLoadFormat(2);

        Presentation ppt =new Presentation(in);




        UUID uuid = UUID.randomUUID();
        long endPage1 = System.currentTimeMillis() - start;
        System.out.println("获取ppts时间："+endPage1);
        int flag = 0;
        for (int i = 0; i< ppt.getSlides().size(); i++ ) {
            long startFor = System.currentTimeMillis();
            ISlide iSlide = ppt.getSlides().get_Item(i);
            long startBi = System.currentTimeMillis();
            BufferedImage bi = iSlide.getThumbnail(new Dimension(800,500));
            BufferedImage mbi = iSlide.getThumbnail(new Dimension(160,100));



            long endBi = System.currentTimeMillis() - startBi;
            System.out.println("第"+i+"个图片获取到的时间："+endBi);

            Graphics g = bi.createGraphics();
            g.drawImage(bi,0,0,null);
            long startFile = System.currentTimeMillis();
            File file = new File(path+ uuid +"/"+i+".jpg");
            File mfile = new File(path+"m"+uuid +"/"+i+".jpg");
            file.mkdirs();
            mfile.mkdirs();
            long endFile = System.currentTimeMillis() - startFile;
            System.out.println("第"+i+"个jpg生成时间："+endFile);
            try {

                Image image = iSlide.getThumbnail(new Dimension(800,500));
                //bi.createGraphics()


                long startPage = System.currentTimeMillis();
                ImageIO.write(bi,"jpg",file);
                ImageIO.write(mbi,"jpg",mfile);
                long endPage = System.currentTimeMillis() - startPage;
                System.out.println("第"+i+"张写入成功时间："+endPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = i;
            long endFor = System.currentTimeMillis() - startFor;
            System.out.println("==============================ForTime: " + endFor );
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("==============================time: " + end );
        return uuid+","+flag;
    }

    /**
     *
     * @param doc
     * @param flag 栏目集合或者标识集合
     * @return
     */
    public static JSONArray wordToNodes(Document doc, List<String> flag){

        JSONArray ja = new JSONArray();

        String nodeFlags="";
        for (String s : flag) {
            nodeFlags+="【"+s+"】";

        }
        List<Node> ln = new ArrayList<>();
        for (Section sec : doc.getSections()) {
            for (Node node : sec.getBody().getChildNodes().toArray()) {
                ln.add(node);
            }
        }
        try {
            //处理段落
            for (int f=0;f<ln.size();f++) {
                Node node = ln.get(f);
                String txt = node.getText().trim();
                for(int i=0;i<flag.size();i++){
                    List<Node> list = new ArrayList<>();
                    list.add(node);
                    int g=1;

                    if(txt.contains(flag.get(i))){
                        while (!(nodeFlags.indexOf(ln.get(f+g).getText().trim())>-1)){
                            list.add(ln.get(f+g));
                            g++;
                        }
                        Document newdoc= getNode(doc, list);
                        newdoc.getRange().replace("【"+flag.get(i)+"】", "", false, false);
                        String docText=newdoc.getRange().getText();
                        docText=docText.replace("【"+flag.get(i)+"】", "");
                        if(null !=docText ){
                            docText = docText.trim();
                        }
                        String html=getHtml(newdoc);
                        String htmls=newdoc.getText();
                        JSONObject ti = new JSONObject();
                        ti.put(txt.toString(),html);
                        ja.add(ti);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ja;
    }

    /**
     * 获取HTML
     * @param doc
     * @return
     * @throws Exception
     */
    public static String getHtml(Document doc) throws Exception{
        String text=doc.getRange().getText();
        HtmlSaveOptions hso = new HtmlSaveOptions(SaveFormat.HTML);
        //hso.setImageSavingCallback(new HandleImageSaving());
        //hso.setSaveFormat(SaveFormat.HTML);
        hso.setExportLanguageInformation(false);
        hso.setExportImagesAsBase64(true);//base6输出标签
        hso.setScaleImageToShapeSize(true);
        hso.setUseHighQualityRendering(true);//是否高保真图片
        hso.setExportHeadersFootersMode(ExportHeadersFootersMode.NONE);//是否有页面页脚
        hso.setPrettyFormat(false);//是否实际格式输出

        //hso.setMetafileFormat(0);

        ByteArrayOutputStream  is = new ByteArrayOutputStream();
        doc.save(is, hso);
        String html=is.toString("UTF-8");
        html=html.split("<div>")[1].split("</div>")[0];
        html=html.trim();
        html=html.replaceAll("", "");
        return html;
    }

    //获取节点
    public static Document getNode(Document doc ,List<Node> ln) throws Exception{
        Document newdoc = new Document();
        newdoc.getLastSection().getBody().getLastParagraph().remove();
        CompositeNode dstNode = newdoc.getLastSection().getBody();
        NodeImporter importer = new NodeImporter(doc, newdoc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        for (Node node : ln) {
            Node newNode = importer.importNode(node, true);
            dstNode.appendChild(newNode);
        }
        return newdoc;
    }


/*    public static ApiMsg test() throws InterruptedException {
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                if(1==1){
                    try {
                        Thread.currentThread().sleep(5000);
                        System.out.println("============================================================本地上传完成");
                        //return new ApiMsg(1,"",null);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        Thread a1 = new Thread();
        System.out.println("++++++++++++++++++++++++++++++++++本地上传完成11");



        //thread.join();


        return new ApiMsg(2,"xixixi","11");
        //System.out.println("");

    }*/


    public static String showPPTPhotos1(InputStream in) {

        long start = System.currentTimeMillis();
        if(!getLicensePPT()){
            return null;
        }

        //DocumentProperties documentProperties = new DocumentProperties() ;
        //IDocumentProperties iDocumentProperties =  documentProperties.
        //documentProperties.s
        //IDocumentProperties iDocumentProperties =  new Presentation(in);

       /* LoadOptions loadOptions = new LoadOptions();
        loadOptions.setDefaultRegularFont("宋体");
        loadOptions.setDefaultSymbolFont("宋体");
        loadOptions.setDefaultAsianFont("宋体");*/

        Presentation ppt =new Presentation(in);
        //TiffOptions tiffOptions = new TiffOptions();
        //tiffOptions.setPixelFormat(ImagePixelFormat.Format8bppIndexed);
       // tiffOptions.setIncludeComments(true);
        UUID uuid = UUID.randomUUID();
        int flag = 0;
//        int sides[] = new int[ppt.getSlides().size()];
//        for (ISlide iSlide : ppt.getSlides()) {
//            sides[flag] = flag+1;
//            flag ++;
//        }
//        ppt.save("I:\\zhxy\\user-service\\target\\classes\\downloadFile\\"+uuid+".tiff",sides, com.aspose.slides.SaveFormat.Tiff);


//        File file = new File(path+ uuid );
//        file.mkdirs();
//        int flag = 0;
//        for (int i = 0; i< ppt.getSlides().size(); i++ ) {
//            ppt.save( path + uuid + "/" + i + ".tiff", com.aspose.slides.SaveFormat.Tiff);
//            flag = i;
//        }




        for (int i = 0; i< ppt.getSlides().size(); i++ ) {
            System.out.println("                              " );
            System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓" );
            long startFor = System.currentTimeMillis();
            ISlide iSlide = ppt.getSlides().get_Item(i);
            long startBi = System.currentTimeMillis();
            BufferedImage bi = iSlide.getThumbnail(new Dimension(1024,768));
            long endBi = System.currentTimeMillis() - startBi;
            System.out.println("↓==第"+i+"张转Buffered成功时间："+endBi);
            //BufferedImage mbi = iSlide.getThumbnail(new Dimension(320,240));
            long startFile = System.currentTimeMillis();
            File file = new File(path+ uuid +"/"+i+".jpg");
          //  File mfile = new File(path+"m"+uuid +"/"+i+".jpg");
            file.mkdirs();
          //  mfile.mkdirs();
            long endFile = System.currentTimeMillis() - startFile;
            System.out.println("|==第"+i+"张转file生成成功时间："+endFile);
            try {

                //Image image = iSlide.getThumbnail(new Dimension(800,500));
                //bi.createGraphics()


                long startPage = System.currentTimeMillis();
                ImageIO.write(bi,"jpg",file);
            //    ImageIO.write(mbi,"jpg",mfile);
                long endPage = System.currentTimeMillis() - startPage;
                System.out.println("|===第"+i+"张写入成功时间："+endPage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = i;
            long endFor = System.currentTimeMillis() - startFor;
            System.out.println("↑=====第"+i+"次for循环操作成功时间："+"\033[31;4m"+endFor+"\033[0m");
            System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑" );
            System.out.println(                              );
        }


        long end = System.currentTimeMillis() - start;
        System.out.println("==============================time: " + end );
        return uuid+","+flag;
    }

    /**
     * word 转html 存oss
     * @param in 文件流
     * @return
     * @throws Exception
     */
    public static String showWordHtml( String in){

        String html = "";
        try {
            Document doc = new Document(in);
            HtmlSaveOptions hso = new HtmlSaveOptions(SaveFormat.HTML);
            hso.setExportLanguageInformation(false);
            hso.setExportImagesAsBase64(true);//base6输出标签
            hso.setScaleImageToShapeSize(true);
            hso.setUseHighQualityRendering(true);//是否高保真图片
            hso.setExportHeadersFootersMode(ExportHeadersFootersMode.NONE);//是否有页面页脚
            hso.setPrettyFormat(false);//是否实际格式输出
            //ByteArrayOutputStream  is = new ByteArrayOutputStream();
            html=getPath(in)+ UUID.randomUUID() +".html";
            doc.save(html, hso);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return html;
    }

    public static String getPath(String path){
        int lastIndex = path.lastIndexOf(File.separator);
        String newPath = path.substring(0, lastIndex+1);
        return newPath;
    }

    /**
     * 展示表格 生成html
     * @param in 输入文件流
     * @return 返回路径
     */
    public static String showExcelHtml (String in){
        String html = "";
        try {
            if(!getLicenseExcel()){
                return null;
            }
            com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(in);
            com.aspose.cells.HtmlSaveOptions hso =new com.aspose.cells.HtmlSaveOptions(com.aspose.cells.SaveFormat.HTML);
          /*  hso.setExportHiddenWorksheet(true);//是否导出隐藏内容
            hso.setExportActiveWorksheetOnly(true);//导出全部
            hso.setExportGridLines(true);//表格线
            hso.setExportImagesAsBase64(true);*/
            hso.setExportHiddenWorksheet(true);//是否导出隐藏内容
            hso.setExportActiveWorksheetOnly(false);//导出全部
            hso.setExportGridLines(true);//表格线
            hso.setExportImagesAsBase64(true);
            hso.setCreateDirectory(true);//目录不存在自动创建


            hso.setAttachedFilesUrlPrefix("bailong");

            html=getPath(in)+ UUID.randomUUID() +".html";
            workbook.save(html,hso);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    /**
     * PPT展示 生成html流
     * @param in
     * @return 返回文件夹名字,size,缩略图为m文件夹名
     */
    public static String showPPTPhotos(String in) {
        if(!getLicensePPT()){
            return null;
        }

        Presentation ppt =new Presentation(in);

        Integer size = ppt.getSlides().size();
        UUID uuid = UUID.randomUUID();
        String path = getPath(in);
        for (int i = 0; i< size; i++ ) {

            ISlide iSlide = ppt.getSlides().get_Item(i);



            long startTime = System.currentTimeMillis();

            BufferedImage bi = iSlide.getThumbnail(new Dimension(1024,768));
//            BufferedImage mbi = iSlide.getThumbnail(new Dimension(160,100));
            long endTime = System.currentTimeMillis() - startTime;
            //System.out.println("程序运行时间：" + (endTime) + "ms");

//            Graphics g = bi.createGraphics();
//            g.drawImage(bi,0,0,null);

            File file = new File(path+ uuid +"/"+i+".png");
//            File mfile = new File(path+"m"+uuid +"/"+i+".png");
            file.mkdirs();

//            mfile.mkdirs();

            try {

                ImageIO.write(bi,"png",file);
//                ImageIO.write(mbi,"png",mfile);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return path + uuid+"/,"+size;
    }

}

