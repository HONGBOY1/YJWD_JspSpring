package pjh22_mvc_plant.model.member;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

import pjh22_mvc_plant.model.member.MemberPageInfoVO;
import pjh22_mvc_plant.model.member.MemberDTO;

import javax.naming.*;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class MemberDAO{
	private PreparedStatement pstmt = null;
	private Connection con = null;
	HttpSession session;
	Context init =null;
	DataSource ds = null;
	ResultSet rs = null;
	
	public MemberDAO() {
		super();
	}
	
	public void dbConnect() {
		try {
			Context init = new InitialContext();
			DataSource ds =(DataSource)init.lookup("java:comp/env/jdbc_mariadb");
			con =ds.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void disConnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(con != null) {
			try {
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean joinMember(MemberDTO member) {
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
		finally {
			disConnect();
		}
		
		return success;		
	}

	public ArrayList<MemberDTO> getmemberList(MemberPageInfoVO mpiVO){
			dbConnect();
			ArrayList<MemberDTO> list =new ArrayList<MemberDTO>();
			
			String SQL = "select * from member where not mem_id='"+"admin"+"' ORDER BY mem_id limit ?, ?";
			String SQL2 = "select count(*) from member";
			ResultSet rs;
			try {
				pstmt = con.prepareStatement(SQL2);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mpiVO.setRecordCnt(rs.getInt(1));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			mpiVO.adjPageInfo();
			try {
				pstmt = con.prepareStatement(SQL);
				
				pstmt.setInt(1, mpiVO.getStartRecord());
				pstmt.setInt(2, mpiVO.getLimitCnt());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberDTO member= new MemberDTO();
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
					list.add(member);	
				}
				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disConnect();
			}
			return list;
		
	}
	
	// ��� ȸ�� ���� - U 
		public boolean updateMember(MemberDTO member) {
			boolean success = false;
			dbConnect();
			
			String sql ="update member set mem_pwd=?,  mem_name=?,  mem_nickname=?, mem_email=?, ";
			sql +="mem_phone=?,mem_zcode= ?,  mem_add=?,  mem_add2=?";
			sql +=" where mem_id = ?";
		
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMem_pwd());
				pstmt.setString(2, member.getMem_name());
				pstmt.setString(3, member.getMem_nickname());
				pstmt.setString(4, member.getMem_email());
				pstmt.setString(5, member.getMem_phone());
				pstmt.setString(6, member.getMem_zcode());
				pstmt.setString(7, member.getMem_add());
				pstmt.setString(8, member.getMem_add2());
				pstmt.setString(9, member.getMem_id());
				
				int rowUdt = pstmt.executeUpdate();
				if(rowUdt ==1) success = true;
			}catch(SQLException e) {
				e.printStackTrace();
				return success;
			}
			finally {
				disConnect();
			}
			
			return success;
		}
		
		public boolean updateMy(MemberDTO member) {
			boolean success = false;
			dbConnect();
			
			String sql ="update member set mem_pwd=?,  mem_name=?,  mem_nickname=?, mem_email=?,mem_phone=?";
			sql +=" where mem_id = ?";
		
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getMem_pwd());
				pstmt.setString(2, member.getMem_name());
				pstmt.setString(3, member.getMem_nickname());
				pstmt.setString(4, member.getMem_email());
				pstmt.setString(5, member.getMem_phone());
				pstmt.setString(6, member.getMem_id());
				
				int rowUdt = pstmt.executeUpdate();
				if(rowUdt ==1) success = true;
			}catch(SQLException e) {
				e.printStackTrace();
				return success;
			}
			finally {
				disConnect();
			}
			
			return success;
		}
		
		public boolean updateaddr(MemberDTO member) {
			boolean success = false;
			dbConnect();
			
			String sql ="update member set mem_zcode= ?,  mem_add=?,  mem_add2=?";
			sql +=" where mem_id = ?";
		
			try {
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, member.getMem_zcode());
				pstmt.setString(2, member.getMem_add());
				pstmt.setString(3, member.getMem_add2());
				pstmt.setString(4, member.getMem_id());
				
				int rowUdt = pstmt.executeUpdate();
				if(rowUdt ==1) success = true;
			}catch(SQLException e) {
				e.printStackTrace();
				return success;
			}
			finally {
				disConnect();
			}
			
			return success;
		}
		
		// ȸ�� Ż�� ���� �޼��� - D
		public boolean deleteMember(String mem_id) {
			boolean success = false;
			dbConnect();
			String sql ="delete from member where mem_id=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				// ���ڷ� ���� �� Ű�� id ���� �̿��� ����
				pstmt.setString(1, mem_id);
				pstmt.executeUpdate();
				success = true;
			}catch(SQLException e) {
				e.printStackTrace();
				return success;
			}
			finally {
				disConnect();
			}
			return success;
		}
	
		// ���� ���� ����Ʈ	
		public MemberDTO getmember(String mem_id){
			dbConnect();
			String SQL = "select * from member where mem_id = ?";
			MemberDTO member = new MemberDTO();
	
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
			finally {
				disConnect();
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
			finally {
				disConnect();
			}
			return -2;
		}
}
