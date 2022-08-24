package mx.com.mundox.maximo.web.dto;

public class CatBancosDTO {
	
	private Integer id;
	private String name;
    private String code;
    private String status;
    
	public CatBancosDTO() {
	}
	
	public CatBancosDTO(Integer id, String name, String code, String status) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.status = status;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CatBancosDTO [id=" + id + ", name=" + name + ", code=" + code + ", status=" + status + "]";
	}
    

}
