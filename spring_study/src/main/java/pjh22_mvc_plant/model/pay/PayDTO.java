package pjh22_mvc_plant.model.pay;

public class PayDTO{
	private int ord_num;
	private String mem_id;
	private int pro_num;
	private String pro_img;
	private String pro_name;
	private int pro_cnt;
	private String ord_name;
	private String ord_phone;
	private String ord_zcode;
	private String ord_add;
	private String ord_add2;
	private String ord_content;
	private int ord_price;
	private int ord_chk;
	private String ord_date;
	
	public PayDTO(){
		super();
	}

	public PayDTO(int ord_num, String mem_id, int pro_num, String pro_img, String pro_name, int pro_cnt,
			String ord_name, String ord_phone, String ord_zcode, String ord_add, String ord_add2, String ord_content,
			int ord_price, int ord_chk, String ord_date) {
		super();
		this.ord_num = ord_num;
		this.mem_id = mem_id;
		this.pro_num = pro_num;
		this.pro_img = pro_img;
		this.pro_name = pro_name;
		this.pro_cnt = pro_cnt;
		this.ord_name = ord_name;
		this.ord_phone = ord_phone;
		this.ord_zcode = ord_zcode;
		this.ord_add = ord_add;
		this.ord_add2 = ord_add2;
		this.ord_content = ord_content;
		this.ord_price = ord_price;
		this.ord_chk = ord_chk;
		this.ord_date = ord_date;
	}

	public int getOrd_num() {
		return ord_num;
	}

	public void setOrd_num(int ord_num) {
		this.ord_num = ord_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getPro_num() {
		return pro_num;
	}

	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
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

	public String getOrd_name() {
		return ord_name;
	}

	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}

	public String getOrd_phone() {
		return ord_phone;
	}

	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}

	public String getOrd_zcode() {
		return ord_zcode;
	}

	public void setOrd_zcode(String ord_zcode) {
		this.ord_zcode = ord_zcode;
	}

	public String getOrd_add() {
		return ord_add;
	}

	public void setOrd_add(String ord_add) {
		this.ord_add = ord_add;
	}

	public String getOrd_add2() {
		return ord_add2;
	}

	public void setOrd_add2(String ord_add2) {
		this.ord_add2 = ord_add2;
	}

	public String getOrd_content() {
		return ord_content;
	}

	public void setOrd_content(String ord_content) {
		this.ord_content = ord_content;
	}

	public int getOrd_price() {
		return ord_price;
	}

	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}

	public int getOrd_chk() {
		return ord_chk;
	}

	public void setOrd_chk(int ord_chk) {
		this.ord_chk = ord_chk;
	}

	public String getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(String ord_date) {
		this.ord_date = ord_date;
	}

	
}