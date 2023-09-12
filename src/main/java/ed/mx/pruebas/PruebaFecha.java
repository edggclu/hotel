package ed.mx.pruebas;

import java.sql.Date;

public class PruebaFecha {
    public static void main(String[] args) {
        Date fecha = java.sql.Date.valueOf("2026-11-26");
        Date fecha2 = java.sql.Date.valueOf("2026-12-01");
        Long valor;
        if(fecha.before(fecha2)) {
            valor = (fecha2.getTime() - fecha.getTime())/172800;
            System.out.println(valor);
        }
    }
}
