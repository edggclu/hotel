package ed.mx.controller;

import ed.mx.dao.ReservaDAO;
import ed.mx.modelo.Reserva;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;

public class ReservasController {
    private ReservaDAO reservaDAO;
    private EntityManager em;

    public void cerrarFormulario(Reserva reserva, EntityManager em){
        em.getTransaction().commit();
        em.close();
    }

    public void formularioParaGuardar(Reserva reserva){
        this.em = JPAUtils.getEntityManager();
        reservaDAO = new ReservaDAO(em);
        em.getTransaction().begin();
        reservaDAO.guardar(reserva);
    }

    public EntityManager getEm() {
        return em;
    }
}
