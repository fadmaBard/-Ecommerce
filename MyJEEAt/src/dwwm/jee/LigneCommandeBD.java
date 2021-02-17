package dwwm.jee;

public class LigneCommandeBD {
	private int idLigneCommandeBD;
	private String idCommandeBD;
	private String idArticleBD;
	private String designationArticleBD;
	private String qtyCommandeeBD;
	public LigneCommandeBD(int idLigneCommandeBD, String idCommandeBD, String idArticleBD, String designationArticleBD, String qtyCommandeeBD) {
		super();
		this.idLigneCommandeBD = idLigneCommandeBD;
		this.idCommandeBD = idCommandeBD;
		this.idArticleBD = idArticleBD;
		this.designationArticleBD = designationArticleBD;
		this.qtyCommandeeBD = qtyCommandeeBD;
	}
	public LigneCommandeBD(String idCommandeBD, String idArticleBD, String designationArticleBD, String qtyCommandeeBD) {
		super();
		this.idCommandeBD = idCommandeBD;
		this.idArticleBD = idArticleBD;
		this.designationArticleBD = designationArticleBD;
		this.qtyCommandeeBD = qtyCommandeeBD;
	}
	public String getDesignationArticleBD() {
		return designationArticleBD;
	}
	public void setDesignationArticleBD(String designationArticleBD) {
		this.designationArticleBD = designationArticleBD;
	}
	public int getIdLigneCommandeBD() {
		return idLigneCommandeBD;
	}
	public void setIdLigneCommandeBD(int idLigneCommandeBD) {
		this.idLigneCommandeBD = idLigneCommandeBD;
	}
	public String getIdArticleBD() {
		return idArticleBD;
	}
	public void setIdArticleBD(String idArticleBD) {
		this.idArticleBD = idArticleBD;
	}
	public String getQtyCommandeeBD() {
		return qtyCommandeeBD;
	}
	public void setQtyCommandeeBD(String qtyCommandeeBD) {
		this.qtyCommandeeBD = qtyCommandeeBD;
	}
	public String getIdCommandeBD() {
		return idCommandeBD;
	}
	
}
