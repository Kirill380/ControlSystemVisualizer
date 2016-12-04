(function ($) {

    $.ajax({
        url: "/signal/input",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
            createTable("#input .table", signalData.coordinates);
            Highcharts.chart('input_signal', {
                title: {
                    text: 'Input signal',
                    x: -20 //center
                },
                credits: {
                    enabled: false
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    data: signalData.coordinates
                }]
            });
        }
    });

    $.ajax({
        url: "/signal/output",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
            createTable("#output .table", signalData.coordinates);
            Highcharts.chart('output_signal', {
                title: {
                    text: 'Output signal',
                    x: -20 //center
                },
                credits: {
                    enabled: false
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    data: signalData.coordinates
                }]
            });
        }
    });


    $(".jsForm").on("submit", function (e) {
        e.preventDefault();
        var $f= $(this);
        var serializeArray = $f.serializeArray();
        var params = {};

        for(var i =0; i < serializeArray.length; i++) {
            var field = serializeArray[i];
            params[field.name] = field.value;
        }

        $.ajax({
            url: "/calculate",
            method: "POST",
            contentType: "application/json",
            data : JSON.stringify(params),
            success: function (data) {
                console.log("success");
                setTimeout(function () {
                    location.reload();
                }, 2000)
            }
        });
    });


    $(".jsCalculate").on("click", function () {
        $(".jsSubmit").click();
    });

    function createTable(selector, coordinates) {
        var table$ = $(selector);
        for(var i = 0; i < coordinates.length; i++) {
            var template = "<tr>" +
                                "<td>" + coordinates[i][0].toFixed(3) + "</td>" +
                                "<td>" + coordinates[i][1].toFixed(3) + "</td>" +
                            "</tr>";
            table$.append(template)
        }
    }

})(jQuery);