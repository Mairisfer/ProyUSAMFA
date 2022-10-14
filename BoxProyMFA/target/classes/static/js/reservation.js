function traerReservas(){
    $.ajax({
        url: "http://129.153.156.239:89/api/Reservation/all",
        type : "GET",
        dataType : "json",
        success: function (respuesta){
            pintarReservas(respuesta)
        },
        error: function (respuesta, xhr){
            alert("Lo siento! ha sucedido un error");
        },
    });
}
function pintarReservas(rs){
    $('#tabladivRes *').remove();
    let myTable= '<div class="container"><div class="row">';
    console.log(rs);
    for(let i=0;i<rs.length;i++){
        myTable+=
            `<div class="card m-2" style="width: 18rem;" >
                <div class="card-body">
                    <h5 class="card-title">Reserva confirmada</h5>                    
                    <h6 type="date" class="card-subtitle mb-3">Inicio: ${rs[i].startDate.substr(0,10)}</h6>
                    <p class="card-text">Devolución: ${rs[i].devolutionDate.substr(0,10)}</p>             
                    <button class="btn btn-danger" onclick=borrarRes(${rs[i].idReservation})>Borrar</button>
                    <button class="btn btn-info" onclick=editarRes(${JSON.stringify(rs[i])})>Editar</button>                    
                    <br/>
                    <!--<a href="#" class="card-link" style="color: darkslategray">Card link</a>-->
                </div>
            </div>`
    }
    myTable += "</div></div>";
    $('#tabladivRes').append(myTable);
}
function guardarReserva() {
    let startDate = $("#startDate").val();
    let devolutionDate = $("#devolutionDate").val();

    let data = {
        startDate: startDate,
        devolutionDate: devolutionDate,
    }
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Reservation/save",
        type: "POST",
        //dataType: "JSON",
        data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {
            $("#startDate").val("");
            $("#devolutionDate").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function() {
            traerReservas();
        }
    });
}
function borrarRes(idReservation) {
    console.log(idReservation);
    let data = {
        id: idReservation
    }
    let dataToSend = JSON.stringify(data);
    //console.log(dataToSend);

    $.ajax({
        url: `http://129.153.156.239:89/api/Reservation/${idReservation}`,
        type: "DELETE",
        //dataType: "JSON",
        //data: dataToSend,
        contentType: "application/JSON",

        success: function (pepito) {

            alert("Reserva Cancelada")
            $("#startDate").val("");
            $("#devolutionDate").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            traerReservas();
        }
    });
}
function actualizarReserva() {

    let startDate = $("#startDate").val();
    let devolutionDate = $("#devolutionDate").val();

    let data = {
         startDate: startDate,
         devolutionDate: devolutionDate
    };
    console.log(data);
    let dataToSend = JSON.stringify(data);

    $.ajax({
        url: "http://129.153.156.239:89/api/Reservation/update",
        type: 'PUT',
        //   dataType : 'json',
        data: dataToSend,
        contentType: 'application/json',
        success: function (pepito) {

            alert("Reserva Actualizada")
            $("#startDate").val("");
            $("#devolutionDate").val("");
        },
        error: function (xhr, status) {
            //    alert("Lo siento! ha sucedido un error");
        },
        complete: function () {
            traerReservas();
        }
    });
}

function editarRes(reserva){
    console.log(`esta es la ${reserva}`)
    $("#idReservation").val(reserva["idReservation"]);
    $("#startDate").val(reserva["startDate"].substr(0,10));
    $("#devolutionDate").val(reserva["devolutionDate"].substr(0,10));
}