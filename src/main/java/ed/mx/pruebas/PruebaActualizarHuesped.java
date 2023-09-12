package ed.mx.pruebas;

import ed.mx.controller.HuespedesController;
import ed.mx.modelo.Huesped;
import ed.mx.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

public class PruebaActualizarHuesped {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
        HuespedesController hc = new HuespedesController();
        Huesped huesped = em.find(Huesped.class,100L);
        huesped.setNombre("A");
        huesped.setApellido("B");
        huesped.setFechaNacimiento(java.sql.Date.valueOf("2026-12-19"));
        huesped.setNacionalidad("Texcoco");
        huesped.setTelefono("44444");
        em.merge(huesped);
        em.getTransaction().commit();
        em.close();

    }
}
