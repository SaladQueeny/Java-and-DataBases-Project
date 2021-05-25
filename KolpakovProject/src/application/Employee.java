package application;
//Колпаков Егор
public class Employee {//Класс для сохранения информации о работнике
	
	private String ID;
	private String First_Name;
	private String Last_Name;
	private String email;
	private String Telephone_number;
	private String PassportID;
	private String password;
	private String security_level;
	
	public Employee() {

	}
	public Employee(String iD, String first_Name, String last_Name, String email, String telephone_number,String passportID, String password, String security_level) {
		this.ID = iD;
		this.First_Name = first_Name;
		this.Last_Name = last_Name;
		this.email = email;
		this.Telephone_number = telephone_number;
		this.PassportID = passportID;
		this.password = password;
		this.security_level=security_level;
		
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone_number() {
		return Telephone_number;
	}
	public void setTelephone_number(String telephone_number) {
		Telephone_number = telephone_number;
	}
	public String getPassportID() {
		return PassportID;
	}
	public void setPassportID(String passportID) {
		PassportID = passportID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurity_level() {
		return security_level;
	}

	public void setSecurity_level(String security_level) {
		this.security_level = security_level;
	}
	
	
}
