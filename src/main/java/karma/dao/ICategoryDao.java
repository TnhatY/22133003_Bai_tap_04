package karma.dao;

import java.util.List;

import karma.entity.Category;

public interface ICategoryDao {

	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> searchByName(String catname);

	List<Category> findAll();

	Category findByCategoryname(String name) throws Exception;

	Category findById(int cateid);

	void delete(int categoryid);

	void update(Category category);

	void insert(Category category);

}
