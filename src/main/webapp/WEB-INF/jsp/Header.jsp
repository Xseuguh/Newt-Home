<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"
%>

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
          <security:authorize access="isAuthenticated()">
            <li>
              <a class="clickable" href="/messagerie/">
                <img
                  alt="Messagerie"
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
                  alt="Profil"
                  src="<%=request.getContextPath()%>/images/icones/profil.png"
                  width="40vw"
                />
                <span class="headerOptions">Mon profil</span>
              </a>
            </li>
            <li>
              <a class="clickable" href="/logout">
                <img
                  alt="Me d&eacute;conecter"
                  src="<%=request.getContextPath()%>/images/icones/logout.png"
                  width="40vw"
                />
                <span class="headerOptions">Me d&eacute;connecter</span>
              </a>
            </li>
          </security:authorize>

          <security:authorize access="isAnonymous()">
            <li>
              <a class="clickable" href="/register">
                <span class="headerOptions">Inscription/Connexion</span>
                <img
                  alt="Me connecter"
                  src="<%=request.getContextPath()%>/images/icones/AuthRe.png"
                  width="40vw"
                />
              </a>
            </li>
          </security:authorize>
        </ul>
      </div>
    </div>
  </nav>
</header>
