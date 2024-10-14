package karma.controllers.admin;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import karma.entity.Category;
import karma.models.Constant;
import karma.services.impl.CategoryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete" })

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoryService cateService = new CategoryService();

	public CategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/categories")) {

			List<Category> list = cateService.findAll();

			req.setAttribute("listcate", list);

			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/add")) {

			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/edit")) {

			int id = Integer.parseInt(req.getParameter("id"));

			Category category = cateService.findById(id);

			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

		} else {

			int id = Integer.parseInt(req.getParameter("id"));

			try {

				cateService.delete(id);

			} catch (Exception e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		Constant constants =new Constant();
		try {
			if (url.contains("/admin/category/add")) {

				// lấy dữ liệu từ form

				String categoryname = req.getParameter("categoryname");

				int status = Integer.parseInt(req.getParameter("status"));

				String images = req.getParameter("images");

				// đưa dữ liệu vào model

				Category category = new Category();

				category.setCategoryname(categoryname);

				category.setStatus(status);
				String fname = "";

				String uploadPath = constants.DIR; // upload vào thư mục bất kỳ

				File uploadDir = new File(uploadPath);

				if (!uploadDir.exists())

					uploadDir.mkdir();

				try {

					Part part = req.getPart("images1");

					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

						int index = filename.lastIndexOf(".");

						String ext = filename.substring(index + 1);

						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);

						category.setImages(fname);

					} else if (images != null) {

						category.setImages(images);
					} else {

						category.setImages("avatar.png");

					}

				} catch (FileNotFoundException fne) {

					fne.printStackTrace();

				}
				// đưa model vào phương thức insert

				cateService.insert(category);

				// chuyển trang

				resp.sendRedirect(req.getContextPath() + "/admin/categories");

			}
		}catch(Exception ex) {
			System.out.print("lỗi");
		}

		

		if (url.contains("/admin/category/update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));

			String categoryname = req.getParameter("categoryname");

			int status = Integer.parseInt(req.getParameter("status"));

			String images = req.getParameter("images");

			// đưa dữ liệu vào model

			Category category = cateService.findById(categoryid);

			String fileold = category.getImages();

			category.setCategoryname(categoryname);

			category.setStatus(status);
			String fname = "";

			String uploadPath = constants.DIR; // upload vào thư mục bất kỳ

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists())

				uploadDir.mkdir();

			try {

				Part part = req.getPart("images1");

				if (part.getSize() > 0) {
					// xóa file cũ trên thư mục

					if (!category.getImages().substring(0, 5).equals("https")) {

						deleteFile(uploadPath + "\\" + fileold);

					}

					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

					int index = filename.lastIndexOf(".");

					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					category.setImages(fname);

				} else if (images != null) {

					category.setImages(images);

				} else {

					category.setImages(fileold);
				}

			} catch (FileNotFoundException fne) {

				fne.printStackTrace();

			}

			// đưa model vào phương thức insert

			cateService.update(category);
			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}

	}

	public static void deleteFile(String filePath) throws IOException {


		 Path path = (Path) Paths.get(filePath);

		 Files.delete(null);
	}

}
