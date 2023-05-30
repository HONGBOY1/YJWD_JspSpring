package pjh22_mvc_plant.model.product;

public class ProductDTO{
	private int pro_num;	
	private int pro_cg;
	private String pro_name;
	private String pro_content;
	private int pro_cnt;
	private int pro_price;
	private String pro_img;
	private int pro_level;
	private int pro_water;
	
	public ProductDTO(int pro_num, int pro_cg, String pro_name, String pro_content, int pro_cnt, int pro_price,
			String pro_img, int pro_level, int pro_water) {
		super();
		this.pro_num = pro_num;
		this.pro_cg = pro_cg;
		this.pro_name = pro_name;
		this.pro_content = pro_content;
		this.pro_cnt = pro_cnt;
		this.pro_price = pro_price;
		this.pro_img = pro_img;
		this.pro_level = pro_level;
		this.pro_water = pro_water;
	}

	public ProductDTO(){
		super();
	}


	public int getPro_level() {
		return pro_level;
	}

	public void setPro_level(int pro_level) {
		this.pro_level = pro_level;
	}

	public int getPro_water() {
		return pro_water;
	}

	public void setPro_water(int pro_water) {
		this.pro_water = pro_water;
	}

	public int getPro_num() {
		return pro_num;
	}

	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}

	public int getPro_cg() {
		return pro_cg;
	}

	public void setPro_cg(int pro_cg) {
		this.pro_cg = pro_cg;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_content() {
		return pro_content;
	}

	public void setPro_content(String pro_content) {
		this.pro_content = pro_content;
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