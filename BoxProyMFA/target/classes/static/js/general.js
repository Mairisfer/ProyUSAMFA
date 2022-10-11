$(document).ready(function(){
    leerClientes();
});
function leerClientes(){
    $.ajax({    
        url: "http://localhost:8089/api/Client/all",
        type : "GET",
        dataType : "json",
        
        success: function (clientes) {
            console.log(clientes);
            //let cs = clientes.items;
            //$("#listaClientes").empty();
            pintarRespuesta(clientes);
        },
        error : function(xhr, status) {
            alert("Lo siento! ha sucedido un error");
        },
    });
}

function pintarRespuesta(cs) {
    let myTable= '<div class="container"><div class="row">';
    for(i=0;i<cs.length;i++){
        myTable+=
            `<div class="card m-2" style="width: 18rem;" >
                <div class="card-body">
                    <h5 class="card-title">${cs[i].name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${cs[i].email}</h6>
                    <p class="card-text">${cs[i].age}</p>
                    <a href="#" class="card-link">Card link</a>
                    <button class={"btn btn-danger" onclick="borrarCliente(${cs[i].id})">Borrar</button>
                </div>
            </div>`
    }
        myTable += "</div></div>";
        $('#listaClientes').append(myTable);

    }
    /*let myTable = "<Table BORDER=1>";
        myTable += "<tr>";
        myTable += "<td>Id<td>";
        myTable += "<td>Nombre<td>";
        myTable += "<td>Correo electrónico<td>";
        myTable += "<td>Edad<td>";
        myTable += "<td>Acción<td>";
        myTable += "</tr>";
    $('#resultado').html("<br><br>");
    for (i = 0; i < cs.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + cs[i].id + "<td>";
        myTable += "<td>" + cs[i].name + "<td>";
        myTable += "<td>" + cs[i].email + "<td>";
        myTable += "<td>" + cs[i].age + "<td>";
        myTable += "<td><button onclick=borrarCliente(" + cs[i].id + ")>Borrar</button><td>";
        myTable += "</tr>";

    }*/


function guardarCliente() {
    let idCliente = $("#idCliente").val();
    let nombre = $("#nombreCliente").val();
    let mailCliente = $("#mailCliente").val();
    let edad = $("#edadCliente").val();

    let data = {
        id: idCliente,
        name: nombre,
        email: mailCliente,
        age: edad
    }
    let dataToSend = JSON.stringify(data);
    //console.log(data);

    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/client/client",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#idCliente").val("");
            $("#nombreCliente").val("");
            $("#mailCliente").val("");
            $("#edadCliente").val("");
        },
        error: function (xhr, status) {
        //    alert("Lo siento! ha sucedido un error");
        },
        complete: function() {
            leerClientes();
        }
    });
}

function editarCliente() {
    let idCliente = $("#idCliente").val();
    let nombre = $("#nombreCliente").val();
    let mailCliente = $("#mailCliente").val();
    let edad = $("#edadCliente").val();

    let data = {
        id: idCliente,
        name: nombre,
        email: mailCliente,
        age: edad
    };
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);
    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/client/client",
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            $("#idCliente").val("");
            $("#nombreCliente").val("");
            $("#mailCliente").val("");
            $("#edadCliente").val("");
        },
        error: function (xhr, status) {
            //     alert('ha sucedido un problema');
        },
        complete: function () {
            leerClientes();
        }
    });
}

function borrarCliente(idCliente) {
    
        let data = {
            id: idCliente
        }
        let dataToSend = JSON.stringify(data);
        //console.log(dataToSend);

        $.ajax({
            url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/client/client",
            type: "DELETE",
            //dataType: "JSON",
            data: dataToSend,
            contentType: "application/JSON",

            success: function (pepito) {
                
                alert("Cliente Eliminado")
                $("#idCliente").val("");
                $("#nombreCliente").val("");
                $("#mailCliente").val("");
                $("#edadCliente").val("");
            },
            error: function (xhr, status) {
                //    alert("Lo siento! ha sucedido un error");
            },
            complete: function () {
                leerClientes();
            }
        });
}

