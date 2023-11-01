/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author tarco
 */
public class ToDo {
    int idTodo;
    String Tittle;
    String Description;
    Date Startedin;
    Date Completedin;
    int Status;

    public ToDo() {
    }

    public ToDo(int idTodo, String Tittle, String Description, Date Startedin, Date Completedin, int Status) {
        this.idTodo = idTodo;
        this.Tittle = Tittle;
        this.Description = Description;
        this.Startedin = Startedin;
        this.Completedin = Completedin;
        this.Status = Status;
    }

    public int getIdTodo() {
        return idTodo;
    }

    public void setIdTodo(int idTodo) {
        this.idTodo = idTodo;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String Tittle) {
        this.Tittle = Tittle;
    }

    public Date getStartedin() {
        return Startedin;
    }

    public void setStartedin(Date Startedin) {
        this.Startedin = Startedin;
    }

    public Date getCompletedin() {
        return Completedin;
    }

    public void setCompletedin(Date Completedin) {
        this.Completedin = Completedin;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
