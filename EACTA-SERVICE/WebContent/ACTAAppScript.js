$(document).ready(function(){

    $.ajax({

        url: '/ACTA-SERVICE/rest/ACTAs',

        type: 'get',

        dataType: 'JSON',

        success: function(response){

            var len = response.length;

            for(var i=0; i<len; i++){

                var email = response[i].emailalumno;

                var coordinador = response[i].emailcoordinador;

                var asignatura = response[i].asignatura;

                var id = response[i].id;

                var notaprovisional = response[i].notaprovisional;

                var tr_str = "<tr>" +

                    "<td align='center'>" + email + "</td>" +

                    "<td align='center'>" + coordinador + "</td>" +

                    "<td align='center'>" + asignatura + "</td>" +

                    "<td align='center'>" + id + "</td>" +

                    "<td align='center'>" + notaprovisional + "</td>" +

                    "<td align='center'>" + advisor + "</td>" +

                    "</tr>";

                $("#userTable tbody").append(tr_str);

            }

        }

    });

});

