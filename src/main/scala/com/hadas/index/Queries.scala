package com.hadas.index

class Queries(filepath: String) {
  val query1 = "select SUM(measurement)  from " + filepath + " WHERE meterid > 5 AND meterid < 300"

  val query2 = "select SUM(measurement)  from " + filepath + " where meterid > 5 and meterid < 49000"

  val query3 = "select SUM(measurement)  from " + filepath + " where meterid > 5 and meterid < 300 and date > timestamp '2013-03-01 17:00:00' and date < timestamp '2013-05-01 17:00:00'"

  val query4 = "select id, meterid, date, measurement  from " + filepath + " where meterid > 0.7 order by meterid"

  val query5 = "select id, meterid, date, measurement  from " + filepath + " where meterid > 0.3 and id > 5 and id < 25000 order by meterid"

  val query6 = "select sum(hr)+94.96 from (select sum(measurement))*0.1114 as hr from " + filepath + " where id = 1 and extract(hour from date)) in (1,2,3,4,5,6,7,15,16,17) union all select sum(measurement)*0.16 as hr from hdfs://localhost:54310/data-v2/file0.csv where meterid = 1 and extract(hour from date) not in (1,2,3,4,5,6,7,15,16,17))"

  val query7 = "select meterid, sum(hr) from (select meterid, sum(measurement)*0.1114+94.96 as hr from " + filepath + " where meterid > 0 and meterid < 15000 and extract(hour from date) in (1,2,3,4,5,6,7,15,16,17) group by meterid union all select meterid, sum(measurement)*0.16 as hr from " + filepath + "  where meterid > 0 and meterid < 15000 and extract(hour from date) not in (1,2,3,4,5,6,7,15,16,17) group by meterid) group by meterid"
  
 def getQuery(queryType: String): String={
    if(queryType == "query1"){
      return query1
    }else if(queryType == "query2"){
      return query2
    }else if(queryType == "query3"){
      return query3
    }else if(queryType == "query4"){
      return query4
    }else if(queryType == "query5"){
      return query5
    }else if(queryType == "query6"){
      return query6
    } else if(queryType == "query7"){
      return query7
    } else{
      return "query" + queryType + "unknown"
    } 
  }
}