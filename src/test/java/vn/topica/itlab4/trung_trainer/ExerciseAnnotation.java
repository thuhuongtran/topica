package vn.topica.itlab4.trung_trainer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import vn.topica.itlab4.exercises.trung_trainer.annotation.JsonParser;
import vn.topica.itlab4.exercises.trung_trainer.annotation.Staff;
import vn.topica.itlab4.exercises.trung_trainer.annotation.StaffJackson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ExerciseAnnotation {
    @Test
    public void testWriteJson() {
        Staff st = new Staff();
        st.setName("mkyong");
        st.setAge(38);
        st.setPosition(new String[]{"Founder", "CTO", "Writer"});
        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        skills.add("node");
        skills.add("kotlin");
        st.setListSkill(skills);
        Map<String, BigDecimal> salary = new HashMap<>();
        salary.put("2018", BigDecimal.valueOf(14000));
        salary.put("2012", BigDecimal.valueOf(12000));
        salary.put("2010", BigDecimal.valueOf(10000));
        st.setSalaryDetails(salary);

        System.out.println(JsonParser.getInstance().writeJson(st));

        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println(om.writeValueAsString(st));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadJson() {
        String json = "{\n" +
                "  \"name\" : \"mkyong\",\n" +
                "  \"age\" : 38,\n" +
                "  \"position\" : [ \"Founder\", \"CTO\", \"Writer\" ],\n" +
                "  \"skills\" : [ \"java\", \"python\", \"node\", \"kotlin\" ],\n" +
                "  \"salary\" : {\n" +
                "    \"2018\" : 14000,\n" +
                "    \"2012\" : 12000,\n" +
                "    \"2010\" : 10000\n" +
                "  }\n" +
                "}";
        StaffJackson st = JsonParser.getInstance().readJson(json);
        ObjectMapper om = new ObjectMapper();
        try {
            assertEquals(json.replaceAll("\\s+", ""), om.writeValueAsString(st));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
