package database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchUser
 */
@WebServlet("/searchUser")
public class searchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		String res_type= request.getParameter("search_by");
		String param = request.getParameter("value");
		List<String> res = new ArrayList<String>();

		if(res_type.equals("name")){
			res = User.searchName(param);
		}
		else if(res_type.equals("user_id")){
			res = User.searchID(param);
			
		}
		else{
			res = User.searchemail(param);
		}
		String[] pass = res.toArray(new String[0]);
		request.setAttribute("userlist", pass);
		request.getRequestDispatcher("./UserSearch.jsp").forward(request, response);
		return;
	}
}
