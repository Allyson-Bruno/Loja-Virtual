package ti.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ti.modelo.Usuario;

public class UsuarioDAO {	
	public boolean validaLogin(Usuario u) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "SELECT u FROM Usuario u WHERE u.login = :pLogin and u.senha = :pSenha";
		
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", u.getLogin());
		query.setParameter("pSenha", u.getSenha());
		
		boolean encontrado = !query.getResultList().isEmpty();

		em.getTransaction().commit();
		em.close();
		return encontrado;
		
	}
	
}
