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
$socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
//socket_set_option($socket, SOL_SOCKET, SO_SNDTIMEO, array("sec"=>20, "usec"=>0));
socket_connect($socket, '127.0.0.1', 8089);
//里面的换行代表 \r\n 注意拷贝的代码后面可能有空格
$send = <<<EOF
userid=1\r\n
EOF;
echo "send data:'userid=1' to server.<br>";
socket_write($socket, $send, strlen($send));
while($str = socket_read($socket, 1024))
{
  echo 'receive:<br>';
  echo $str;
}
socket_close($socket);
