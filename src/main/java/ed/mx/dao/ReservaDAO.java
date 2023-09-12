package ed.mx.dao;

import ed.mx.controller.UsuarioController;
import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;
import ed.mx.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ReservaDAO {

    private final EntityManager em;

    public ReservaDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Reserva reserva){
        this.em.persist(reserva);
    }

    public List<Reserva> ListarPorUsuario(String temporal) {
        UsuarioController uc = new UsuarioController();
        Usuario usuario = uc.buscarUsuario(temporal);
        String jpql = "SELECT r FROM Reserva r WHERE r.usuario =:usuario";
        return em.createQuery(jpql, Reserva.class).setParameter("usuario", usuario).getResultList();
    }

    public List<Reserva> ListarPorId(Long id, String temporal) {
        UsuarioController uc = new UsuarioController();
        Usuario usuario = uc.buscarUsuario(temporal);

        String jpql = "SELECT r FROM Reserva r WHERE r.usuario=:usuario AND r.id=:id";
        TypedQuery<Reserva> query =  em.createQuery(jpql,Reserva.class);
        query.setParameter("id",id);
        query.setParameter("usuario", usuario);

        return query.getResultList();
    }

    public void modificar(Long id, Date fechaE, Date fechaS, BigDecimal valor, EntityManager em) {
        Reserva reserva = em.find(Reserva.class,id);
        reserva.setFechaE(fechaE);
        reserva.setFechaS(fechaS);
        reserva.setValor(valor);
        em.merge(reserva);
    }

    public void eliminar(Long id, EntityManager em) {
        Reserva reserva = em.find(Reserva.class,id);
        Huesped huesped = em.find(Huesped.class,reserva.getHuesped().getId());
        em.remove(huesped);
    }
}
