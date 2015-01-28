package model.game

/**
 * Created by yorg on 28.01.15.
 */
class Table {

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
      res = res.concat(key).concat(": ").concat(Integer.toString(tableMap(key))).concat(", ")
    }
    res.substring(0, res.length - 2).concat("]")
  }

  def pickCard(cardName: String) = {
    tableMap(cardName) = tableMap(cardName) - 1
  }

}
