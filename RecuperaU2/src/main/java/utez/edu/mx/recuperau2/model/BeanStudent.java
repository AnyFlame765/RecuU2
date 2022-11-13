package utez.edu.mx.recuperau2.model;

public class BeanStudent {
    int id;
    String name;
    String apellido;
    String fechaNac;
    String curp;
    String matricula;

    BeanCal cal;

    public BeanStudent() {
    }

    public BeanStudent(int id, String name, String apellido, String fechaNac, String curp, String matricula) {
        this.id = id;
        this.name = name;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.curp = curp;
        this.matricula = matricula;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BeanCal getCal() {
        return cal;
    }

    public void setCal(BeanCal cal) {
        this.cal = cal;
    }
}
