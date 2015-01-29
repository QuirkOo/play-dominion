package model.game

import play.api.libs.iteratee.Concurrent

import scala.collection.mutable

/**
 * Created by yorg on 28.01.15.
 */
class GameEngine {
}

object GameEngine {

  val numPlayers: Int = 3

  val players: mutable.Map[Int, Player] = mutable.Map[Int, Player] ()

  var count = 1

  def startGame() = {
    count = 0
    nextTurn()
  }

  def AddPlayer(channel: Concurrent.Channel[String]): Unit = {
    players(count) = Player(channel)
    count += 1

    if( count == numPlayers + 1 ) {
      startGame()
    }
  }

  def nextTurn() = {
    count = (count % numPlayers) + 1

    for(i <- 1 to numPlayers)
      if(i == count) players(count).yourMove()
      else players(i).notYourMove()

  }

  def handleMessage(msg: String) = {
    if( msg.replace(" ", "").contains("\"action\":\"buy\"") ) {
      var cardName: String = msg.replace(" ", "").split(",")(1)
      cardName = cardName.replace("\"", "").split(":")(1)
      players(count)
    }
  }

}
