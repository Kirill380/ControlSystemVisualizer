<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Visualizer</title>
    <link rel="stylesheet" href="/vendor/normalize.css">
    <link rel="stylesheet" href="/project/css/visualizer.css">
</head>
<body>

<div class="plot-container">
    <div id="input" class="plot-container__item">
        <div class="item">
            <div class="item__plot">
                <div id="input_signal" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
            </div>
            <div class="item__plot-table">
                <table class="plot-table">
                    <tr>
                        <th>t</th>
                        <th>y(t)</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div id="output" class="plot-container__item">
        <div class="item">
            <div class="item__plot">
                <div id="output_signal" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
            </div>
            <div class="item__plot-table">
                <table class="plot-table">
                    <tr>
                        <th>t</th>
                        <th>y(t)</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="input-parameters">
    <form action="">

    </form>
</div>
<div class="controls">

</div>


<script src="/vendor/jquery-3.1.1.min.js"></script>
<script src="/vendor/table-scroll.js"></script>
<script src="/vendor/highcharts/highcharts.js"></script>
<script src="/vendor/highcharts/modules/exporting.js"></script>
<script src="/project/js/visualizer.js"></script>

</body>
</html>