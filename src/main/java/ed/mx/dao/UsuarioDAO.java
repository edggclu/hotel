package ed.mx.dao;

import ed.mx.modelo.Usuario;
import ed.mx.views.Login;
import ed.mx.views.RegistroDeUsuario;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.*;

import java.awt.*;

import static java.lang.System.*;

public class UsuarioDAO {
    private final EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void guardarUsuario(Usuario usuario, RegistroDeUsuario component) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

            em.close();
            System.out.println("Usuario " + usuario.getUser() + " registrado con exito");
            JOptionPane.showMessageDialog(component, "Registrado Con Exito");

        } catch (EntityExistsException | RollbackException ex) {
            JOptionPane.showMessageDialog(component, "Este Usuario ya ha sido ocupado");
        }
    }

    public boolean login(String usuarioGiven, String passwordGiven, Login component) {
        em.getTransaction().begin();

        Usuario jpql = em.find(Usuario.class, usuarioGiven);
        em.close();

        try {
            if ((jpql.getPassword()).equals(passwordGiven)) {
                out.println("*");
                return true;

            } else {
                JOptionPane.showMessageDialog(component, "Usuario o contraseña incorrectos");
                return false;
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(component, "Usuario o contraseña incorrectos");
            return false;
        }

    }

    public Usuario buscarUsuarioParaReserva(String usuario) {
        em.getTransaction().begin();
        Usuario usuarioEncontrado = em.find(Usuario.class, usuario);
        em.close();
        return usuarioEncontrado;
    }
}
