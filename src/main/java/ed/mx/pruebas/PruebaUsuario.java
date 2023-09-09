package ed.mx.pruebas;

import ed.mx.dao.UsuarioDAO;
import ed.mx.modelo.Usuario;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class PruebaUsuario {
    public static void main(String[] args) {
        Usuario diego = new Usuario("Tilin", "1234");
        Usuario no = new Usuario("789", "1234");
        EntityManager em = JPAUtils.getEntityManager();
        UsuarioDAO dao = new UsuarioDAO(em);
        em.getTransaction().begin();

        try {
            em.getTransaction().commit();
        } catch (RollbackException | EntityExistsException e){
            throw new RuntimeException("Usuario usado");
        }
        em.close();
        System.out.println(diego.getUser());
    }
}
