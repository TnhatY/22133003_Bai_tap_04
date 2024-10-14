package karma.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import karma.entity.Category;
import karma.entity.Users;
import karma.entity.Video;
@PersistenceContext
public class Test {
	public static void main(String[] args) {

		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();

		Category cate = new Category();

		cate.setCategoryname("Iphone");

		cate.setImages("abc.jpg");

		cate.setStatus(1);

		Video video = new Video();

		video.setVideoId("v01");

		video.setTitle("test");

		video.setCategory(cate);
		Users user =new Users();
		user.setUserId(0);
		user.setUserName("anh");
		user.setPassword("123");
		user.setFullName("Trần Thế Anh");
		user.setEmail(null);
		user.setImage(null);
		user.setPhone(null);
		user.setRoleid(1);
				
		

		try {

			trans.begin();

			//enma.persist(cate);
			enma.persist(user);
			//enma.persist(video);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}

	}
}
