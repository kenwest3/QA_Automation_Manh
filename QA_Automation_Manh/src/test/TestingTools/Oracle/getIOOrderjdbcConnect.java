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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwestberg on 4/17/2015.
 */
public class getIOOrderjdbcConnect {

    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@CTDAYQ2DB07:51521:QA2RPL";

    //  Database credentials
    static final String USER = "kwestber";
    static final String PASS = "changeme671";

    public static String getIOOrderjdbcConnect() {
        Connection conn = null;
        Statement stmt = null;
        String  be_cd = null;
        try{
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
            sql = " SELECT bs.be_cd " +
                    "  FROM t_ca c, t_be bd, t_be bs  " +
                    "  WHERE c.dest_be_id = bd.be_id AND c.src_be_id = bs.be_id  " +
                    "  AND ca_ordr_grp_id IS NULL  " +
                    "  AND ordr_due_ind = 1 " +
                    "  AND bd.be_cd = 'RID' " +
                    "  AND rownum = 1 ";
            sql2 = "ALTER Session SET Current_Schema = \"IO_OLTP\" ";
            ResultSet nope = stmt.executeQuery(sql2);
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                //locationData ld = new locationData();
                be_cd  = rs.getString("be_cd");
                //int age = rs.getInt("age");
                //String first = rs.getString("first");
                //String last = rs.getString("last");

                //Display values
                System.out.print("be_cd: " + be_cd + "\n");
                //System.out.print(", Age: " + age);
                //System.out.print(", First: " + first);
                //System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return be_cd;

    }//end main
}//end FirstExample