function leerMensajes() {
    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/message/message",
        type: "GET",
        dataType: "json",

        success: function (mensajes) {
            let mje = mensajes.items;
            $("#listaMensajes").empty();
            pintarRespuestamje(mje);

        },
        error: function (xhr, status) {
            alert("Lo siento! ha sucedido un error");

        },

    });
}
function pintarRespuestamje(mje) {

    let myTablemje = "<Table BORDER=1>";
    myTablemje += "<tr>";
    myTablemje += "<td>Id<td>";
    myTablemje += "<td>Mensaje<td>";
    myTablemje += "<td>Acción<td>";
    myTablemje += "</tr>";
    $('#resultadomje').html("<br><br>");
    for (i = 0; i < mje.length; i++) {
        myTablemje += "<tr>";
        myTablemje += "<td>" + mje[i].id + "<td>";
        myTablemje += "<td>" + mje[i].messagetext+ "<td>";
        myTablemje += "<td><button onclick=borrarMensaje(" + mje[i].id + ")>Borrar</button><td>";
        myTablemje += "</tr>";

    }
    myTablemje += "</table>";
    $('#listaMensajes').append(myTablemje);

}
function enviarMensaje() {
    let idMensaje = $("#idMensaje").val();
    //let mensajeDelCliente = $("#mensajeDelCliente").val();
    let mensajeDelCliente = document.getElementById("mensajeDelCliente").value;
    let data = {
        id: idMensaje,
        messagetext: mensajeDelCliente
    }
    let dataToSend = JSON.stringify(data);
    //console.log(data);

    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/message/message",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#idMensaje").val("");
            $("#mensajeDelCliente").val("");
            
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            alert('Tu mensaje ha sido enviado');
        }
    });
}

function borrarMensaje(idMensaje) {

    let data = {
        id: idMensaje
    }
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);

    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/message/message",
        type: "DELETE",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {

            alert("Mensaje Eliminado")
            $("idMensaje").val("");
            //$("#mensajeDelCliente").val("");

        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            leerMensajes();
        }
    });
}

function leerPalcos() {
    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/box/box",
        type: "GET",
        dataType: "json",

        success: function (palcos) {
            let ps = palcos.items;
            $("#listaPalcos").empty();
            pintarRespuestaPal(ps);
            /*for (i = 0; i < cs.length; i++){
                $("#listaClientes").append(cs[i].id + " <b>" + cs[i].name + "</b> " + cs[i].email + " " + cs[i].age);
                $("#listaClientes").append("<button onclick='borrarCliente(" + cs[i].id + ")'>Borrar</button><br>");
            }    */
        },
        error: function (xhr, status) {
            alert("Lo siento! ha sucedido un error");

        },

    });
}
function pintarRespuestaPal(ps) {

    let myTablepal = "<Table BORDER=1>";
    myTablepal += "<tr>";
    myTablepal += "<td>Id<td>";
    myTablepal += "<td>Locacion<td>";
    myTablepal += "<td>Capacidad<td>";
    myTablepal += "<td>Categoria<td>";
    myTablepal += "<td>Nombre<td>";
    myTablepal += "<td>Acción<td>";
    myTablepal += "</tr>";
    $('#resultadopal').html("<br><br>");
    for (i = 0; i < ps.length; i++) {
        myTablepal += "<tr>";
        myTablepal += "<td>" + ps[i].id + "<td>";
        myTablepal += "<td>" + ps[i].location + "<td>";
        myTablepal += "<td>" + ps[i].capacity + "<td>";
        myTablepal += "<td>" + ps[i].category_id + "<td>";
        myTablepal += "<td>" + ps[i].name + "<td>";
        myTablepal += "<td><button onclick=cancelarPalco(" + ps[i].id + ")>Borrar</button><td>";
        myTablepal += "</tr>";

    }
    myTablepal += "</table>";
    $('#listaPalcos').append(myTablepal);

}
function reservarPalco() {
    let idPalco = $("#idPalco").val();
    let locacion = $("#locacion").val();
    let capacidad = $("#capacidad").val();
    let categoria = $("#categoria").val();
    let nombre = $("#nombre").val();

    let data = {
        id: idPalco,
        location: locacion,
        capacity: capacidad,
        category_id: categoria,
        name: nombre
    }
    let dataToSend = JSON.stringify(data);
    //console.log(data);

    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/box/box",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#idPalco").val("");
            $("#locacion").val("");
            $("#capacidad").val("");
            $("#categoria").val("");
            $("#nombre").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            leerPalcos();
        }
    });
}
function cancelarPalco(idCliente) {

    let data = {
        id: idCliente
    }
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);

    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/box/box",
        type: "DELETE",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {

            alert("Palco cancelado")
            $("#idPalco").val("");
            $("#locacion").val("");
            $("#capacidad").val("");
            $("#categoria").val("");
            $("#nombre").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            leerPalcos();
        }
    });
}

function editarPalco() {
    let idPalco = $("#idPalco").val();
    let locacion = $("#locacion").val();
    let capacidad = $("#capacidad").val();
    let categoria = $("#categoria").val();
    let nombre = $("#nombre").val();

    let data = {
        id: idPalco,
        location: locacion,
        capacity: capacidad,
        category_id: categoria,
        name: nombre
    };
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);
    $.ajax({
        url: "https://g962bbad0e1fc09-minticusa.adb.us-ashburn-1.oraclecloudapps.com/ords/admin/box/box",
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            $("#idPalco").val("");
            $("#locacion").val("");
            $("#capacidad").val("");
            $("#categoria").val("");
            $("#nombre").val("");
        },
        error: function (xhr, status) {
            //     alert('ha sucedido un problema');
        },
        complete: function () {
            leerPalcos();
        }
    });
}
