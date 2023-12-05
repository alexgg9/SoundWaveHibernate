package accesoadatos.soundwaveproject.model;

import accesoadatos.soundwaveproject.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CANCION")
public class Cancion implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	@Column(name = "name")
    private String nombre;
	@Column(name = "duration")
    private int duracion;
	@Column(name = "gender")
    private String genero;
	@Column(name = "url")
	private String url;
	@ManyToOne
	@JoinColumn(name = "disc_id")
	private Disco disco;



	public Cancion(int id, String nombre, int duracion, String genero, String url, Disco disco) {
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.genero = genero;
		this.url = url;
		this.disco = disco;
	}

	public Cancion() {
		this(-1, "", 0, "", "",null);

	}

	public Disco getDisco() {
		return disco;
	}

	public void setDisco(Disco disco) {
		this.disco = disco;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero() {
        return genero;
    }

	public void setId(int id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}

    @Override
	public int hashCode() {
		return Objects.hash(duracion, genero, id, nombre);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cancion cancion = (Cancion) o;
		return id == cancion.id;
	}

	@Override
	public String toString() {
		return nombre + " - Duración: " + duracion;
	}

}

