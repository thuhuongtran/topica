<%@ page import="topica.itlab4.mvc.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Topica students</title>
  <style type="text/css">
    <%@include file="/css/bootstrap.min.css"%>
  </style>
  <% List<User> users = (List<User>) request.getAttribute("users");%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
        integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <script>
      var isEdit = false;
      $(document).ready(function () {
          $("#formModal").on('hidden.bs.modal', function () {
              $(this).find('form').trigger('reset');
          })
          $("#btnModal").click(function () {
              isEdit = false;
          })
      });
      var id = 0;
      function setData(editId, name, gender, year, date) {
          isEdit = true;
          id = editId;
          console.log('run to click edit=' + id + "-" + name + "-" + gender + "-" + "-" + year + "-" + date);
          $("#name").val(name);
          $("#gender").val(gender);
          $("#year").val(year);
          $("#date").val(date);
      }
      function getEditURL() {
          return "?action=edit&id=" + id;
      }
      function doDel(deleteId) {
          if(confirm("Are you sure to delete this ?")) {
              window.location.href = "?action=delete&id="+deleteId;
          }
      }
      function doSearch() {
          var txt = document.getElementById("ipSearch").value;
          window.location.href = "?action=search&txt="+txt;
      }
  </script>
</head>
<body>
<div class="container">
  <br>
  <div class="row">
    <div class="col-9">
      <h2>Topica students</h2>
    </div>
    <div class="col-3 btn-group btn-group-justified">
      <button id="btnAdd" type="button" class="btn btn-success" data-toggle="modal"
              data-target="#formModal">Add new
      </button>
    </div>
  </div>
  <div class="row">
    <div class="col-9">
      <p>Topica student list. Learn online. Go global</p>
    </div>
  </div>
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">Search by name</span>
    </div>
    <input type="text" required id="ipSearch" name="search" placeholder="Enter student name" class="form-control">
    <div class="input-group-append">
      <button class="btn btn-info" onclick="doSearch()" type="button">Search</button>
    </div>
  </div>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Birth Year</th>
      <th>Gender</th>
      <th>Enter Date</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%for (int i = 0; i < users.size(); i++) {%>
    <tr>
      <td id="tdId"><%=users.get(i).getId()%>
      </td>
      <td id="tdName"><%=users.get(i).getName()%>
      </td>
      <td id="tdYear"><%=users.get(i).getBirthYear()%>
      </td>
      <td id="tdGender"><%=users.get(i).getGender()%>
      </td>
      <td id="tdDate"><%=users.get(i).getEnterDate()%>
      </td>
      <td>
        <a href="#" id="btnEdit" class="btn btn-warning btn-md"
           data-toggle="modal" onclick="setData(<%=users.get(i).getId()%>, '<%=users.get(i).getName()%>'
            , '<%=users.get(i).getGender()%>', '<%=users.get(i).getBirthYear()%>', '<%=users.get(i).getEnterDate()%>')"
           data-target="#formModal">
          <i class="fas fa-edit"></i>
        </a>
        <a href="#" class="btn btn-danger btn-md" onclick="doDel(<%=users.get(i).getId()%>)">
          <i class="fas fa-times-circle"></i>
        </a>
      </td>
    </tr>
    <%}%>
    </tbody>
  </table>

  <div class="modal modal-fade" data-backdrop="static" data-keyboard="false" id="formModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header bg-light">
          <h4 class="modal-title">Student Information</h4>
          <button type="button" class="close" data-dismiss="modal">x</button>
        </div>
        <div class="modal-body">
          <form id="addEditForm" method="post">
            <div class="form-row">
              <div class="form-group col-md-7">
                <label class="small font-weight-bold" for="name">Name</label>
                <input type="text" required name="name" id="name"
                       class="form-control"
                       placeholder="Enter student name here">
              </div>
              <div class="form-group col-md-5">
                <label class="small font-weight-bold" for="gender">Gender</label>
                <select id="gender" name="gender" class="form-control">
                  <option selected value="MAN">Man</option>
                  <option value="WOMAN">Woman</option>
                </select>
              </div>
            </div>
            <div class="form-row">
              <div class="form-group col-md-3">
                <label class="small font-weight-bold" for="year">Birth Year</label>
                <input type="text" required name="year"
                       id="year"
                       class="form-control"
                       placeholder="Birth year">
              </div>
              <div class="form-group col-md-9">
                <label class="small font-weight-bold" for="date">Enter Date</label>
                <input type="date" required name="date"
                       id="date"
                       class="form-control">
              </div>
            </div>
            <button type="submit" onclick="this.form.action = isEdit ? getEditURL() : '?action=create'"
                    class="btn btn-primary">Submit
            </button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" id="btnModal" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>