package Servlets;

import Models.ToDo;
import ModelsDao.ToDoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FiltroSvToDo", urlPatterns = {"/FiltroSvToDo"})
public class FiltroSvToDo extends HttpServlet {

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
        ToDoDao daoF = new ToDoDao();
        try {
            //FILTROS
            int filtro = Integer.parseInt(request.getParameter("filtro"));
            List<ToDo> filteredList = daoF.Buscar(filtro);

            // Establece la lista filtrada como atributo del request
            request.setAttribute("datalist", filteredList);

            // Redirige a tu página JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/ToDoMenu.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Manejo de excepciones...
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
