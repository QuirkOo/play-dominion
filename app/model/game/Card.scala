package model.game

import model.game.AffectType.AffectType

/**
 * Created by yorg on 28.01.15.
 */

abstract class Card(cardName: String) { val name: String = cardName }

case class Regular(cardName: String, affectType: AffectType, cardsMod: Int, actionsMod: Int, buysMod: Int, goldMod: Int,
                    rejectsMod: Int, throwsMod: Int) extends Card(cardName) {

  def play(player: Player) = {

  }

}

case class Gold(cardName: String, _amount: Int) extends Card(cardName) { val amount: Int = _amount }
case class Victory(cardName: String, amountOfPoints: Int) extends Card(cardName) { val amount: Int = amountOfPoints }

object Card {

  def apply(cardName: String) = {

    val cardToReturn: Card = cardName match {

      case "grosz" => new Gold(cardName, 1)
      case "srebrnik" => new Gold(cardName, 2)
      case "zlocisz" => new Gold(cardName, 3)

      case "prowincja" => new Victory(cardName, 8)
      case "posiadlosc" => new Victory(cardName, 5)
      case "domostwo" => new Victory(cardName, 3)

      case "wioska" => new Regular(cardName, AffectType.Self, 1, 2, 0, 0, 0, 0)
      case "drwal" => new Regular(cardName, AffectType.Self, 0, 0, 1, 2, 0, 0)
      case "kanclerz" => new Regular(cardName, AffectType.Self, 0, 0, 0, 2, 1, 0)

    }

    cardToReturn
  }

  def getDescr(cardName: String): String = {
    cardName match {

      case "grosz" => "1 sztuka zlota"
      case "srebrnik" => "2 sztuki zlota"
      case "zlocisz" => "3 sztuki zlota"

      case "prowincja" => "8 pt zwyciestwa"
      case "posiadlosc" => "5 pkt zwyciestwa"
      case "domostwo" => "3 pkt zwyciestwa"

      case "wioska" => "Dobierz jedna karte. Zyskujesz dodatkowe 2 akcje"
      case "drwal" => "Plus jeden zakup, dodatkowo 2 sztuki zlota"
      case "kanclerz" => "Plus dwie sztuki zlota, mozesz natychmiast wyrzucic jedna karte na smietnisko"

    }
  }

  def getCost(cardName: String) : Int = {
    cardName match {

      case "grosz" => 1
      case "srebrnik" => 3
      case "zlocisz" => 6

      case "prowincja" => 8
      case "posiadlosc" => 5
      case "domostwo" => 3

      case "wioska" => 3
      case "drwal" => 3
      case "kanclerz" => 4

    }
  }

  def getType(cardName: String) : String = {
    cardName match {

      case "grosz" => "Zloto"
      case "srebrnik" => "Zloto"
      case "zlocisz" => "Zloto"

      case "prowincja" => "Zwyciestwo"
      case "posiadlosc" => "Zwyciestwo"
      case "domostwo" => "Zwyciestwo"

      case "wioska" => "Action"
      case "drwal" => "Action"
      case "kanclerz" => "Action"

    }
  }

}