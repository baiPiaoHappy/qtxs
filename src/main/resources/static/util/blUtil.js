


var blUtil = (function($){

    //加密
    function compileStr(code){
        var c=String.fromCharCode(code.charCodeAt(0)+code.length);
        for(var i=1;i<code.length;i++){
            c+=String.fromCharCode(code.charCodeAt(i)+code.charCodeAt(i-1));
        }
        return escape(c);
    }

    //字符串解密
    function _uncompileStr(code){
        code=unescape(code);
        var c=String.fromCharCode(code.charCodeAt(0)-code.length);
        for(var i=1;i<code.length;i++)
        {
            c+=String.fromCharCode(code.charCodeAt(i)-c.charCodeAt(i-1));
        }
        return c;
    }




})(this);

var bl_util = {

    /**
     * 加密
     * @param code
     * @returns {*}
     * @private
     */
    _compileStr:function(code){
        var c=String.fromCharCode(code.charCodeAt(0)+code.length);
        for(var i=1;i<code.length;i++){
            c+=String.fromCharCode(code.charCodeAt(i)+code.charCodeAt(i-1));
        }
        return escape(c);
    },
    /**
     * 解码
     * @param code
     * @returns {string}
     * @private
     */
    _uncompileStr:function(code){
        code=unescape(code);
        var c=String.fromCharCode(code.charCodeAt(0)-code.length);
        for(var i=1;i<code.length;i++)
        {
            c+=String.fromCharCode(code.charCodeAt(i)-c.charCodeAt(i-1));
        }
        return c;
    },
    /**
     *  获取
     * @param name
     * @returns {*}
     */
    getQueryString:function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

}