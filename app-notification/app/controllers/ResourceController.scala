package controllers

import io.swagger.annotations.Api
import play.api.libs.json.Writes
import play.api.mvc._
import play.api.libs.json._


object Resource {
  implicit val resourceWrites: Writes[Resource] = Json.writes[Resource]
  implicit val resourceReads: Reads[Resource] = Json.reads[Resource]
}
case class Resource(name: String, available: Boolean)

@Api
class ResourceController extends Controller {

  def availableResources = Action {
    Ok(Json.toJson(Resource("Printer", true)))
  }
}
