
package com.unknownpotato.ohtu.miniproj;

import com.unknownpotato.ohtu.miniproj.domain.ReferenceFactory;
import com.unknownpotato.ohtu.miniproj.domain.References;
import com.unknownpotato.ohtu.miniproj.io.ConsoleIO;
import com.unknownpotato.ohtu.miniproj.ui.TextUI;


public class App {
    public static void main(String[] args) {
        References references = new References();
        ReferenceFactory factory = new ReferenceFactory(references);
        TextUI textUi = new TextUI(references, new ConsoleIO());
        textUi.run();
    }
}
