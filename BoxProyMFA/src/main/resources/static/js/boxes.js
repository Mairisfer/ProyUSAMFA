
function traerPalcos(){
    $.ajax({
        url: "http://129.153.156.239:89/api/Box/all",
        type : "GET",
        dataType : "json",
        success: function (respuesta){
            pintarPalcos(respuesta)
        },
        error: function (respuesta, xhr){
            alert("Lo siento! ha sucedido un error");
        },
    });
}

function pintarPalcos(p){
    $('#tabladivPal *').remove();
    let myTable= '<div class="container"><div class="row">';
    console.log(p);
    for(let i=0;i<p.length;i++){
        myTable+=
            `<div class="card m-2" style="width: 18rem;" >
                <div class="card-body">
                    <h5 class="card-title">${p[i].name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${p[i].location}</h6>
                    <p class="card-text">${p[i].capacity}</p>             
                    <button class="btn btn-danger" onclick=borrarPalco(${p[i].id})>Borrar</button>
                    <button class="btn btn-info" onclick=editarPalco(${JSON.stringify(p[i])})>Editar</button>                    
                    <br/>
                    <!--<a href="#" class="card-link" style="color: darkslategray">Card link</a>-->
                </div>
            </div>`
    }
    myTable += "</div></div>";
    $('#tabladivPal').append(myTable);
}
function guardarPalco() {
    let name = $("#name").val();
    let location = $("#location").val();
    let capacity = $("#capacity").val();
    let description = $("#description").val();

    let data = {
        name: name,
        location: location,
        capacity: capacity,
        description: description
    }
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Box/save",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#name").val("");
            $("#location").val("");
            $("#capacity").val("");
            $("#description").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function() {
            traerPalcos();
        }
    });
}
function borrarPalco(id) {
    console.log(id);
    let data = {
        id: id
    }
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);

    $.ajax({
        url: `http://129.153.156.239:89/api/Box/${id}`,
        type: "DELETE",
        //dataType: "JSON",
        //data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {

            alert("Palco Eliminado")
            $("#name").val("");
            $("#location").val("");
            $("#capacity").val("");
            $("#description").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            traerPalcos();
        }
    });
}
function actualizarPalco() {

    let id = $("#id").val();
    let name = $("#name").val();
    let location = $("#location").val();
    let capacity = $("#capacity").val();
    let description = $("#description").val();

    let data = {
        id:id,
        name: name,
        location: location,
        capacity: capacity,
        description: description
    };
    console.log(data);
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Box/update",
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {
            $("#name").val("");
            $("#location").val("");
            $("#capacity").val("");
            $("#description").val("");
        },
        error: function (xhr, status) {
           // alert('ha sucedido un problema');
        },
        complete: function () {
            traerPalcos();
        }
    });
}

function editarPalco(palco){
    console.log(`este es el ${palco}`)
    $("#id").val(palco["id"])
    $("#name").val(palco["name"])
    $("#location").val(palco["location"]);
    $("#capacity").val(palco["capacity"]);
    $("#age").val(palco["age"]);
    $("#description").val(palco["description"]);
}