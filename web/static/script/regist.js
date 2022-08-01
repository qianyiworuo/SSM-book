function $(id){
     return document.getElementById(id);
}
function checkRegist(){
    //用户名应为6~16位数字和字母组成
    var unameText = $("unameText");
    var unameReg = /^[0-9a-zA-Z]{6,16}$/gim;
    var uname = unameText.value;
    var unameSpan = $("unameSpan");
    if(!unameReg.test(uname)){
        unameSpan.style.visibility="visible";
        return false;
    }else {
        unameSpan.style.visibility="hidden";
    }
    //密码必须包含大写字母 、数字、 特殊字符（四种里至少三种，且至少8位）
    var pwdText = $("pwdText");
    var pwdReg = /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,}$/;
    var pwd = pwdText.value;
    var pwdSpan = $("pwdSpan");
    if(!pwdReg.test(pwd)){
        pwdSpan.style.visibility="visible";
        return false;
    }else {
        pwdSpan.style.visibility="hidden";
    }
    //密码两次输入是否一致
    var pwdText = $("pwdText");
    var pwd = pwdText.value;
    var rePwdText = $("rePwdText");
    var rePwd = rePwdText.value;
    var rePwdSpan = $("rePwdSpan");
    if(pwd != rePwd){
        rePwdSpan.style.visibility="visible";
        return false;
    }else {
        rePwdSpan.style.visibility="hidden";
    }
    //邮箱格式
    var emailText = $("emailText");
    var emailReg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
    var email = emailText.value;
    var emailSpan = $("emailSpan");
    if(!emailReg.test(email)){
        emailSpan.style.visibility="visible";
        return false;
    }else {
        emailSpan.style.visibility="hidden";
    }
    return true;
}
//如果需要进行异步传输，需要创建XMLHttpRequest对象。
var XMLHttpRequest;
function createXMLHttpRequest(){
    if (window.XMLHttpRequest)
    {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        XMLHttpRequest = new XMLHttpRequest();
    }
    else if(window.ActiveXObject)
    {
        try {
            // IE6, IE5 浏览器执行代码
            XMLHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e){
            XMLHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
}
function chkUname(uname){
    createXMLHttpRequest();
    var url = "user.do?operate=chkUname&uname=" + uname;
    //开启异步传输
    XMLHttpRequest.open("GET",url,true);
    //设置回调函数
    XMLHttpRequest.onreadystatechange = chkUnameCB;
    //发送请求
    XMLHttpRequest.send();
}
function chkUnameCB(){
    if(XMLHttpRequest.readyState == "4" && XMLHttpRequest.status == 200){
        //XMLHttpRequest.responseText表示服务器响应到客户端的文本内容
        //alert(XMLHttpRequest.responseText);
        var respText = XMLHttpRequest.responseText;
        if(respText == "{'uname' : '1'}"){
            alert("用户名已存在，请重新注册！");
        }
        else{
            alert("OK!")
        }
        //alert("Hello Ajax!");
    }
}