<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cosmic Comix</title>
        <script src="lib/require/require.js" type="text/javascript"></script>
        <script src="js/require-config.js" type="text/javascript"></script>
        <script src="lib/jquery/jquery.js" type="text/javascript"></script>
        <script src="lib/jqueryui/jquery-ui.js" type="text/javascript"></script>
        <script src="lib/underscore/underscore.js" type="text/javascript"></script>
        <script src="lib/backbone/backbone.js" type="text/javascript"></script>
        <script src="js/comix.js" type="text/javascript"></script>
        <link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
    </head>
    <body>
        <header class="block-center">
            <img src='assets/header.png' alt="Cosmic Comix"/>
        </header>
        <div id="AppContainer">
        </div>
        <div>
            <%@include file="../jspf/underscoreTemplates.jspf" %>
        </div>
    </body>
</html>
