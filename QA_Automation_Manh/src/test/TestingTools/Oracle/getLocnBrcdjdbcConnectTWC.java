package TestingTools.Oracle;

import com.WM_2012.tests.locationData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by kwestberg on 3/12/2015.
 */


public class getLocnBrcdjdbcConnectTWC {
    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@CTDAYQ2DB51:51521:QA2WTWC";

    //  Database credentials
    static final String USER = "kwestber";
    static final String PASS = "changeme671";

    public static List<locationData> getLocnBRCD(String Item, String Location) {
        Connection conn = null;
        Statement stmt = null;
        List<locationData> locations = new ArrayList<locationData>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            String sql2;
            sql = " select lh.locn_brcd, ic.item_name from wm_twc.locn_hdr lh " +
                    "  inner join WM_TWC.PICK_LOCN_DTL pld on pld.LOCN_ID = lh.LOCN_ID  " +
                    "  inner join Item_cbo ic on ic.ITEM_ID = pld.ITEM_ID " +
                    "  inner join item_wms iw on iw.item_id = ic.ITEM_ID  " +
                    "  where lh.LOCN_BRCD like '9%' " +
                    "  and pld.max_invn_qty > 24 " +
                    "  and iw.XPIRE_DATE_REQD = '0' " +
                    "  and lh.LOCN_ID > 841397  " +
                    "  and pld.ACTL_INVN_CASES = '0'  ";
            //sql = " SELECT locn_ID FROM PICK_LOCN_HDR where repl_locn_brcd = '931346309' ";
            sql2 = "ALTER Session SET Current_Schema = \"WM_TWC\" ";
            ResultSet nope = stmt.executeQuery(sql2);
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                locationData ld = new locationData();
                String locn_brcd  = rs.getString("locn_brcd");
                ld.ItemName = rs.getString("item_name");
                ld.LocationBarcode = rs.getString("locn_brcd");
                locations.add(ld);
                //int age = rs.getInt("age");
                //String first = rs.getString("first");
                //String last = rs.getString("last");

                //Display values
                //System.out.print("locn_id: " + locn_id + "\n");
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
        return locations;

    }//end main
}//end FirstExample

