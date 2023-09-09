package ed.mx.dao;

import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;

import javax.persistence.EntityManager;

public class HuespedDAO {
    private final EntityManager em;

    public HuespedDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Huesped huesped ){
        this.em.persist(huesped);
    }
    public void asignarReserva(Huesped h, Reserva r){
        h.setIdReserva(r);
    }
}
