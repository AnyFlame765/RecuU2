package utez.edu.mx.recuperau2.model;

import utez.edu.mx.recuperau2.utils.MySQLConnection;
import utez.edu.mx.recuperau2.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoStudent implements Repository<BeanStudent>{
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();


    @Override
    public List<BeanStudent> findAll() {

        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM alumnos;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setApellido(rs.getString("apellido"));
                student.setFechaNac(rs.getString("fechaNac"));
                student.setCurp(rs.getString("curp"));
                student.setMatricula(rs.getString("matricula"));
                students.add(student);
            }
        } catch (Exception e){
            System.out.println("Error -> findAll" + e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return students;
    }


    @Override
    public BeanStudent findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanStudent> save(BeanStudent object) {
        try{
            if (verifyCurp(object.getCurp())){
                return new Response<BeanStudent>(200, "Curp existente", true);
            }
            if (verifyMatricula(object.getMatricula())){
                return new Response<BeanStudent>(200, "Matricula Existente", true);
            }
            conn = client.getConnection();
            String query = "INSERT INTO alumnos (name, apellido, fechaNac, curp, matricula) VALUES (?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1, object.getName());
            ps.setString(2, object.getApellido());
            ps.setString(3, object.getFechaNac());
            ps.setString(4, object.getCurp());
            ps.setString(5, object.getMatricula());
            if(ps.executeUpdate() == 1){
                return new Response<BeanStudent>(200, "Registrado correctamente", object, false);
            } else {
                return new Response<>(200, "Error al registrar", object, true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanStudent> update(BeanStudent object) {
        try{
            conn=client.getConnection();
            String query = "UPDATE alumnos SET name=?, apellido=?, fechaNac=?, curp=?, matricula=? WHERE id=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, object.getName());
            ps.setString(2, object.getApellido());
            ps.setString(3, object.getFechaNac());
            ps.setString(4, object.getCurp());
            ps.setString(5, object.getMatricula());
            ps.setInt(6, object.getId());

            if(ps.executeUpdate() == 1){
                return new Response<BeanStudent>(200, "Todo bien", object, false);
            } else {
                return new Response<>(200, "Todo mal", object, true);
            }

        }catch (SQLException e){
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error en el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }

    }

    @Override
    public Response<BeanStudent> remove(Long id) {
        return null;
    }

    @Override
    public List<BeanStudent> findSC(String name) {
        return null;
    }


    @Override
    public List<BeanStudent> findSC() {
        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        BeanCal calificacion = null;
        try{
            conn = client.getConnection();
            String query = "SELECT alumnos.name, alumnos.apellido, calificaciones.materia, calificaciones.calificacion FROM alumnos" +
                    " INNER JOIN calificaciones ON alumnos.id = calificaciones.curp_estudiante;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                calificacion = new BeanCal();
                student.setName(rs.getString("name"));
                student.setApellido(rs.getString("apellido"));
                calificacion.setMateria(rs.getString("materia"));
                calificacion.setCal(rs.getInt("calificacion"));
                student.setCal(calificacion);
                students.add(student);
            }
        }catch (Exception e){
            System.out.println("Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return students;

    }

    public List<BeanStudent> findAverage(){
        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        BeanCal cal = null;
        try{
            cal = new BeanCal();
            conn = client.getConnection();
            String query = "SELECT alumno.name, alumno.apellido," +
                    " AVG(evaluations.qualification) AS promedio FROM alumnos INNER JOIN calificaciones ON alumnos.id = calificaciones.id_alumnos GROUP BY alumnos.name;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                student.setName(rs.getString("name"));
                student.setApellido(rs.getString("apellido"));
                cal.setCal(rs.getInt("promedio"));
                student.setCal(cal);
                students.add(student);
            }
        }catch (Exception e){
            System.out.println("Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return students;
    }

    public boolean verifyCurp(String curp){
        try{
            conn = client.getConnection();
            String query = "SELECT * FROM alumnos WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, curp);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        }catch (Exception e){
            System.out.println("Error -> verifyCurp"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

    public boolean verifyMatricula(String matricula){
        try{
            conn = client.getConnection();
            String query = "SELECT * FROM alumnos WHERE matricula = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("Error -> verifyMatricula"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }
}
