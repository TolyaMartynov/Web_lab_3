

import java.io.*;
import java.util.Collection;
import java.util.Enumeration;

import myPack.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EnterServlet
 */
public class EnterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterServlet() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("login")!=null && session.getAttribute("password")!=null)
		{
			if(Entering.Enter((String)session.getAttribute("login"), (String)session.getAttribute("password"))!=null)
			{
				getServletConfig().getServletContext().getRequestDispatcher("/UsingServlet").forward(request, response);
			}
		}
		else
		{
			getServletConfig().getServletContext().getRequestDispatcher("/Enter.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("exit") == null)
		{
			if(session.getAttribute("login")!= null && session.getAttribute("password")!= null)
			{
				if(Entering.Enter((String)session.getAttribute("login"), (String)session.getAttribute("password"))!=null)
				{
					getServletConfig().getServletContext().getRequestDispatcher("/UsingServlet").forward(request, response);
					return;
				}
			}
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String exception = "Wrong login or password";
			if(Entering.Enter(login, password)!=null)
			{
				session = request.getSession();
				session.setAttribute("login", login);
				session.setAttribute("password", password);
				exception = "";
			}
			if(exception.equals(""))
			{
				getServletConfig().getServletContext().getRequestDispatcher("/UsingServlet").forward(request, response);
				return;
			}
			else
			{
				session = request.getSession();
				session.setAttribute("exception", exception);
				System.out.println("exception: " + exception);
				getServletConfig().getServletContext().getRequestDispatcher("/Enter.jsp").forward(request, response);
				return;
			}
		}
		else
		{
			session = request.getSession();
			session.removeAttribute("login");
			session.removeAttribute("password");
			getServletConfig().getServletContext().getRequestDispatcher("/Enter.jsp").forward(request, response);
		}
	}
	
}

