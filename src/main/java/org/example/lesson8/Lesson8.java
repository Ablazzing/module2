package org.example.lesson8;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Lesson8 {

    public static void main(String[] args) throws IOException {

        File file = new File("E:\\repos\\module2\\src\\main\\java\\org\\example\\file.json");
        JsonHelper jsonHelper = new JsonHelper();
        Integer countPeople = jsonHelper.getDataFromJson(file, "countPeople", Integer.class);
        System.out.println(countPeople);

        Map<String, Object> data = jsonHelper.getData(file);
        jsonHelper.addValueToData("people.name", "Yuri", data);
        System.out.println(data);

        //Преобразование json в java объект
        ObjectMapper mapper = new ObjectMapper();
        File file2 = new File("E:\\repos\\module2\\src\\main\\java\\org\\example\\user.json");
        User user = mapper.readValue(file2, User.class);
        System.out.println(user);
    }
}
