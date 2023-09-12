package ed.mx.pruebas;

import ed.mx.controller.UsuarioController;
import ed.mx.dao.HuespedDAO;
import ed.mx.dao.ReservaDAO;
import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;
import ed.mx.modelo.Usuario;
import ed.mx.utils.JPAUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Prueba1 {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        Usuario encontrado = usuarioController.buscarUsuario("admin");
        Reserva reserva = new Reserva(
                java.sql.Date.valueOf("2023-06-09"),
                java.sql.Date.valueOf("2023-07-09"),
                new BigDecimal(455),
                "Tarjeta", encontrado);

                Huesped diego = new  Huesped("diego",
                "Tilines",
                java.sql.Date.valueOf("2003-07-09"),
                "Mexicano",
                "778989746");

                diego.setIdReserva(reserva);



        EntityManager em = JPAUtils.getEntityManager();

        ReservaDAO reservaDAO = new ReservaDAO(em);
        HuespedDAO huespedDAO = new HuespedDAO(em);


        em.getTransaction().begin();

        reservaDAO.guardar(reserva);
        huespedDAO.guardar(diego);
        em.getTransaction().commit();

        em.close();

        System.out.println(diego.getIdReserva());
        System.out.println(reserva.getUsuario().getUser());





    }
}
