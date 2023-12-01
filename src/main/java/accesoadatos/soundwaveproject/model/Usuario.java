package accesoadatos.soundwaveproject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contraseña")
    private String contraseña;
    @Column(name = "foto", columnDefinition = "BLOB")
    private byte[] foto;
    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL)
    private List<Lista> misListas = new ArrayList<>();
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "SUSCRIPCION",
            joinColumns = @JoinColumn(name = "dni"),
            inverseJoinColumns = @JoinColumn(name = "id_lista")
    )
    private List<Lista> suscripciones = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String dni, String nombre, String correo, String contraseña, byte[] foto) {
        this.dni = dni;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.foto = foto;
    }

    public Usuario(String dni, String nombre, String correo, String contraseña, byte[] foto, List<Lista> misListas, List<Comentario> comentarios, List<Lista> suscripciones) {
        this.dni = dni;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.foto = foto;
        this.misListas = misListas;
        this.comentarios = comentarios;
        this.suscripciones = suscripciones;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<Lista> getMisListas() {
        return misListas;
    }

    public void setMisListas(List<Lista> misListas) {
        this.misListas = misListas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Lista> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<Lista> suscripciones) {
        this.suscripciones = suscripciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni) && Objects.equals(correo, usuario.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, correo);
    }

    @Override
    public String toString() {
        return "Usuario - " +
                "dni: '" + dni + '\'' +
                ", nombre: '" + nombre + '\'' +
                ", correo: '" + correo + '\'' +
                ", contraseña: '" + contraseña + '\'' +
                ", misListas: " + misListas +
                ", comentarios: " + comentarios +
                ", suscripciones: " + suscripciones;
    }
}
