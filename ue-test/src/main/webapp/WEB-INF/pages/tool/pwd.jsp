<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>PWD加密工具</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
	<h1>PWD加密工具</h1>


	<form action="pwd.htm" method="post" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td align="right"><label>pwd:</label></td>
					<td><input type="text" name="pwd" value="${pwd}"
						maxlength="64" /></td>
				</tr>
				<tr>
					<td align="right"><label>encryptPwd:</label></td>
					<td><label>${encryptPwd}</label></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>