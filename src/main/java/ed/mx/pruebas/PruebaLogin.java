package ed.mx.pruebas;

import ed.mx.dao.UsuarioDAO;
import ed.mx.modelo.Usuario;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;

public class PruebaLogin {
    public static void main(String[] args) {
        Usuario diego= new Usuario("diego", "diego");
        EntityManager em = JPAUtils.getEntityManager();


        UsuarioDAO usuarioDAO = new UsuarioDAO(em);
//        usuarioDAO.login( "fg","diego");

    }
}
