package pjh22_mvc_beer2.model.beer;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

import pjh22_mvc_beer2.model.beer.BeerDTO;

import javax.naming.*;

public class BeerDAO {
	/*
	 * private final static String driverName ="org.mariadb.jdbc.Driver"; private
	 * final static String url ="jdbc:mariadb://localhost/pjh22_mall_db"; private
	 * final static String user ="root"; private final static String passwd ="1234";
	 */
	
	private PreparedStatement pstmt = null;
	private Connection con = null;
	
	
	Context init =null;
	DataSource ds = null;
	ResultSet rs = null;
	
	/*
	 * // JDBC 드라이버 로드 public void jdbcDriverLoad() { try {
	 * Class.forName(driverName); } catch(Exception e) { e.printStackTrace(); } }
	 * 
	 * // 데이터베이스 연결 메소드 public void dbConnect() { try { con =
	 * DriverManager.getConnection(url,user,passwd); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */
	// 데이터베이스 연결 해제 메소드
	
	public BeerDAO() {
		super();
		dbConnect();
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
	
	// 모든 게시판 반환 메소드 R
	public ArrayList<BeerDTO> getBeerList(){
		
		//dbConnect();
		ArrayList<BeerDTO> list =new ArrayList<BeerDTO>();
		
		String SQL = "select * from beer";
		try {
			pstmt = con.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BeerDTO beer= new BeerDTO();
				beer.setB_id(rs.getInt("b_id"));
				beer.setB_code(rs.getString("b_code"));
				beer.setB_category(rs.getString("b_category"));
				beer.setB_name(rs.getString("b_name"));
				beer.setB_country(rs.getString("b_country"));
				beer.setB_price(rs.getInt("b_price"));
				beer.setB_alcohol(rs.getString("b_alcohol"));
				beer.setB_content(rs.getString("b_content"));
				beer.setB_like(rs.getInt("b_like"));
				beer.setB_dislike(rs.getInt("b_dislike"));
				beer.setB_image(rs.getString("b_image"));
				list.add(beer);	
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
	// 게시판의 현재 페이지 레코드를 반환 메서드 - R4
	public ArrayList<BeerDTO> getBeerListForPage(BeerPageInfoVO bpiVO){
			
			//dbConnect();
			ArrayList<BeerDTO> list =new ArrayList<BeerDTO>();

			String SQL = "select * from beer ORDER BY b_id limit ?, ?";
			String SQL2 = "select count(*) from beer";
			ResultSet rs;
			
			try {
				pstmt = con.prepareStatement(SQL2);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					bpiVO.setRecordCnt(rs.getInt(1));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			bpiVO.adjPageInfo();
			
			try {
				pstmt = con.prepareStatement(SQL);
				
				pstmt.setInt(1, bpiVO.getStartRecord());
				pstmt.setInt(2, bpiVO.getLimitCnt());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BeerDTO beer= new BeerDTO();
					beer.setB_id(rs.getInt("b_id"));
					beer.setB_code(rs.getString("b_code"));
					beer.setB_category(rs.getString("b_category"));
					beer.setB_name(rs.getString("b_name"));
					beer.setB_country(rs.getString("b_country"));
					beer.setB_price(rs.getInt("b_price"));
					beer.setB_alcohol(rs.getString("b_alcohol"));
					beer.setB_content(rs.getString("b_content"));
					beer.setB_like(rs.getInt("b_like"));
					beer.setB_dislike(rs.getInt("b_dislike"));
					beer.setB_image(rs.getString("b_image"));
					list.add(beer);	
				}
				rs.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				disConnect();
			}
			
			return list;
	}
	// 주 키 b_id의 레코드를 반환하는 메서드 - R
	public BeerDTO getBeer(int b_id) {
		//dbConnect();
		String SQL = "select * from beer where b_id = ?";
		BeerDTO beer = new BeerDTO();
		
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, b_id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			beer.setB_id(rs.getInt("b_id"));
			beer.setB_code(rs.getString("b_code"));
			beer.setB_category(rs.getString("b_category"));
			beer.setB_name(rs.getString("b_name"));
			beer.setB_country(rs.getString("b_country"));
			beer.setB_price(rs.getInt("b_price"));
			beer.setB_alcohol(rs.getString("b_alcohol"));
			beer.setB_content(rs.getString("b_content"));
			beer.setB_like(rs.getInt("b_like"));
			beer.setB_dislike(rs.getInt("b_dislike"));
			beer.setB_image(rs.getString("b_image"));
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return beer;
		
	}
	// 게시물 등록 메서드
	public boolean insertBeer(BeerDTO beer) {
		boolean success = false;
		//dbConnect();
		String sql ="insert into beer(b_id,b_code,b_category,b_name,";
		sql+="b_country,b_price,b_alcohol,b_content,b_like,b_dislike,b_image)";
		sql+="values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, beer.getB_id());
			pstmt.setString(2, beer.getB_code());
			pstmt.setString(3, beer.getB_category());
			pstmt.setString(4, beer.getB_name());
			pstmt.setString(5,beer.getB_country());
			pstmt.setInt (6, beer.getB_price());
			pstmt.setString(7, beer.getB_alcohol());
			pstmt.setString(8, beer.getB_content());
			pstmt.setInt (9, beer.getB_like());
			pstmt.setInt (10, beer.getB_dislike());
			pstmt.setString(11, beer.getB_image());
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
	
	// 데이터 갱신을 위한 메서드 - U 
	public boolean updateBeer(BeerDTO beer) {
		boolean success = false;
		//dbConnect();
		
		String sql ="update beer set b_code=?,  b_category=?,  b_name=?, b_country=?, ";
		sql +="b_price=?,  b_alcohol=?,  b_content=?,  b_like=?,  b_dislike=?, ";
		sql +="b_image=? where b_id=?";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, beer.getB_code());
			pstmt.setString(2, beer.getB_category());
			pstmt.setString(3, beer.getB_name());
			pstmt.setString(4,beer.getB_country());
			pstmt.setInt(5, beer.getB_price());
			pstmt.setString(6, beer.getB_alcohol());
			pstmt.setString(7, beer.getB_content());
			pstmt.setInt(8, beer.getB_like());
			pstmt.setInt(9, beer.getB_dislike());
			pstmt.setString(10, beer.getB_image());
			pstmt.setInt(11, beer.getB_id());
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
	
	// 게시물 삭제를 위한 메서드 - D
	public boolean deleteBeer(int b_id) {
		boolean success = false;
		//dbConnect();
		String sql ="delete from beer where b_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			// 인자로 받은 주 키인 id 값을 이용해 삭제
			
			pstmt.setInt(1, b_id);
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
}
