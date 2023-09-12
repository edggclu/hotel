package ed.mx.pruebas;

import ed.mx.controller.ReservasController;

public class PruebaBusquedaReserva {
    public static void main(String[] args) {
        ReservasController reservasController = new ReservasController();
        reservasController.listarPorId(4L,"admin");
    }
}
