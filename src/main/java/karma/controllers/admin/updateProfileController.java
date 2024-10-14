package karma.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import karma.entity.Category;
import karma.entity.Users;
import karma.models.Constant;
import karma.services.impl.UserServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

@MultipartConfig

@WebServlet(urlPatterns = {"/profile","/updateProfile"})
public class updateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateProfileController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = request.getRequestURI();
		HttpSession session = request.getSession(false);
		if (session != null) {
		    Users user = (Users) session.getAttribute("account");
		    if (url.contains("profile")) {
		        request.setAttribute("user", user);
		        request.getRequestDispatcher("/views/account.jsp").forward(request, response);
		    } else if (url.contains("updateProfile")) {
		        request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
		    }
		} else {
		    
		    response.sendRedirect("/home");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String url = request.getRequestURI();
			if(url.contains("updateProfile")) {
				int userid = Integer.parseInt(request.getParameter("userid"));

				String fullname = request.getParameter("fullname");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");

				String images = request.getParameter("image");

				// đưa dữ liệu vào model
				UserServiceImpl usedao=new UserServiceImpl();
				Constant constants =new Constant();
				Users user = new Users();
				user = usedao.findById(userid);

				String fileold = user.getImage();

				user.setFullName(fullname);

				user.setPhone(phone);
				
				user.setEmail(email);
				String fname = "";

				String uploadPath = constants.DIR; // upload vào thư mục bất kỳ

				File uploadDir = new File(uploadPath);

				if (!uploadDir.exists())

					uploadDir.mkdir();

				try {

					Part part = request.getPart("image");

					if (part.getSize() > 0) {
						// xóa file cũ trên thư mục

						if (!user.getImage().substring(0, 5).equals("https")) {

							deleteFile(uploadPath + "\\" + fileold);

						}

						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

						int index = filename.lastIndexOf(".");

						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);

						user.setImage(fname);

					} else if (images != null) {

						user.setImage(images);

					} else {

						user.setImage(fileold);
					}

				} catch (FileNotFoundException fne) {

					fne.printStackTrace();

				}
				
				usedao.update_Profile(user);
				// chuyển trang
				//System.out.print(user.getEmail());
				
				request.setAttribute("user", user);
				request.getRequestDispatcher("/views/account.jsp").forward(request, response);
			}
			
		}catch(Exception ex) {
			System.out.print("loi");
		}
	

	}
	private void deleteFile(String string) {
		// TODO Auto-generated method stub
		  
	}

	
}
