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

        try (PrintWriter out = response.getWriter()) {
            //CAPTURA DE DATOS BOTONES
            String btnAdd = request.getParameter("btnAdd");
            String btnEdit = request.getParameter("btnEdit");
            String btnDelete = request.getParameter("btnDelete");
            // Verifica si la solicitud es una operación de formulario
            if (btnAdd != null || btnEdit != null || btnDelete != null) {
                // Procesar el formulario solo si se envió un botón de formulario
                processForm(request, response);
            } else {
                // Si no se envió un botón de formulario, redirige a la página sin procesar el formulario
                response.sendRedirect(request.getContextPath() + "/Views/ToDoMenu.jsp");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }

    }

    private void processForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //CAPTURA DE DATOS     
        String idParam = request.getParameter("txtId");
        int id = 0;
        //Validacion Campo Vacio Id
        if (idParam != null && !idParam.trim().isEmpty()) {
            id = Integer.parseInt(idParam);
        }

        String titulo = request.getParameter("txtTitulo");
        String descripcion = request.getParameter("txtDescripcion");
        String msg = "Algo salio mal...";
        boolean res;

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
        newTodo.setIdTodo(id);

        //LOGICA STATUS
        Date fechaHoy = new Date();
        if (fechaDate.equals(fechaHoy)) {
            newTodo.setStatus(0); // Fecha de hoy
        } else if (fechaHoy.before(fechaDate)) {
            newTodo.setStatus(1); // Fecha pasada
        }

        //Insertar
        if (request.getParameter("btnAdd") != null) {
            res = dao.add(newTodo);
            if (res != false) {
                msg = "Tarea registrada correctamente.";
            }
            //Editar
        } else if (request.getParameter("btnEdit") != null) {
            res = dao.edit(newTodo);
            if (res != false) {
                msg = "Tarea editada correctamente.";
            }
        } else if (request.getParameter("btnDelete") != null) {
            res = dao.delete(newTodo);
            if (res != false) {
                msg = "Tarea eliminada correctamente.";
            }
        }
        request.setAttribute("message", msg);
        request.getRequestDispatcher("/Views/ToDoMenu.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
