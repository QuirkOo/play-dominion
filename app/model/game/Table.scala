package model.game

/**
 * Created by yorg on 28.01.15.
 */
object Table {

  val tableMap = scala.collection.mutable.Map[String, Int] ()

  tableMap += ( "grosz" -> 100 )
  tableMap += ( "srebrnik" -> 100 )
  tableMap += ( "zlocisz" -> 100 )

  tableMap += ( "prowincja" -> 12 )
  tableMap += ( "posiadlosc" -> 15 )
  tableMap += ( "domostwo" -> 20 )

  tableMap += ( "wioska" -> 10 )
  tableMap += ( "drwal" -> 10 )
  tableMap += ( "kanclerz" -> 10 )

  def getTableState: String = {
    var res: String = new String("[")
    for(key:String <- tableMap.keySet) {
      var element: String = "{"
      element = element.concat(kv("name", "key")).concat(", ")
        .concat(kv("desc", Card.getDescr(key))).concat(", ")
        .concat(kv("type", Card.getType(key))).concat(", ")
        .concat(kv("cost", Integer.toString(Card.getCost(key)))).concat(", ")
        .concat(kv("amount", Integer.toString(tableMap(key)))).concat("}, ")
        res = res.concat(element)
    }
    res.substring(0, res.length - 2).concat("]")
  }

  def pickCard(cardName: String) = {
    tableMap(cardName) = tableMap(cardName) - 1
  }

  def kv(k: String, v: String) = {
    q(k) + ": " + q(v)
  }

  def q(s: String) : String = {
    "\"" + s + "\""
  }

}
