package com.hadas.index

object RelOperatorType extends Enumeration {
  type RelOperatorType = Value
  val GREATER,
      GREATEREQ,
      LOWER,
      LOWEREQ,
      NOTEQ,
      EQ = Value
}
