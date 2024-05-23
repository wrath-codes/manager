package codes.wrath.manager.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import codes.wrath.manager.util.HibernateUtil;
import codes.wrath.manager.domain.User;

public class UserDAO extends GenericDAO<User>{
	
	public User authenticate(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(User.class);
			
			// Add the person to the query
			Criteria queryPerson = query.createCriteria("person");
			
			// Adding the email to the query
			queryPerson.add(Restrictions.eq("email", email));
			
			// Hashing the password
			SimpleHash hash = new SimpleHash("md5", password);
			
			// Adding the password to the query
			query.add(Restrictions.eq("password", hash.toHex()));
			
			// Getting the result
			User result = (User) query.uniqueResult();
			System.out.println(result.getPassword());
			System.out.println(hash.toHex());
			return result;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			session.close();
		}
	}
	
			
}
