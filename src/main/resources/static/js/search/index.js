
layui.use(['layer',"jquery",'form','laypage'], function() {
    var layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage,
        $ = layui.jquery;


    var key = bl_util._uncompileStr(bl_util.getQueryString("key"));
    $("#key").val(key);
    $.ajax({
        type: "get",
        dataType: "json",
        url: "/home/esAll.do",
        data: {
            "key": key
        },
        success: function (re) {
            var list = re.list.list,
                page = re.list.page,
                show = "";
            list.forEach( x=>{
                show +="<div id='"+x.id+"' style='margin: 10px 10px 5px 100px;'><a style='font-size: 20px;'><u>"+x.tw_content+"</u></a><p/> <span style='font-size: 15px'>"+x.question+"</span><p/> </div>"
            })
            $("#show").html(show);
            pageRender(page);
        }
    });

    $("#searchButton").on("click", function () {
        var key = $("#key").val();
        $.ajax({
            type: "get",
            dataType: "json",
            url: "/home/esAll.do",
            data: {
                "key": key
            },
            success: function (re) {
                var list = re.list.list,
                    page = re.list.page,
                    show = "";
                list.forEach( x=>{
                    show +="<div id='"+x.id+"' style='margin: 10px 10px 5px 100px;'><a style='font-size: 20px;'><u>"+x.tw_content+"</u></a><p/> <span style='font-size: 15px'>"+x.question+"</span><p/> </div>"
                })
                $("#show").html(show);
                pageRender(page);
            }
        });
    })


    function pageRender (page){
        laypage.render({
            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: page.count >10 ? page.count-10:page.count//数据总数，从服务端得到
            ,limit:10
            ,theme: '#1E9FFF',
            jump:function(obj, first){
                console.log(obj);
                $.ajax({
                    type: "get",
                    dataType: "json",
                    url: "/home/esAll.do",
                    data: {
                        "key": $("#key").val(),
                        "from": obj.curr == -1 ? 1:obj.curr *obj.limit
                    },
                    success: function (re) {
                        var list = re.list.list,
                            page = re.list.page,
                            show = "";
                        list.forEach( x=>{
                            show +="<div id='"+x.id+"' style='margin: 10px 10px 5px 100px;'><a style='font-size: 20px;'><u>"+x.tw_content+"</u></a><p/> <span style='font-size: 15px'>"+x.question+"</span><p/> </div>"
                        })
                        $("#show").html(show);

                    }
                });
            }
        });
    }

});


/*
var vue = new Vue({
    el:"#search",
    data:{

    },
    mounted(){
        console.log("11");
        $.ajax({
            type: "get",
            dataType: "json",
            url: "/home/esAll.do",
            data: {
                "key": key
            },
            success: function (re) {
                console.log(re);
            }
        });
    },
    methods:{

    }

});*/
