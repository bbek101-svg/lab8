/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author admin
 */
@WebFilter(filterName = "AdminFilter", servletNames = {"AdminServlet"})
public class AdminFilter implements Filter {
    
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
       HttpServletRequest httpRequest = (HttpServletRequest) request;
       HttpServletResponse httpResponse = (HttpServletResponse) response;
       HttpSession session = httpRequest.getSession();
       String email = (String)session.getAttribute("email");
       
       UserService service = new UserService();
       User user = null;
        try {
            user = service.get(email);
            // check to see if the user is an admin
            // if admin,call the do filter if not redirect to the login page
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(user.getRole().getRoleId() == 1){
            chain.doFilter(request, response);
        }else{
            if (email == null) {
               
                httpResponse.sendRedirect("login");
                
            }else{
                 httpResponse.sendRedirect("notes");
            }
           
                return;
        }
       
       
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        

    }
    
}
