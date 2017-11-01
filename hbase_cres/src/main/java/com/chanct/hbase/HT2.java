package com.chanct.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;


public class HT2 {

    public static void main(String args[])throws MasterNotRunningException, IOException{

        // Instantiating a configuration class
        Configuration conf = HBaseConfiguration.create();
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab("u_chanct@HADOOP.COM", "/home/u_chanct.keytab");


        System.out.println("0");
        /*
        // Instantiating HBaseAdmin class
        HBaseAdmin admin = new HBaseAdmin(conf);
        System.out.println("1");

        // Getting all the list of tables using HBaseAdmin object
        HTableDescriptor[] tableDescriptor = admin.listTables();
        System.out.println(tableDescriptor.length);

        // printing all the table names.
        for (int i=0; i<tableDescriptor.length;i++ ){
            System.out.println(tableDescriptor[i].getNameAsString());
        }
        */

        HTable t = new HTable(conf, "t");
        Get get = new Get("ip1".getBytes());
        try{
            t.exists(get);
            Result result = t.get(get);
            String value = Bytes.toString(t.get(get).getValue(Bytes.toBytes("f"), Bytes.toBytes("col1")));
            System.out.println(value);
        }finally{
            t.close();
        }

        System.out.println("Done!");
    }
}
