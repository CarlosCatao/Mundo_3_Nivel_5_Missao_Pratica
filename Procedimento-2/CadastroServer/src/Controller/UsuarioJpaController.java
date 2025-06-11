/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */
package Controller;

import Model.Usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class UsuarioJpaController {

    private final EntityManagerFactory emf;

    // Construtor
    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Metodo auxiliar para obter o EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /*
    * Busca um usuario com login e senha fornecidos.
    * @param login o nome de login
    * @param senha a senha do usuário
    * @return objeto Usuario se encontrado; null se nao encontrado
    */
    
    public Usuario findByLoginSenha(String login, String senha) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);

            List<Usuario> results = query.getResultList();

            if (!results.isEmpty()) {
                return results.get(0);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }
    
    /*
    * Retorna todos os usuarios da base de dados.
    * @return Lista de todos os usuários
    */
    
    public List<Usuario> findUsuarioEntities() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }
}
