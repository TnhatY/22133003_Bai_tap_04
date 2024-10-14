package karma.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import karma.entity.Category;
import karma.entity.Video;
import karma.models.Constant;

import karma.services.impl.CategoryService;
import karma.services.impl.VideoServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit",
		"/admin/video/update", "/admin/video/delete" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VideoServiceImpl videoService = new VideoServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/videos")) {

			List<Video> list = videoService.findAll();

			req.setAttribute("listvideo", list);

			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/add")) {
			CategoryService cateService = new CategoryService();
			List<Category> cate = cateService.findAll();
			req.setAttribute("category", cate);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/edit")) {

			String id = req.getParameter("id");

			Video video = videoService.findById(id);
			System.out.print(video.getCategory().getCategoryname());
			CategoryService cateService = new CategoryService();
			List<Category> cate = cateService.findAll();
			req.setAttribute("category", cate);

			req.setAttribute("video", video);

			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);

		}
			else {
				String videoid = req.getParameter("id");
				try {
					videoService.delete(videoid);
					resp.sendRedirect(req.getContextPath() + "/admin/videos");
				} catch (Exception e) {
					System.out.print("lỗi");
					e.printStackTrace();
				}
				
			
	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		Constant constants =new Constant();
		VideoServiceImpl videoService = new VideoServiceImpl();
		if (url.contains("/admin/video/add")) {
			String videoid = req.getParameter("videoid");

			int status = Integer.parseInt(req.getParameter("status"));

			String images = req.getParameter("images");
			
			String title = req.getParameter("title");

			// đưa dữ liệu vào model

			Video video = new Video();

			video.setVideoId(videoid);

			video.setActive(status);
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

					video.setPoster(images);

				} else if (images != null) {

					video.setPoster(images);
				} else {

					video.setPoster("avatar.png");

				}

			} catch (FileNotFoundException fne) {

				fne.printStackTrace();

			}
			CategoryService cateService = new CategoryService();
			String categoryname = req.getParameter("shoes");
			System.out.print(categoryname);
			Category cate= new Category() ;
			cate= cateService.findByCategoryname(categoryname);
			video.setCategory(cate);
			// đưa model vào phương thức insert
			
			videoService.insert(video);

			// chuyển trang

			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}else if (url.contains("/admin/video/edit")) {
			String videoid = req.getParameter("videoid");

			int status = Integer.parseInt(req.getParameter("status"));

			String images = req.getParameter("images");

			String title = req.getParameter("title");

			Video video = new Video();

			video = videoService.findById(videoid);

			video.setActive(status);
			
			video.setTitle(title);
			
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

					video.setPoster(images);

				} else if (images != null) {

					video.setPoster(images);
				} else {

					video.setPoster("hinhanh.png");

				}

			} catch (FileNotFoundException fne) {

				fne.printStackTrace();

			}

			video.setPoster(images);
			CategoryService cateService = new CategoryService();
			String categoryname = req.getParameter("shoes");
			System.out.print(categoryname);
			Category cate= new Category() ;
			cate= cateService.findByCategoryname(categoryname);
			video.setCategory(cate);
			// đưa model vào phương thức insert
			
			videoService.update(video);

			//System.out.print(categoryname);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
}
