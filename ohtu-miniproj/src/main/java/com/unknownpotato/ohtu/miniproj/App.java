
package com.unknownpotato.ohtu.miniproj;

import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.ConsoleIO;
import com.unknownpotato.ohtu.miniproj.ui.TextUI;

/**
 *
 * @author axwikstr
 */
public class App {
    public static void main(String[] args) {
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
//
//        References references = ctx.getBean(References.class);
//        IO io = ctx.getBean(IO.class);
//        TextUI textUi = ctx.getBean(TextUI.class);

        References references = new References();
        TextUI textUi = new TextUI(references, new ConsoleIO());
        textUi.run();
    }
}
