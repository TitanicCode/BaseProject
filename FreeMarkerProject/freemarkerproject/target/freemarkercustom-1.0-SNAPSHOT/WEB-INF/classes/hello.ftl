<HTML>

<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
</head>

<body>
        hello:--->${hello}<br>
        名字: ${user.name}<br>
        密码:${user.password}<br>

    -----------------华丽的分割线------------------<br>
<#list users as user>
<span style="background-color: <#if user_index %2 ==0>#f00 <#else> #0f0</#if>">${user.name}</span><br>
<span>${user.password}</span><br>




</#list>
--------------------高到天际的发际线------------------------<br>
        <#--fadsfadsadfs表示参数，date表示该参数显示的格式-->
    ${fadsfadsadfs?date} <br>
    ${fadsfadsadfs?time} <br>
    ${fadsfadsadfs?datetime} <br>
    ${fadsfadsadfs?string("yyyy/MM/dd HH:mm:ss:SSS")}<br>
    <!--如果为空,则去获取另外一个变量的值-->
    ${nulldata!hello!"阿萨啊"}<br>
        ${nulldata!"dadas"}<br>
    <#if nulldata??>
        ${nulldata}<br>
        <#else>
        空值 <br>
    </#if>
    </body>
----------------------割割割割割割割割割割割割割割割割割割割割割割割割------------------------
<#--引入另一个文件-->
    <#include "abc.ftl">

</HTML>
