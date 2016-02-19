<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>Sign生成工具</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
	<h1>Sign生成工具</h1>

	<%-- 	<h3>Message : ${message}</h3> --%>
	<%-- 	<h3>Counter : ${counter}</h3> --%>

	<form id="xxForm" action="sign.htm" method="post"
		enctype="multipart/form-data">
		<table>
			<tbody>

				<tr>
					<td colspan="2"><label><font size="10" color="red">${error}</font></label></td>
				</tr>
                <tr>
                    <td colspan="2"><label>--------------------------------------------------------</label></td>
                </tr>
				<tr>
					<td colspan="2"><label>一、签名密钥:</label></td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="appSecret" value="${appSecret}" width="60" maxlength="64" /></td>
				</tr>
				<tr>
					<td colspan="2"><label>二、请求参数JSON格式:(自动剔除key为“sign”的值，不区分大小写)</label></td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="jsonStr" form="xxForm" rows="20" cols="60">${jsonStr}</textarea></td>
				</tr>
                <tr>
                    <td colspan="2"><label>---------------------------------------------------------</label></td>
                </tr>
				<tr>
					<td><label>signSrc:</label></td>
					<td><label>${signSrcStr}</label></td>
				</tr>
				<tr>
					<td><label>sign:</label></td>
					<td><label>${sign}</label></td>
				</tr>
                <tr>
                    <td colspan="2"><label>---------------------------------------------------------</label></td>
                </tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>