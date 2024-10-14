package karma.services;

import karma.entity.Users;

public interface IUserService {
	Users get(String username);
	 Users login(String username, String password);
	 void insert(Users user);
	 boolean checkExistEmail(String email);
	 boolean checkExistUsername(String username);
	 boolean checkExistPhone(String phone);
	 void update(String username,String password);
	 void update_Profile(Users user);
	 boolean checkIns(Users user);
	 boolean forgetPass(String username,String password);
	 public Users findById(int userid) ;
}
