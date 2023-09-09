package ed.mx.modelo;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "huespedes")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String Nacionalidad;
    private String telefono;
    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
    @JoinColumn(name = "idReserva")
    private Reserva idReserva;

    public Huesped() {

    }

    public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.Nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getIdReserva() {
        return idReserva.getId();
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }
}
