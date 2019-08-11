package vn.topica.itlab4.exercises.trung_trainer.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private static final JsonParser intance = new JsonParser();

    private JsonParser() {

    }

    public static JsonParser getInstance() {
        return intance;
    }
    public StaffJackson readJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        StaffJackson st = null;
        try {
            st = objectMapper.readValue(json, StaffJackson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return st;
    }

    public String writeJson(Staff obj) {
        StringBuilder sb = new StringBuilder();
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                JsonField jsonField = fields[i].getDeclaredAnnotation(JsonField.class);
                String name = jsonField != null ? jsonField.name() : fields[i].getName();
                String strField = "";
                if (fields[i].getType() == String.class || fields[i].getType().isPrimitive()) {
                    strField = String.format("\"%s\",",fields[i].get(obj));
                } else if (fields[i].getType().isArray()) {
                    String[] eles = (String[]) fields[i].get(obj);
                    List<String> ss = new ArrayList<>();
                    for (String ele : eles) {
                        ss.add(String.format("\"%s\"", ele));
                    }
                    strField = String.format("[%s],", StringUtils.join(ss, ","));
                } else if (fields[i].getType().equals(List.class)) {
                    List<String> eles = (List<String>) fields[i].get(obj);
                    List<String> ss = new ArrayList<>();
                    for (String ele : eles) {
                        ss.add(String.format("\"%s\"", ele));
                    }
                    strField = String.format("[%s],", StringUtils.join(ss, ","));
                } else if (fields[i].getType().equals(Map.class)) {
                    Map<String, BigDecimal> salary = (Map<String, BigDecimal>) fields[i].get(obj);
                    List<String> strs = new ArrayList<>();
                    String item = "";
                    for (String s : salary.keySet()) {
                        String key = s;
                        String value = salary.get(s).toString();
                        item = String.format("\"%s\" : %s,", key, value);
                        strs.add(item);
                    }
                    strField = String.format("{\n%s\n}", StringUtils.join(strs, "\n"));
                }
                String str = String.format("\"%s\" : %s\n",
                        name,
                        strField);
                sb.append(str);
            }
        } catch (Exception e) {
            System.out.println("Failed. Error: " + e.getMessage());
            e.printStackTrace();
        }
        return String.format("{\n%s}", sb.toString());
    }
}
