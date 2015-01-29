package model.game

import play.api.libs.iteratee.Concurrent


/**
 * Created by yorg on 28.01.15.
 */
class Player(out: Concurrent.Channel[String]) {

  val channel: Concurrent.Channel[String] = out

  var actions: Int = 0
  var buys: Int = 0
  var gold: Int = 0
  var rejects: Int = 0
  var throws: Int = 0

  private var hand: List[Card] = List[Card] ()
  private var deck: List[Card] = List[Card] ()
  private var used: List[Card] = List[Card] ()


  def addCard(cardName: String) = {
      deck = deck ::: List[Card] (Card(cardName))
  }

  def newHand() = {
    hand = randomSelect(5, deck)
    deck = deck diff hand
  }

  def getState: String = {

    var playerState = ("{'actions': " + i2s(actions) + ", 'buys': " + i2s(buys) + ", 'gold': " + i2s(gold) + ", 'rejects': " + i2s(rejects) + ", 'throws': " + i2s(throws) + ", 'hand': " + getHandJSON + "}").replace("'", "\"")
    var tableState: String = Table.getTableState
    "{" + "\"" + "state" + "\"" + ":" + playerState + ", " + "\"table" + "\"" + ":" + tableState + "}"
  }

  def getEmptyState: String = {

    var playerState = ("{'actions': " + i2s(0) + ", 'buys': " + i2s(0) + ", 'gold': " + i2s(0) + ", 'rejects': " + i2s(0) + ", 'throws': " + i2s(0) + ", 'hand': " + getHandJSON + "}").replace("'", "\"")
    var tableState: String = Table.getTableState
    "{" + "\"" + "state" + "\"" + ":" + playerState + ", " + "\"table" + "\"" + ":" + tableState + "}"
  }

  private def getHandJSON: String = {
    "[" + hand.dropRight(1).map("'" + _.name + "'" + ", ").reduce((a: String, b:String) => a + b) + "'" + hand.last.name + "'" + "]"
  }

  private def i2s(int: Int): String = {
    java.lang.Integer.toString(int)
  }

  def removeAt[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case (Nil, _) if n < 0 => throw new NoSuchElementException
    case (pre, e :: post)  => (pre ::: post, e)
    case (pre, Nil)        => throw new NoSuchElementException
  }

  def randomSelect[A](n: Int, ls: List[A]): List[A] = {
    def randomSelectR(n: Int, ls: List[A], r: util.Random): List[A] =
      if (n <= 0) Nil
      else {
        val (rest, e) = removeAt(r.nextInt(ls.length), ls)
        e :: randomSelectR(n - 1, rest, r)
      }
    randomSelectR(n, ls, new util.Random)
  }

  def countGold: Int = {
    def countGoldR(handy: List[Card], n: Int) : Int = {
      handy match {
        case Nil => n
        case head :: tail =>
          head match {
            case Gold(_, toAdd) => countGoldR(tail, n + toAdd )
            case _ => countGoldR(tail, n)
          }
      }
    }
    countGoldR(hand, 0)
  }

  def setInitialState() = {
    actions = 1
    buys = 1
    gold = countGold
    rejects = 0
    throws = 0
  }

  def pullCards(n: Int) = {
    val pulled = randomSelect(n, deck)
    hand = hand ::: pulled
    deck = deck diff pulled
  }

  def yourMove() = {
    newHand()
    setInitialState()
    sendToBrowser()
  }

  def notYourMove() = {
    channel push getEmptyState
  }

  def sendToBrowser() = {
    channel push getState
  }

}

object Player {

  def apply(out: Concurrent.Channel[String]): Player = {
    val player: Player = new Player(out)
    player.actions = 2
    player.buys = 1

    for(i <- 1 to 7) {
      player.addCard("grosz")
    }
    for(i <- 1 to 3) {
      player.addCard("domostwo")
    }

    player
  }

}