$(document).ready(function(){
    traerDatos();
});
function traerDatos(){
    $.ajax({
        url: "http://localhost:8089/api/Client/all",
        type : "GET",
        dataType : "json",
        success: function (respuesta){
            pintarClientes()
        },
        error: function (respuesta, xhr){
            alert("Lo siento! ha sucedido un error");
        },
    });
}

function pintarClientes(){
    let html="";

    html+= "<thead>";
    html+= "<tr></tr>Nombre</th><th>Email</th><th>Edad</th><th>";
    html+= "<thead>";

    html+= "<tbody>";
    for(let i = 0; i<datos.length; i++){
        html+="<tr><td>"+datos[i].name+"</td>";
        html+="<tr><td>"+datos[i].email+"</td>";
        html+="<tr><td>"+datos[i].age+"</td>";
    }
    html+="</tbody>";

    $("#tabla").empty();
    $("#tabla").append(html);
}