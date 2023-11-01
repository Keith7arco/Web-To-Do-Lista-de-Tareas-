/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Models.ToDo;
import java.util.List;

/**
 *
 * @author tarco
 */
public interface CRUD {
    public List listar();
    public ToDo list(int id);
    public boolean add(ToDo todo);
    public boolean edit(ToDo todo);
    public boolean delete(ToDo todo);
}
