package dwwm.jee;

import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO ud = new UserDAO(); 
    private int idUserCourant = 0;
    private int numNouvelleCommande = 0;
    Commande c;
    LigneCommande lc;
    
    Date aujourdhui = new Date();
	DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
    DateFormat.SHORT,
    DateFormat.SHORT);
    //private int numNouvelleCommande = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String op = request.getParameter("operation");
		ud.etablirConnection();
		if(op.equalsIgnoreCase("connect")) {
			this.doConnexion(request,response);
		}
		if(op.equalsIgnoreCase("inscrit")) {
			this.doInscription(request,response);
		}
		if(op.equalsIgnoreCase("ajoutCat")) {
			this.doInsertCategorie(request,response);
		}
		if(op.equalsIgnoreCase("listerCat")) {
			this.doListerCategorie(request,response);
		}
		if(op.equalsIgnoreCase("modifCat")) {
			this.doModifierCategorie(request,response);
		}
		if(op.equalsIgnoreCase("supprimerCat")) {
			this.doSupprimerCategorie(request,response);
		}
		if(op.equalsIgnoreCase("ajoutArticle")) {
			this.doInsertArticle(request,response);
		}
		if(op.equalsIgnoreCase("modifArticle")) {
			this.doModifDesignationArticle(request,response);
		}
		if(op.equalsIgnoreCase("modifQtyArticle")) {
			this.doModifQtyArticle(request,response);
		}
		if(op.equalsIgnoreCase("modifPuArticle")) {
			this.doModifPuArticle(request,response);
		}
		if(op.equalsIgnoreCase("supprimerArticle")) {
			this.doSupprimerArticle(request,response);
		}
		if(op.equalsIgnoreCase("gererCommande")) {
			this.doGererCommande(request,response);
		}
		if(op.equalsIgnoreCase("ajouterLigneCommande")) {
			this.doAjouterLigneCommande(request,response);
		}
		
		ud.cloturerConnection();
	}

	private void doAjouterLigneCommande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String numCommande = request.getParameter("numCommandeForLigne");
		String articleSelected = request.getParameter("articleSelected");
		String mots[] = articleSelected.split(" -- ");
		String idArticleSelected = mots[0];
		String qtyCommandee = request.getParameter("qtyCommandee");
		session.setAttribute("numCommande", numCommande);
		session.setAttribute("idArticleSelected", idArticleSelected);
		session.setAttribute("qtyCommandee", qtyCommandee);
		LigneCommandeBD lcbd = new LigneCommandeBD(numCommande,idArticleSelected,mots[1],qtyCommandee);
		session.setAttribute("lcbd", lcbd);
		if(ud.verifierQtyCommandee(ud.recupQtyStock(Integer.parseInt(idArticleSelected)), Integer.parseInt(qtyCommandee))) {
			lc = new LigneCommande(Integer.parseInt(qtyCommandee));
			if(!ud.commandeExist(numNouvelleCommande)) {
				ud.ajouterCommande(c.getDate(), idUserCourant);
			}
			if(!ud.ligneCommandeExist(numNouvelleCommande,Integer.parseInt(idArticleSelected))){
				ud.ajouterLigneCommande(numNouvelleCommande,Integer.parseInt(idArticleSelected),Integer.parseInt(qtyCommandee));
			}else {
				ud.modifierQtyCommandee(numNouvelleCommande, Integer.parseInt(idArticleSelected), Integer.parseInt(qtyCommandee));
			}
			int qtyAEnlever = (-1) * Integer.parseInt(qtyCommandee);
			ud.modifierQtyArticle(mots[1], qtyAEnlever);
		}
		request.getRequestDispatcher("/gererCommande.jsp").forward(request, response);
	}

	private void doGererCommande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("idUser", idUserCourant);
		numNouvelleCommande = ud.numNouvelleCommande();
		session.setAttribute("numNouvelleCommande", numNouvelleCommande);
		String date = shortDateFormat.format(aujourdhui);
		c = new Commande(date);
		request.getRequestDispatcher("/gererCommande.jsp").forward(request, response);
	}

	private void doSupprimerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String designation = request.getParameter("eltSelected");
		String mots[] = designation.split("--");
		ud.supprimerArticle(mots[0]);
		request.getRequestDispatcher("/modifierArticle.jsp").forward(request, response);
	}

	private void doModifPuArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float pu = Float.parseFloat(request.getParameter("pu"));
		String designation = request.getParameter("eltSelected");
		String mots[] = designation.split("--");
		ud.modifierPuArticle(mots[0], pu);
		request.getRequestDispatcher("/modifierArticle.jsp").forward(request, response);
	}

	private void doModifQtyArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qty = Integer.parseInt(request.getParameter("qty"));
		String designation = request.getParameter("eltSelected");
		String mots[] = designation.split("--");
		ud.modifierQtyArticle(mots[0], qty);
		request.getRequestDispatcher("/modifierArticle.jsp").forward(request, response);	
	}

	private void doModifDesignationArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nvxDesignation = request.getParameter("nvxDesignation");
		String ancienneDesignation = request.getParameter("eltSelected");
		ud.modifierDesignationArticle(ancienneDesignation, nvxDesignation);
		request.getRequestDispatcher("/modifierArticle.jsp").forward(request, response);
	}

	private void doInsertArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat = request.getParameter("catSelected");
		String designation = request.getParameter("designation");
		int qty = Integer.parseInt(request.getParameter("qty"));
		float pu = Float.parseFloat(request.getParameter("pu"));
		Article a = new Article(designation,pu,qty);
		ud.ajouterArticle(a, ud.getIdFromCategorie(cat));
		request.getRequestDispatcher("/ajouterArticle.jsp").forward(request, response);
	}

	private void doSupprimerCategorie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String designation = request.getParameter("itemSelectedSupprimer");
		session.setAttribute("msgSup", "");
		if(ud.verifierArticleAvantSuppressionCategorie(designation)) {
			session.setAttribute("msgSupCat", "Suppression impossible : catégorie " + designation + " utilisé dans Article");
			request.getRequestDispatcher("/gererCategorie.jsp").forward(request, response);
		}else {
			ud.supprimerCategorie(designation);
			session.setAttribute("msgSup", "OK");
			session.setAttribute("msgSupCat", "Suppression du catégorie " + designation + " réussie");
			request.getRequestDispatcher("/gererCategorie.jsp").forward(request, response);
		}
	}

	private void doModifierCategorie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String nvxDesignation = request.getParameter("nvxDesignation");
		String ancienneDesignation = request.getParameter("eltSelected");
		session.setAttribute("msgModif", "OK");
		session.setAttribute("msgModifCat", "Modification du catégorie " + ancienneDesignation + " vers " + nvxDesignation + " réussie");
		ud.modifierCategorie(ancienneDesignation, nvxDesignation);
		request.getRequestDispatcher("/gererCategorie.jsp").forward(request, response);
	}

	private void doListerCategorie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Categorie> l = ud.listerCategorie();
		session.setAttribute("listCat", l);
		request.getRequestDispatcher("/gererCategorie.jsp").forward(request, response);
	}

	private void doInsertCategorie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String designation = request.getParameter("designation");
		ud.ajouterCategorie(designation);
		session.setAttribute("msgAdd", "OK");
		session.setAttribute("msgAddCat", "Ajout du catégorie " + designation + " réussie");
		request.getRequestDispatcher("/gererCategorie.jsp").forward(request, response);
	}

	private void doInscription(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(true);
		String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
		//récupération des champs saisis
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String adresse = request.getParameter("adresse");
		String tel = request.getParameter("tel");
		int age = Integer.parseInt(request.getParameter("age"));
		String sexe = request.getParameter("sexe");
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String type = request.getParameter("type");
		//controle des champs
		/* Validation du champ email. */
        try {
            validationAge( age );
        } catch ( Exception e ) {
            erreurs.put( ""+age, e.getMessage() );
        }

        /* Validation des champs mot de passe et confirmation. */
        try {
            validationMotsDePasse( pwd, pwd2 );
        } catch ( Exception e ) {
            erreurs.put( pwd, e.getMessage() );
        }

        /* Validation du champ nom. */
        try {
            validationNom( lName );
        } catch ( Exception e ) {
            erreurs.put( lName, e.getMessage() );
        }
        /* Validation du champ prenom. */
        try {
            validationPrenom( fName );
        } catch ( Exception e ) {
            erreurs.put( fName, e.getMessage() );
        }


        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription.";
            User u = new User(login,pwd,type);
    		Inscription ins = new Inscription(fName, lName, adresse, tel, age, sexe);
    		ud.ajouterUser(u);
    		ud.ajouterInscrit(ins);
    		session.setAttribute("mes", "inscription reussie");
        } else {
            resultat = "Échec de l'inscription.";
	        /* Stockage du résultat et des messages d'erreur dans l'objet request */
		}
        request.setAttribute( "erreurs", erreurs );
        request.setAttribute( "resultat", resultat );
		//response.sendRedirect("inscription.jsp");
        request.getRequestDispatcher("/inscription.jsp").forward(request, response);
	}

	private void doConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		
		String mdp = ud.verifierCoordonnees(login);
		if(mdp == null) {
			session.setAttribute("message", "login " + login + " inexistant");
			request.getRequestDispatcher("/connexionKO.jsp").forward(request, response);
		}
		else {
			if(mdp.equals(pwd)) {
				idUserCourant = ud.getIdFromUsers(login, pwd);
				session.setAttribute("pseudo", login);
				request.getRequestDispatcher("/menuAdmin.jsp").forward(request, response);
			}else {
				session.setAttribute("message", "mot de passe " + pwd + " incorrect");
				request.getRequestDispatcher("/connexionKO.jsp").forward(request, response);
			}
		}
		
	}

	/**
	 * Valide l'adresse mail saisie.
	 */
	private void validationEmail( String email ) throws Exception {
	    if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}

	/**
	 * Valide les mots de passe saisis.
	 */
	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
	    if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	        if (!motDePasse.equals(confirmation)) {
	            throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	        } else if (motDePasse.trim().length() < 3) {
	            throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
	        }
	    } else {
	        throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	    }
	}

	/**
	 * Valide le nom saisi.
	 */
	private void validationNom( String nom ) throws Exception {
	    if ( nom != null && nom.trim().length() < 3 ) {
	        throw new Exception( "Le nom doit contenir au moins 3 caractères." );
	    }
	}
	/**
	 * Valide l'age saisi.
	 */
	private void validationAge( int age ) throws Exception {
	    if ( age < 15 || age > 120 ) {
	        throw new Exception( "L'age doit etre compris entre 15 et 120." );
	    }
	}
	/**
	 * Valide le prenom saisi.
	 */
	private void validationPrenom( String prenom ) throws Exception {
	    if ( prenom != null && prenom.trim().length() < 3 ) {
	        throw new Exception( "Le prenom doit contenir au moins 3 caractères." );
	    }
	}

}
