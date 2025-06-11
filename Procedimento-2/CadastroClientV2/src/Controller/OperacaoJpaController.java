/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package Controller;

import Model.Operacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

public class OperacaoJpaController {

    private final EntityManagerFactory emf;

    public OperacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operacao movimento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movimento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void edit(Operacao movimento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(movimento);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Operacao findMovimento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operacao.class, id);
        } finally {
            em.close();
        }
    }

    public List<Operacao> findMovimentoEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT m FROM Operacao m", Operacao.class).getResultList();
        } finally {
            em.close();
        }
    }
}
