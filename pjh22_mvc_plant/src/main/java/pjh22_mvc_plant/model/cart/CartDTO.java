package pjh22_mvc_plant.model.cart;

public class CartDTO{
	private int pro_num;
	private String pro_name;
	private int pro_cnt;
	private int pro_price;
	private String pro_img;
	
	public CartDTO() {
		super();
	}
	
	public CartDTO(int pro_num, String pro_name, int pro_cnt, int pro_price, String pro_img) {
		super();
		this.pro_num = pro_num;
		this.pro_name = pro_name;
		this.pro_cnt = pro_cnt;
		this.pro_price = pro_price;
		this.pro_img = pro_img;
	}

	public int getPro_num() {
		return pro_num;
	}

	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getPro_cnt() {
		return pro_cnt;
	}

	public void setPro_cnt(int pro_cnt) {
		this.pro_cnt = pro_cnt;
	}

	public int getPro_price() {
		return pro_price;
	}

	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	
}