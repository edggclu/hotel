package ed.mx.controller;

import ed.mx.dao.UsuarioDAO;
import ed.mx.modelo.Usuario;
import ed.mx.utils.JPAUtils;
import ed.mx.views.Login;
import ed.mx.views.RegistroDeUsuario;

import javax.persistence.EntityManager;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    private RegistroDeUsuario registroDeUsuario;
    private Login login;

    public UsuarioController(){

    }
    public UsuarioController(RegistroDeUsuario registroDeUsuario) {
        this.registroDeUsuario = registroDeUsuario;
    }

    public UsuarioController(Login login) {
        this.login = login;
    }

    public void guardar(String usuario, String password) {
        EntityManager em = JPAUtils.getEntityManager();
        usuarioDAO = new UsuarioDAO(em);
        usuarioDAO.guardarUsuario(new Usuario(usuario, password), registroDeUsuario);
    }

    public boolean login(String user, String password) {
        EntityManager em = JPAUtils.getEntityManager();
        usuarioDAO = new UsuarioDAO(em);
        if(usuarioDAO.login(user, password, login)){
            return true;
        } else {
            return false;
        }
    }

    public Usuario buscarUsuario(String usuario){
        EntityManager em = JPAUtils.getEntityManager();
        usuarioDAO = new UsuarioDAO(em);
        return usuarioDAO.buscarUsuarioParaReserva(usuario);

    }
}

