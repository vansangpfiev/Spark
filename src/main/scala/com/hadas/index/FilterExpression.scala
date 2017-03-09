package com.hadas.index

//import mongoDriverExamples.RelOperator

class FilterExpression(left: String, right: String, operator: RelOperator){
  def getRight(): String ={
    return this.right
  }
  def getLeft(): String ={
    return this.left
  }
  def getOperator(): RelOperator ={
    return this.operator
  }
}
