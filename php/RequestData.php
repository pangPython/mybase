<?php
/**
 *  发送socket请求
 */
class RequestData
{
  private $ip = '127.0.0.1';
  private $port;

  public function __construct($port,$ip)
  {
    $this->port = $port;
    $this->ip = $ip;
  }
/* 发送数据到服务器 */
  public function send($sendData)
  {
    //创建socket
    $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    socket_connect($socket, $this->ip, $this->port);
    //要发送的内容
    echo 'send data:'.$sendData.' to server.<br>';
    //发送给服务器数据
    socket_write($socket, $sendData, strlen($sendData));
    //接受服务器返回的数据
    $rec = '';
    $receive = '';
    echo "接受服务器返回的数据";
    while($receive = socket_read($socket, 1024))
    {
      //echo '=='.$receive.'==';
      $rec .= $receive;
    }
    // $rec = socket_read($socket,1024);
    //echo $rec;
    socket_close($socket);
    return $rec;
  }
}
