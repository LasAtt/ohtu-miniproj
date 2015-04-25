
package com.unknownpotato.ohtu.miniproj;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.ConsoleIO;
import com.unknownpotato.ohtu.miniproj.io.IO;
import com.unknownpotato.ohtu.miniproj.ui.TextUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author axwikstr
 */
public class App {
    public static void main(String[] args) {
//        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
//
//        References references = ctx.getBean(References.class);
//        ConsoleIO io = ctx.getBean(ConsoleIO.class);
//        Reference ref = ctx.getBean(Reference.class);
//        TextUI textUi = ctx.getBean(TextUI.class);

        References references = new References();
        TextUI textUi = new TextUI(references, new ConsoleIO());
        textUi.run();
    }
}
