package pjh22_mvc_beer3.model.beer;

public class BeerCountryDTO {
	private String type;
	private String code;
	
	public BeerCountryDTO(){
		super();
	}

	public BeerCountryDTO(String type, String code) {
		super();
		this.type = type;
		this.code = code;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
