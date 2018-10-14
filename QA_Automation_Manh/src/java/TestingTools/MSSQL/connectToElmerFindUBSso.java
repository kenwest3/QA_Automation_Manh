/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.MSSQL;

/**
 * Created by kwestberg on 4/25/2015.
 */

import java.sql.*;

/*
Find a UBS Purchase Order in Elmer Manhattan
 */

public class connectToElmerFindUBSso {

    public connectToElmerFindUBSso(String salesOrder) {
    }
    String returnSO = null;

    public String connectToElmerFindUBSso(String salesOrder) throws SQLException, ClassNotFoundException {

        //Loading the required JDBC Driver class
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Creating a connection to the database
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://CTDAYEMQ211:42000;database=ELMER_MANHATTAN;integratedSecurity=true;");

        //Executing SQL query and fetching the result
        Statement st = conn.createStatement();

        String sqlStr = "select SalesOrderId FROM [ELMER_MANHATTAN].[dbo].[SalesOrderResequenceUbs] where [SalesOrderId] = '" + salesOrder + "' ";
        ResultSet rs = st.executeQuery(sqlStr);

        while (rs.next()) {
            System.out.println(rs.getString("SalesOrderId"));
            returnSO = (rs.getString("SalesOrderId"));

        }
        return returnSO;
    }
}

