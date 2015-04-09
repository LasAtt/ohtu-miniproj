package com.unknownpotato.ohtu.miniproj.io;

import java.util.List;

public interface IO {
    String readLine(String prompt);
    int readInt(String prompt);
    void print(String toPrint);
    List<String> getPrints();
}
