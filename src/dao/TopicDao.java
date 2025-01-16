package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TopicDao {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "아이디";
		String pw = "비번";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}
	
	public void writeTopicComment(int memberIdx, int topicIdx, int topicContentsIdx, String commentContent) throws Exception {
		String sql = "INSERT .INTO topic_comment(comment_idx, member_idx, topic_idx, topic_contents_idx, comment_content, comment_date, is_modified) " + 
				" VALUES (lk;.nextval, ?, ?, ?, ?, sysdate, 1)";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memberIdx);
		pstmt.setInt(2, topicIdx);
		pstmt.setInt(3, topicContentsIdx);
		pstmt.setString(4, commentContent);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
}









