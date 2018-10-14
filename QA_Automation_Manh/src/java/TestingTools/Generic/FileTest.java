package TestingTools.Generic;

/**
 * Created by kwestberg on 3/12/2015.
 */

import java.io.File;

public class FileTest {
    public static void main(String args[]) {
        File f = new File(args[0]);
        System.out.println
                (f + (f.exists() ? " is found " : " is missing "));
    }
}
