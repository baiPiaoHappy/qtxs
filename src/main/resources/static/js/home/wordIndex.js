/**
 *
 */

layui.use(['element',"layer","upload"], function(){
    var element = layui.element,
        upload = layui.upload,
        layer = layui.layer;


    var uploadInst = upload.render({
        elem: '#upload' //绑定元素
        ,url: 'uploadShow.do' //上传接口
        ,method: 'post'
        ,accept: "file"
        ,exts: "doc|docx"
        ,acceptMime: "file/doc,file/docx"
        ,done: function(res){
            //上传完毕回调
        }
        ,error: function(){
            //请求异常回调
        }
    });

    $("#button").on("click",function (){
        $("#paper").wordExport("word下载");
    });

});

