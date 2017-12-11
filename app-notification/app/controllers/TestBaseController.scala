package controllers

import io.swagger.annotations.Api
import models.Common.buildMongoDbObject
import models.{MongoFactory, Stock}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.mvc.Controller
import com.mongodb.casbah.Imports._
import models.Common._


@Api
class TestBaseController extends Controller {

  val apple = Stock("AAPL", 600)
  val google = Stock("GOOG", 650)
  val netflix = Stock("NFLX", 60)

  def test = Action {
    val mongoObj = buildMongoDbObject(apple)
    MongoFactory.collection.save(mongoObj)
    val mongoObj1 = buildMongoDbObject(google)
    MongoFactory.collection.save(mongoObj1)
    val mongoObj2 = buildMongoDbObject(netflix)
    MongoFactory.collection.save(mongoObj2)
    Ok(Json.toJson("dziala"))
  }

}
