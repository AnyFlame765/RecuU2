package utez.edu.mx.recuperau2.model;

public class BeanTeacher {
    int id;
    String name;
    String apellidos;
    String fechaNac;
    String curp;
    String nss;

    BeanCal cal;

    public BeanCal getCal() {
        return cal;
    }

    public void setCal(BeanCal cal) {
        this.cal = cal;
    }

    public BeanTeacher() {
    }

    public BeanTeacher(int id, String name, String apellidos, String fechaNac, String curp, String nss) {
        this.id = id;
        this.name = name;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.curp = curp;
        this.nss = nss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }
}
