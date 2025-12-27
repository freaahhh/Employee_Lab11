
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@page import = "java.sql.*"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Employee List</title>
    </head>
    <body>
        <h1>Employee List</h1>
        <table border="2">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>salary</th>
                <th>designation</th>
                <th>EDIT</th>
                <th>DELETE</th>
            </tr>
            <% 
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try{
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Java DB", "app", "app");
                String query = "SELECT * FROM Staff";
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                
                while (rs.next()) {
                    
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String salary = rs.getString("salary");
                    String designation = rs.getString("designation");

                    %>
                    <tr>
                        <td><%=rs.getInt("id")%></td>
                        <td><%=rs.getString("name")%></td>
                        <td><%=rs.getString("salary")%></td>
                        <td><%=rs.getString("designation")%></td>
                        <td>
                            <a href="editForm.jsp?id=<%=id%>">Edit</a>
                        </td>
                        <td>
                            <a href="empController?operation=delete&id=<%=id%>"
                               onclick="return confirm('Are you sure want to delete?')">
                               Delete
                            </a>
                        </td>                 
                    </tr>
                    <%
                                        }
                        } catch (SQLException e){
                        out.println("Error retrieving music recorder: " + e.getMessage());
                        } finally {
                        if (rs!=null) rs.close();  
                        if (stmt!=null) stmt.close();
                        if (conn!=null) conn.close();
                        }
                        %>
        </table>
        <p><a href="index.html">Home Page</a></p>
        <p><a href="empForm.jsp">Add New Employee</a></p>
    </body>
</html>
