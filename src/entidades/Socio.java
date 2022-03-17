package entidades;

import java.util.Objects;

public class Socio {
    private Long id;
    private String nombre;
    private String curp;
    private String direccion;

    public Socio() {
    }

    public Socio(String nombre, String curp) {
        this.nombre = nombre;
        this.curp = curp;
    }

    public Socio(Long id, String nombre, String curp) {
        this.id = id;
        this.nombre = nombre;
        this.curp = curp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Socio other = (Socio) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Socio{" + "id=" + id + ", nombre=" + nombre + ", curp=" + curp + '}';
    }        
}
