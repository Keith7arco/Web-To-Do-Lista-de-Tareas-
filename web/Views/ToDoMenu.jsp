<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.ToDo"%>
<%@page import="Configuration.Conexion"%>
<%@page import="ModelsDao.ToDoDao"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.IOException" %>
<%@page import="java.text.ParseException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/styles.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="${pageContext.servletContext.contextPath}/js/todo.js"></script> 
    </head>
    <body style="background-color: #184770;">
        <section class="vh-100">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card" id="list1" style="border-radius: .75rem; background-color: #eff1f2;">
                            <div class="card-body py-4 px-4 px-md-5">
                                <p class="h1 text-center mt-3 mb-4 pb-3 text-primary">
                                    <i class="fas fa-check-square me-1"></i>
                                    <b>Lista de Tareas</b>
                                </p>

                                <div>
                                    <button type="button" id="btnAgregarM" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        <i class="fas fa-plus"></i>
                                    </button>       
                                </div>
                                <!-- Modal Agregar-->
                                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Agregar Nueva Tarea</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="${pageContext.servletContext.contextPath}/SvToDo" id="Formulario" method="POST" role="form">
                                                    <div class="pb-2">
                                                        <div class="card">
                                                            <div class="card-body">
                                                                <div class="d-flex flex-row align-items-center">                                                                  
                                                                    <input type="text" name="txtTitulo" id="txtTitulo" class="form-control form-control"  maxlength="30"
                                                                           style="font-weight: bold;"
                                                                           placeholder="Titulo...">
                                                                    <input class="id" name="txtId" id="txtId" style="opacity: 0;">
                                                                </div>                               
                                                                <div class="d-flex flex-row align-items-center">
                                                                    <textarea type="text" name="txtDescripcion" id="txtDescripcion" class="form-control form-control"
                                                                              maxlength="150"
                                                                              placeholder="Descripcion..."></textarea>
                                                                </div>
                                                                <div class="d-flex flex-row align-items-center">
                                                                    <input type="date" name="txtFecha" id="txtFecha" class="form-control form-control-lg" id="fechaInicio"
                                                                           required>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                                                        <button type="submit" name="btnAdd" id="btnAgregar" class="btn btn-primary">Guardar</button>
                                                        <button type="submit" name="btnEdit" id="btnEditar" class="btn btn-info">Editar</button>
                                                        <button type="submit" name="btnDelete" id="btnEliminar" class="btn btn-dark">Eliminar</button>
                                                    </div>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>



                                <hr class="my-4">          
                                <div class="d-flex justify-content-end align-items-center mb-4 pt-2 pb-3">
                                    <p class="small mb-0 me-2 text-muted">Filtro</p>

                                    <select id="filtroSelect" class="select" name="filtro">
                                        <option value="0">En Progreso</option>
                                        <option value="1">Pendiente</option>
                                        <option value="2">Completado</option>
                                        <option value="3">Todos</option>
                                    </select>



                                    <p class="small mb-0 ms-4 me-2 text-muted">Ordenado por</p>
                                    <a style="color: #23af89;" data-mdb-toggle="tooltip" title="Ascending"><i
                                            class="fas fa-sort-amount-down-alt ms-2"></i></a>
                                </div>

                                <%
                                  List<ToDo> datalist = null;
                                  datalist = (List<ToDo>) request.getAttribute("datalist");
                                  ToDoDao dao=new ToDoDao();
                                  if (datalist == null) {
                                    datalist = dao.Buscar(0);
                                  }else{
                                    datalist = (List<ToDo>) request.getAttribute("datalist");
                                  }
                                  
                                  request.getAttribute("datalist");
  
                                  if(datalist == null || datalist.isEmpty()){
                                  
                                %>
                                <div class="text-center container d-flex justify-content-center align-items-center h-100">
                                    <p class="h1 text-center mt-3 mb-4 pb-3 text-dark">

                                        <b>No hay tareas pendientes</b>
                                        <i class="fa-regular fa-face-laugh-beam"></i>
                                        <img src="https://cdn-icons-png.flaticon.com/128/7809/7809425.png" alt="alt"/>
                                        <img src="https://cdn-icons-png.flaticon.com/128/237/237668.png" alt="alt"/>
                                    </p>
                                </div>
                                <%    
                                    }
                                else{
                                  for (ToDo data:datalist) {
                                      int id = data.getIdTodo();
                                      String titulo = data.getTittle();
                                      String descripcion = data.getDescription();
                                      String fecha = data.getCompletedin().toString();
                                %>

                                <ul class="list-group list-group-horizontal rounded-0 bg-transparent pagination">
                                    <li
                                        class="list-group-item d-flex align-items-center ps-0 pe-3 py-1 rounded-0 border-0 bg-transparent">
                                        <div class="form-check">
                                            <input class="form-check-input me-0" type="checkbox" value="" id="flexCheckChecked1"
                                                   aria-label="..."/>
                                        </div>
                                    </li>
                                    <li
                                        class="list-group-item px-3 py-1 d-flex align-items-center flex-grow-1 border-0 bg-transparent">
                                        <p class="id" style="opacity:0;"><%= id%></p>
                                        <h2 class="lead fw-bold mb-0 titulo"><%= titulo%></h2>
                                    </li>

                                    <li
                                        class="list-group-item px-3 py-1 d-flex align-items-center flex-grow-1 border-0 bg-transparent">
                                        <h2 class="lead fw-normal mb-0 descripcion"><%= descripcion%></h2>
                                    </li>

                                    <li class="list-group-item ps-3 pe-0 py-1 rounded-0 border-0 bg-transparent">
                                        <div class="d-flex flex-row justify-content-end mb-1">
                                            <a href="#!" class="text-info btnEditar" data-mdb-toggle="tooltip" title="Editar Tarea"
                                               data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                                                    class="fas fa-pencil-alt me-3"></i></a>
                                            <a href="#!" class="text-danger btnEliminar" data-mdb-toggle="tooltip" title="Eliminar Tarea"
                                               data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                                                    class="fas fa-trash-alt"></i></a>
                                        </div>
                                        <div class="text-end text-muted">
                                            <a class="text-muted" data-mdb-toggle="tooltip" title="Created date">
                                                <p class="small mb-0 fecha"><i class="fas fa-info-circle me-2"></i><%= fecha%></p>
                                            </a>
                                        </div>
                                    </li>
                                </ul>      

                                <%}}%>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>   
    </body>
    <script>
        $(document).ready(function () {
            $('#filtroSelect').change(function () {
                var selectedValue = $(this).val();
                var storedValue = localStorage.getItem('selectedValue');

                if (selectedValue !== storedValue) {
                    // Hacer una petición al servidor solo si el valor ha cambiado
                    $.ajax({
                        type: 'POST',
                        url: '${pageContext.servletContext.contextPath}/FiltroSvToDo',
                        data: {filtro: selectedValue},
                        success: function (response) {
                            // Actualizar el contenido de la página con la respuesta del servidor
                            $('body').html(response);
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });

                    // Actualizar el valor almacenado en el localStorage
                    localStorage.setItem('selectedValue', selectedValue);
                }
            });

            // Establecer el valor seleccionado al cargar la página
            var storedValue = localStorage.getItem('selectedValue');
            if (storedValue) {
                $('#filtroSelect').val(storedValue);
            }
        });
    </script>
</html>
