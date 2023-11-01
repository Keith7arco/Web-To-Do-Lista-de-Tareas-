/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Models.ToDo;
import ModelsDao.ToDoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SvToDo", urlPatterns = {"/SvToDo"})
public class SvToDo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out=response.getWriter()){
            //CAPTURA DE DATOS
            //int id = Integer.parseInt(request.getParameter("txtId"));
            String titulo = request.getParameter("txtTitulo");
            String descripcion = request.getParameter("txtDescripcion");
            String msg = "Algo salio mal...";

            //FECHA || CAPTURA DE DATOS
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = null;
            String fecha = request.getParameter("txtFecha");
            try {
                fechaDate = formato.parse(fecha);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ToDoDao dao = new ToDoDao();
            ToDo newTodo = new ToDo();
            newTodo.setTittle(titulo);
            newTodo.setDescription(descripcion);
            newTodo.setStartedin(fechaDate);
            newTodo.setCompletedin(fechaDate);

            //LOGICA STATUS
            Date fechaHoy = new Date();
            if (fechaHoy == fechaDate) {
                newTodo.setStatus(0);
            } else {
                newTodo.setStatus(1);
            }

            //Insertar
            if (request.getParameter("btnAdd") != null) {
                boolean res = dao.add(newTodo);
                if (res != true) {
                    msg = "Tarea registrada correctamente.";
                }
            }
            request.setAttribute("Mensaje", msg);
            request.getRequestDispatcher("/Views/ToDoMenu.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Error "+ e.getLocalizedMessage());
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
