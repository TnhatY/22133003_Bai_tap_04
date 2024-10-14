package karma.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import karma.configs.JPAconfig;
import karma.dao.VideoDao;
import karma.entity.Video;

public class VideoDaoImpl implements VideoDao {

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> searchByName(String videoname) {
		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT c FROM Category c WHERE c.catename like :catname";

		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);

		query.setParameter("catename", "%" + videoname + "%");

		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAconfig.getEntityManager();
		
		String query = "SELECT v FROM Video v ";
		List<Video> video = enma.createQuery(query, Video.class)
		                                  .getResultList();
		return video;
	}
	@Override
	public void findAll1(){
		EntityManager enma = JPAconfig.getEntityManager();

		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		query.getResultList();

	}
	
	public static void main(String[] args) {

		VideoDaoImpl vidao=new VideoDaoImpl();
		Video video = vidao.findById("v01");
		video.setPoster("kkkkk");
		video.setCategory(null);
		vidao.update(video);
		//System.out.print(video.getCategory().getCategoryname());
		
		//System.out.print(us.getUserId());
	}

	@Override
	public Video findByVideoname(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video findById(String videoid) {

		EntityManager enma = JPAconfig.getEntityManager();

		Video video = enma.find(Video.class, videoid);

		return video;
	}

	@Override
	public void delete(String videoid) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.remove(videoid);
			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.merge(video);

			// enma.persist(video);

			trans.commit();

		} catch (Exception e) {

			e.printStackTrace();

			trans.rollback();

			throw e;

		} finally {

			enma.close();

		}
	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.persist(video);

			// enma.persist(video);

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
