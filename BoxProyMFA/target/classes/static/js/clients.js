
function traerDatos(){
    $.ajax({
        url: "http://localhost:8089/api/Client/all",
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
    let myTable= '<div class="container"><div class="row">';
    console.log(cs);
    for(i=0;i<cs.length;i++){
        myTable+=
            `<div class="card m-2" style="width: 18rem;" >
                <div class="card-body">
                    <h5 class="card-title">${cs[i].name}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${cs[i].email}</h6>
                    <p class="card-text">${cs[i].age}</p>             
                    <button class="btn btn-danger" onclick="borrarCliente(${cs[i].id})">Borrar</button>
                    <br/>
                    <!--<a href="#" class="card-link" style="color: darkslategray">Card link</a>-->
                </div>
            </div>`
    }
    myTable += "</div></div>";
    $('#tabla').append(myTable);
}