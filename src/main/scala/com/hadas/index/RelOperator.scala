package com.hadas.index

import RelOperatorType._

class RelOperator(otype : RelOperatorType){

  def getOperatorType(): RelOperatorType = {
    return this.otype
  }

}
