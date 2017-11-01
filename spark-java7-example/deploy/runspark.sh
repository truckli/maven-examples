s=/opt/spark-2.0.0-bin-hadoop2.6/bin/spark-submit
$s --class com.chanct.demo.LineCounter --master yarn --deploy-mode client --driver-java-options "-Dlog4j.configuration=file:///home/limz/log4j.properties" app-1.0-SNAPSHOT.jar $@
exit
