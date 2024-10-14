package karma.dao;

import java.util.List;


import karma.entity.Video;

public interface VideoDao {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> searchByName(String videoname);

	List<Video> findAll();
	
	public void findAll1();

	Video findByVideoname(String name) throws Exception;

	Video findById(String videoid);

	void delete(String Videoid);

	void update(Video Video);

	void insert(Video Video);
}
