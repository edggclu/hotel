package ed.mx.modelo;

import java.sql.Date;

public class Reserva {
    private Integer id;
    private Date fechaE;
    private Date fechaS;
    private String valor;
    private String formaPago;

    public Reserva(Integer id, Date fechaE, Date fechaS, String valor, String formaPago) {
        this.id = id;
        this.fechaE = fechaE;
        this.fechaS = fechaS;
        this.valor = valor;
        this.formaPago = formaPago;
    }
}
