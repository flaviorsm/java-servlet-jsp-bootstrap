<nav class="navbar navbar-inverse navbar-fixed-top">
 <div class="container-fluid">
  <div class="navbar-header">
   <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
   </button>
   <%
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("usuario")) 
				userName = cookie.getValue();
			}
		}
		if(userName == null)
			response.sendRedirect("../index.html");
	%>
	
   <a class="navbar-brand" href="../index.html">Olá <%= userName %>, bem vindo! </a>
  </div>
  <div id="navbar" class="navbar-collapse collapse">
   <ul class="nav navbar-nav navbar-right">
    <li><a href="./agenda.jsp">Agenda</a></li>
    <li><a href="./cadastrar.jsp">Cadastro</a></li>
    <li><a href="../index.html">Sair</a></li>
   </ul>
  </div>
 </div>
</nav>
