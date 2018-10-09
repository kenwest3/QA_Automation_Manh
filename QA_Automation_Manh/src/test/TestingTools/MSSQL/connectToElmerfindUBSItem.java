/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.MSSQL;

import java.sql.*;

/**
 * Created by kwestberg on 4/29/2015.
 */
public class connectToElmerfindUBSItem {

    public connectToElmerfindUBSItem(String getItem) {
    }

    String returnItemDesc = null;

    public String connectToElmerfindUBSItem(String getItem) throws SQLException, ClassNotFoundException {

        //Loading the required JDBC Driver class
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Creating a connection to the database
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://CTDAYEMQ211:42000;database=ELMER_MANHATTAN;integratedSecurity=true;");

        //Executing SQL query and fetching the result
        Statement st = conn.createStatement();

        //String sqlStr = "select SalesOrderId FROM [ELMER_MANHATTAN].[dbo].[SalesOrderResequenceUbs] where [SalesOrderId] = '" + getItem + "' ";
        String sqlStr = "WITH XMLNAMESPACES ( 'http://UNFI.Enterprise.Manhattan.Shared.Schemas.ItemTransaction' AS ns0 ) " +
                "SELECT top 1 iru.MessageXML.value('(/ns0:ItemTransaction/ns0:Item/ns0:Description)[1]', 'varchar(max)')  " +
                "FROM ItemResequenceUbs iru \n" +
                "WHERE iru.MessageXML.value('(/ns0:ItemTransaction/ns0:Item/ns0:ItemName)[1]', 'varchar(max)')  = " + getItem +
                "order by UpdateDate desc";
        ResultSet rs = st.executeQuery(sqlStr);

        while (rs.next()) {
            System.out.println(rs.getString("SalesOrderId"));
            returnItemDesc = (rs.getString("SalesOrderId"));

        }
        return returnItemDesc;
    }
}


