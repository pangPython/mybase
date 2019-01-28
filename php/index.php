<?php
set_time_limit(0);
require 'RequestData.php';
 ?>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>

  </body>
</html>
<?php
$request = new RequestData(8089,'127.0.0.1');
$data = <<<EOF
userid=1\n
EOF;

$rec = $request->send($data);
echo $rec;
