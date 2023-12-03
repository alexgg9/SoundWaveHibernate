package accesoadatos.soundwaveproject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DISCO")
public class Disco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "date_song", columnDefinition = "DATE")
    private LocalDate fechaPublicacion;
    @Lob
    @Column(name = "foto", columnDefinition = "BLOB")
    private byte[] foto;

    @Column(name = "reproducciones")
    private String reproduccion;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artista artista;


    public Disco() {

    }

    public Disco(int id, String nombre, LocalDate fechaPublicacion, byte[] foto, String reproduccion, Artista artista, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.fechaPublicacion = fechaPublicacion;
        this.foto = foto;
        this.reproduccion = reproduccion;
        this.artista = artista;
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getReproduccion() {
        return reproduccion;
    }

    public void setReproduccion(String reproduccion) {
        this.reproduccion = reproduccion;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disco disco = (Disco) o;
        return id == disco.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", foto=" + Arrays.toString(foto) +
                ", reproduccion='" + reproduccion + '\'' +
                ", artista=" + artista + '}';
    }
}
