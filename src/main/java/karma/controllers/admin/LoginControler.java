package karma.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import karma.entity.Users;
import karma.models.Constant;
import karma.services.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = { "/login", "/logout", "/forgetPass", "/register","/checkUser" })
public class LoginControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginControler() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		if (url.contains("login")) {
			if (session != null && session.getAttribute("account") != null) {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			}
			request.getRequestDispatcher("/views/login.jsp").forward(request, response);
		} else if (url.contains("logout")) {
			if (session != null) {
				session.invalidate();
			}

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			response.sendRedirect("/jpa/home");
		} else if (url.contains("forgetPass")) {
			request.getRequestDispatcher("/views/forgetPass.jsp").forward(request, response);
		} else if (url.contains("/register")) {
			if (session != null && session.getAttribute("username") != null) {
				response.sendRedirect(request.getContextPath() + "/admin");
				return;
			}
			request.getRequestDispatcher("/views/register.jsp").forward(request, response);
		} else if(url.contains("checkUser")) {
			request.getRequestDispatcher("/views/checkUser.jsp").forward(request, response);
			
		}

	}

	private void saveRemeberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURI();
		if (url.contains("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			boolean isRememberMe = false;
			String remember = request.getParameter("remember");

			if ("on".equals(remember)) {
				isRememberMe = true;
			}
			String alertMsg = "";

			if (username==null || password==null) {
				alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
				request.setAttribute("alert", alertMsg);
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
			
			UserServiceImpl service = new UserServiceImpl();
			Users user = service.login(username, password);
			if (user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("account", user);
				session.setAttribute("usernameS", user.getFullName());
				if (isRememberMe) {
					saveRemeberMe(response, username);
				}
				Users us = (Users) session.getAttribute("account");

				String fullname = (String) session.getAttribute("usernameS");
				request.setAttribute("fullname", fullname);
				System.out.print(us.getFullName());
				response.sendRedirect(request.getContextPath()+"/admin/categories");
				//request.getRequestDispatcher("/views/admin/category-list.jsp").forward(request, response);
			} else {
				System.out.print("lỗi");
				alertMsg = "Tài khoản hoặc mật khẩu không đúng";
				request.setAttribute("alert", alertMsg);
				request.getRequestDispatcher("/views/login.jsp").forward(request, response);
			}
		}else if(url.contains("register")) {
			Users user =new Users();
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email")) ;
			user.setFullName(request.getParameter("fullname"));
			user.setPhone(request.getParameter("phone"));
			UserServiceImpl service = new UserServiceImpl();
			String alertMsg = "";
			PrintWriter out=response.getWriter();		//response.sendRedirect(request.getContextPath() + "/views/test.html");
			if (service.checkExistUsername(user.getUserName())) {
				alertMsg = "Tài khoản đã tồn tại!";
				request.setAttribute("alert", alertMsg);
				request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
				//out.print("ko lỗi");
				return;
			}
			boolean isSuccess = service.checkIns(user);
			if (isSuccess) {
				//System.out.print("hi");
				request.setAttribute("alert", alertMsg);
				response.sendRedirect(request.getContextPath() + "/login");
				
			} else {
				System.out.print("lỗi");
				alertMsg = "System error!";
				System.out.print(user.getFullName());
				System.out.print(user.getEmail());
				
				request.setAttribute("alert", alertMsg);
				request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
				response.sendRedirect(request.getContextPath() + "views/test.html");
			}
		}else if(url.contains("checkUser")) {
			String username = request.getParameter("username");
			UserServiceImpl service = new UserServiceImpl();
			String alertMsg = "";

			if (username.isEmpty()) {
				alertMsg = "Vui lòng nhập tài khoản";
				request.setAttribute("alert", alertMsg);
				request.getRequestDispatcher("/views/checkUser.jsp").forward(request, response);
				return;
			}
			if (service.checkExistUsername(username)) {
				request.setAttribute("username", username);
				request.getRequestDispatcher("/views/forgetPass.jsp").forward(request, response);
			}else {
				alertMsg = "Không có tài khoản này";
				request.setAttribute("alert", alertMsg);
			}
		}else if(url.contains("forgetPass")) {
			String password = request.getParameter("password");
			String password2= request.getParameter("password2");
			String username = request.getParameter("username");
			String alertMsg = "";
			System.out.print(password);
			System.out.print(password2);
			if(!(password.equals(password2))) {
				alertMsg="Mật khẩu không khớp!";
				request.setAttribute("alert", alertMsg);
				request.setAttribute("username", username);
				request.getRequestDispatcher("/views/forgetPass.jsp").forward(request, response);
				return;
			}
				
				System.out.print(username);  
				UserServiceImpl service = new UserServiceImpl();
				boolean reset = service.forgetPass(username, password);
				if (reset) {
					request.getRequestDispatcher("/views/login.jsp").forward(request, response);
				} else {
					System.out.print(username);  
					alertMsg = "Lỗi";
					request.setAttribute("alert", alertMsg);
					request.getRequestDispatcher("/views/test.html").forward(request, response);
				}
			
			
		}
	}
}

