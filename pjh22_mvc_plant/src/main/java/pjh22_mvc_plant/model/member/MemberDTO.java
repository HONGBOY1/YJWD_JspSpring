package pjh22_mvc_plant.model.member;

public class MemberDTO{
	private String mem_id;
	private String mem_pwd;
	private int mem_level;
	private String mem_name;
	private String mem_nickname;
	private String mem_email;
	private String mem_phone;
	private String mem_zcode;
	private String mem_add;
	private String mem_add2;
	private String mem_admin;
	private String mem_date;
	
	public MemberDTO(){
		super();
	}
	
	public MemberDTO(String mem_id, String mem_pwd, int mem_level, String mem_name, String mem_nickname,
			String mem_email, String mem_phone, String mem_zcode, String mem_add, String mem_add2, String mem_admin, String mem_date) {
		super();
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_level = mem_level;
		this.mem_name = mem_name;
		this.mem_nickname = mem_nickname;
		this.mem_email = mem_email;
		this.mem_phone = mem_phone;
		this.mem_zcode = mem_zcode;
		this.mem_add = mem_add;
		this.mem_add2 = mem_add2;
		this.mem_admin = mem_admin;
		this.mem_date = mem_date;
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public int getMem_level() {
		return mem_level;
	}
	public void setMem_level(int mem_level) {
		this.mem_level = mem_level;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_zcode() {
		return mem_zcode;
	}
	public void setMem_zcode(String mem_zcode) {
		this.mem_zcode = mem_zcode;
	}
	public String getMem_add() {
		return mem_add;
	}
	public void setMem_add(String mem_add) {
		this.mem_add = mem_add;
	}
	public String getMem_add2() {
		return mem_add2;
	}
	public void setMem_add2(String mem_add2) {
		this.mem_add2 = mem_add2;
	}
	public String getMem_admin() {
		return mem_admin;
	}
	public void setMem_admin(String mem_admin) {
		this.mem_admin = mem_admin;
	}
	public String getMem_date() {
		return mem_date;
	}
	public void setMem_date(String mem_date) {
		this.mem_date = mem_date;
	}
	
	
}