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
    <%@ page errorPage="erreur.jsp" %>
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
    <script>
      $(document).ready(function(){
  alert("Coucou");
  function ajax(){ 
      $.ajax({
         url : 'http://localhost:8080/jquery/servlet/AppelAjaxPersonne',
         type : 'GET',
         dataType : 'text',
         data: {
              deb: $("#deb").val()
          },
         //succes de la requete
         success : function(code_html){
            alert("Success");
            $("textarea#change").empty();
            var test = code_html.split(";");
            $.each(test, function() {
          $("textarea#change").append(this+"\n");
        });
            
          },
         //echec de la requete
         error : function(resultat, statut, erreur){
          alert("Error");
        },
         //une fois que tout est finis
         complete : function(resultat, statut){
          alert("Complete");
        }

        });
    }
    //Chaque lettre tapée
    $("input").keypress(function(){
      //lancer requete
      ajax();
    });
});
      $(document).ready(function() {

var data = {}; 
$("#browsers option").each(function(i,el) {  
   data[$(el).data("value")] = $(el).val();
});
// `data` : object of `data-value` : `value`
console.log(data, $("#browsers option").val());


    $('#submit').click(function()
    {
        var value = $('#selected').val();
        alert($('#browsers [value="' + value + '"]').data('value'));
    });
});
    </script>
  </head>
  <body>
  <jsp:useBean id="user" type="tools.User" scope="session" />
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
              <a href="fil.jsp"><i class="glyphicon glyphicon-list-alt"></i> Fil d'actualités</a>
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
                  <form class="navbar-form navbar-left" method="GET" action="./other.jsp">
                    <div class="input-group input-group-sm" style="max-width:360px;">
                      <input list="name" type="text" class="form-control" placeholder="Rechercher un ami" name="friendToDisplayProfilId">
                      <input type="hidden" value="true" name="friendToDisplayProfil">
                      <datalist id="name" name="friendToDisplayProfilId">
                        <%  
                          Model model = new Model();
                          model.initialize();
                          for(User friend : model.getAllUsers()){ %>
                          <!-- value = affichage datavalue = donnée -->
                            <option value="<%=friend.getFirstName()%>" data-value="<%= friend.getId() %>"> 
                        <%}
                        %>
                      </datalist>
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
              <div class="col-sm-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                    <h4>Vos amis</h4>
                  </div>
                  <div class="panel-body">
                  <%for(User friend : user.getFriends()){ %>
                      <p class="lead"><%= friend.getFirstName()%> <%= friend.getLastName() %></p>
                  <%} %>
                  </div>
                </div>
              </div>
              <!-- main col right --> 
              <div class="col-sm-6">
                <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Ajouter amis</h4>
                  </div>
                  <div class="panel-body">
                  <p class="lead">
                  <%
                  model.initialize();
                  for(User friend : model.getAllUsers()){
                    if(friend.getId() != user.getId() && !user.containsFriend(friend.getId())){
                  %>
                    <form class="navbar-form navbar-left" method="GET" action="./tools/ControlFriend">
                      <input class="btn btn-primary pull-right" type="submit" value="<%= friend.getFirstName() %> <%= friend.getLastName() %>"></input>
                      <input type="hidden" value="<%= friend.getId() %>" name="addFriendId"></input>
                      <input type="hidden" value="true" name="addFriend"></input>
                    </form>
                    
                  <% } } %>
                  </p>
                  </div>
                </div>
              </div>
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