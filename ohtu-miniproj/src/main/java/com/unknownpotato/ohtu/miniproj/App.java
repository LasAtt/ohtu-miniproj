
package com.unknownpotato.ohtu.miniproj;

import com.unknownpotato.ohtu.miniproj.domain.Reference;
import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.ConsoleIO;
import com.unknownpotato.ohtu.miniproj.ui.TextUI;
import com.unknownpotato.ohtu.miniproj.io.BibtexFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author axwikstr
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");

        References references = ctx.getBean(References.class);
        ReferenceFactory factory = ctx.getBean(ReferenceFactory.class);
        TextUI textUi = ctx.getBean(TextUI.class);
        textUi.run();
    }
}
