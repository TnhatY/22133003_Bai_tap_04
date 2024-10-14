package karma.services.impl;

import java.util.List;

import karma.dao.impl.CategoryDao;
import karma.entity.Category;
import karma.services.ICategoryService;

public class CategoryService implements ICategoryService {
	public CategoryDao cateDao = new CategoryDao();
	
	@Override

	public List<Category> findAll() {

		return cateDao.findAll();

	}

	@Override

	public Category findById(int id) {

		return cateDao.findById(id);

	}

	@Override

	public List<Category> searchByName(String keyword) {

		return cateDao.searchByName(keyword);

	}

	@Override

	public void insert(Category category) {

		Category cate = this.findByCategoryname(category.getCategoryname());

		if (cate == null) {

			cateDao.insert(category);

		}

	}

	@Override

	public void update(Category category) {

		Category cate = this.findById(category.getCategoryId());

		if (cate != null) {

			cateDao.update(category);

		}

	}

	@Override

	public void delete(int id) {

		try {

			cateDao.delete(id);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Override

	public int count() {

		return cateDao.count();

	}

	@Override

	public List<Category> findAll(int page, int pagesize) {

		return cateDao.findAll(page, pagesize);

	}

	@Override

	public Category findByCategoryname(String name) {

		try {

			return cateDao.findByCategoryname(name);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

}
