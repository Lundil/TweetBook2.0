<!-- #AE2573-->
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>TweetBook</title>
    <%@ page import="java.util.*" %>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.sql.ResultSet" %>
    <%@ page import="java.sql.ResultSetMetaData" %>
    <%@ page session="true" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <!--   import de servlet   -->
    <%@ page import="java.io.*" %>
    <%@ page import="tools.*" %>
    <%@ page import="javax.servlet.*" %>
    <%@ page import="javax.servlet.http.*" %>
    <%@ page import="javax.servlet.annotation.WebServlet" %>
    <meta name="generator" content="Bootply" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <link rel="icon" href="http://moodle.univ-lille1.fr/theme/image.php/ulille/theme/1484841149/favicon" />
  </head>
  <body>
    
  <jsp:useBean id="user" type="tools.User" scope="session" />
  
  <%

    User other = null;
    int countOtherFriend = 0;
    Model model = new Model();
    
    //récupère les infos de l'utilisateur pour afficher son profil
    if(request.getParameter("friendToDisplayProfil") != null){
      if(request.getParameter("friendToDisplayProfil").equals("true")){
        model.initialize();
        other = model.getFriend(Integer.valueOf(request.getParameter("friendToDisplayProfilId")));
      }
    }
    if(other != null){
      //ajout des publications et des amis au profil
      model.initialize();
      other.setPublications(model.getPublication(other));
      model.initialize();
      other.setFriends(model.getFriend(other));
      model.initialize();
      countOtherFriend = model.getNumberOfFriend(other);
      } %>

      <div class="wrapper">
        <div class="box">
          <div class="row row-offcanvas row-offcanvas-left">
          <!-- sidebar -->
          <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
          <ul class="nav">
            <li>
              <a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a>
            </li>
          </ul>
          <ul class="nav hidden-xs" id="lg-menu">
            <li class="active">
              <a href="amis.jsp"><i class="glyphicon glyphicon-list-alt"></i> Ajouter amis</a>
            </li>
            <li>
              <a href="profil.jsp"><i class="glyphicon glyphicon-list-alt"></i> Mur</a>
            </li>
            <li>
              <a href="fil.jsp"><i class="glyphicon glyphicon-list-alt"></i> Fil d actualités</a>
            </li>
            <li>
              <a href="admin.jsp"><i class="glyphicon glyphicon-list-alt"></i> Administration</a>
            </li>
          </ul>
          <ul class="list-unstyled hidden-xs" id="sidebar-footer">
            <li>
              <a href="http://www.bootply.com"><h3><a href="deconnexion" class="btn btn-info btn-lg">
              <span class="glyphicon glyphicon-log-out"></span> Log out
              </a></h3>
            </li>
          </ul>
          <!-- tiny only nav-->
          <ul class="nav visible-xs" id="xs-menu">
            <li><a href="#featured" class="text-center"><i class="glyphicon glyphicon-list-alt"></i></a></li>
            <li><a href="#stories" class="text-center"><i class="glyphicon glyphicon-list"></i></a></li>
            <li><a href="#" class="text-center"><i class="glyphicon glyphicon-paperclip"></i></a></li>
            <li><a href="#" class="text-center"><i class="glyphicon glyphicon-refresh"></i></a></li>
          </ul>
          </div>
            <!-- /sidebar -->
            <!-- main right col -->
            <div class="column col-sm-10 col-xs-11" id="main">
              <!-- top nav -->
              <div class="navbar navbar-blue navbar-static-top">  
                <div class="navbar-header">
                  <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                  <a href="./profil.jsp" class="navbar-brand logo">t</a>
                </div>
                <nav class="collapse navbar-collapse" role="navigation">
                  <form class="navbar-form navbar-left">
                    <div class="input-group input-group-sm" style="max-width:360px;">
                      <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                      <div class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                          <i class="glyphicon glyphicon-search"></i>
                        </button>
                      </div>
                    </div>
                  </form>
                </nav>
              </div>
              <!-- /top nav -->

              <div class="padding">
              <div class="full col-sm-9">

              <!-- content -->                      
              <div class="row">
              <!-- main col left --> 
              <div class="col-sm-5">
                <div class="panel panel-default">
                  <div class="panel-thumbnail">
                    <img src=<%= other.getProfilPhoto() %> class="img-responsive">
                  </div>
                  <div class="panel-body">
                    <p class="lead"><%= other.getFirstName() %>  <%= other.getLastName() %></p>
                  </div>
                </div>
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <h4>Vos amis (<%= countOtherFriend %>)</h4>
                  </div>
                  <div class="panel-body">
                    <ul class="list-unstyled">
                    <% model.initialize();
                    for(String photo : model.getProfilPhotoOf5Friend(other)) { %>
                        <li>
                          <img src="<%= photo%>" width="28px" height="28px">
                        </li>
                      <%}%>
                    </ul>
                  </div>
                </div>
                <div class="panel panel-default">
                  <div class="panel-heading"><h4>A propos</h4>
                  </div>
                  <div class="panel-body">
                  <%= other.getFirstName() %> <%= other.getLastName() %><br>
                  <%= other.getDate() %><br>
                  <%= other.getPlace() %><br>
                  <%= other.getMail() %><br>
                  <%= other.getAddress() %><br>
                  <%= other.getPhoneNumber() %><br>
                  </div>
                </div>
              </div>

              <!-- main col right -->
              <div class="col-sm-7">
                <% for(Publication publication : other.getPublications()) { %>
                <div class="panel panel-default">
                  <div class="panel-heading"><h4><%= publication.getTitle() %></h4></div>
                  <div class="panel-body">
                    <p>publié le <%= publication.getDate() %></p>
                    <hr>
                    <p><%= publication.getContent() %></p>
                    <div class="clearfix"></div>
                    <hr>
                  </div>
                </div>
                <% } %>
              </div><!--/row-->

              <h4 class="text-center">
              <a href="https://da2i.univ-lille1.fr/" target="ext">TweetBook DA2I 2016-2017</a>
              </h4>
              <hr>
              </div><!-- /col-9 -->
              </div><!-- /padding -->
            </div>
            <!-- /main -->
          </div>
        </div>
    </div>


    <!--post modal-->
    <div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>Update Status
          </div>
          <div class="modal-body">
            <form class="form center-block">
              <div class="form-group">
                <textarea class="form-control input-lg" autofocus="" placeholder="What do you want to share?"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <div>
              <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
              <ul class="pull-left list-inline">
                <li>
                  <a href=""><i class="glyphicon glyphicon-upload"></i></a>
                </li>
                <li>
                  <a href=""><i class="glyphicon glyphicon-camera"></i></a>
                </li>
                <li>
                  <a href=""><i class="glyphicon glyphicon-map-marker"></i></a>
                </li>
              </ul>
            </div>	
          </div>
        </div>
      </div>
    </div>

    <!-- script references -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
  </body>
</html>