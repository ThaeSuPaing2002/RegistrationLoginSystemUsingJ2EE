package layer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import layer.dao.UserDAO;
import layer.entity.User;
import layer.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	private UserDAO dao = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String result = service.loginValidation(email, password);
		if(result.equals("success")) {
			User user = dao.getUserByEmail(email);
			//creating session so that non logged users cannot access dashboard
			HttpSession session = request.getSession();
			session.setAttribute("validated", user);//pass user data
			request.setAttribute("result", result);
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}else {
			request.setAttribute("result", result);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
