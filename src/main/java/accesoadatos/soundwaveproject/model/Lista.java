package accesoadatos.soundwaveproject.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "LISTA")
public class Lista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "description")
    private String descripcion;
    @ManyToOne // Relación con misListas
    @JoinColumn(name = "dni_user")
    private Usuario creador;
    @Column(name = "suscripciones")
    private int numSuscripciones;
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();
    @ManyToMany(mappedBy = "suscripciones")
    @Fetch(FetchMode.JOIN)
    private List<Usuario> suscriptores = new ArrayList<>();
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "cancion_lista",
            joinColumns = { @JoinColumn(name = "id_song") },
            inverseJoinColumns = { @JoinColumn(name = "id_list") }
    )
    private List<Cancion> canciones = new ArrayList<>();



    public Lista() {
    }

    // Constructor para nuevas listas (sin ID)
    public Lista(String nombre, String descripcion, Usuario creador, int numSuscripciones,List<Comentario> comentarios, List<Usuario> suscriptores) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.comentarios = comentarios;
        this.suscriptores = suscriptores;
        this.numSuscripciones = 0;
    }

    // Constructor para listas existentes (con ID)
    public Lista(int id, String nombre, String descripcion, Usuario creador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
    }

    // Constructor completo con comentarios y suscriptores
    public Lista(int id, String nombre, String descripcion, Usuario creador, List<Comentario> comentarios, List<Usuario> suscriptores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.comentarios = comentarios;
        this.suscriptores = suscriptores;
    }

    public Lista(int id, String nombre, String descripcion, ArrayList<Usuario> usuarios, ArrayList<Comentario> comentarios, ArrayList<Usuario> usuarios1) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(List<Usuario> suscriptores) {
        this.suscriptores = suscriptores;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public int getNumSuscriptores() {
        return this.numSuscripciones;
    }

    public void setNumSuscriptores(int numSuscriptores) {
        this.numSuscripciones = numSuscriptores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lista lista = (Lista) o;
        return id == lista.id;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", numSuscripciones=" + numSuscripciones +
                '}';
    }
}
