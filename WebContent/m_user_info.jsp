<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<title>信息管理系统</title>

<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post">
		<div id="container">
		
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table2" cellspacing="0" cellpadding="0" width="50%" align="left" border="0" >
						<tr>
							<td>账号：</td><td>${user.user_id}</td>
						    </tr>
						<tr>
							<td>姓名：</td><td>${user.user_name}</td>
						    </tr>
							<td>当前借阅量：</td><td>${user.user_number}</td>
							</tr>
					</table>
				</div>
				
			</div>
		</div>
	</form>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
