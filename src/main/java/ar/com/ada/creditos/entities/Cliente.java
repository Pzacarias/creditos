package ar.com.ada.creditos.entities;


import java.util.Date;
import java.util.Scanner;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import ar.com.ada.creditos.excepciones.*;
import java.util.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    public static Scanner Teclado = new Scanner(System.in);

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTOINCREMENTAL
    private int clienteId;

    private String nombre;

    @NaturalId
    private int dni;

    private String direccion;

    @Column(name = "direccion_alternativa")
    private String direccionAlternativa;

    @Column(name="fecha_nacimiento")
    @Temporal(TemporalType.DATE) //SOLO Poner esto si no queremos manejar HORA en el DB Server.
    private Date fechaNacimiento;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Prestamo> prestamos = new ArrayList<>();
    
   
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }



    public Cliente(String nombre) {
        this.nombre = nombre;

    }



    public Cliente() {
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.isEmpty()) {
            System.out.println("Por favor ingrese un nombre válido.");
           nombre = Teclado.nextLine();
           this.nombre = nombre;
        }
        else this.nombre = nombre;

    }

    public void setDni(int dni) throws ClienteDNIException {

        if (dni < 0) {
            // no se ejecuta nada mas despues del throw
            throw new ClienteDNIException(this, "ocurrio un error con el DNI");

        }
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Cliente [id="+ clienteId +", dni=" + dni + ", nombre=" + nombre + "]";
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion.isEmpty()) {
            System.out.println("Por favor ingrese una dirección válida.");
           direccion = Teclado.nextLine();
           this.direccion = direccion;
        }
        else this.direccion = direccion;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void agregarPrestamo(Prestamo prestamo){
        this.prestamos.add(prestamo);
    }
}