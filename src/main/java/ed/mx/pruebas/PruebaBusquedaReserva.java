package ed.mx.pruebas;

import ed.mx.controller.ReservasController;
import ed.mx.modelo.Huesped;
import ed.mx.modelo.Reserva;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

public class PruebaBusquedaReserva {
    public static void main(String[] args) {
//        reservasController.listarPorId(4L,"admin");
        ReservasController reservasController = new ReservasController();

        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
//        em.remove(em.find(Reserva.class,45L));

//        Reserva r = em.find(Reserva.class,45L);
//        Huesped h = em.find(Huesped.class, r.getHuesped().getId());
        Huesped hhh = em.find(Huesped.class,38L);
//        if(r == null){
//            System.out.println("si es null");
//        } else  {
//            System.out.println("no es");
//        }
        em.remove(hhh);
//        em.remove(r);

        em.getTransaction().commit();
//        System.out.println(r.getHuesped().getNombre());

        em.close();
    }
}
