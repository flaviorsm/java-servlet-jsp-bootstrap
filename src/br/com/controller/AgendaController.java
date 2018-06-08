package br.com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bean.Agenda;
import br.com.dao.AgendaDAO;

@WebServlet("/AgendaController")
public class AgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Agenda contato;
	private String forward = "";
	private AgendaDAO csv;
	
    public AgendaController() {
        super();
        csv = new AgendaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {

            String cmd = request.getParameter("cmd");  

            if(cmd.equalsIgnoreCase("listar")) {				
                forward = "./pages/lista.jsp";				
                request.setAttribute("contatos", csv.ler());				
            }

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } catch (Exception e) {
                pw.println("Erro: " + e.getMessage()); 
        }
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{  

        String cmd = request.getParameter("cmd");  

        if(cmd.equalsIgnoreCase("login")) {
        	           
            String usuario = request.getParameter("inputUsuario");
            String email = request.getParameter("inputEmail");
            String senha = request.getParameter("inputPassword");
                       
            if(email.equals("admin@admin.com") && senha.equals("admin")) {
            	
            	Cookie loginCookie = new Cookie("usuario", usuario);
                loginCookie.setMaxAge(30*60);
    			response.addCookie(loginCookie);
    			
                response.sendRedirect("pages/agenda.jsp");
            }
            else {
            	response.setContentType("text/html");
                PrintWriter pw = response.getWriter();
                pw.println("<div class=\"container\">\r\n" + 
                		"       <div class=\"card card-container\">            \r\n" +  
                		"           <p>Usuário ou senha inválidos.</p>\r\n" + 
                		"           <form class=\"form-signin\" action=\"index.html\" method=\"post\">\r\n" + 	
                		"               <button class=\"btn btn-lg btn-primary btn-block btn-signin\" type=\"submit\">VOLTAR</button>\r\n" + 
                		"           </form>\r\n" + 
                		"       </div>\r\n" + 
                		"    </div>");
                pw.close();
            }	     		
         } 
         else if(cmd.equalsIgnoreCase("cadastrar")){  
            contato = new Agenda();
            contato.setId(0);
            contato.setNome(request.getParameter("nome"));
            contato.setEmail(request.getParameter("email"));
            contato.setTelefone(request.getParameter("tel"));

            csv.inserirLinha(contato);

            response.sendRedirect("./pages/agenda.jsp");	            	            
         }
         else if(cmd.equalsIgnoreCase("delete")) {              
            int userId = Integer.parseInt(request.getParameter("id"));
            csv.excluirLinha(userId);
            request.setAttribute("contatos", csv.ler());
         }

        }catch (Exception e) {  
        	response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println(e.getMessage());
            pw.close();
        }  
    }

}
