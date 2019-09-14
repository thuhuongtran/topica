package topica.itlab4.mvc.controller;

import topica.itlab4.mvc.UserFactory;
import topica.itlab4.mvc.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller extends HttpServlet {
    private UserFactory factory = new UserFactory();
    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doGet(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listen(req, resp);
    }

    protected void listen(HttpServletRequest req,
                          HttpServletResponse resp) {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";
        try {
            List<User> us = new ArrayList<>();
            boolean isSearch = false;
            switch (action) {
                case "create":
                    createUser(req, resp);
                    break;
                case "edit":
                    editUser(req, resp);
                    break;
                case "delete":
                    deleteUser(req, resp);
                    break;
                case "search":
                    isSearch = true;
                    us = searchUser(req, resp);
                    break;
            }
            listUser(req, resp, isSearch, us);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String birthYear = req.getParameter("year");
        String date = req.getParameter("date");
        factory.createUser(name, birthYear, gender, date);
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String birthYear = req.getParameter("year");
        String date = req.getParameter("date");
        System.out.println(id + "-" + name + "-" + gender + "-" + date);
        factory.editUser(id, name, birthYear, gender, date);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        factory.deleteUser(id);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp, boolean isSearch, List<User> us) throws ServletException, IOException {
        List<User> users = factory.getUsers() != null ? factory.getUsers() : new ArrayList<>();
        if (isSearch) {
            req.setAttribute("users", us);
        }
        else req.setAttribute("users", users);
        req.getRequestDispatcher("ListUser.jsp").forward(req, resp);
    }

    private List<User> searchUser(HttpServletRequest req, HttpServletResponse response) {
        String txt = req.getParameter("txt");
        return factory.searchByName(txt);
    }
}
