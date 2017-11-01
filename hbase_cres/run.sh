#!/bin/sh

scp target/hbase*.jar udapba5:~/hbase/libs
ssh udapba5 'java -cp ~/hbase/libs/*:/etc/hbase/conf com.chanct.hbase.ListTables'
