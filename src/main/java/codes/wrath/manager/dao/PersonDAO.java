package codes.wrath.manager.dao;

import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import codes.wrath.manager.util.HibernateUtil;

import codes.wrath.manager.domain.Person;

public class PersonDAO extends GenericDAO<Person>{
	public Person searchByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Person.class);
			query.add(Restrictions.eq("email", email));
			Person result = (Person) query.uniqueResult();
			return result;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			session.close();
		}
	}

}
