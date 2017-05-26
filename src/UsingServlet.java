

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myPack.Entering;

/**
 * Servlet implementation class UsingServlet
 */
public class UsingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("login")!=null && session.getAttribute("password")!=null)
		{
			if(Entering.Enter((String)session.getAttribute("login"), (String)session.getAttribute("password"))!=null)
			{
				getServletConfig().getServletContext().getRequestDispatcher("/Using.jsp").forward(request, response);
			}
			else
			{
				getServletConfig().getServletContext().getRequestDispatcher("/EnterServlet").forward(request, response);
			}
		}
		else
		{
			getServletConfig().getServletContext().getRequestDispatcher("/EnterServlet").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("remove")!=null)
		{
			Entering.RemoveUser(Integer.parseInt(request.getParameter("remove")));
		}
		if(session.getAttribute("login")!=null && session.getAttribute("password")!=null)
		{
			if(Entering.Enter((String)session.getAttribute("login"), (String)session.getAttribute("password"))!=null)
			{
				getServletConfig().getServletContext().getRequestDispatcher("/Using.jsp").forward(request, response);
			}
			else
			{
				getServletConfig().getServletContext().getRequestDispatcher("/EnterServlet").forward(request, response);
			}
		}
		else
		{
			getServletConfig().getServletContext().getRequestDispatcher("/EnterServlet").forward(request, response);
		}
	}

}
