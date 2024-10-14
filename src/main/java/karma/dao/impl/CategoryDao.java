package karma.dao.impl;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import karma.configs.JPAconfig;
import karma.dao.ICategoryDao;
import karma.entity.Category;

public class CategoryDao implements ICategoryDao {

	@Override
	public void insert(Category category) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.persist(category);

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
	public void update(Category category) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.merge(category);

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
	public void delete(int id) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.remove(id);

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
	public Category findById(int cateid) {

		EntityManager enma = JPAconfig.getEntityManager();

		Category category = enma.find(Category.class, cateid);

		return category;

	}

	@Override
	public Category findByCategoryname(String name) throws Exception {

		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT c FROM Category c WHERE c.categoryname =:catename";

		try {

			TypedQuery<Category> query = enma.createQuery(jpql, Category.class);

			query.setParameter("catename", name);

			Category category = query.getSingleResult();

			if (category == null) {

				throw new Exception("Category Name đã tồn tại");

			}

			return category;

		} finally {

			enma.close();

		}
	}

	@Override
	public List<Category> findAll() {

		EntityManager enma = JPAconfig.getEntityManager();

		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);

		return query.getResultList();

	}

	@Override
	public List<Category> searchByName(String catname) {

		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT c FROM Category c WHERE c.catename like :catname";

		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);

		query.setParameter("catename", "%" + catname + "%");

		return query.getResultList();

	}

	@Override
	public List<Category> findAll(int page, int pagesize) {

		EntityManager enma = JPAconfig.getEntityManager();

		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);

		query.setFirstResult(page * pagesize);

		query.setMaxResults(pagesize);

		return query.getResultList();

	}

	@Override
	public int count() {

		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT count(c) FROM Category c";

		Query query = enma.createQuery(jpql);

		return ((Long) query.getSingleResult()).intValue();

	}
}
