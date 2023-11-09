package ModelsDao;

import Configuration.Conexion;
import Interfaces.CRUD;
import Models.ToDo;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ToDoDao implements CRUD {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int rowCount;
    ToDo td = new ToDo();

    public List Buscar(int status) {
        String sql;
        sql = "select * from Todo where Status=" + status;
        if(status==3){
            sql = "select * from Todo";
        }
        ArrayList<ToDo> list = new ArrayList<>();
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ToDo td = new ToDo();
                td.setIdTodo(rs.getInt("idTodo"));
                td.setTittle(rs.getString("Tittle"));
                td.setDescription(rs.getString("Description"));
                td.setStartedin(rs.getDate("Startedin"));
                td.setCompletedin(rs.getDate("Completedin"));
                td.setStatus(rs.getInt("Status"));
                list.add(td);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("Error en la Base de Datos: " + e);
        }
        return list;
    }

    @Override
    public ToDo list(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(ToDo todo) {
        try{
            String sql="insert into Todo(Tittle,Description,Startedin,Completedin,Status) values (?,?,?,?,?)";
            con=cn.Conectar();    
            ps=con.prepareStatement(sql); 
            ps.setString(1, todo.getTittle());
            ps.setString(2, todo.getDescription());
            
            //FECHA CONVERSION DATES
            java.util.Date FI =todo.getStartedin(); 
            java.util.Date FF =todo.getCompletedin(); 
            Date sqlFI = new java.sql.Date(FI.getTime());
            Date sqlFF = new java.sql.Date(FF.getTime());
            ps.setDate(3,sqlFI);
            ps.setDate(4,sqlFF);
            
            ps.setInt(5, todo.getStatus());
            
            rowCount=ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.err.println("Error en la Base de Datos: "+e);
            return false;
        }   
    }

    @Override
    public boolean edit(ToDo todo) {
        try {
            String sql = "UPDATE Todo SET Tittle = ?,\n"
                    + "    Description = ?,\n"
                    + "    Startedin = ?,\n"
                    + "    Completedin = ?,\n"
                    + "    Status = ? \n"
                    + "WHERE idTodo = ?;";
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, todo.getTittle());
            ps.setString(2, todo.getDescription());

            //FECHA CONVERSION DATES
            java.util.Date FI = todo.getStartedin();
            java.util.Date FF = todo.getCompletedin();
            Date sqlFI = new java.sql.Date(FI.getTime());
            Date sqlFF = new java.sql.Date(FF.getTime());
            ps.setDate(3, sqlFI);
            ps.setDate(4, sqlFF);

            ps.setInt(5, todo.getStatus());
            ps.setInt(6, todo.getIdTodo());

            rowCount = ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error en la Base de Datos: " + e);
            return false;
        }
    }

    @Override
    public boolean delete(ToDo todo) {
        try {
            String sql = "UPDATE Todo SET Status = 2 WHERE idTodo = ?;";
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, todo.getIdTodo());

            rowCount = ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error en la Base de Datos: " + e);
            return false;
        }
    }

    @Override
    public List listar() {
        ArrayList<ToDo> list = new ArrayList<>();
        String sql = "select * from Todo";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ToDo td = new ToDo();
                td.setIdTodo(rs.getInt("idTodo"));
                td.setTittle(rs.getString("Tittle"));
                td.setDescription(rs.getString("Description"));
                td.setStartedin(rs.getDate("Startedin"));
                td.setCompletedin(rs.getDate("Completedin"));
                td.setStatus(rs.getInt("Status"));
                list.add(td);
            }
            rs.close();
        } catch (Exception e) {
            System.err.println("Error en la Base de Datos: " + e);
        }
        return list;
    }

}
