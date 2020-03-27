/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class LoginBean {
    
    private String username;
    private String password;
    private String message="";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
  public  String login()
    {
        try{
            message="";
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.login(username, password);
        if(request.isUserInRole("AdminRole"))
            return "/admins/AdminHome.xhtml";
        else if(request.isUserInRole("SupervisorRole"))
            return "/users/UserHome.xhtml";
        else{
            message= "Either Login or Password is wrong";
        return "/index.xhtml";
        }
        }
        catch(Exception e)
        {
            message= "Either Login or Password is wrong";
        }
            
        
        return null;
    }
  public String Logout() throws IOException,ServletException{
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      request.logout();
      
      
//      HttpSession objHttpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//        objHttpSession.invalidate();
//        FacesContext.getCurrentInstance().getExternalContext().redirect("/SecureJSFApp/faces/index.xhtml");
   return "/index.xhtml";  
  }
}
