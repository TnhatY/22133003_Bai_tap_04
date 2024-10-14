package karma.services;

import java.util.List;

import karma.entity.Video;

public interface IVideoService {
	void insert(Video Video);

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> searchByName(String videoname);

	List<Video> findAll();

	Video findById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video Video);

	Video findByVideoname(String name);
}
