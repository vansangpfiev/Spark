package com.hadas.index

import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._

/*import mongoDriverExamples.FilterExpression
import mongoDriverExamples.RelOperator */
import RelOperatorType._
import ExtendedString._
import Helpers._


class MongoIndex(mongoClient: MongoClient) {

  final val databaseName = "indexes"
  val database: MongoDatabase = mongoClient.getDatabase(databaseName)

  def getFileNames(dir: String, filter: Array[FilterExpression]) : String ={

    val list = dir.split("/")
    val collectionName = list(list.length - 1)
    val collection: MongoCollection[Document] = database.getCollection(collectionName);
    var filterMo, righttmp: String = "{}"

    if (filter.length == 1) {

      righttmp = filter(0).getRight()
      var right : String = ""
      if (!righttmp.isNumber)
        right =  "\"" + righttmp +"\""
      else
        right = righttmp

      val operator = MatchOperatorObject.matchOperator(filter(0).getOperator().getOperatorType())
      filterMo = "{ " + filter(0).getLeft() + ": {" + operator + ": " + right + "}}"

    } else {
      if (filter.length == 2){

        righttmp = filter(0).getRight()
        var right1, right2: String = ""
        if (!righttmp.isNumber)
          right1 ="\"" + righttmp +"\""
        else
          right1 = righttmp

        righttmp = filter(1).getRight()
        if (!righttmp.isNumber)
          right2 ="\"" + righttmp +"\""
        else
          right2 = righttmp

        val operator1 = MatchOperatorObject.matchOperator(filter(0).getOperator().getOperatorType())
        val operator2 = MatchOperatorObject.matchOperator(filter(1).getOperator().getOperatorType())

        if (filter(0).getLeft() == filter(1).getLeft()){
           filterMo = "{ " + filter(0).getLeft() + ": {" + operator1 + ": " +  right1 + ", " + operator2 + ": " + right2 + "}}"
        } else {
           filterMo = "{ " + filter(0).getLeft() + ": {" + operator1 + ": " + right1 + "}, " + filter(1).getLeft() + ": {" + operator2 + ": " + right2 + "} }"
        }
      }
    }


    val observable = collection.find(Document(filterMo)).projection(Document("{ _id:0, fname:1 }")).results().distinct
    
    var result = ""

    observable.foreach(e => result += dir + "/" + e.get("fname").get.asString().getValue() + ",")
    val slen = result.length()

    return result.substring(0, slen-1)
    }

  object MatchOperatorObject extends App {
    def matchOperator(operator: RelOperatorType): String = operator match {
      case RelOperatorType.GREATER => "$gt"
      case RelOperatorType.GREATEREQ => "$ge"
      case RelOperatorType.LOWER => "$lt"
      case RelOperatorType.LOWEREQ => "$le"
      case RelOperatorType.NOTEQ => "$ne"
      case RelOperatorType.EQ => "$eq"
    }
  }

}
