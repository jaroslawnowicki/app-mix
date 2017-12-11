package controllers

import io.swagger.annotations.Api
import play.api.mvc._

@Api
class Application extends Controller {

  def redirectDocs = Action {
    Redirect("/public/assets/lib/swagger-ui/index.html?/url=http://localhost:9000/swagger.json")
  }

//
//  def index = Action {
//    Ok(views.html.index())
//  }


}