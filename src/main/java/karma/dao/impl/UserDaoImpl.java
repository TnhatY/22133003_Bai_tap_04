package karma.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import karma.configs.JPAconfig;
import karma.dao.UserDao;
import karma.entity.Category;
import karma.entity.Users;

public class UserDaoImpl implements UserDao {

	@Override
	public Users get(String username)  {
		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT u FROM users u WHERE u.username =:username";

		try {

			TypedQuery<Users> query = enma.createQuery(jpql, Users.class);

			query.setParameter("username", username);

			Users user = query.getSingleResult();

			if (user == null) {

				throw new Exception("Category Name đã tồn tại");

			}

			return user;
		}catch(Exception ex) {
			return null;

		} finally {

			enma.close();

		}
	}

	@Override
	public void insert(Users user) {
		EntityManager enma = JPAconfig.getEntityManager();

		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.persist(user);
			
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
	public boolean checkExistEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		EntityManager enma = JPAconfig.getEntityManager();
		
		String jpql = "SELECT u FROM Users u WHERE u.username = :username";

		try {

			TypedQuery<Users> query = enma.createQuery(jpql, Users.class);

			query.setParameter("username", username);

			Users user = query.getSingleResult();

			if (user == null) {

				return false;

			}
			return true;

		}catch(Exception ex) {
			return false;

		} finally {

			enma.close();

		}
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
		EntityManager enma = JPAconfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();

			enma.merge(user);
			
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
	public Users login(String username, String password) {
		EntityManager enma = JPAconfig.getEntityManager();

		String jpql = "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password";

		try {

			TypedQuery<Users> query = enma.createQuery(jpql, Users.class);

			query.setParameter("username", username);
			query.setParameter("password", password);

			Users user = query.getSingleResult();

			if (user == null) {

				throw new Exception("Tài khoản không tồn tại");

			}

			return user;
		}catch(Exception ex) {
			return null;

		} finally {

			enma.close();

		}
	}
	public static void main(String[] args) {
		Users us=new Users();
		UserDaoImpl uspl =new UserDaoImpl();
		us=uspl.findById(1);
		//us.getPhone();
		//us.setUserId(0);
		us.setEmail("123");
		System.out.print(us.getUserId());
		//us.setPhone("0987654321");
		
		uspl.update_Profile(us);
	}

	@Override
	public boolean forgetPass(String username, String password) {
        EntityManager enma = JPAconfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        String query = "SELECT u FROM Users u WHERE u.username = :username";
        try {
        	trans.begin();
            Users user = enma.createQuery(query, Users.class)
                             .setParameter("username", username)
                             .getSingleResult();
            user.setPassword(password); 
            
            enma.merge(user); 
            trans.commit();
            return true;
        } catch (jakarta.persistence.NoResultException e) {
            return false; 
        }
    }

	@Override
	public Users findById(int userid) {

		EntityManager enma = JPAconfig.getEntityManager();

		Users user = enma.find(Users.class, userid);

		return user;

	}
}
