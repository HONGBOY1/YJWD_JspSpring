package pjh22_mvc_beer3.model.beer;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

import pjh22_mvc_beer3.model.beer.BeerDTO;

import javax.naming.*;
import javax.servlet.http.HttpSession;

public class BeerDAO {

	private PreparedStatement pstmt = null;
	private Connection con = null;
	HttpSession session;
	Context init =null;
	DataSource ds = null;
	ResultSet rs = null;
	

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
	
	// ��� �Խ��� ��ȯ �޼ҵ� R
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
	// �Խ����� ���� ������ ���ڵ带 ��ȯ �޼��� - R4
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
		// �Խ����� ���� ������ ���� �ڵ�
		
		// �Խ����� ���� ������ ���ڵ带  ���� �� �°�
		public ArrayList<BeerDTO> getBeerListRCPage(BeerPageInfoVO bpiVO, String type,String country,String pay,String select){
				
				//dbConnect();
				ArrayList<BeerDTO> list =new ArrayList<BeerDTO>();

				String SQL="";
				String SQL2 = "";
				ResultSet rs;
				
				if(type==null) {
					SQL= "select * from beer ORDER BY b_id limit ?, ?";
				}
				else {
				
					if(type.equals("null") && country.equals("null") && pay.equals("null") && select.length()==0) {
						SQL= "select * from beer ORDER BY b_id limit ?, ?";
					}else {
						if(type.equals("null") && country.equals("null") && select.length()==0) {
							SQL= "select * from beer ORDER BY b_price "+pay+", b_id limit ?, ?";
						}
						else if(select.length()>=1) {
							SQL= "select * from beer where b_name LIKE '"+select+ "%' ORDER BY  b_id limit ?, ?";
						}
						else if(pay.equals("null")){
							SQL= "select * from beer where  b_category = '"+type+ "' or b_country = '"+
									country +"' ORDER BY b_id limit ?, ?";
						}
						else {
							SQL= "select * from beer where  b_category = '"+type+ "' or b_country = '"+
									country+"' ORDER BY b_price "+pay+", b_id limit ?, ?";
						}		
					}
				}
				SQL2= "select count(*) from beer";
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
	// �� Ű b_id�� ���ڵ带 ��ȯ�ϴ� �޼��� - R
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
	// �Խù� ��� �޼���
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
	
	// ������ ������ ���� �޼��� - U 
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
	
	// �Խù� ������ ���� �޼��� - D
	public boolean deleteBeer(int b_id) {
		boolean success = false;
		//dbConnect();
		String sql ="delete from beer where b_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			// ���ڷ� ���� �� Ű�� id ���� �̿��� ����
			
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
	// ������� �޼���(I)
	   public boolean updateImage(BeerDTO beer, String image,String code) {
	      boolean success = false;
	      //dbConnect();
	      
	      String sql ="update beer set b_image=? where b_code=?";
	   
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, image);
	         pstmt.setString(2, code);
	         int rowUdt = pstmt.executeUpdate();
	         if(rowUdt ==1) success = true;
	      }catch(SQLException e) {
	         e.printStackTrace();
	         return success;
	      }
	      finally {
	         disConnect();
	      }
	      System.out.println("�޼��� code " + beer.getB_code());
	      System.out.println("�޼��� image " + image);
	      return success;
	   }
	
	//�ڵ� �ڵ� ����
	public String getBeerOTPage(String type, String country){
		String pull="";
		String SQL="select * from beer where b_code like '____"+country+"%' order by b_code desc";
		
		ResultSet rs;
		
		try {
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				BeerDTO beer= new BeerDTO();
				String num = rs.getString("b_code");
				System.out.println(num);
				String data ="";
				int ber = Integer.valueOf(num.substring(7,11)) + 1;
				if(ber < 10) {
					data="000"+ber;
				}
				else if(ber > 10) {
					data="00"+ber;
				}
				pull+="BE"+type+country+data;
			}
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			disConnect();
		}
		return pull;
	}



}
