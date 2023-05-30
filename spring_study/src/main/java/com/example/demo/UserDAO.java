package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	   final String JDBC_URL = "jdbc:mariadb://localhost/plant_db";

	   private Connection con = null;

	   private PreparedStatement pstmt = null;

	   ResultSet rs = null;

	   public Connection dbConnect() {
	      try {
	         Class.forName(JDBC_DRIVER);
	         con = DriverManager.getConnection(JDBC_URL, "root", "1234");
	         System.out.println("DB 연결 성공!!!");
	      } catch (Exception e) {
	         System.out.println("DB 연결 실패!!!");
	         e.printStackTrace();
	      }
	      return con;
	   }

	   public void close() {
	      try {
	         pstmt.close();
	         con.close();
	         rs.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }

    
    public boolean joinMember(UserDTO member) {
		Date nowTime = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		
		boolean success = false;
		dbConnect();
		String sql ="insert into member(mem_id,mem_pwd,mem_level,mem_name, mem_nickname,";
		sql+="mem_email,mem_phone,mem_zcode,mem_add,mem_add2,mem_admin,mem_date)";
		sql+="values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pwd());
			pstmt.setInt(3,1);
			pstmt.setString(4, member.getMem_name());
			pstmt.setString(5, member.getMem_nickname());
			pstmt.setString(6, member.getMem_email());
			pstmt.setString(7, member.getMem_phone());
			pstmt.setString(8, member.getMem_zcode());
			pstmt.setString(9, member.getMem_add());
			pstmt.setString(10, member.getMem_add2());
			pstmt.setString(11, "0");
			pstmt.setString(12,day.format(nowTime));

			pstmt.executeUpdate();
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
			return success;
		}
		
		return success;		
	}


		
	
		public UserDTO getmember(String mem_id){
			dbConnect();
			String SQL = "select * from member where mem_id = ?";
			UserDTO member = new UserDTO();
	
			try {
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, mem_id);
				ResultSet rs = pstmt.executeQuery();
			
				rs.next();
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_pwd(rs.getString("mem_pwd"));
				member.setMem_level(rs.getInt("mem_level"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_nickname(rs.getString("mem_nickname"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_zcode(rs.getString("mem_zcode"));
				member.setMem_add(rs.getString("mem_add"));
				member.setMem_add2(rs.getString("mem_add2"));
				member.setMem_date(rs.getString("mem_date"));
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return member;
		
		}
		
		public int login(String mem_id, String mem_pwd){
			dbConnect();
			String sql = "SELECT mem_pwd FROM member WHERE mem_id= ?";
			try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					rs = pstmt.executeQuery();
					if(rs.next()){
						if(rs.getString(1).equals(mem_pwd))
							return 1;
						else 
							return 0;
					}
					return -1;
			} catch (Exception e){
				e.printStackTrace();
			}
			
			return -2;
		}
}
