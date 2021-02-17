package dwwm.jee;

public class Commande {
	private int idCommande;
	private String dateCommande;
	public Commande(int idCommande, String date) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = date;
	}
	public Commande(String date) {
		super();
		this.dateCommande = date;
	}
	public String getDate() {
		return dateCommande;
	}
	public void setDate(String date) {
		this.dateCommande = date;
	}
	public int getIdCommande() {
		return idCommande;
	}
	
	
}
