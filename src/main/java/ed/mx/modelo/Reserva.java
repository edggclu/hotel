package ed.mx.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaE;
    private Date fechaS;
    private BigDecimal valor;
    private String forma_de_pago;
    @OneToOne(mappedBy = "idReserva", fetch = FetchType.LAZY)
    private Huesped huesped;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public Reserva(Date fechaE, Date fechaS, BigDecimal valor, String forma_de_pago, Usuario usuario) {
        this.fechaE = fechaE;
        this.fechaS = fechaS;
        this.valor = ((valor));
        this.forma_de_pago = forma_de_pago;
        this.usuario = usuario;

    }

    public Reserva(Date fechaE, Date fechaS, BigDecimal valor, String forma_de_pago, Huesped huesped) {
        this.fechaE = fechaE;
        this.fechaS = fechaS;
        this.valor = valor;
        this.forma_de_pago = forma_de_pago;
        this.huesped = huesped;
    }

    public Reserva() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaE() {
        return fechaE;
    }

    public void setFechaE(Date fechaE) {
        this.fechaE = fechaE;
    }

    public Date getFechaS() {
        return fechaS;
    }

    public void setFechaS(Date fechaS) {
        this.fechaS = fechaS;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getForma_de_pago() {
        return forma_de_pago;
    }

    public void setForma_de_pago(String formaPago) {
        this.forma_de_pago = formaPago;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public String getHuespedString() {
        return (huesped.getNombre() + "   id: " + huesped.getId());
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}
