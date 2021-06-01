<header class="fixed-top">
  <a href="/Accueil/">
    <h1 class="Htitle">New't Home</h1>
  </a>

  <div class="Menu">
    <% if(Boolean.parseBoolean(request.getParameter("isAuthenticated"))){ %>
    <a href="/messagerie/">
      <img
        alt="messagerie"
        src="<%=request.getContextPath()%>/images/icones/messagerie.png"
        width="50px"
      />
    </a>

    <a href="/profil/">
      <img
        class="rond"
        alt="profil"
        src="<%=request.getContextPath()%>/images/icones/profil.png"
        width="50px"
      />
    </a>

    <a href="/logout/">
      <img
        alt="logout"
        src="<%=request.getContextPath()%>/images/icones/logout.png"
        width="50px"
      />
    </a>
    <%}%>
  </div>
</header>
