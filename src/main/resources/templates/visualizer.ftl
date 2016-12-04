<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Visualizer</title>
    <link rel="stylesheet" href="/vendor/normalize.css">
    <link rel="stylesheet" href="/vendor/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/project/css/visualizer.css">
</head>
<body>

<div class="visualizer-container">
    <div class="plot-container">
        <div id="input" class="plot-container__item">
            <div class="item">
                <div class="item__plot">
                    <div id="input_signal" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                </div>
                <div class="item__plot-table">
                    <table class="table table-bordered">
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
                    <table class="table table-bordered">
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
        <form class="jsForm" action="/calculate" method="POST">
            <div class="form-group">
                <label for="func">Input fucntion</label>
                <select class="form-control" name="function" id="func">
                    <option value="sin">sin(t)</option>
                    <option value="cos">cos(t)</option>
                    <option value="exp">exp(t)</option>
                </select>
            </div>
            <div class="form-group">
                <label for="k1">k1</label>
                <input type="number" step="any" class="form-control" id="k1">
            </div>
            <div class="form-group">
                <label for="k2">k2</label>
                <input type="number" step="any" class="form-control" id="k2">
            </div>
            <div class="form-group">
                <label for="k3">k3</label>
                <input type="number" step="any" class="form-control" id="k3">
            </div>
            <div class="form-group">
                <label for="T">T</label>
                <input type="number" step="any" class="form-control" id="T">
            </div>
            <div class="form-group">
                <label for="ksi">ksi</label>
                <input type="number" step="any" class="form-control" id="ksi">
            </div>
            <div class="form-group">
                <label for="from">from</label>
                <input type="number" class="form-control" id="from">
            </div>

            <div class="form-group">
                <label for="to">to</label>
                <input type="number" class="form-control" id="to">
            </div>

            <div class="form-group">
                <label for="step">step</label>
                <input type="number" step="any" class="form-control" id="step">
            </div>

        </form>
    </div>
    <div class="clear-fix"></div>
    <div class="controls">
        <button type="button" class="jsCalculate btn btn-default">Calculate</button>
        <button type="button" class="jsClear btn btn-default">Clear</button>
    </div>

</div>


<script src="/vendor/jquery-3.1.1.min.js"></script>
<script src="/vendor/table-scroll.js"></script>
<script src="/vendor/highcharts/highcharts.js"></script>
<script src="/vendor/highcharts/modules/exporting.js"></script>
<script src="/project/js/visualizer.js"></script>

</body>
</html>