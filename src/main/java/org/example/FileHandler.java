package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void saveStudentsToFile(String fileName, List<Student> studentList) {
        try {
            if (fileName.endsWith(".json")) {
//                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), studentList);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(studentList);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), studentList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Student> loadStudentsFromFile(String fileName){
        List<Student> studentList= new ArrayList<>();
        File file = new File(fileName);

        if (file.exists()){
            try {
                if (fileName.endsWith(".json")) {
                    studentList = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        studentList = (List<Student>) ois.readObject();
                    }
                }else if (fileName.endsWith(".xml")){
                    studentList = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
            }catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return studentList;
    }
}
