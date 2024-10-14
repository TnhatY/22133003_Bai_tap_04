package karma.services.impl;

import karma.dao.impl.UserDaoImpl;
import karma.entity.Users;
import karma.services.IUserService;

public class UserServiceImpl implements IUserService {

	UserDaoImpl userDao =new UserDaoImpl();
	@Override
	public Users get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Users user) {
		userDao.insert(user);
	}

	@Override
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		if (userDao.checkExistUsername(username)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(String username, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update_Profile(Users user) {
		userDao.update_Profile(user);
	}

	@Override
	public Users login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	public boolean checkIns(Users user) {
		try {
			userDao.insert(user);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean forgetPass(String username, String password) {
		if (userDao.forgetPass(username, password)) {
			return true;
		}
		return false;
	}

	@Override
	public Users findById(int userid) {
		return userDao.findById(userid);
	}

}
