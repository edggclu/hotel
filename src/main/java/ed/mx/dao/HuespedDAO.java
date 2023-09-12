package ed.mx.dao;

import ed.mx.controller.UsuarioController;
import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;
import ed.mx.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class HuespedDAO {
    private final EntityManager em;

    public HuespedDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Huesped huesped) {
        this.em.persist(huesped);
    }

    public void asignarReserva(Huesped h, Reserva r) {
        h.setIdReserva(r);
    }

    public List<Huesped> listarPorUsuario(String temporal) {
        UsuarioController uc = new UsuarioController();
        Usuario usuario = uc.buscarUsuario(temporal);
        String jpql = "SELECT h FROM Huesped h WHERE h.idReserva.usuario=:usuario";
        return em.createQuery(jpql, Huesped.class).setParameter("usuario", usuario).getResultList();
    }

    public List<Huesped> ListarPorId(Long id, String temporal) {
        UsuarioController uc = new UsuarioController();
        Usuario usuario = uc.buscarUsuario(temporal);
        String jpql = "SELECT h FROM Huesped h WHERE h.idReserva.usuario=:usuario AND h.id=:id";
        TypedQuery<Huesped> query = em.createQuery(jpql, Huesped.class);
        query.setParameter("usuario", usuario);
        query.setParameter("id", id);

        return query.getResultList();


    }

    public void modificar(Long id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, EntityManager em) {
        Huesped huesped = em.find(Huesped.class, id);
        huesped.setNombre(nombre);
        huesped.setApellido(apellido);
        huesped.setFechaNacimiento(fechaNacimiento);
        huesped.setNacionalidad(nacionalidad);
        huesped.setTelefono(telefono);
        em.merge(huesped);
    }
}
