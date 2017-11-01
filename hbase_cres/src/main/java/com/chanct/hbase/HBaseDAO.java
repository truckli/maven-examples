package com.chanct.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.security.UserGroupInformation;

public class HBaseDAO {

	private static Configuration conf = null;
	private static HBaseAdmin admin = null;
	private static HConnection hConnection;
	@SuppressWarnings("unused")
	private static HTableInterface table = null;

	static {
		conf = HBaseConfiguration.create();
		// conf = new Configuration();

		// conf.set("hbase.zookeeper.property.maxclientcnxns", "300");
		// conf.set("hbase.ipc.client.socket.timeout.connect", "1000");
		// conf.set("zookeeper.session.timeout", "500");
		// conf.set("hbase.regionserver.handler.count", "500");
		System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");
		conf.set("hadoop.security.authentication", "kerberos");
		conf.set("hbase.security.authentication", "kerberos");
		conf.set("kerberos.kdc.host", "10.96.208.5");
		conf.set("kerberos.principal", "u_chanct@HADOOP.COM");
		conf.set("kerberos.cm.admin.pwd", "111111");
		conf.set("hbase.master.kerberos.principal","hbase/_HOST@HADOOP.COM"); // 从Hbase-site.xml文件中获取配置信息
		 conf.set("hbase.regionserver.kerberos.principal","hbase/_HOST@HADOOP.COM"); // 从Hbase-site.xml文件中获取配置信息
		 conf.set("hbase.zookeeper.property.clientPort", "2181");
		 conf.set("hbase.zookeeper.quorum", "10.96.208.1");
//		conf.set("kerberos.principal", "u_jczy@HADOOP.COM");
//		conf.set("hbase.master.kerberos.principal", "hbase/_HOST@HADOOP.COM");
//		conf.set("hbase.regionserver.kerberos.principal", "hbase/_HOST@HADOOP.COM");
//		conf.set("hbase.zookeeper.quorum", "10.96.208.1");
//		conf.set("hbase.zookeeper.property.clientPort", "2181");
//		conf.set("hbase.security.authentication", "kerberos");
//		conf.set("hadoop.security.authentication", "kerberos");
//		conf.set("kerberos.kdc.host", "10.96.208.5");
//		conf.set("kerberos.cm.admin.pwd", "111111");

		UserGroupInformation.setConfiguration(conf);
		try {
			// UserGroupInformation.loginUserFromKeytab("u_jczy@HADOOP.HOME",
			// "C:\\Users\\c704usr3\\Desktop\\keytab\\u_jczy.keytab"); // 认证用户部分
			UserGroupInformation.loginUserFromKeytab("u_chanct@HADOOP.COM", "/home/u_chanct.keytab"); // 认证用户部分
			conf = HBaseConfiguration.create(conf);
			System.err.println("当前用户：" + UserGroupInformation.getCurrentUser().getShortUserName());
			System.err.println("登陆用户：" + UserGroupInformation.getLoginUser());
			// UserGroupInformation.loginUserFromKeytab("u_jczy@HADOOP.COM",
			// "/sftp/keytab/u_jczy.keytab"); // 认证用户部分
			System.out.println("creat admin ");
			table = new HTable(conf, "emp");
			admin = new HBaseAdmin(conf);
			System.out.println(admin);
			hConnection = HConnectionManager.createConnection(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean exitTable(String tableName) {
		boolean flag = false;
		try {
			if (admin.tableExists(tableName)) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println("tableExists error ");
			e.printStackTrace();
		}
		return flag;
	}

	public static HTableDescriptor[] showTables() throws IOException {

		// 获取数据库中表的集合
		HTableDescriptor[] tableDescriptor = admin.listTables();

		// 遍历打印所有表名
		for (int i = 0; i < tableDescriptor.length; i++) {
			System.out.println(tableDescriptor[i].getNameAsString());
		}
		return tableDescriptor;
	}

	public static void main(String[] args) throws IOException {
		// System.out.println("start++++++++++++");
		boolean exitTable = new HBaseDAO().exitTable("emp");
		System.out.println(exitTable);
		try {
			HBaseDAO.showTables();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TableName[] listTableNames = hConnection.listTableNames();
		for (TableName tableName : listTableNames) {
			System.out.println(tableName.toString());
		}
	}
}
