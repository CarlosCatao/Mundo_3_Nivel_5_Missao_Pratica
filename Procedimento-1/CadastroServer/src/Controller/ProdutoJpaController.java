/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package Controller;

import Model.Produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class ProdutoJpaController {

    private final EntityManagerFactory emf;

    // Construtor
    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Metodo auxiliar para obter o EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*
     * Insere um novo produto no banco de dados.
     */
    public void create(Produto produto) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(produto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao criar produto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /*
     * Atualiza um produto existente no banco de dados.
     */
    public void update(Produto produto) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(produto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /*
     * Remove um produto com base no codProduto.
     */
    public void delete(Integer codProduto) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            Produto produto = em.find(Produto.class, codProduto);
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado com codProduto: " + codProduto);
            }
            tx.begin();
            em.remove(produto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw new RuntimeException("Erro ao remover produto: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    /*
    * Retorna todos os produtos cadastrados no banco de dados.
    * @return Lista de objetos Produto
    */
    public List<Produto> findProdutoEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        } finally {
            em.close();
        }
    }

    /*
    * Retorna um produto específico pelo seu ID.
    * @param id identificador do produto
    * @return Produto correspondente, ou null se não encontrado
    */
    public Produto findProduto(Integer codProduto) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, codProduto);
        } finally {
            em.close();
        }
    }

        /*
     * Busca produtos com parte do nome/descricao correspondente (case-insensitive).
     */
    public List<Produto> findByDescricao(String descricao) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Produto> query = em.createQuery(
                "SELECT p FROM Produto p WHERE LOWER(p.descricao) LIKE :descricao", Produto.class);
            query.setParameter("descricao", "%" + descricao.toLowerCase() + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
