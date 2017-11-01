package com.chanct.tryout

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}


object Hello {


  def main(arg: Array[String]) {

    var logger = Logger.getLogger(this.getClass())
    Logger.getRootLogger().setLevel(Level.ERROR)


    val jobName = "MainExample"

    val conf = new SparkConf().setAppName(jobName)
    val sc = new SparkContext(conf)

    val pathToFiles = arg(0)


    val rawRdd = sc.textFile(pathToFiles)
        .collect()
          .foreach(println)


  }
}
