
var date = new Date();

$(document).ready(function () {

    $('#calendar').fullCalendar({
        dayClick: function (current_date, jsEvent, view) {
            current_date = new Date(current_date.toString()); // Ex: Sun Dec 11 2016 07:00:00 GMT+0300 (AST)
            //alert(current_date);
            //Open visits modal
            visitDate = current_date.getFullYear() + '-' + (current_date.getMonth() + 1) + '-' + current_date.getDate();
            visitTime = current_date.getHours() + ':' + current_date.getMinutes();
            if (current_date.getMinutes() == 0)
                visitTime = current_date.getHours() + ':' + current_date.getMinutes() + '0';
            /*
             *	get all tehnician from java Resful (JAX-RS) web resvice
             */
            $('.addVisitDate').html(visitDate);
            $('.visitTime').html(visitTime);
            $("#addVisit").modal();

        },
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        firstDay: new Date().getDay(),
        defaultView: 'agendaWeek',
        defaultDate: date,
        timezone: 'local',
        navLinks: true, // can click day/week names to navigate views
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        minTime: '12:00:00',
        maxTime: '16:30:00',
        events: [
            {
                title: 'All Day Event',
                start: '2016-12-09'
            },
            {
                title: 'Birthday Party',
                start: '2016-12-13T07:00:00'
            }
        ]
    });

    $.ajax({
        method: 'GET',
        url: 'http://localhost:8080/visitsScheduler/api/technicians/getAll',
        success: function (result) {
//                alert("Result is: " + result.name);
            technicians = result.name;
            techniciansIds = result.id;
            // poulate technician list
            for (i = 0; i < technicians.length; i++) {
                $('#technicianList').append("<option value=\"" + techniciansIds[i] + "\"> " + technicians[i] + "</option>");
            }
        }
    });


}); // End jQuery ready function