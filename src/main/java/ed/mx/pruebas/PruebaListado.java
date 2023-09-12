package ed.mx.pruebas;

import ed.mx.controller.HuespedesController;
import ed.mx.controller.ReservasController;

public class PruebaListado {
    public static void main(String[] args) {

        ReservasController reservasController = new ReservasController();
        var reservas = reservasController.reservaList("admin");
        reservas.forEach(reserva -> System.out.println(reserva.getHuesped().getNombre()));

        HuespedesController hc = new HuespedesController();
        var huespedes = hc.huespedList("admin");
        huespedes.forEach(huesped -> System.out.println(huesped.getId()));
    }
}
