package br.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bean.Usuario;
import br.com.dao.AgendaDAO;

@WebServlet("/AgendaController")
public class AgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Usuario contato;
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
                RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
            else if(cmd.equalsIgnoreCase("delete")) {   
                int userId = Integer.parseInt(request.getParameter("Id"));
                if(csv.ler().size() > 1) {
                	csv.excluirLinha(userId);                                        
                }
                response.sendRedirect("./pages/agenda.jsp");
             }

        } catch (Exception e) {
            pw.println("Exception: " + e); 
        }
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{  

        String cmd = request.getParameter("cmd");  

        if(cmd.equalsIgnoreCase("login")) {        	           
            String email = request.getParameter("inputEmail");
            String senha = request.getParameter("inputPassword");
            AgendaDAO dao = new AgendaDAO();
            List<Usuario> contatos = null;
            contatos = dao.ler();
            boolean valido = false;
            for(Usuario u: contatos) {
            	if(email.equals(u.getEmail()) && senha.equals(u.getSenha())) {
            		Cookie loginCookie = new Cookie("usuario", u.getNome());
                    loginCookie.setMaxAge(30*60);
        			response.addCookie(loginCookie);
        			valido = true;        			
            	}
            }
            if(valido) {
            	
            	response.sendRedirect("pages/agenda.jsp");
            	
            } else {
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
            contato = new Usuario();
            
            contato.setId(0);
            contato.setNome(request.getParameter("nome"));
            contato.setEmail(request.getParameter("email"));
            contato.setTelefone(request.getParameter("tel"));
            contato.setSenha(request.getParameter("inputPassword"));
            
            csv.inserirLinha(contato);

            response.sendRedirect("./pages/agenda.jsp");	            	            
         }         

        }catch (Exception e) {  
        	response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println(e);
            pw.close();
        }  
    }

}
