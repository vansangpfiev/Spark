package com.hadas.index

import org.mongodb.scala._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model._

import org.apache.spark.sql.SparkSession
import org.joda.time.DateTimeZone
import java.util.Formatter.DateTime
import org.joda.time.DateTime
import java.io.PrintWriter
import java.io.File
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.TimestampType
import org.apache.spark.sql.types.DoubleType
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import java.io.FileOutputStream

/*import mongoDriverExamples.Helpers._
import mongoDriverExamples.MongoIndex
import mongoDriverExamples.RelOperator
import mongoDriverExamples.RelOperatorType._*/

object SparkMain {

  def main(args: Array[String]) {
    
    val tbName = "indexes"
    val queries: Queries = new Queries(tbName)
    
    val queryString = queries.getQuery(args(0)) 
    val filters: Filters = new Filters()
    
    val spark = SparkSession
      .builder()
      .appName("Spark with secondary indexes")
      .getOrCreate()

    val sqlContext = spark.sqlContext
    val mongoClient: MongoClient = MongoClient("mongodb://localhost") //:27017")
    val mongoIndex: MongoIndex = new MongoIndex(mongoClient)
    val filter = filters.getFilter(args(0))
    val dir = args(1)+"meterdata"

    val t1 = DateTime.now(DateTimeZone.UTC).getMillis()

    val names = mongoIndex.getFileNames(dir, filter)

    val t2 = DateTime.now(DateTimeZone.UTC).getMillis()
    val t_MongoExec = t2 - t1 
    
    println("results : ")
    println(names)
    //  val database: MongoDatabase = mongoClient.getDatabase("cities")
    //  val collection: MongoCollection[Document] = database.getCollection("city");
    //  val list = collection.find().printResults()

    mongoClient.close()
    
    val customSchema = StructType(Array(
      StructField("id", IntegerType, true),
      StructField("meterid", IntegerType, true),
      StructField("measurement", DoubleType, true),
      StructField("date", TimestampType, true),
      StructField("obs", StringType, true)))
      
    val t3 = DateTime.now(DateTimeZone.UTC).getMillis()  
   
    val df = spark.read
      .format("org.apache.spark.csv")
      .option("header", "false")
      .schema(customSchema)
      .csv(names)
    
    
    df.createOrReplaceTempView(tbName)

    val results = sqlContext.sql(queryString)

    val t4 = DateTime.now(DateTimeZone.UTC).getMillis()
    
    results.show()
    val t_SparkExec = t4 - t3
    
    val writer = new PrintWriter(new FileOutputStream(new File("/home/paladin/Desktop/BigDataIndexing/spark/queries_Log.txt"), true))
    writer.append(args(0) + "\t" +t_MongoExec.toString() + "\t" + t_SparkExec.toString() + "\t" +(t_MongoExec + t_SparkExec).toString() +"\n")
    writer.close()

  }

}
