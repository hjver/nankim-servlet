package notice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.NoticeDao;
import dto.NoticeDTO;
import model.Encryptor;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 5, //5MB
		maxFileSize = 1024 * 1024 * 50, //최대용량 50MB
		maxRequestSize = 1024 * 1024 * 500  //여러개 파일 업로드시
)
public class notice_writeok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//첨부파일 다루기
		Part nfile = request.getPart("nfile");
		long filesize = nfile.getSize();
		String filenm = null; ////사용자가 업로드한 파일명 
		String fullnm = null; //서버에 저장된 파일경로 및 파일명 
		String fileok = "";   //첨부파일 저장 관련
		
		if(filesize > 0) {
			filenm = nfile.getSubmittedFileName();
			
			//첨부파일 저장될 Web Directory 설정
			String uploadPath = request.getServletContext().getRealPath("/notice_file/");
			File uploadDir = new File(uploadPath);
			fullnm  = uploadPath + filenm;
			
			// 디렉토리가 없으면 생성
			if (!uploadDir.exists()) {
			    uploadDir.mkdirs();
			}
			
			try {
				nfile.write(fullnm); //해당 디렉토에 저장
			}catch (Exception e) {
				System.out.println("파일저장 오류!!" + e.getMessage());
				fileok = "error";   //첨부파일 저장에 오류가 발생한 경우 
				pw.write("<script>"
						+ "alert('첨부파일 저장에 오류가 발생했습니다.');"
						+ "history.go(-1);"
						+ "</script>");						
			}
		}
		
		if(!fileok.equals("error")) {  //첨부파일이 없거나 첨부파일 서버저장이 정상인 경우 게시물 DB 저장 진행 
			//패스워드 암호화
			Encryptor enc = new Encryptor();
			String spw = enc.encodePassword(request.getParameter("passwd"));
			
			//게시물 입력 데이터를 Notice DTO에 임시저장
			NoticeDTO ndto = new NoticeDTO();
			ndto.setSubject(request.getParameter("subject")); //게시판 제목 
			ndto.setWriter(request.getParameter("writer"));   //글쓴이 
			ndto.setPasswd(spw);                              //암호화된 패스워드 
			ndto.setTexts(request.getParameter("texts"));     //editor 작성내용 
			ndto.setFilenm(filenm);                           //사용자가 업로드한 파일명 
			ndto.setNfile(fullnm);                            //서버에 저장된 파일경로 및 파일명 
	
			//Notice DTO에 저장된 게시물 입력 데이터를 데이타베이스에 저장
			NoticeDao ndao = new NoticeDao();
			int result = ndao.insert_data(ndto); 
			
			if(result > 0) {
				pw.write("<script>"
						+ "alert('게시물이 올바르게 등록 되었습니다.');"
						+ "location.href = './notice_list.do';"
						+ "</script>");
			}
			else {
				pw.write("<script>"
						+ "alert('데이터베이스 문제로 게시물이 등록되지 않았니다.');"
						+ "history.go(-1);"
						+ "</script>");					
			}
		}	
	}
}
