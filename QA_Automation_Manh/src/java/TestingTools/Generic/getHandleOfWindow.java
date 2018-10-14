/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package TestingTools.Generic;

import com.WM_2012.tests.WM_2012_TWC_Test;
import org.openqa.selenium.WebDriver;
import java.util.Set;
import com.WM_2012.tests.WM_2012_TWC_Test.*;

public class getHandleOfWindow {

    public static WebDriver  getHandleOfWindow(String title){  //WebDriver

        //parentWindowHandle = WebDriverInitialize.getDriver().getWindowHandle(); // save the current window handle.


        WebDriver popup = null;
        //WM_2012_TWC_Test.driverIE popup = null;
        Set<String> windowIterator  = WM_2012_TWC_Test.driverIE.getWindowHandles();
        System.err.println("No of windows :  " + windowIterator.size());
        for (String s : windowIterator) {
            String windowHandle = s;
            popup = WM_2012_TWC_Test.driverIE.switchTo().window(windowHandle); // getDriver().switchTo().window(windowHandle);
            System.out.println("Window Title : " + popup.getTitle());
            System.out.println("Window Url : " + popup.getCurrentUrl());
            if (popup.getTitle().equals(title) ){
                System.out.println("Selected Window Title : " + popup.getTitle());
                return popup;
            }

        }
        System.out.println("Window Title :" + popup.getTitle());
        System.out.println();
        return popup;
    }
}

    /*
It will take you to desired window once title of the window is passed as parameter. In your case you can do.

        Webdriver childDriver = Utility.getHandleToWindow("titleOfChildWindow");
        and then again switch to parent window using the same method

        Webdriver parentDriver = Utility.getHandleToWindow("titleOfParentWindow");

        This method works effectively when dealing with multiple windows.*/