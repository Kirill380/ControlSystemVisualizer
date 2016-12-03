(function ($) {

    $.ajax({
        url: "/signal/input",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
            createTable("#input .plot-table", signalData.coordinates);
            Highcharts.chart('input_signal', {
                title: {
                    text: 'Input signal',
                    x: -20 //center
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
            createTable("#output .plot-table", signalData.coordinates);
            Highcharts.chart('output_signal', {
                title: {
                    text: 'Output signal',
                    x: -20 //center
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


    function createTable(selector, coordinates) {
        var table$ = $(selector);
        table$
        for(var i = 0; i < coordinates.length; i++) {
            var template = "<tr>" +
                                "<td>" + coordinates[i][0].toFixed(3) + "</td>" +
                                "<td>" + coordinates[i][1].toFixed(3) + "</td>" +
                            "</tr>";
            table$.append(template)
        }
    }

})(jQuery);