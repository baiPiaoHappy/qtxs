
layui.use(['layer',"jquery",'form','upload'], function() {
    var layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        $ = layui.jquery;

      form.on('submit(search)', function(data){
          console.log(encodeURI(data.field.key)) //当前容器的全部表单字段，名值对形式：{name: value}
          var key = encodeURI(data.field.key);
          window.location.href="/home/search.do?key="+bl_util._compileStr(data.field.key);
          return true; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
      });


    //执行实例
    var uploadInst1 = upload.render({
        elem: '#test1' //绑定元素
        ,url:'/home/testLoad1.do?zyType=1' //上传接口
        ,accept:"file"
        ,exts:"doc|docx|"
        ,multiple:true
        ,size:0

        ,acceptMime:"file/docx,file/doc"
        ,before: function(obj){
            layer.load();
        }
        ,done: function(res){
            //上传完毕回调
            layer.closeAll();
            $("#showWord").html(res.data);
            console.log(res);
        }
        ,error: function(){
            //请求异常回调
        }
    });


    /*$("#searchButton").on("click", function () {
        var key = $("#key").val();
        for(var i=0;i<9999;i++){
            $.ajax({
                type: "get",
                dataType: "json",
                url: "/home/esAll.do",
                data: {
                    "key": i+key,
                    "id": "test:"+i+"13283c09bfd011e9bdab00163e080e1c"
                },
                success: function (re) {
                    console.log(re)
                }
            });
        }*/
/*        $.ajax({
            type: "get",
            dataType: "json",
            url: "/home/esAll.do",
            data: {
                "key": $("#key").val()
            },
            success: function (re) {
                console.log(re)
            }
        });*/

});





