package dwwm.jee;

import java.io.*;
import java.sql.*;
import java.util.*;

public class UserDAO {
	private String serveur = "jdbc:mysql://localhost";
	private String port = "8889";
	private String baseDeDonnees = "prjJdbc";
	private String url = serveur + ":"+ port + "/" + baseDeDonnees;
	private String nomUtilisateur = "root";
	private String motDePasse = "root";
	
	Connection cn = null;
	Statement st = null;
	Statement stSecours = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ResultSet rsSecours = null;
	String sql = null;
	public void etablirConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, nomUtilisateur, motDePasse);
			st = cn.createStatement();
			stSecours = cn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	public void cloturerConnection() {
		try {
			cn.close();
			st.close();
			stSecours.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String verifierCoordonnees(String login) {
		String mdp = null;
		sql = "select pwd from compte where login like '" + login + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mdp = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mdp;
	}
	public boolean findByNomAndPrenom(String fname, String lname) {
		sql = "select * from users where fname like '" + fname + "' and lname like '" + lname + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean findByLoginAndPwd(String login, String pwd) {
		sql = "select * from compte where login like '" + login + "' and pwd like '" + pwd + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public int getIdFromUsers(String login, String pwd) {
		int idCompte = 0;
		int idUsers = 0;
		sql = "select idCompte from compte where login like '" + login + "' and pwd like '" + pwd + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				idCompte = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "select idUsers from users where idCompte = " + idCompte;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				idUsers = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idUsers;
	}
	public void ajouterUser(User u) {
		if(!findByLoginAndPwd(u.getLogin(), u.getPwd())) {
			sql = "insert into compte(login,pwd,type) "
					+ "values('" + u.getLogin() + "','" 
					+ u.getPwd() + "','" + u.getType() + "')";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Compte existant");
		}
	}
	public int numCompte() {
		int num = 0;
		sql = "select max(idCompte) from compte";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	public void ajouterInscrit(Inscription ins) {
		int num = 0;
		if(!findByNomAndPrenom(ins.getfName(), ins.getlName())) {
			num = numCompte();
			sql = "insert into users(fname,lname,adresse,tel,age,sexe,idCompte) "
					+ "values('" + ins.getfName() + "','" 
					+ ins.getlName() + "','" + ins.getAdresse() + "','" +
					ins.getTel() + "'," + ins.getAge() + ",'" + ins.getSexe() + "'," + num + ")";
			//System.out.println(sql);
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("user existant");
		}
	}
	public void ajouterCategorie(String cat) {
		if(!categorieExist(cat)) {
			sql = "insert into categorie(designation) values('" + cat + "')";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void modifierCategorie(String ancienCat, String newCat) {
		sql = "update categorie set designation = '" + newCat + "' where designation like '" + ancienCat + "'";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public void supprimerCategorie(String cat) {
		sql = "delete from categorie where designation like '" + cat + "'";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public List<Categorie> listerCategorie() {
		List<Categorie> l = new ArrayList<Categorie>();
		Categorie c;
		sql = "select * from categorie order by designation";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				c = new Categorie(rs.getInt(1),rs.getString(2));
				l.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	public int getIdFromCategorie(String designation) {
		int identifiant = 0;
		sql = "select idCategorie from categorie where designation like '" + designation + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				identifiant = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return identifiant;
	}
	public boolean categorieExist(String designation) {
		sql = "select idCategorie from categorie where designation like '" + designation + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Integer> categorieExistDansArticle() {
		List<Integer> lCategorie = new ArrayList<Integer>();
		sql = "select distinct(idCategorie) from article";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				lCategorie.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lCategorie;
	}
	public List<Integer> userExistDansCommande() {
		List<Integer> lCategorie = new ArrayList<Integer>();
		sql = "select distinct(idUsers) from commande";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				lCategorie.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lCategorie;
	}
	public List<Integer> commandeExistDansLigneCommande() {
		List<Integer> lCategorie = new ArrayList<Integer>();
		sql = "select distinct(idCommande) from ligneCommande";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				lCategorie.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lCategorie;
	}
	public List<Integer> articleExistDansLigneCommande() {
		List<Integer> lCategorie = new ArrayList<Integer>();
		sql = "select distinct(idArticle) from ligneCommande";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				lCategorie.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lCategorie;
	}
	
	public boolean verifierArticleAvantSuppressionCategorie(String designation) {
		int idCat = 0;
		List<Integer> lst = categorieExistDansArticle();
		sql = "select idCategorie from categorie where designation like '" + designation + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				idCat = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i : lst) {
			if(i == idCat) {
				return true;
			}
		}
		return false;
	}
	public boolean verifierCommandeAvantSuppressionUser(int idUser) {
		List<Integer> lst = userExistDansCommande();
		for(int i : lst) {
			if(i == idUser) {
				return true;
			}
		}
		return false;
	}
	public boolean verifierLigneCommandeAvantSuppressionArticle(int idArticle) {
		List<Integer> lst = articleExistDansLigneCommande();
		for(int i : lst) {
			if(i == idArticle) {
				return true;
			}
		}
		return false;
	}
	public boolean verifierLigneCommandeAvantSuppressionCommande(int idCommande) {
		List<Integer> lst = commandeExistDansLigneCommande();
		for(int i : lst) {
			if(i == idCommande) {
				return true;
			}
		}
		return false;
	}

	//---------------- Article --------------------------------
	public boolean articleExist(String designation) {
		sql = "select idArticle from article where designation like '" + designation + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public int getIdFromArticle(String designation) {
		int identifiant = 0;
		sql = "select idArticle from article where designation like '" + designation + "'";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				identifiant = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return identifiant;
	}
	public List<Article> listerArticle() {
		List<Article> l = new ArrayList<Article>();
		Article a;
		sql = "select * from article order by designation";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				a = new Article(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
				l.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	public void ajouterArticle(Article a, int idCat) {
		if(!articleExist(a.getDesignation())) {
			sql = "insert into article(designation,pu,qty,idCategorie) values('" + 
		a.getDesignation() + "'," + a.getPu() + "," + a.getQty() + "," + idCat + ")";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void modifierDesignationArticle(String oldDesignation, String newDesignation) {
		sql = "update article set designation = '" + newDesignation + "' where designation like '" + oldDesignation + "'";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public void modifierQtyArticle(String designation, int qtyAajouter) {
		sql = "update article set qty = qty + " + qtyAajouter + " where designation like '" + designation + "'";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public void modifierPuArticle(String designation, float newPu) {
		sql = "update article set pu = " + newPu + " where designation like '" + designation + "'";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public void supprimerArticle(String designation) {
		//if(articleExist(designation)) {
			sql = "delete from article where designation like '" + designation + "'";
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		//}
	}
	//---------------- Commandes --------------------------------
	public int numNouvelleCommande() {
		int num = 0;
		sql = "select max(idCommande) from commande";
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num + 1;
	}
	public boolean commandeExist(int idCommande) {
		sql = "select * from commande where idCommande = " + idCommande;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean ligneCommandeExist(int idCommande, int idArticle) {
		sql = "select * from ligneCommande where idCommande = " + idCommande + " and idArticle = " + idArticle;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void modifierQtyCommandee(int idCommande, int idArticle, int qtyCommandee) {
		sql = "update ligneCommande set qtyCommandee = qtyCommandee + " + qtyCommandee + " where idCommande = " + idCommande + " and idArticle = " + idArticle;
			try {
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public boolean verifierQtyCommandee(int qStock, int qCommandee) {
		if(qCommandee <= qStock) {
			return true;
		}else {
			return false;
		}
	}
	public int recupQtyStock(int idArticle) {
		int q = 0;
		sql = "select qty from article where idArticle = " + idArticle;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				q = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return q;
	}
	public void ajouterCommande(String date, int idUsers) {
		sql = "insert into commande(dateCommande,idUsers) values('" + date + "'," + idUsers + ")";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void ajouterLigneCommande(int idCommande, int idArticle, int qtyCommandee) {
		sql = "insert into ligneCommande(idCommande,idArticle,qtyCommandee) values(" + 
	idCommande + "," + idArticle + "," + qtyCommandee + ")";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public String designationArticleFromId(int idArticle) {
		String design = null;
		sql = "select designation from article where idArticle = " + idArticle;
		try {
			rsSecours = stSecours.executeQuery(sql);
			if(rsSecours.next()) {
				design = rsSecours.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return design;
	}
	public List<LigneCommandeBD> ligneCommandeParCommande(int idCommande) {
		LigneCommande lc;
		LigneCommandeBD lcbd;
		int x;
		String designationArticle;
		List<LigneCommandeBD> llc = new ArrayList();
		sql = "select * from ligneCommande where idCommande = " + idCommande;
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				x = rs.getInt(3);
				designationArticle = designationArticleFromId(x);
				//lc = new LigneCommande(rs.getInt(1), rs.getInt(4));
				lcbd = new LigneCommandeBD(rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), designationArticle, rs.getString(4));
				llc.add(lcbd);
				//System.out.println(rs.getInt(1) + " -- " + rs.getInt(2) + " -- " + rs.getInt(3) + " -- " + rs.getInt(4) + " -- " + designationArticleFromId(x));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return llc;
	}
	
	
	//---------------- image - Tester --------------------------------

	public void sauveIMG(String location, String name) throws Exception 
	{
	  File monImage = new File(location);
	  FileInputStream istreamImage = new FileInputStream(monImage);
	  try 
	  {
	    ps = cn.prepareStatement("insert into image (name, img) values (?,?)");
	    try 
	    {
	        ps.setString(1, name);
	        ps.setBinaryStream(2, istreamImage, (int) monImage.length());
	        ps.executeUpdate();
	    }
	    finally 
	    {
	      ps.close();
	    }
	  } 
	  finally 
	  {
	    istreamImage.close();
	  }
	}
	

	public void chargeIMG(String name, String location) throws Exception
	{
	  File monImage = new File(location);
	  FileOutputStream ostreamImage = new FileOutputStream(monImage);
	            
	  try
	  {
	    ps = cn.prepareStatement("select img from image where name=?");
	
	    try
	    {
	      ps.setString(1,name);
	      rs = ps.executeQuery();
	      
	      try
	      {
	        if(rs.next())
	        {
	      	  InputStream istreamImage = rs.getBinaryStream("img");
	      
	      	  byte[] buffer = new byte[1024];
	      	  int length = 0;
		
	      	  while((length = istreamImage.read(buffer)) != -1)
	      	  {
	      	    ostreamImage.write(buffer, 0, length);
		  }
	  	}
	      }
	      finally
	      {
	        rs.close();
	      }
	    }
	    finally
	    {
	      ps.close();
	    }
	  }
	  finally
	  {
	    ostreamImage.close();
	  }
	}
	
	//---------------- main - Tester --------------------------------
	
	public static void main(String[] args) {
		UserDAO ud = new UserDAO();
		ud.etablirConnection();
		for(LigneCommandeBD lc : ud.ligneCommandeParCommande(1)) {
			System.out.println(lc.getQtyCommandeeBD());
		}
		ud.cloturerConnection();
	}
}
