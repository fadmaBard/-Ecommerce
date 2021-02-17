package dwwm.jee;

public class Article {
	private int idArticle;
	private String designation;
	private float pu;
	private int qty;
	
	public Article(int idArticle, String designation, float pu, int qty) {
		super();
		this.idArticle = idArticle;
		this.designation = designation;
		this.pu = pu;
		this.qty = qty;
	}
	public Article(String designation, float pu, int qty) {
		super();
		this.designation = designation;
		this.pu = pu;
		this.qty = qty;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public float getPu() {
		return pu;
	}
	public void setPu(int pu) {
		this.pu = pu;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getIdArticle() {
		return idArticle;
	}
	
}
