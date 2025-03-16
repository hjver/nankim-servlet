package notice;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeDao;

public class notice_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ctn_per_page = 3;  //페이지당 출력갯

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageno = request.getParameter("pageno");
		if(pageno == null) { //최초 게시판에 접속시 1페이지 화면 출력 
			pageno = "1";
		}
		
		request.setAttribute("ctn_per_page", ctn_per_page);
		
		Map<String, Object> result = new NoticeDao().select_page(Integer.parseInt(pageno), this.ctn_per_page);		
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher("./notice_list.jsp");
		rd.forward(request, response);		
	}
}
