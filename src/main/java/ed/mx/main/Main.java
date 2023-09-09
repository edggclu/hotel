package ed.mx.main;

import ed.mx.utils.JPAUtils;
import ed.mx.views.MenuPrincipal;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManager();
        em.getTransaction().begin();
        em.close();
        MenuPrincipal v1 = new MenuPrincipal();
        v1.setVisible(true);
    }
}