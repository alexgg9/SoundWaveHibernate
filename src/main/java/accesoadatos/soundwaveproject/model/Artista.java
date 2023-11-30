package accesoadatos.soundwaveproject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ARTISTA")
public class Artista implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "foto", columnDefinition = "BLOB")
    private byte[] foto;
    @OneToMany(mappedBy = "artista")
    private List<Disco> discos;


    public Artista() {
    }

    public Artista(String dni, String nombre, String nacionalidad, byte[] foto, List<Disco> discos) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
        this.discos = discos;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artista artista = (Artista) o;
        return dni.equals(artista.dni) && Arrays.equals(foto, artista.foto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dni);
        result = 31 * result + Arrays.hashCode(foto);
        return result;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", foto=" + Arrays.toString(foto) +
                ", discos=" + discos +
                '}';
    }
}
