package ed.mx.controller;

import ed.mx.dao.ReservaDAO;
import ed.mx.modelo.Reserva;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ReservasController {
    private ReservaDAO reservaDAO;

    private EntityManager em;


    public void cerrarFormulario(Reserva reserva, EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

    public void formularioParaGuardar(Reserva reserva) {
        EntityManager em = JPAUtils.getEntityManager();
        reservaDAO = new ReservaDAO(em);
        this.em = em;
        em.getTransaction().begin();
        reservaDAO.guardar(reserva);
    }

    public EntityManager getEm() {
        return em;
    }

    public List<Reserva> reservaList(String usuario) {
        EntityManager em = JPAUtils.getEntityManager();
        reservaDAO = new ReservaDAO(em);
        em.getTransaction().begin();
        List<Reserva> reservas = reservaDAO.ListarPorUsuario(usuario);
        em.close();
        return reservas;
    }

    public List<Reserva> listarPorId(Long id, String usuario) {
        EntityManager em = JPAUtils.getEntityManager();
        reservaDAO = new ReservaDAO(em);
        em.getTransaction().begin();
        List<Reserva> reservas = reservaDAO.ListarPorId(id, usuario);
        em.close();

        return reservas;
    }

    public void modificar(Long id, Date fechaE, Date fechaS, BigDecimal valor) {
        EntityManager em = JPAUtils.getEntityManager();
        reservaDAO = new ReservaDAO(em);
        em.getTransaction().begin();
        reservaDAO.modificar(id,fechaE,fechaS,valor, em);
        em.getTransaction().commit();
        em.close();
    }
}
