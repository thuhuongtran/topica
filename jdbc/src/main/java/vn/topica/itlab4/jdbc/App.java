package vn.topica.itlab4.jdbc;


import vn.topica.itlab4.jdbc.entity.db.ClassEntity;
import vn.topica.itlab4.jdbc.entity.db.StudentEntity;
import vn.topica.itlab4.jdbc.utils.db.DatabaseUtil;
import vn.topica.itlab4.jdbc.utils.db.Query;

import java.util.List;

public class App {
    private static DatabaseUtil database = DatabaseUtil.INSTANCE;

    public static void main(String[] args) {
        doFetchLazy();
        System.out.println("------------------------------------");
        doFetchEager();
    }

    private static void doFetchLazy() {
        List<ClassEntity> clss = (List<ClassEntity>) database.fetchLazy(ClassEntity.class);
        clss.forEach(c -> {
            System.out.println(c.getId() + "-" + c.getName() + "-" + c.getDescription());
            database.fetchLazyJoinData(c);
            c.getListStudent().forEach(s -> System.out.println(s.getName() + "-" + s.getId()));
        });
    }

    private static void doFetchEager() {
        List<ClassEntity> clss = (List<ClassEntity>) database.fetchEager(ClassEntity.class);
        clss.forEach(c -> {
            System.out.println(c.getId() + "-" + c.getName() + "-" + c.getDescription());
            c.getListStudent().forEach(s -> System.out.println(s.getName() + "-" + s.getId()));
        });
    }
}
