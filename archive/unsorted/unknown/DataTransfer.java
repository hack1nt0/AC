package main;

import template.debug.InputReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataTransfer {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        out.println('[');
        while (in.hasNext()) {
            out.printf("%s, \n",in.nextLine());
        }
        out.println("]");
    }
}
