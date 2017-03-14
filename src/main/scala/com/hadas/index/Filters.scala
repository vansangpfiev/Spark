package com.hadas.index

class Filters {
  val filter1 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  val filter2 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  val filter3 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  val filter4 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  val filter5 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  val filter6 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  val filter7 = Array(new FilterExpression("measurement", "0.6", new RelOperator(RelOperatorType.GREATER)), new FilterExpression("meterid", "1", new RelOperator(RelOperatorType.LOWER)))
  
  def getFilter(queryType: String): Array[FilterExpression]={
    if(queryType == "query1"){
      return filter1
    }else if(queryType == "query2"){
      return filter2
    }else if(queryType == "query3"){
      return filter3
    }else if(queryType == "query4"){
      return filter4
    }else if(queryType == "query5"){
      return filter5
    }else if(queryType == "query6"){
      return filter6
    } else{
      return filter7
    } 
  }
}