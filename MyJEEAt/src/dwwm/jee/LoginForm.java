package dwwm.jee;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LoginForm extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String action = "";
	private String login = "";
	private String pwd = "";
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print( "<form method='POST' " );
			if ( action != null ) out.print( "action='" + action + "' " );
			out.println( "style='border: 1px solid black; width: 60%; margin: auto;'>" );
			
			out.println( "Login : " );
			out.println( "<input name='login' value=\"" + login.replace( "\"", "&quot;" ) + "\" autofocus />" );
			out.println( "<br/>" );

			out.println( "Password : " );
			out.println( "<input name='pwd' type='password' value=\"" + pwd.replace( "\"", "&quot;" ) + "\" />" );
			out.println( "<br/><br/>" );
	        
			out.println( "<input type='submit' value='Connect' />" );
			out.println( "<br/><br/>" );
			
		} catch ( IOException exception ) {
			exception .printStackTrace();
		}
		return 2;		// State of the life cycle of my TagSupport 
	}
	
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print( "</form>" );
		} catch ( IOException exception ) {
			exception .printStackTrace();
		}
		return 4;		// State of the life cycle of my TagSupport 
	}
	
}
