package ti.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import ti.modelo.NotaFiscal;

public class NotaFiscalDAO {

    public List<NotaFiscal> listaComItens() {

        EntityManager em = new JPAUtil().getEntityManager();

        try {
        	
        	/*
        	 * O LEFT JOIN FETCH permite carregar a nota fiscal e seus itens na mesma consulta.
        	 * O DISTINCT garante que cada nota apareça uma única vez no resultado, mesmo que possua vários itens.
        	 * O ORDER BY organiza as notas pelos identificadores em ordem decrescente.
        	 */
        	
            String jpql =
                "SELECT DISTINCT n FROM NotaFiscal n " +
                "LEFT JOIN FETCH n.itens " +
                "ORDER BY n.id DESC";

            return em.createQuery(jpql, NotaFiscal.class).getResultList();

        } finally {
            em.close();
        }
    }
}