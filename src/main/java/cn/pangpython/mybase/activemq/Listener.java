/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.pangpython.mybase.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

class Listener {
    public static final String QueueName = "MessageQueue";

    public static void main(String[] args) throws JMSException
    {
        //1. 创建ConnctionFactroy 工厂对象，需要用户名 密码 链接的地址
        ConnectionFactory facotry = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        //2. 通过ConnectionFactory工厂对象创建Connection,并且调用start()方法开启连接，默认是关闭状态
        Connection connection = facotry.createConnection();
        connection.start();
        //3. 通过Connection创建会话，用于发送、接收消息。args0(是否开启事务) args1(签收的模式)
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        //4 .创建Destination 对象, 生产消息目标或消费消息来源的的对象，在PTP中是Queue对象，在Pusb/Sec 是Topic对象
        Destination destination = session.createQueue(QueueName);
        //⑤ 通过Session创建生产者或者消费者
        MessageProducer producer = session.createProducer(destination);
        //⑥ 设置消息的持久化或非持久化 持久化方式（KaHaDB JDBC[MYSQL ORACLE] ）
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //⑦ 创建消息
        TextMessage message = session.createTextMessage();
        message.setText("hello ActiveMQ");
        int i = 0;
        while (i < 7)
        {
            producer.send(message);
            i++;
        }

        if (connection != null)
        {
            connection.close();
        }
    }
}