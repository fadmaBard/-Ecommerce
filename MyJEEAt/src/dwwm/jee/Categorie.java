package dwwm.jee;

public class Categorie {
	private int idCategorie;
	private String designation;
	public Categorie(int idCategorie, String designation) {
		super();
		this.idCategorie = idCategorie;
		this.designation = designation;
	}
	public Categorie(String designation) {
		super();
		this.designation = designation;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	
}
