package controllers

import javax.inject._

import akka.NotUsed
import akka.actor.ActorSystem
import akka.kafka.{ConsumerSettings, ProducerSettings}
import akka.kafka.scaladsl._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, ByteArraySerializer, StringDeserializer, StringSerializer}
import play.api.libs.EventSource
import play.api.libs.EventSource.Event
import play.api.mvc._
import services.Sender

import scala.concurrent.ExecutionContext


@Singleton
class HomeController @Inject()(webJarAssets: WebJarAssets, sender: Sender)(implicit ec: ExecutionContext) extends Controller {

  implicit val actorSystem = ActorSystem("KafkaStreamDemo")
  implicit val materializer = ActorMaterializer()


  def index = Action {
    Ok("Test")
  }

  def test = Action {
    Ok(views.html.index(webJarAssets))
  }

  def numberStream = Action {
    // generate a stream of numbers as text
    val testSource: Source[String, NotUsed] = Source(1 to 10).map(_.toString)

    // transform the Source stream into a stream of events
    val result: Source[Event, NotUsed] = testSource via EventSource.flow

    Ok.chunked(result)
  }

  def kafkaStream = Action {
    Ok.chunked(openKafkaStream(kafkaConsumerSettings) via EventSource.flow)
  }


  def capitalize = Action(parse.json) { implicit request =>
    val json = request.body.toString()
    sender.send(json)
    Ok
  }

  def openKafkaStream(consumerSettings: ConsumerSettings[Array[Byte], String]) =
    Consumer.plainSource(consumerSettings)
      .map(x => x.value)

  def kafkaProducerSettings: ProducerSettings[Array[Byte], String] =
    ProducerSettings(actorSystem, new ByteArraySerializer, new StringSerializer)
      .withBootstrapServers("localhost:9092")

  def kafkaConsumerSettings: ConsumerSettings[Array[Byte], String] =
    ConsumerSettings(actorSystem, new ByteArrayDeserializer, new StringDeserializer,
      Set("test"))
      .withBootstrapServers("localhost:9092")
      .withGroupId("group1")
      .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
}
