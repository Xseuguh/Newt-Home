<header class="fixed-top">
  <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container float-right">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button
          type="button"
          class="navbar-toggle"
          data-toggle="collapse"
          data-target="#navbarToggler"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a href="/Accueil" class="navbar-brand clickable">
          <span class="glyphicon glyphicon-home" aria-hidden="true"> </span>
          Newt'Home
        </a>
      </div>
      <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <%
            if(Boolean.parseBoolean(request.getParameter("isAuthenticated"))){
            %>
            <a class="clickable" href="/messagerie/">
              <img
                alt="messagerie"
                src="<%=request.getContextPath()%>/images/icones/messagerie.png"
                width="40vw"
              />
              <span class="headerOptions">Mes messages</span>
            </a>
          </li>
          <li>
            <a class="clickable" href="/profil/">
              <img
                class="rond"
                alt="profil"
                src="<%=request.getContextPath()%>/images/icones/profil.png"
                width="40vw"
              />
              <span class="headerOptions">Mon profil</span>
            </a>
          </li>
          <li>
            <a class="clickable" href="/logout">
              <img
                alt="logout"
                src="<%=request.getContextPath()%>/images/icones/logout.png"
                width="40vw"
              />
              <span class="headerOptions">Me d&eacute;connecter</span>
            </a>
          </li>
          <%}%>
        </ul>
      </div>
    </div>
  </nav>
</header>
