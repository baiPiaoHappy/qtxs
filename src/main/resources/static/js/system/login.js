/**
 * @description: 登录
 * @author: 小猴子
 * @date: 2020-04-09 15:45
 */



layui.use(['layer','form','jquery'],function(){
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;

    /*form.on('submit(login)',function (data) {
        /!*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回*!/
        console.log(data.field);

        $.ajax({
           type:"post",
           dataType:"json",
           url:"/login/login",
            data: data.field,
           success:function(data){
               console.log(data);
           },
            error:function(res){
               console.log(res);
            }


        });*!/
        return false;
    })*/


});

/*
layui.use('form', function(){
    var form = layui.form;

    //各种基于事件的操作，下面会有进一步介绍
});*/
