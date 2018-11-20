package com.gravel.sbkw.commons;

/**
 * Kafka 和 WebSocket 监听发送主Topic 枚举类
 *
 * @author Gravel
 * @date 2018/11/20.
 */
public enum TopicEnums {

    KAFKA_LISTENER_TOPIC(Constants.KAFKA_LISTENER_TOPIC, "kafka 消费者监听主题"),
    WEBSOCKET_CHANNEL(Constants.WEBSOCKET_CHANNEL, "页面 WebSocket 监听通道也就是发送目标");

    private String topic;
    private String desc;

    TopicEnums(String topic, String desc) {
        this.setTopic(topic);
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class Constants {
        public static final String KAFKA_LISTENER_TOPIC = "public-topic";
        public static final String WEBSOCKET_CHANNEL = "/topic/getMessage";
    }

}
