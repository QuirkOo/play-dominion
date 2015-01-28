package controllers

import play.api.libs.iteratee.{Concurrent, Iteratee}
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index.render("Your new application is ready, biatch."))
  }

  def websocket =  WebSocket.using[String] { request =>

    val (out,channel) = Concurrent.broadcast[String]

    val in = Iteratee.foreach[String] {
      msg => println(msg)
        channel push("Got message: " + msg)
    }
    (in,out)
  }

}