package pjh22_mvc_plant.model.product;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

import pjh22_mvc_plant.model.product.ProductPageInfoVO;
import pjh22_mvc_plant.model.member.MemberDTO;
import pjh22_mvc_plant.model.member.MemberPageInfoVO;
import pjh22_mvc_plant.model.product.ProductDTO;

import javax.naming.*;
import javax.servlet.http.HttpSession;

public class ProductDAO{
	private PreparedStatement pstmt = null;
	private Connection con = null;
	HttpSession session;
	Context init =null;
	DataSource ds = null;
	ResultSet rs = null;
	
	public ProductDAO() {
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
	
	
	public boolean insertProduct(ProductDTO prodcut) {
		boolean success = false;
		dbConnect();
		String sql ="insert into product(pro_cg,pro_name,pro_content,pro_cnt,pro_price,pro_img,pro_level,pro_water)";
		sql+="values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, prodcut.getPro_cg());
			pstmt.setString(2, prodcut.getPro_name());
			pstmt.setString(3, prodcut.getPro_content());
			pstmt.setInt(4, prodcut.getPro_cnt());
			pstmt.setInt(5, prodcut.getPro_price());
			pstmt.setString(6, prodcut.getPro_img());
			pstmt.setInt(5, prodcut.getPro_level());
			pstmt.setInt(5, prodcut.getPro_water());
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
	
	// 모든 상품 정보 반환 메소드
		public ArrayList<ProductDTO> getproductList(ProductPageInfoVO mpiVO){
			dbConnect();
				ArrayList<ProductDTO> list =new ArrayList<ProductDTO>();
				
				String SQL = "select * from product ORDER BY pro_num limit ?, ?";
				String SQL2 = "select count(*) from product";
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
						ProductDTO product= new ProductDTO();
						product.setPro_num(rs.getInt("pro_num"));
						product.setPro_cg(rs.getInt("pro_cg"));
						product.setPro_name(rs.getString("pro_name"));
						product.setPro_content(rs.getString("pro_content"));
						product.setPro_cnt(rs.getInt("pro_cnt"));
						product.setPro_price(rs.getInt("pro_price"));
						product.setPro_img(rs.getString("pro_img"));
						product.setPro_level(rs.getInt("pro_level"));
						product.setPro_water(rs.getInt("pro_water"));
						list.add(product);	
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
		// 게시판 삭제 를 위한 메서드 - D
		public boolean deleteProduct(int pro_num) {
			boolean success = false;
			dbConnect();
			String sql ="delete from product where pro_num=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				// 인자로 받은 주 키인 id 값을 이용해 삭제
				pstmt.setInt(1, pro_num);
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
		

		public ProductDTO getproduct(int pro_num){
			dbConnect();
			String SQL = "select * from product where pro_num = ?";
			ProductDTO product = new ProductDTO();
	
			try {
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, pro_num);
				ResultSet rs = pstmt.executeQuery();
			
				rs.next();
				product.setPro_num(rs.getInt("pro_num"));
				product.setPro_cg(rs.getInt("pro_cg"));
				product.setPro_name(rs.getString("pro_name"));
				product.setPro_content(rs.getString("pro_content"));
				product.setPro_cnt(rs.getInt("pro_cnt"));
				product.setPro_price(rs.getInt("pro_price"));
				product.setPro_img(rs.getString("pro_img"));
				product.setPro_level(rs.getInt("pro_level"));
				product.setPro_water(rs.getInt("pro_water"));

				rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				disConnect();
			}
			return product;
		
		}
		public boolean updateProduct(ProductDTO product) {
			boolean success = false;
			dbConnect();
			
			String sql ="update product set pro_cg=?,  pro_name=?,  pro_content=?, pro_cnt=?, ";
			sql +="pro_price=?,pro_img= ?, pro_level=?, pro_water=?";
			sql +=" where pro_num = ?";
		
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, product.getPro_cg());
				pstmt.setString(2, product.getPro_name());
				pstmt.setString(3, product.getPro_content());
				pstmt.setInt(4, product.getPro_cnt());
				pstmt.setInt(5, product.getPro_price());
				pstmt.setString(6, product.getPro_img());
				pstmt.setInt(7, product.getPro_level());
				pstmt.setInt(8, product.getPro_water());
				pstmt.setInt(9, product.getPro_num());
				
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
		
		public ArrayList<ProductDTO> getproductmlist(ProductPageInfoVO mpiVO,int cg){
			dbConnect();
			ArrayList<ProductDTO> list =new ArrayList<ProductDTO>();
			
			String SQL = "";
			
			if(cg== 0)SQL = "select * from product ORDER BY pro_num limit ?, ?";
			else {
				 SQL = "select * from product where pro_cg="+cg+" ORDER BY pro_num limit ?, ?";
			}
			String SQL2 = "select count(*) from product";
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
					ProductDTO product= new ProductDTO();
					product.setPro_num(rs.getInt("pro_num"));
					product.setPro_cg(rs.getInt("pro_cg"));
					product.setPro_name(rs.getString("pro_name"));
					product.setPro_content(rs.getString("pro_content"));
					product.setPro_cnt(rs.getInt("pro_cnt"));
					product.setPro_price(rs.getInt("pro_price"));
					product.setPro_img(rs.getString("pro_img"));
					list.add(product);	
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
