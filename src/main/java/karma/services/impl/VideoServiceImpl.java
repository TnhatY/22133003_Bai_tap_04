package karma.services.impl;

import java.util.List;

import karma.dao.impl.VideoDaoImpl;
import karma.entity.Video;
import karma.services.IVideoService;

public class VideoServiceImpl implements IVideoService {

	VideoDaoImpl videoDao =new VideoDaoImpl();

	@Override
	public void insert(Video Video) {
		videoDao.insert(Video);
	}

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
		return videoDao.searchByName(videoname);
	}

	@Override
	public List<Video> findAll() {
		
		return videoDao.findAll();
	}

	@Override
	public Video findById(String videoid) {
		// TODO Auto-generated method stub
		return videoDao.findById(videoid);
	}

	@Override
	public void delete(String videoid) throws Exception {
		videoDao.delete(videoid);
	}

	@Override
	public void update(Video Video) {
		videoDao.update(Video);
	}

	@Override
	public Video findByVideoname(String name) {
		return null;
	}
	

}
