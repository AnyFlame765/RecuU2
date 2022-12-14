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

public class DaoTeacher  implements Repository<BeanTeacher>  {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();
    @Override
    public List<BeanTeacher> findAll() {
        List<BeanTeacher> teacher = new ArrayList<>();
        BeanTeacher teach = null;
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM docentes;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                teach = new BeanTeacher();
                teach.setId(rs.getInt("id"));
                teach.setName(rs.getString("name"));
                teach.setApellidos(rs.getString("apellidos"));
                teach.setFechaNac(rs.getString("fechaNac"));
                teach.setCurp(rs.getString("curp"));
                teach.setNss(rs.getString("nss"));
                teacher.add(teach);
            }
        } catch (Exception e){
            System.out.println("Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return teacher;
    }

    @Override
    public  BeanTeacher findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanTeacher> save(BeanTeacher object) {

        try{
            if (validateCurp(object.getCurp())){
                return new Response<>(false, "Curp Existete", null);
            }
            if (validateNss(object.getNss())){
                return new Response<>(false, "NSS existente", null);
            }
            conn = client.getConnection();
            String query = "INSERT INTO docentes (name, apellidos, fechaNac, curp, nss) VALUES (?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1, object.getName());
            ps.setString(2, object.getApellidos());
            ps.setString(3, object.getFechaNac());
            ps.setString(4, object.getCurp());
            ps.setString(5, object.getNss());
            if(ps.executeUpdate() == 1){
                return new Response<BeanTeacher>(200, "Registrado correctamente", object, false);
            } else {
                return new Response<>(200, "Error al registrar", object, true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanTeacher> update(BeanTeacher object) {
        try{
            conn=client.getConnection();
            String query = "UPDATE docentes SET name=?, apellidos=?, fechaNac=?, curp=?, nss=? WHERE id=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, object.getName());
            ps.setString(2, object.getApellidos());
            ps.setString(3, object.getFechaNac());
            ps.setString(4, object.getCurp());
            ps.setString(5, object.getNss());
            ps.setInt(6, object.getId());
            if(ps.executeUpdate() == 1){

                return new Response<BeanTeacher>(200, "Actualizado correctamente", object, false);
            } else {
                return new Response<>(200, "Error al actualizar", object, true);
            }

        }catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanTeacher> remove(Long id) {
        return null;
    }

    @Override
    public List<BeanTeacher> findSC(String name) {
        return null;
    }

    @Override
    public List<BeanStudent> findSC() {
        return null;
    }

    public boolean validateCurp(String curp){
        try{
            conn = client.getConnection();
            String query = "SELECT * FROM docentes WHERE curp=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, curp);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> validateCurp: "+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

    public boolean validateNss(String nss){
        try{
            conn = client.getConnection();
            String query = "SELECT * FROM docentes WHERE nss=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, nss);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e){
            Logger.getLogger(DaoTeacher.class.getName())
                    .log(Level.SEVERE, "Error -> validateNss: "+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

}

