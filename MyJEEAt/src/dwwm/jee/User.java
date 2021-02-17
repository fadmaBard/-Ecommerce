package dwwm.jee;

public class User {
	private int numero;
	private String login;
	private String pwd;
	private String type;
	
	public User(int numero, String login, String pwd, String type) {
		super();
		this.numero = numero;
		this.login = login;
		this.pwd = pwd;
		this.type = type;
	}
	public User(String login, String pwd, String type) {
		super();
		this.login = login;
		this.pwd = pwd;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
