import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import person.bean.Staff;

/**
 *
 * @author USER
 */
@WebServlet(urlPatterns = {"/empController"})
public class empController extends HttpServlet {
    
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement delete;

    @Override
    public void init() throws ServletException {
        initializeJdbc();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try {

            String operation = request.getParameter("operation");

            int id = 0;
            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                id = Integer.parseInt(request.getParameter("id"));
            }

            String name = null;
            String designation = null;
            int salary = 0;
            if (!operation.equals("delete")) {
                name = request.getParameter("name");
                designation = request.getParameter("designation");
                salary = Integer.parseInt(request.getParameter("salary"));
            }

            Staff st = new Staff();
            st.setId(id);
            st.setName(name);
            st.setSalary(salary);
            st.setDesignation(designation);

            request.setAttribute("Staff", st);

            switch (operation) {
                case "insert":
                    if (name != null && !name.isEmpty()) {
                        insertEmployee(name, salary, designation);
                        RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
                        rd.forward(request, response);
                        return;
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                        rd.forward(request, response);
                        return;
                    }

                case "update":
                    if (id != 0) {
                        updateEmployee(name, salary, designation, id);
                        RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
                        rd.forward(request, response);
                        return;
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    
                case "delete":
                    if (id != 0) {
                        deleteEmployee(id);
                        RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
                        rd.forward(request, response);
                        return;
                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                        rd.forward(request, response);
                        return;
                    }

                default:
                    RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                    rd.forward(request, response);
                    return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }

    }
    
    private void initializeJdbc() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Java DB", "app", "app");
            
            /*String driver = "org.apache.derby.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/Java DB;create=true;user=app;password=app";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);*/

            insert = conn.prepareStatement("INSERT INTO STAFF (NAME, SALARY, DESIGNATION) VALUES (?,?,?)");
            update = conn.prepareStatement("UPDATE STAFF SET NAME=?, SALARY=?, DESIGNATION=? WHERE ID=?");
            delete = conn.prepareStatement("DELETE FROM STAFF WHERE ID=?");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insertEmployee(String name, int salary, String designation)
            throws SQLException {
        insert.setString(1, name);
        insert.setInt(2, salary);
        insert.setString(3, designation);
        insert.executeUpdate();
    }

    private void updateEmployee(String name, int salary, String designation, int id)
            throws SQLException {
        update.setString(1, name);
        update.setInt(2, salary);
        update.setString(3, designation);
        update.setInt(4, id);
        update.executeUpdate();
    }
    
    private void deleteEmployee(int id) throws SQLException {
        delete.setInt(1, id);
        delete.executeUpdate();
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
