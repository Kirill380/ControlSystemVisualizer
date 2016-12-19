(function ($) {

    Highcharts.theme = {
        colors: ['#2b908f', '#90ee7e', '#f45b5b', '#7798BF', '#aaeeee', '#ff0066', '#eeaaee',
            '#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
        chart: {
            backgroundColor: {
                linearGradient: {x1: 0, y1: 0, x2: 1, y2: 1},
                stops: [
                    [0, '#2a2a2b'],
                    [1, '#3e3e40']
                ]
            },
            style: {
                fontFamily: '\'Unica One\', sans-serif'
            },
            plotBorderColor: '#606063'
        },
        title: {
            style: {
                color: '#E0E0E3',
                textTransform: 'uppercase',
                fontSize: '20px'
            }
        },
        subtitle: {
            style: {
                color: '#E0E0E3',
                textTransform: 'uppercase'
            }
        },
        xAxis: {
            gridLineColor: '#707073',
            labels: {
                style: {
                    color: '#E0E0E3'
                }
            },
            lineColor: '#707073',
            minorGridLineColor: '#505053',
            tickColor: '#707073',
            title: {
                style: {
                    color: '#A0A0A3'

                }
            }
        },
        yAxis: {
            gridLineColor: '#707073',
            labels: {
                style: {
                    color: '#E0E0E3'
                }
            },
            lineColor: '#707073',
            minorGridLineColor: '#505053',
            tickColor: '#707073',
            tickWidth: 1,
            title: {
                style: {
                    color: '#A0A0A3'
                }
            }
        },
        tooltip: {
            backgroundColor: 'rgba(0, 0, 0, 0.85)',
            style: {
                color: '#F0F0F0'
            }
        },
        plotOptions: {
            series: {
                dataLabels: {
                    color: '#B0B0B3'
                },
                marker: {
                    lineColor: '#333'
                }
            },
            boxplot: {
                fillColor: '#505053'
            },
            candlestick: {
                lineColor: 'white'
            },
            errorbar: {
                color: 'white'
            }
        },
        legend: {
            itemStyle: {
                color: '#E0E0E3'
            },
            itemHoverStyle: {
                color: '#FFF'
            },
            itemHiddenStyle: {
                color: '#606063'
            }
        },
        credits: {
            style: {
                color: '#666'
            }
        },
        labels: {
            style: {
                color: '#707073'
            }
        },

        drilldown: {
            activeAxisLabelStyle: {
                color: '#F0F0F3'
            },
            activeDataLabelStyle: {
                color: '#F0F0F3'
            }
        },

        navigation: {
            buttonOptions: {
                symbolStroke: '#DDDDDD',
                theme: {
                    fill: '#505053'
                }
            }
        },

        // scroll charts
        rangeSelector: {
            buttonTheme: {
                fill: '#505053',
                stroke: '#000000',
                style: {
                    color: '#CCC'
                },
                states: {
                    hover: {
                        fill: '#707073',
                        stroke: '#000000',
                        style: {
                            color: 'white'
                        }
                    },
                    select: {
                        fill: '#000003',
                        stroke: '#000000',
                        style: {
                            color: 'white'
                        }
                    }
                }
            },
            inputBoxBorderColor: '#505053',
            inputStyle: {
                backgroundColor: '#333',
                color: 'silver'
            },
            labelStyle: {
                color: 'silver'
            }
        },

        navigator: {
            handles: {
                backgroundColor: '#666',
                borderColor: '#AAA'
            },
            outlineColor: '#CCC',
            maskFill: 'rgba(255,255,255,0.1)',
            series: {
                color: '#7798BF',
                lineColor: '#A6C7ED'
            },
            xAxis: {
                gridLineColor: '#505053'
            }
        },

        scrollbar: {
            barBackgroundColor: '#808083',
            barBorderColor: '#808083',
            buttonArrowColor: '#CCC',
            buttonBackgroundColor: '#606063',
            buttonBorderColor: '#606063',
            rifleColor: '#FFF',
            trackBackgroundColor: '#404043',
            trackBorderColor: '#404043'
        },

        // special colors for some of the
        legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
        background2: '#505053',
        dataLabelsColor: '#B0B0B3',
        textColor: '#C0C0C0',
        contrastTextColor: '#F0F0F3',
        maskColor: 'rgba(255,255,255,0.3)'
    };

    Highcharts.setOptions(Highcharts.theme);

    $.ajax({
        url: "/signal/parameters",
        method: "GET",
        dataType: "json",
        success: function (parameters) {
            var form = $(".jsForm");

            for (var name in parameters) {
                if (name == "function") {
                    form.find("select[name=function]").val(parameters[name]);
                } else {
                    form.find("input[name=" + name + "]").val(parameters[name]);
                }
            }

        }
    });


    $.ajax({
        url: "/signal/input",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
            createTable("#input .table", signalData.coordinates);
            drawPlot('input_signal', 'Input signal', signalData.coordinates);
        }
    });

    $.ajax({
        url: "/signal/output",
        method: "GET",
        dataType: "json",
        success: function (signalData) {
            createTable("#output .table", signalData.coordinates);
            drawPlot('output_signal', 'Output signal', signalData.coordinates);
        }
    });


    $(".jsForm").on("submit", function (e) {
        e.preventDefault();
        var $f = $(this);
        var serializeArray = $f.serializeArray();
        var params = {};

        for (var i = 0; i < serializeArray.length; i++) {
            var field = serializeArray[i];
            params[field.name] = field.value;
        }

        $.ajax({
            url: "/calculate",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(params),
            success: function (data) {
                console.log("success");
                setTimeout(function () {
                    location.reload();
                }, 1000)
            },
            error: function (data) {
                alert(data.responseText);
            }
        });
    });


    $(".jsCalculate").on("click", function () {
        $(".jsSubmit").click();
    });

    $(".jsDefault").on("click", function () {
        $.ajax({
            url: "/signal/default",
            method: "POST",
            success: function (data) {
                console.log("success");
                setTimeout(function () {
                    location.reload();
                }, 1000)
            }
        });
    });

    function createTable(selector, coordinates) {
        var table$ = $(selector);
        for (var i = 0; i < coordinates.length; i++) {
            var template = "<tr>" +
                "<td>" + coordinates[i][0].toFixed(3) + "</td>" +
                "<td>" + coordinates[i][1].toFixed(3) + "</td>" +
                "</tr>";
            table$.append(template)
        }
    }

    function drawPlot(selector, title, coordinates) {
        Highcharts.chart(selector, {
            title: {
                text: title,
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
                data: coordinates
            }]
        });
    }

})(jQuery);