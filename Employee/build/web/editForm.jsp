<!DOCTYPE html>
<html>
    <head>
        <title>Edit Employee</title>
        <style>
            .form-box {
                width: 350px;
                padding: 10px;
            }
            .form-box form {
                display: grid;
                width: 300px;
                grid-template-columns: 120px 150px;
                row-gap: 8px;
                column-gap: 5px;
            }
            .form-box input {
                width: 100%;
            }
        </style>
    </head>

    <body>

        <%@ page import="java.sql.*" %>
        <%
            int id = Integer.parseInt(request.getParameter("id"));

            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Java DB", "app", "app");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM STAFF WHERE ID=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
        %>

        <h2>Edit Employee</h2>
        <div class="form-box">
            <form action="empController" method="post">
                <input type="hidden" name="operation" value="update">
                <input type="hidden" name="id" value="<%=rs.getInt("ID")%>">
                Name:<input type="text" name="name" value="<%=rs.getString("NAME")%>"><br>

                Salary:<input type="text" name="salary" value="<%=rs.getInt("SALARY")%>"><br>

                Designation:<input type="text" name="designation" value="<%=rs.getString("DESIGNATION")%>"><br>

                <input type="submit" value="Update">
            </form>
        </div>
    </body>
</html>
