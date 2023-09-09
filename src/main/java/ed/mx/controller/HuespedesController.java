package ed.mx.controller;

import ed.mx.dao.HuespedDAO;
import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;

public class HuespedesController {
    private HuespedDAO huespedDAO;
    private ReservasController reservasController;
    private void guardar(Huesped huesped, EntityManager em){
        huespedDAO = new HuespedDAO(em);
        huespedDAO.guardar(huesped);
    }

    public void formularioCompleto(Huesped huesped, Reserva reserva, EntityManager em){
        reservasController = new ReservasController();
        guardar(huesped, em);
        reservasController.cerrarFormulario(reserva, em);

    }
}
