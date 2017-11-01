package com.chanct.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class LineCounter{

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Please provide the path of input file as first parameter.");
      return;
    }
    System.out.println("Hello World");
    SparkConf conf = new SparkConf().setAppName(LineCounter.class.getName());
    JavaSparkContext context = new JavaSparkContext(conf);
    long count = context.textFile(args[0]).count();
    System.out.println(count);
  }
}
