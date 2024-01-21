package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_JSON = "students.json";
    public static final String FILE_XML = "students.xml";
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        File file = new File(FILE_JSON);
        if (file.exists() && !file.isDirectory()){
            studentList = FileHandler.loadStudentsFromFile(FILE_JSON);
        }else{
            studentList.add(new Student("Иванов", 20, 7.2));
            studentList.add(new Student("Петров", 19, 8.3));
            FileHandler.saveStudentsToFile(FILE_BIN, studentList);
            FileHandler.saveStudentsToFile(FILE_JSON, studentList);
            FileHandler.saveStudentsToFile(FILE_XML, studentList);
        }
        System.out.println(studentList);

    }
}