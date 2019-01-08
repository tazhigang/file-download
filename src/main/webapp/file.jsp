<%--
  Created by IntelliJ IDEA.
  User: tuo
  Date: 2018/12/30
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <script src="/js/jquery.min.js"></script>
    <script>
        function submit2(){
//            var type = "txtfile";          //后台接收时需要的参数名称，自定义即可
//            var id = "txtfile";            //即input的id，用来寻找值
            var formData = new FormData();
            formData.append("txtfile", $("#txtfile")[0].files[0]);    //生成一对表单属性
            $.ajax({
                type: "POST",           //因为是传输文件，所以必须是post
                url: '/file/checktxt',         //对应的后台处理类的地址
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    alert(data.errorMsgs);
                }
            });
        }
    </script>
</head>
<body>
        <input type="file" name="txtfile" id="txtfile"><br/>
        <button type="submit" onclick="submit2()">提交</button><br/>
        <a href="/file/download?fileName=hello.txt">hello.txt</a><br/>
        <a href="/file/download?fileName=mobile.txt">mobile.txt</a>
</body>
</html>
