
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dto.NoticeDTO;
import model.DBConnect;

public class NoticeDao {
	
	private DBConnect db = new DBConnect(); 
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
	
	public int insert_data(NoticeDTO ndto) {
		
		int result = 0;
		
		try {
			this.con = db.getConnection();
			String sql = "insert into notice values ('0',?,?,?,?,?,?,1,now())";
			this.ps = con.prepareStatement(sql);
			this.ps.setString(1, ndto.getSubject());
			this.ps.setString(2, ndto.getWriter());
			this.ps.setString(3, ndto.getPasswd());
			this.ps.setString(4, ndto.getTexts());
			this.ps.setString(5, ndto.getFilenm());
			this.ps.setString(6, ndto.getNfile());
			
			result = this.ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			result = 0;
        } finally {
            try {
                if (this.ps != null) this.ps.close();
                if (this.con != null) this.con.close();
            } catch (Exception e) {
                e.printStackTrace(); // 자원 해제 실패 시 출력
            }
        }
		
		return result;
	}
	
	public Map<String, Object> select_page(int pageno, int ctn) {
		
		NoticeDTO nDto = null;
		ArrayList<NoticeDTO> pageAll = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		
		//"select * from 테이블명 limit 시작번호, 갯수" 에서 시작번호는 0부터 시작, pageno(페이지번호)는 1부터 시작 
		int first_no = (pageno - 1) * ctn;  //각 페이지별 시작번호 

		try {
			this.con = db.getConnection();

			String sql_count = "select count(*) as total from notice";
			this.ps = con.prepareStatement(sql_count);
			this.rs = this.ps.executeQuery();
			this.rs.next();
			int total_ctn = this.rs.getInt("total");
			
			//최신 게시물부터 출력(역순 출력) : order by nidx desc
			String sql_pageall = "select nidx,subject,writer,nview,ndate from notice order by nidx desc limit ?,?"; 
			this.ps = con.prepareStatement(sql_pageall);
			this.ps.setInt(1, first_no);  //각 페이지별 시작번호 
			this.ps.setInt(2, ctn);       //페이지당 출력갯수 
			this.rs = this.ps.executeQuery();
			
			while(this.rs.next()) {
				nDto = new NoticeDTO();
				nDto.setNidx(this.rs.getInt("nidx"));
				nDto.setSubject(this.rs.getString("subject"));
				nDto.setWriter(this.rs.getString("writer"));
				nDto.setNvew(this.rs.getInt("nview"));
				nDto.setNdate(this.rs.getTimestamp("ndate"));
				
				pageAll.add(nDto);
			}
			
			result.put("total_ctn", total_ctn);
			result.put("pageAll", pageAll);
			
		}catch (Exception e) {
			e.printStackTrace();  // 예외 메시지 출력
			pageAll = null;
		}finally{
            try {
            	if (this.rs != null) this.rs.close();
                if (this.ps != null) this.ps.close();
                if (this.con != null) this.con.close();
            } catch (Exception e) {
                e.printStackTrace(); // 자원 해제 실패 시 출력
            }		
		}
		
		return result;
	}
}
