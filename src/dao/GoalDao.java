package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import dto.GoalThingsDto;

public class GoalDao {
	public Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "아이디";
		String pw = "비번";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pw);
		return conn;
	}

	public ArrayList<Integer> getAllGoalIdxFromProjectIdx(int projectIdx) throws Exception {
		String sql = "SELECT goal_idx " + 
				"FROM goal " + 
				"WHERE goal_idx NOT IN ( " + 
				"	SELECT goal_idx " + 
				"	FROM goal " + 
				"	WHERE project_idx = ? " + 
				")";
		ArrayList<Integer> listRet = new ArrayList<Integer>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, projectIdx);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
//			int goalIdx = rs.getInt("goal_idx");
//			listRet.add(goalIdx);
			listRet.add(rs.getInt("goal_idx"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return listRet;
	}
	
	public ArrayList<GoalThingsDto> getGoalThingsListFromGoalIdx
							(ArrayList<Integer> listGoalIdx) throws Exception {
		String sql = "SELECT title, goal.period_idx, member.profile_img, project_period.name" + 
				" FROM goal " + 
				" INNER JOIN member" + 
				" ON goal.owner = member.member_idx" + 
				" INNER JOIN project_period" + 
				" ON goal.period_idx = project_period.period_idx" + 
				" WHERE goal_idx IN (" +
				listGoalIdx.toString().replace("[","").replace("]","") +
				")";
		ArrayList<GoalThingsDto> listRet = new ArrayList<GoalThingsDto>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String title = rs.getString("title");
			int periodIdx = rs.getInt("period_idx");
			String profileImg = rs.getString("profile_img");
			String name = rs.getString("name");
			GoalThingsDto dto = new GoalThingsDto(title, periodIdx, profileImg, name);
			listRet.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return listRet;
	}

	public String getStatusNameFromGoalIdx(int goalIdx) throws Exception {
		String sql = "SELECT name" + 
				" FROM status" + 
				" WHERE status_idx IN (" + 
				"	SELECT status_idx" + 
				"	FROM (" + 
				"		SELECT *" + 
				"		FROM goal_status" + 
				"		WHERE goal_idx=?" + 
				"		ORDER BY update_date DESC" + 
				"	)" + 
				"	WHERE ROWNUM=1" + 
				" )";
		String ret = "";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, goalIdx);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			ret = rs.getString("name");
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		GoalDao dao = new GoalDao();
		ArrayList<Integer> listGoalIdx = dao.getAllGoalIdxFromProjectIdx(1);
		System.out.println(listGoalIdx);
		
		for(int goalIdx : listGoalIdx) {
			System.out.println(goalIdx + " -> " + dao.getStatusNameFromGoalIdx(goalIdx));
		}
		
		ArrayList<GoalThingsDto> listGoalThingsDto = 
				dao.getGoalThingsListFromGoalIdx(listGoalIdx);
		for(GoalThingsDto dto : listGoalThingsDto) {
			System.out.println("title : " + dto.getTitle());
			System.out.println("period_idx : " + dto.getPeriodIdx());
			System.out.println("profile_img : " + dto.getProfileImg());
			System.out.println("project period name : " + dto.getProjectPeriodName());
			System.out.println();
		}
		
	}
}








