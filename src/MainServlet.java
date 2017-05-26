
import myPack.*;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("logined")!=null ? (boolean)session.getAttribute("logined") : false)
		{
			Writer wr = response.getWriter();
			wr.write("<html><h1>PROFIT</h1></html>");
		} else
		{
			getServletConfig().getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
		}
		//wr.write(BuildForm("", "", null));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		System.out.println("login: " + login);
		String password = request.getParameter("password");
		System.out.println("password: " + password);
		String isAdmin = request.getParameter("isAdmin");
		System.out.println("isAdmin: " + isAdmin);
		String exception = "";
		Pattern p = Pattern.compile("[^a-zA-Z0-9]+$");
		Matcher m = p.matcher(login);  
		if (login.equals("") || password.equals("")) {
			exception = "You have to fill form";
		}
		else if (m.matches()) {
			exception = "Name field include wrong characters";
		}
		File f = new File("C:\\Users\\Компьютер\\workspace\\TestServer\\logindata");
		if(!f.exists())
		{
			f.createNewFile();
		}
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = null;
		try { 
			ois = new ObjectInputStream(new BufferedInputStream(fis));
		} catch(EOFException e) {
		}
		ArrayList<UserData> userArray = new ArrayList<UserData>();
		if(ois != null)
		{
			try {
				UserData ud;
				while(true)
				{
					try { 
					ud = (UserData)ois.readObject();
					userArray.add(ud);
					} catch(EOFException e)
					{
						break;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
			for(int i = 0; i<userArray.size(); i++)
			{
				if(userArray.get(i).login.equals(login))
				{
					exception = "This login has already registrated";
				}
			}
		}
		fis.close();
		if(exception.equals(""))
		{
			FileOutputStream fos = new FileOutputStream("C:\\Users\\Компьютер\\workspace\\TestServer\\logindata");
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));
			for(int i = 0; i < userArray.size(); i++)
			{
				oos.writeObject(userArray.get(i));
			}
			oos.writeObject(new UserData(login, password, isAdmin!=null));
			oos.close();
			fos.close();
			Writer wr = response.getWriter();
			wr.write("<html>"
					+ "<h3>Registration completed</h3><p>"
					+ "<a href=\"http://localhost:8080/TestServer/EnterServlet\">Click to enter</a>"
					+ "</html>");
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("exception", exception);
			System.out.println("exception: " + exception);
			getServletConfig().getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
		}
		
	}
	
	/*protected String BuildForm(String login, String password, String exception)
	{
		String s ="<html>"
				+ "<form action=\"MainServlet\" method=\"post\">"
				+ "<h3>Registration</h3>"
				+ (exception != null ? "<font color=\"red\">" + exception + "</font>" : "")
				+ "<table border=\"0\">"
				+ "<tr><td>Login:</td><td><input name=\"login\" type=\"text\" value=\""+ login + "\"></td></tr>"
				+ "<tr><td>Password:</td><td><input name=\"password\" type=\"text\" value=\""+ password + "\"></td></tr>"
				+ "<tr><td><input type=\"submit\" value=\"Rigistration\"></tr</td>"
				+ "</table>"
				+ "</form>"
				+ "</html>";
		return s;
	}*/
}
