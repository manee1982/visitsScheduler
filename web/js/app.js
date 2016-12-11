
var date = new Date();

$(document).ready(function () {

    // Get the host name instead of localhost
    var host = window.location.host;

    $('#calendar').fullCalendar({
        dayClick: function (current_date, jsEvent, view) {
            current_date = new Date(current_date.toString()); // Ex: Sun Dec 11 2016 07:00:00 GMT+0300 (AST)
            //alert(current_date);
            //Open visits modal
            visitDate = current_date.getFullYear() + '-' + (current_date.getMonth() + 1) + '-' + current_date.getDate();
            visitTime = current_date.getHours() + ' : ' + current_date.getMinutes();
            if (current_date.getMinutes() == 0)
                visitTime = current_date.getHours() + ':' + current_date.getMinutes() + '0';
            /*
             *	get all tehnician from java Resful (JAX-RS) web resvice
             */
            $('.addVisitDate').val(visitDate);
            $('.visitTime').val(visitTime);
            $('#visitEndtime').val("");
            $('#description').val("");
            $('#div-message').remove();
            $("#addVisit").modal({
                backdrop: 'static',
                keyboard: false
            });

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
        events: 'http://' + host + '/visitsScheduler/api/scheduleVisit/getAll'
    });

    $.ajax({
        method: 'GET',
        url: 'http://' + host + '/visitsScheduler/api/technicians/getAll',
        success: function (result) {
//                alert("Result is: " + result.name);
            technicians = result.name;
            techniciansIds = result.id;
            // poulate technician list
            for (i = 0; i < technicians.length; i++) {
                $('#technician').append("<option value=\"" + techniciansIds[i] + "\"> " + technicians[i] + "</option>");
            }
        }
    });

    $('#visitEndtime').click(function () {
        $('.time-picker').fadeIn(500);
    });

    $('.time-picker ul li, #close, .close').click(function () {

        var visitStartTime = $('#visitStartTime').val().substring(0, 2);
        var visitEndTime = $(this).html();
        var visitEndTimeAfterCut = $(this).html().substring(0, 2);
        checkContains = visitEndTime.indexOf("30");
        if ((visitStartTime <= visitEndTimeAfterCut) && visitEndTime !== "Close" && visitEndTime !== '<span aria-hidden="true">×</span>') {
            if ((visitStartTime == visitEndTimeAfterCut) && checkContains === -1) {
                alert("Can't start and end in the same time");
            } else {
                $('#visitEndtime').val(visitEndTime);
            }
        } else {
            if (visitEndTime != "Close" && visitEndTime !== '<span aria-hidden="true">×</span>')
                alert("Please pick time above to Start Time value");
        }
        $('.time-picker').fadeOut(500);
    });

    $('#scheduleVisit').click(function () {
        // alert(calculateWorkLoadPercentage());
        // send visit scheduling to server
        addVisitDate = $('#addVisitDate').val();
        visitStartTime = $('#visitStartTime').val().replace(/ /g, '');
        alert(visitStartTime);
        visitEndtime = $('#visitEndtime').val();
        if (visitEndtime == "") {
            alert("Error: Please be sure to inter all data required");
            return;
        }
        description = $('#description').val();
        if (description == "") {
            alert("Error: Please be sure to inter all data required");
            return;
        }

        technician = $('#technician').val();
        percentage = calculateWorkLoadPercentage();
        $.ajax({
            method: 'POST',
            url: 'http://' + host + '/visitsScheduler/api/scheduleVisit/scheduleTechnicianVisit',
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                visitDate: addVisitDate,
                visitStartTime: visitStartTime,
                visitEndtime: visitEndtime,
                description: description,
                technician: technician,
                percentage: percentage
            }),
            success: function (result) {
//                alert(result.toSource());
                $('.form-message').append("<div id='div-message' class='col-md-12'><p style='padding:4px' class='bg-success'>Visit successfully scheduled</p></div>");
            },
            error: function (error) {
//                alert("Error" + error.toSource);
                alert("Error: Please be sure to inter all data required");
            }
        });
    });


    // Calculate work load percentage
    function calculateWorkLoadPercentage() {

        visitStartTime = $('#visitStartTime').val();
        visitEndtime = $('#visitEndtime').val();

        isTimeHasHaveHour = visitEndtime.indexOf("30");
        startTime = visitStartTime.substring(0, 2);
        endTime = visitEndtime.substring(0, 2);
//        alert(endTime);
        range = endTime - startTime;
        rangeWithHaveHour = (parseInt(endTime) + 0.5 - parseInt(startTime));
//        alert("rangeWithHaveHour: " + rangeWithHaveHour);
        switch (isTimeHasHaveHour) {
            case -1:
                {
                    switch (range) {
                        case 1:
                            return 25;
                        case 2:
                            return 50;
                        case 3:
                            return 75;
                        case 4:
                            return 100;
                    }
                }
                break;
            case 3:
                {
                    switch (rangeWithHaveHour) {
                        case 0.5:
                            return 15;
                        case 1.5:
                            return 40;
                        case 2.5:
                            return 65;
                        case 3.5:
                            return 90;
                    }
                }
                break;
        }
        return null;
    } // End calculate function

}); // End jQuery ready function