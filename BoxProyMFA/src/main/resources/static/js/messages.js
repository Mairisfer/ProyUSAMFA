
function traerMensajes(){
    $.ajax({
        url: "http://129.153.156.239:89/api/Message/all",
        type : "GET",
        dataType : "json",
        success: function (respuesta){
            pintarMensajes(respuesta)
        },
        error: function (respuesta, xhr){
            alert("Lo siento! ha sucedido un error");
        },
    });
}

function pintarMensajes(m){
    $('#tabladivMes *').remove();
    let myTable= '<div class="container"><div class="row">';
    console.log(m);
    for(let i=0;i<m.length;i++){
        myTable+=
            `<div class="card m-2" style="width: 18rem;" >
                <div class="card-body">
                    <h5 class="card-title">Mensaje</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${m[i].messageText}</h6>                              
                    <button class="btn btn-danger" onclick=borrarMensaje(${m[i].idMessage})>Borrar</button>
                    <button class="btn btn-info" onclick=editarMensaje(${JSON.stringify(m[i])})>Editar</button>                    
                    <br/>
                    <!--<a href="#" class="card-link" style="color: darkslategray">Card link</a>-->
                </div>
            </div>`
    }
    myTable += "</div></div>";
    $('#tabladivMes').append(myTable);
}
function guardarMensaje() {
    let messageText = $("#messageText").val();

    let data = {
        messageText: messageText
    }
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Message/save",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#messageText").val();
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function() {
            traerMensajes();
        }
    });
}
function borrarMensaje(idMessage) {
    console.log(idMessage);
    let data = {
        id: idMessage
    }
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);

    $.ajax({
        url: `http://129.153.156.239:89/api/Message/${idMessage}`,
        type: "DELETE",
        //dataType: "JSON",
        //data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {

            alert("Mensaje Eliminado")
            $("#messageText").val();
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            traerMensajes();
        }
    });
}
function actualizarMensaje() {

    let id = $("#idMessage").val();
    let messageText = $("#messageText").val();

    let data = {
        idMessage:id,
        messageText: messageText
    };
    console.log(data);
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Message/update",
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {

            alert("Mensaje Eliminado")
            $("#messageText").val();
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            traerMensajes();
        }
    });
}

function editarMensaje(mje){
    console.log(`este es ${mje}`)
    $("#idMessage").val(mje["idMessage"])
    $("#messageText").val(mje["messageText"]);
}