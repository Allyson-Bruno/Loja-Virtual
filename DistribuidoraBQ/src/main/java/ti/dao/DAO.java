package ti.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	private final Class<T> classe;
	
	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	public boolean adiciona(T t) {

	    EntityManager em = new JPAUtil().getEntityManager();
	    EntityTransaction transacao = em.getTransaction();

	    try {
	        transacao.begin();
	        em.persist(t);
	        transacao.commit();

	        return true;

	    } catch (RuntimeException e) {

	        if (transacao.isActive()) {
	            try {
	                transacao.rollback();
	            } catch (RuntimeException erroRollback) {
	                e.addSuppressed(erroRollback);
	            }
	        }

	        // Para diagnóstico durante o desenvolvimento.
	        e.printStackTrace();

	        return false;

	    } finally {
	        em.close();
	    }
	}
	
	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}
	
	public void altera(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<T> listaTodos(){
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		em.close();
		return lista;
	}
	
	public T buscaPorId(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();

		try {
			return em.find(classe, id);
		} finally {
			em.close();
		}
		
	}
}
