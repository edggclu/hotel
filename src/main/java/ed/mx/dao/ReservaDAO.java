package ed.mx.dao;

import ed.mx.modelo.Reserva;

import javax.persistence.EntityManager;

public class ReservaDAO {

    private final EntityManager em;

    public ReservaDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Reserva reserva){
        this.em.persist(reserva);
    }
}
