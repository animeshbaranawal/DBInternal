package database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoToHome
 */
@WebServlet("/GoToHome")
public class GoToHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToHome() {
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String res_type=request.getParameter("home_link");
		if(res_type.equals("Login")){
			String user_id=request.getParameter("User_name");
			String pass=request.getParameter("Password");
			String name=User.getName(user_id,pass);
			request.setAttribute("Name",name);
			request.setAttribute("ID", user_id);
			request.getRequestDispatcher("./home.jsp").forward(request, response);
			return;
		}
		else {
			if(res_type.equals("SignUp")){
				String re_pass=request.getParameter("repass");
				String name=request.getParameter("name");
				String U_name=request.getParameter("user_name");
				String email=request.getParameter("email");
				String pass=request.getParameter("pass");
				String dob=request.getParameter("dob");
				String phno=request.getParameter("phone");
				String gender=request.getParameter("gender");
				if(!(pass.equals(re_pass))){
					out.println("Password doesn't match!");
					return;
				}
				Boolean ins_st=User.insert(name,U_name,email,pass,dob,phno,gender);
				if(ins_st){
					out.println("Thanks for registration");
					return;
				}
				else{
					out.println("Failed to register(invalid input)");
					return;
				}
			}
			else{
				out.println("Something's Wrong:"+res_type);
			}
		}
	}

}