package com.hadas.index

//
// Compile and run:
//  scalac isnumber.scala
//  scala IsNumber
//

// this is the class that provides the isNumber method when called on java.lang.String
class ExtendedString(s:String) {
	def isNumber: Boolean = s.matches("[-+]?\\d+(\\.\\d+)?")	
}

// and this is the companion object that provides the implicit conversion
object ExtendedString {
	implicit def String2ExtendedString(s:String) = new ExtendedString(s)
}


