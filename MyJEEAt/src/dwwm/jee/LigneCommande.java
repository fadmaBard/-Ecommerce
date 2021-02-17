package dwwm.jee;

public class LigneCommande {
	private int idLigneCommande;
	private int qtyCommandee;
	public LigneCommande(int idLigneCommande, int qtyCommandee) {
		super();
		this.idLigneCommande = idLigneCommande;
		this.qtyCommandee = qtyCommandee;
	}
	public LigneCommande(int qtyCommandee) {
		super();
		this.qtyCommandee = qtyCommandee;
	}
	public int getQtyCommandee() {
		return qtyCommandee;
	}
	public void setQtyCommandee(int qtyCommandee) {
		this.qtyCommandee = qtyCommandee;
	}
	public int getIdLigneCommande() {
		return idLigneCommande;
	}
	
	
}
