/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function llenarForm(fila) {
    var id = $(fila).find(".id").text();
    var titulo = $(fila).find(".titulo").text();
    var desc = $(fila).find(".descripcion").text();
    var fecha = $(fila).find(".fecha").text();

    $("#txtId").val(id);
    $("#txtTitulo").val(titulo);
    $("#txtDescripcion").val(desc);
    $("#txtFecha").val(fecha);
}


$(document).ready(function () {

    $("#exampleModal").on("hidden.bs.modal", function () {
        $('form')[0].reset();
    });

    $(document).on('click', '.btnEditar', function () {
        llenarForm($(this).closest('ul'));
    });

    $(document).on('click', '.btnEliminar', function () {
        llenarForm($(this).closest('ul'));
    });

    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Botón que abre el modal
        var modal = $(this);

        if (button.attr("id") === "btnAgregarM") {
            // Si se abre el modal de "Agregar", oculta el botón "Editar"
            modal.find("#btnEditar").hide();
            modal.find("#btnAgregar").show();
            modal.find("#btnEliminar").hide();
        } else {
            // Si se abre el modal de "Editar", oculta el botón "Agregar"
            modal.find("#btnAgregar").hide();
            modal.find("#btnEditar").show();
            modal.find("#btnEliminar").show();
        }
    });
});

