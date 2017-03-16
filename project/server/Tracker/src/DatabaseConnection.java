import java.io.*;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DatabaseConnection extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
        String JDBC_DRIVER="com.mysql.jdbc.Driver";  
        String DB_URL="jdbc:mysql://localhost/TestDatabase";

        String USER = "root";
        String PASS = "2302001";

        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String outputString = "";

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sqlRequest = "SELECT * FROM users";
            rs = stmt.executeQuery(sqlRequest);

            while(rs.next()){
                outputString += ("<tr><th>" + rs.getInt("id_user") + "</th>" 
                + "<th>" + rs.getString("first_name") + "</th>"
                + "<th>" + rs.getString("second_name") + "</th>" 
                + "<th>" + rs.getString("email") + "</th></tr>");
            }

            rs.close();
        }
        catch(SQLException se)
        {
            outputString += se.toString();
            se.printStackTrace();
        }
        catch(Exception e)
        {
            outputString += e.toString();
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){}

            try
            {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }

        request.setAttribute("user", outputString);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
