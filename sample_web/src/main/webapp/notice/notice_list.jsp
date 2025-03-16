<%@page import="java.util.Map"%>
<%@page import="dto.NoticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int ctn_per_page = (int)request.getAttribute("ctn_per_page"); //페이지당 출력갯수 
	
	Map<String, Object> result = (Map)request.getAttribute("result");
	int total_ctn = (int)result.get("total_ctn");  //게시물 총갯수 
	ArrayList<NoticeDTO> pageAll = (ArrayList)result.get("pageAll");
	
	//페이지번호 갯수 계산 예시 : 7.0/3.0 = 2.33 => Math.ceil(2.33) = 3 (참고로 정수/정수는 정수이므로 double 형변환이 필요)
	int pageno_ctn = (int)Math.ceil((double)total_ctn/(double)ctn_per_page);  //페이지번호 갯수
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
</head>
<body>
<p>현재 등록된 게시물 : <%=total_ctn%></p>
<table border="1" cellpadding="0" cellspacing="0">
<thead>
	<tr>
		<th width="50">번호</th>
		<th width="500">제목</th>
		<th width="100">글쓴이</th>
		<th width="100">조회</th>
		<th width="150">등록일</th>
	</tr>
</thead>
<%
	for(int i=0; i<pageAll.size(); i++){
%>
<tbody>
	<tr height="30" align="center">
		<td><%=pageAll.get(i).getNidx()%></td>
		<td align="left" onclick="notice_view()"><%=pageAll.get(i).getSubject()%></td>
		<td><%=pageAll.get(i).getWriter()%></td>
		<td><%=pageAll.get(i).getNvew()%></td>
		<td><%=pageAll.get(i).getNdate().toString().substring(0,10)%></td>
	</tr>
</tbody>
<%
	}
%>
</table>
<br><br><br>
<table border="1" cellpadding="0" cellspacing="0">
<tr>
<%
	for(int i=1; i<=pageno_ctn; i++){
%>
	<td width=20 height=20 align="center"><a href="./notice_list.do?pageno=<%=i%>"><%=i%></a></td>
<%	
	}
%>
</tr>
</table>
</body>
</html>