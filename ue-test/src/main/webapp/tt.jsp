<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>AccessToken生成工具</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
    <h1>AccessToken生成工具</h1>

    <%--    <h3>Message : ${message}</h3> --%>
    <%--    <h3>Counter : ${counter}</h3> --%>

    <form action="test_post" method="post" enctype="multipart/form-data">
        <table>
            <tbody>
                <tr>
                    <td><label>test中文:</label></td>
                    <td><input type="text" name="test中文" value="test中文"
                        maxlength="64" /></td>
                </tr>
                <tr>
                    <td><label>appId:</label></td>
                    <td><input type="text" name="appId" value="test中文"
                        maxlength="64" /></td>
                </tr>
                <tr>
                    <td><label>appSecret:</label></td>
                    <td><input type="text" name="appSecret" value="test中文"
                        maxlength="64" /></td>
                </tr>
                <tr>
                    <td><label>timestamp:</label></td>
                    <td><label>${timestamp}</label></td>
                </tr>
                <tr>
                    <td><label>accessToken:</label></td>
                    <td><label>${checkToken}</label></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="提交" /></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>