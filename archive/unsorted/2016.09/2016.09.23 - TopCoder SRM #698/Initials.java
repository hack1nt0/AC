package main;

public class Initials {
    public String getInitials(String name) {
        String[] tmp = name.split("[ ]");
        String ans = "";
        for (String n: tmp) ans += n.charAt(0);
        return ans;
    }
}
