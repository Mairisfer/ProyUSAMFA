
function traerDatos(){
    $.ajax({
        url: "http://129.153.156.239:89/api/Client/all",
        type : "GET",
        dataType : "json",
        success: function (respuesta){
            pintarClientes(respuesta)
        },
        error: function (respuesta, xhr){
            alert("Lo siento! ha sucedido un error");
        },
    });
}

function pintarClientes(cs){
    $('#tabladiv *').remove();
    let myTable= '<div class="container"><div class="row">';
    console.log(cs);
    for(let i=0;i<cs.length;i++){
        myTable+=
            `<div class="card m-2" style="width: 18rem;" >
                <div class="card-body">
                    <h5 class="card-title">${cs[i].name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${cs[i].email}</h6>
                    <p class="card-text">${cs[i].age}</p>             
                    <button class="btn btn-danger" onclick=borrarCliente(${cs[i].idClient})>Borrar</button>
                    <button class="btn btn-info" onclick=editarCliente(${JSON.stringify(cs[i])})>Editar</button>                    
                    <br/>
                    <!--<a href="#" class="card-link" style="color: darkslategray">Card link</a>-->
                </div>
            </div>`
    }
    myTable += "</div></div>";
    $('#tabladiv').append(myTable);
}
function guardarCliente() {
    let name = $("#name").val();
    let email = $("#email").val();
    let age = $("#age").val();
    let password = $("#password").val();

    let data = {
        name: name,
        email: email,
        age: age,
        password: password
    }
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Client/save",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#name").val("");
            $("#email").val("");
            $("#age").val("");
            $("#password").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function() {
            traerDatos();
        }
    });
}
function borrarCliente(idClient) {
    console.log(idClient);
    let data = {
        id: idClient
    }
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);

    $.ajax({
        url: `http://129.153.156.239:89/api/Client/${idClient}`,
        type: "DELETE",
        //dataType: "JSON",
        //data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {

            alert("Cliente Eliminado")
            $("#name").val("");
            $("#email").val("");
            $("#age").val("");
            $("#password").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            traerDatos();
        }
    });
}
function actualizarCliente() {

    let id = $("#idClient").val();
    let name = $("#name").val();
    let email = $("#email").val();
    let age = $("#age").val();
    let password = $("#password").val();

    let data = {
        idClient:id,
        name: name,
        email: email,
        age: age,
        password: password
    };
    console.log(data);
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Client/update",
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            $("#name").val("");
            $("#email").val("");
            $("#age").val("");
            $("#password").val("");
        },
        error: function (xhr, status) {
           // alert('ha sucedido un problema');
        },
        complete: function () {
            traerDatos();
        }
    });
}

function editarCliente(cliente){
    console.log(`este es ${cliente}`)
    $("#idClient").val(cliente["idClient"])
    $("#name").val(cliente["name"]);
    $("#email").val(cliente["email"]);
    $("#age").val(cliente["age"]);
    $("#password").val(cliente["password"]);
}