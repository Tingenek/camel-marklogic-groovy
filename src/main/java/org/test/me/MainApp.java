package org.test.me;

import org.apache.camel.spring.Main;

/**
 * A Camel Application
 */
public class MainApp {

 	private MainApp() {
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.setApplicationContextUri("camel-context.xml");
        main.enableHangupSupport();
        main.run(args);
    }
}


