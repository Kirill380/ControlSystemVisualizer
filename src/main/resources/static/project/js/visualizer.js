(function ($) {

    $.ajax({
        url: "/signal/input",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
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
                    data: signalData.dots
                }]
            });
        }
    });

    $.ajax({
        url: "/signal/output",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
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
                    data: signalData.dots
                }]
            });
        }
    });

})(jQuery);