package services

import javax.inject.Inject

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class Sender @Inject() (configuration: play.api.Configuration) {

  def send(json: String) = {
    val producer = new KafkaProducer[Integer,String](config())
    var record = new ProducerRecord[Integer, String]("test", 1, json)
    producer.send(record)
  }

  def config() = {
    val props = new java.util.Properties()
    props.put("bootstrap.servers", configuration.getString("bootstrap.servers").get)
    props.put("client.id", configuration.getString("client.id").get)
    props.put("key.serializer", configuration.getString("key.serializer").get)
    props.put("value.serializer", configuration.getString("value.serializer").get)
    props
  }
}

