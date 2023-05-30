package pjh22_mvc_plant.model.pay;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

import pjh22_mvc_plant.model.member.MemberDTO;
import pjh22_mvc_plant.model.member.MemberPageInfoVO;
import pjh22_mvc_plant.model.pay.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import javax.naming.*;
import javax.servlet.http.HttpSession;

public class PayDAO{
	private PreparedStatement pstmt = null;
	private Connection con = null;
	HttpSession session;
	Context init =null;
	DataSource ds = null;
	ResultSet rs = null;
	
	public PayDAO() {
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
	
	
	public boolean order(PayDTO pay) {

		Date nowTime = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
		
		boolean success = false;
		dbConnect();
		String sql ="insert into product_order(mem_id,pro_img,pro_name,pro_cnt, ord_name,ord_phone, ord_zcode,";
		sql+="ord_add,ord_add2,ord_content,ord_price,ord_chk,ord_date)";
		sql+="values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pay.getMem_id());
			pstmt.setString(2, pay.getPro_img());
			pstmt.setString(3, pay.getPro_name());
			pstmt.setInt(4, pay.getPro_cnt());
			pstmt.setString(5, pay.getOrd_name());
			pstmt.setString(6, pay.getOrd_phone());
			pstmt.setString(7, pay.getOrd_zcode());
			pstmt.setString(8, pay.getOrd_add());
			pstmt.setString(9, pay.getOrd_add2());
			pstmt.setString(10, pay.getOrd_content());
			pstmt.setInt(11, pay.getOrd_price());
			pstmt.setInt(12, 0);
			pstmt.setString(13, day.format(nowTime));

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
	
	public ArrayList<PayDTO> getPayList(String mem_id){
		dbConnect();
		ArrayList<PayDTO> list =new ArrayList<PayDTO>();
		
		String SQL = "select * from product_order where mem_id ='"+mem_id+"'ORDER BY mem_id";
		String SQL2 = "select count(*) from member";
		ResultSet rs;

		try {
			pstmt = con.prepareStatement(SQL);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PayDTO pay= new PayDTO();
				pay.setMem_id(rs.getString("mem_id"));
				pay.setPro_img(rs.getString("pro_img"));
				pay.setPro_name(rs.getString("pro_name"));
				pay.setPro_cnt(rs.getInt("pro_cnt"));
				pay.setOrd_chk(rs.getInt("ord_chk"));
				pay.setOrd_price(rs.getInt("ord_price"));
				pay.setOrd_date(rs.getString("ord_date"));
				list.add(pay);	
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

}
