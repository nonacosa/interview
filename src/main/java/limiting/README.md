### MQ 中间件如何限流

- 改变缓冲流量

MQ-server 推消息改为  MQ-client 拉消息

```java
//rabbitmq

/**
 * Retrieve a message from a queue using {@link com.rabbitmq.client.AMQP.Basic.Get}
 * @see com.rabbitmq.client.AMQP.Basic.Get
 * @see com.rabbitmq.client.AMQP.Basic.GetOk
 * @see com.rabbitmq.client.AMQP.Basic.GetEmpty
 * @param queue the name of the queue
 * @param autoAck true if the server should consider messages
 * acknowledged once delivered; false if the server should expect
 * explicit acknowledgements
 * @return a {@link GetResponse} containing the retrieved message data
 * @throws java.io.IOException if an error is encountered
 */
GetResponse basicGet(String queue, boolean autoAck) throws IOException;

//example
private void consume(Channel channel) throws IOException, InterruptedException {
    while (true) {
        if (!isConditionSatisfied()) {
            TimeUnit.MILLISECONDS.sleep(1);
            continue;
        }
        GetResponse response = channel.basicGet(CAOSH_TEST_QUEUE, false);
        if (response == null) {
            TimeUnit.MILLISECONDS.sleep(1);
            continue;
        }
        String data = new String(response.getBody());
        logger.info("Get message <= {}", data);
        channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
    }
}
```

批量拉消息

RabbitMQ支持客户端批量拉取消息，客户端可以连续调用 basicGet 方法拉取多条消息，处理完成之后一次性ACK

```java
String bridgeQueueName = extractorProperties.getBridgeQueueName();
int batchSize = extractorProperties.getBatchSize();
List<GetResponse> responseList = Lists.newArrayListWithCapacity(batchSize);
long tag = 0;
while (responseList.size() < batchSize) {
    GetResponse getResponse = channel.basicGet(bridgeQueueName, false);
    if (getResponse == null) {
        break;
    }
    responseList.add(getResponse);
    tag = getResponse.getEnvelope().getDeliveryTag();
}
if (responseList.isEmpty()) {
    TimeUnit.MILLISECONDS.sleep(1);
} else {
    logger.info("Get <{}> responses this batch", responseList.size());
    // handle messages
    channel.basicAck(tag, true);
}
```

- 上游流量过大，回不回消息堆积？

下游MQ-client拉取消息，消息接收方能够批量获取消息，需要下游消息接收方进行优化，方能够提升整体吞吐量，例如：批量写、或者增加消费者。
