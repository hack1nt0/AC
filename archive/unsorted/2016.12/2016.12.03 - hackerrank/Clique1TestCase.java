package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Clique1TestCase {
    @TestCase
    public Collection<Test> createTests() {
        String fileName = "input07.txt";
        FileReader in;
        String input = null;
        try {
            input = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Test> ret = new ArrayList<Test>();
        ret.add(new Test(input));
        return ret;
    }
}
