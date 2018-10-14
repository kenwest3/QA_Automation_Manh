/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.Oracle;

import com.WM_2012.tests.locationData;

import java.sql.*;


/**
 * Created by kwestberg on 4/28/2015.
 */
public class getWMitemCBO {
    // JDBC driver name and database URL
    //static final String DB_URL = "jdbc:oracle:thin:@CTDAYQ2DB51:51521:QA2WTWC";
    static final String DB_URL = "jdbc:oracle:thin:@CTDAYQ2DB06:51521:QA2WHVA";
    //static final String DB_URL = "jdbc:oracle:thin:@CTDAYQ2DB03:51521:QA2WRAC";
    //static final String DB_URL = "jdbc:oracle:thin:@
    //static final String DB_URL = "jdbc:oracle:thin:@
    //static final String DB_URL = "jdbc:oracle:thin:@
    //static final String DB_URL = "jdbc:oracle:thin:@
    //static final String DB_URL = "jdbc:oracle:thin:@
    //static final String DB_URL = "jdbc:oracle:thin:@



    static final String USER = "kwestber";
    static final String PASS = "changeme671";

    public String getWMitemCBO(String itemNumber, String wHnum) {
        Connection conn = null;
        Statement stmt = null;
        String myItem = itemNumber;
        String myWarehouse = wHnum;
        String description = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            String sql2;
            sql = " SELECT item_name, description " +
                    "FROM item_cbo " +
                    "WHERE item_name = " + myItem;
            //sql = " SELECT locn_ID FROM PICK_LOCN_HDR where repl_locn_brcd = '931346309' ";
            sql2 = "ALTER Session SET Current_Schema = WM_" + myWarehouse;
            ResultSet nope = stmt.executeQuery(sql2);
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String item_name = rs.getString("item_name");
                description = rs.getString("description");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return description;

    }//end main
}//end FirstExample