/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelsDao;

import Configuration.Conexion;
import Interfaces.CRUD;
import Models.ToDo;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class ToDoDao implements CRUD{

    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int rowCount;
    ToDo td=new ToDo();

    @Override
    public List listar() {
        ArrayList<ToDo>list=new ArrayList<>();
        String sql="select * from Todo";
        //JOptionPane.showMessageDialog(null, "Llamando");
        try{
            con=cn.Conectar();    
            ps=con.prepareStatement(sql);                 
            rs=ps.executeQuery();

            while(rs.next()){
                ToDo td=new ToDo();
                td.setIdTodo(rs.getInt("idTodo"));
                td.setTittle(rs.getString("Tittle"));
                td.setDescription(rs.getString("Description"));
                td.setStartedin(rs.getDate("Startedin"));
                td.setCompletedin(rs.getDate("Completedin"));
                td.setStatus(rs.getInt("Status"));
                list.add(td);
            }
            rs.close();
        }catch(Exception e){
            System.err.println("Error en la Base de Datos: "+e);
        }
        return list;
    }
    
    public List Buscar(int status ){
        ArrayList<ToDo>list=new ArrayList<>();
        String sql="select * from Todo where Status="+status;
        try{
            con=cn.Conectar();    
            ps=con.prepareStatement(sql);                 
            rs=ps.executeQuery();

            while(rs.next()){
                ToDo td=new ToDo();
                td.setIdTodo(rs.getInt("idTodo"));
                td.setTittle(rs.getString("Tittle"));
                td.setDescription(rs.getString("Description"));
                td.setStartedin(rs.getDate("Startedin"));
                td.setCompletedin(rs.getDate("Completedin"));
                td.setStatus(rs.getInt("Status"));
                list.add(td);
            }
            rs.close();
        }catch(Exception e){
            System.err.println("Error en la Base de Datos: "+e);
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
        return true;
    }

    @Override
    public boolean delete(ToDo todo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ToDo> Listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
