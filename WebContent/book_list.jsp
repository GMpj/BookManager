<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>

<title>信息管理系统</title>

<style>
	.alt td{ background:black !important
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="SearchServlet" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							查询种类
							<select name="book_kind" id="fyXq" class="ui_select01" onchange="getFyDhListByFyXqCode();">
                                <option value=""
                                >--请选择--</option>
                                <option value="书刊">书刊</option>
                                <option value="文章">文章</option>
                              
                            </select>

							请输入要查询到的书名&nbsp;&nbsp;<input type="text" id="fyZldz" name="book_name" class="ui_input_txt02" value=""/>
						</div>
						<div id="box_bottom">
							<input type="submit" value="查询" class="ui_input_btn01" /> 
							
						</div>
					</div>
				</div>
			</div>
			</form>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th>书号</th>
							<th>书名</th>
							<th>作者</th>
							<th>图书种类</th>
							<th>现存量</th>
						
						</tr>
						 <c:forEach var="book" items="${books}">
						
							<tr>
								<td><input type="checkbox" name="IDCheck" value="14458579642011" class="acb" /></td>
							  <td>${book.book_id }</td>
								<td>${book.book_name }</td>
								<td>${book.book_author }</td>
								<td>${book.book_kind }</td>
								<td>${book.book_number }</td>  
								<td>
								</td>
							</tr>
</c:forEach>
									 <c:forEach var="article" items="${articles}">
							<tr>
								<td><input type="checkbox" name="IDCheck" value="14458579642011" class="acb" /></td>
								
								<td>${article.article_id }</td>
								<td>${article.article_name }</td>
								<td>${article.article_author }</td> 
								<td>文章</td>
								<td>3</td>
								<td>
								</td>
							</tr>
</c:forEach>		
						

						
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">5</span>
						条记录，当前第
						<span class="ui_txt_bold04">1
						/
						3</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						
							<input type="button" value="首页" class="ui_input_btn01" />
							<input type="button" value="上一页" class="ui_input_btn01" />
							<input type="button" value="下一页" class="ui_input_btn01"
								onclick="jumpNormalPage(2);" />
							<input type="button" value="尾页" class="ui_input_btn01"
								onclick="jumpNormalPage(9);" />
						
						
						
						<!--     如果是最后一页，则只显示首页、上一页 -->
						
						转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" />页
							 <input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage(9);" />
					</div>
				</div>
			</div>
		</div>
	
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
