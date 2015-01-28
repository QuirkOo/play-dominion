package controllers

import model.game.GameEngine
import play.api.libs.iteratee.{Concurrent, Iteratee}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def index = Action {
    Ok(views.html.index.render())
  }

  def websocket =  WebSocket.using[String] { request =>

    val (out,channel) = Concurrent.broadcast[String]

    GameEngine.AddPlayer(channel)

    val in = Iteratee.foreach[String] {
      msg => println(msg)
        channel push("Got message: " + msg)
    }
    (in,out)
  }

}