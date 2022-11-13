package utez.edu.mx.recuperau2.model;

public class BeanCal {
    int id;
    String materia;
    int  cal;

    public BeanCal() {
    }

    public BeanCal(int id, String materia, int cal) {
        this.id = id;
        this.materia = materia;
        this.cal = cal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }
}
