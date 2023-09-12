package ed.mx.controller;

import ed.mx.dao.HuespedDAO;
import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

public class HuespedesController {
    private HuespedDAO huespedDAO;
    private ReservasController reservasController;

    private void guardar(Huesped huesped, EntityManager em) {
        huespedDAO = new HuespedDAO(em);
        huespedDAO.guardar(huesped);
    }

    public void formularioCompleto(Huesped huesped, Reserva reserva, EntityManager em) {
        reservasController = new ReservasController();
        guardar(huesped, em);
        reservasController.cerrarFormulario(reserva, em);
    }

    public List<Huesped> huespedList(String usuario) {
        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
        huespedDAO = new HuespedDAO(em);
        List<Huesped> h = huespedDAO.listarPorUsuario(usuario);
        em.close();
        return h;
    }


    public List<Huesped> listarPorId(Long id, String usuario) {
        EntityManager em = JPAUtils.getEntityManager();
        huespedDAO = new HuespedDAO(em);
        em.getTransaction().begin();
        List<Huesped> result = huespedDAO.ListarPorId(id, usuario);
        em.close();
        return result;
    }

    public void modificar(Long id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono) {
        EntityManager em = JPAUtils.getEntityManager();
        huespedDAO = new HuespedDAO(em);
        em.getTransaction().begin();
        huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, em);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(Long id) {
        EntityManager em = JPAUtils.getEntityManager();
        huespedDAO = new HuespedDAO(em);
        em.getTransaction().begin();
        huespedDAO.eliminar(id,em);
        em.getTransaction().commit();
        em.close();
    }
}
