<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글등록 페이지</title>
<script src="./ckeditor/ckeditor.js?=v2"></script>
</head>
<body>
<form id="frm" method="post" action="./notice_writeok.do" enctype="multipart/form-data">
제목 : <input type="text" name="subject"><br>
글쓴이 : <input type="text" name="writer"><br>
비밀번호 : <input type="password" name="passwd"><br>
내용 : <textarea name="texts" id="editor"></textarea><br>
첨부파일 : <input type="file" name="nfile"><br>
<input type="button" value="글등록" onclick="writeck()">
</form>
</body>
<script>
//window.onload : 현재 페이지가 실행이 되었을 때
window.onload = function(){
	var ck = CKEDITOR.replace("editor",{
		width:900,
		height:100,
		versionCheck:false
	});
}

//게시물 등록시 체크
function writeck(){
	if(frm.subject.value==""){
		alert("제목을 입력하셔야 합니다.");
		frm.subject.focus();
	}
	else if(frm.writer.value==""){
		alert("글쓴이를 입력하셔야 합니다.");
		frm.writer.focus();
	}
	else if(frm.passwd.value==""){
		alert("비밀번호를 입력하셔야 합니다.");
		frm.pw.focus();
	}
	else {
		//CKEDITOR.instance.html id이름.getData()
		var txt = CKEDITOR.instances.editor.getData();
		
		if(txt == ""){
			alert("내용을 입력하셔야 합니다.");
		}
		else if(txt.length < 10){
			alert("최소 10자 이상 입력하셔야 합니다.");
		}
		else{
			frm.submit();
		}
	}
}
</script>
</html>