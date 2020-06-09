package com.base.controller.system;


import com.base.bean.ApiMsg;
import com.base.bean.QueryObjectElastic;
import com.base.utils.DocumentUtil;
import com.base.utils.ElasticSerachUtil;
import com.base.utils.RedisUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @description:前台
 * @author: 小猴子
 * @date: 2019-11-07 15:00
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private RestHighLevelClient client;




    @RequestMapping("/index.do")
    public String index(){
        //全文检索首页
        // return "index";
        //word首页
        return "index";
    }

    @RequestMapping("/ajaxIndex.do")
    @ResponseBody
    public ApiMsg ajaxIndex(){
       // RedisUtil.addRedis(RedisUtil.SET,"qtxs",null,"www.qtxs.com");
        Object o = RedisUtil.getRedis(RedisUtil.SET,"qtxs",null);
        return new ApiMsg(0,"是我",o);
    }


    @RequestMapping("/userIndex.do")
    public String HomeIndex(){
        return "home/index";
    }

    @RequestMapping("/welcome.do")
    public String welcome(){
        return "home/welcome";
    }


    @GetMapping("/search.do")
    public String search(QueryObjectElastic queryObjectElastic) {
        //ApiMsg apiMsg =new ApiMsg(0,"",ElasticSerachUtil.fuzzySearch(queryObjectElastic,client));
        return "search/index";
    }

    @GetMapping("/esAll.do")
    @ResponseBody
    public ApiMsg esAll(QueryObjectElastic queryObjectElastic) {
        // return new ApiMsg(0,"",ElasticSerachUtil.querySearch(queryObjectElastic,client));
     /*   for(int i=0;i<9999;i++){
            QueryObjectElastic qoe = new QueryObjectElastic();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("tw_content",queryObjectElastic.getKey());
            map.put("question","<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta http-equiv=\"Content-Style-Type\" content=\"text/css\" /><meta name=\"generator\" content=\"Aspose.Words for Java 16.4.0.0\" /><title></title></head><body><div><p style=\"margin-top:0pt; margin-bottom:0pt; text-align:justify; line-height:150%; widows:0; orphans:0; font-size:10.5pt\"><span style=\"font-family:'宋体'\"><span style='color:aqua'>第"+i+"</span>下列语句中加点的成语使用正确的一项是</span><span style=\"font-family:'宋体'\">（    ）</span></p><p style=\"margin-top:0pt; margin-bottom:0pt; text-align:justify; line-height:150%; widows:0; orphans:0; font-size:10.5pt\"><span style=\"font-family:'Times New Roman'\">A</span><span style=\"font-family:'宋体'\">．</span><span style=\"font-family:'宋体'\">电视剧《恰同学少年》以毛泽东在湖南第一师范的读书生活为背景，展现了以毛泽等为代表的一批优秀青年</span><span style=\"font-family:'宋体'\">风华正茂</span><span style=\"font-family:'宋体'\">的学习和生活故事。</span></p><p style=\"margin-top:0pt; margin-bottom:0pt; text-align:justify; line-height:150%; widows:0; orphans:0; font-size:10.5pt\"><span style=\"font-family:'Times New Roman'\">B</span><span style=\"font-family:'宋体'\">．</span><span style=\"font-family:'宋体'\">登上仰慕已久的泰山，同学们眼界大开，他们一会儿俯瞰脚下的云雾松柏，一会儿举目仰望远处的落日归鸟，</span><span style=\"font-family:'宋体'\">指点江山</span><span style=\"font-family:'宋体'\">，心情澎湃。</span></p><p style=\"margin-top:0pt; margin-bottom:0pt; text-align:justify; line-height:150%; widows:0; orphans:0; font-size:10.5pt\"><span style=\"font-family:'Times New Roman'\">C</span><span style=\"font-family:'宋体'\">．</span><span style=\"font-family:'宋体'\">近年来，一些正值</span><span style=\"font-family:'宋体'\">豆蔻年华</span><span style=\"font-family:'宋体'\">的青年小伙子沉迷在网吧里，从而荒废了学业，浪费了青春，真让人痛惜不已。</span></p><p style=\"margin-top:0pt; margin-bottom:0pt; text-align:justify; line-height:150%; widows:0; orphans:0; font-size:10.5pt\"><span style=\"font-family:'Times New Roman'\">D</span><span style=\"font-family:'宋体'\">．</span><span style=\"font-family:'Times New Roman'\">2009</span><span style=\"font-family:'宋体'\">年诺贝尔文学奖得主德国女作家兼诗人赫塔•米勒的作品多以二战为背景，反映的是</span><span style=\"font-family:'宋体'\">峥嵘岁月</span><span style=\"font-family:'宋体'\">中罗马尼亚裔德国人的生活。</span></p></div></body></html>");
            qoe.setIndex("paper");
            qoe.setType("cloud");
            qoe.setId(i+queryObjectElastic.getId());
            qoe.setMapKeys(map);
            ElasticSerachUtil.add(qoe,client);
            System.out.println("第："+i+"插入成功");
        }
         ApiMsg apiMsg =new ApiMsg(0,"",ElasticSerachUtil.fuzzySearch(queryObjectElastic,client));
        */
        return new ApiMsg(0,"",ElasticSerachUtil.fuzzySearch(queryObjectElastic,client));
    }

    @PostMapping("/uploadShow.do")
    @ResponseBody
    public String uploadShow(@RequestParam("file") MultipartFile file){
        System.out.println(file);
        String request = null;
        try {
            request = DocumentUtil.getInstance().POIHtmlToWord(null,file);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return request;
    }


}
