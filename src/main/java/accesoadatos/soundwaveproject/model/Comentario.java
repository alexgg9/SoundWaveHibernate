package accesoadatos.soundwaveproject.model;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "COMENTARIO")
public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "content")
    private String contenido;
    @Column(name = "date",columnDefinition = "DATE")
    private LocalDate fecha;
    @ManyToOne()
    @JoinColumn(name = "dni_user")
    private Usuario usuario;
    @ManyToOne()
    @JoinColumn(name = "id_list")
    private Lista lista;

    public Comentario() {
        this(-1,"",null,null,null);
    }

    public Comentario(int id, String contenido, LocalDate fecha, Usuario usuario, Lista lista) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.usuario = usuario;
        this.lista = lista;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lista getLista() {
        return lista;
    }

    public void setLista(Lista lista) {
        this.lista = lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", lista=" + lista +
                '}';
    }
}
