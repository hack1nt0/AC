package main;

import java.util.Arrays;
import java.util.Collections;

public class OldestOne {
    public String oldest(String[] data) {
        class Student implements Comparable<Student>{
            String name;
            int age;

            public Student(String name, int age) {
                this.name = name.trim();
                this.age = age;
            }

            @Override
            public int compareTo(Student o) {
                return age - o.age;
            }
        }
        Student[] students = new Student[data.length];
        for (int i = 0; i < data.length; ++i) {
            int pos = 0;
            StringBuffer sb = new StringBuffer();
            while (!Character.isDigit(data[i].charAt(pos))) {
                sb.append(data[i].charAt(pos));
                pos++;
            }
            String name = sb.toString();
            sb.setLength(0);
            while (Character.isDigit(data[i].charAt(pos))) {
                sb.append(data[i].charAt(pos));
                pos++;
            }
            int age = Integer.parseInt(sb.toString());
            students[i] = new Student(name, age);
        }
        Arrays.sort(students, Collections.reverseOrder());
        return students[0].name;
    }
}
