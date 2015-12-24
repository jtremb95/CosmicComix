<%-- 
    Document   : launcher
    Created on : Jul 1, 2013, 12:48:44 PM
    Author     : Coveros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <% if (request.getSession().getAttribute("login") == null) {
        response.sendRedirect("/CosmicComix");
    }%>
<html>
    <head>
        <title>Choose A Comic To View</title>
        <script src="./lib/jquery/jquery.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('div.list>span').click(function() {
                    $(this).next().slideToggle();
                });
                $('li').append("<span class='action'><img src='./assets/listen.png' title='Listen to this comic' id='listen' /></span><span class='action'><img src='./assets/read.png' title='Read this comic' id='read' /></span>");
                $('li').hover(function() {
                    $(this).children('span.action').each(function() {
                        $(this).show();
                    });
                }, function() {
                    $(this).children('span.action').each(function() {
                        $(this).hide();
                    });
                });
            });
        </script>	
        <style type='text/css'>
            body { font:12px Verdana, Arial, Helvetica; background-color:#003399; padding:0px; margin:0px; color:white; }
            h1 { position:relative; top:-50px; }
            div { margin-left: auto; margin-right: auto; text-align:center; height:auto; width:auto; }
            div#titles { width:400px; height:200px; padding:20px; border:2px solid black; overflow-y: scroll; }
            div.list { text-align:left; width:300px; }
            div.list>span { font-weight:bold; cursor:pointer; }
            ul { list-style-type: none; margin:0px; padding:5px; }
            ul.books { display:none; }
            li { height:20px; }
            li span.number { font-weight:bold; }
            li span.title {  }
            li span.action { cursor:pointer; display:none; margin:0px 10px; }			
        </style>
    </head>
    <body>
        <div id='header'>
            <img src='./assets/header.png' />
            <h1>Online Reader</h1>
        </div>
        <div id='titles'>
            <div id='mine'>
                <h2>My Titles</h2>
                <div class="list">
                    <span id="SpidermanToggle">Amazing Spiderman</span>
                    <ul id='AmazingSpiderman' class='books'>
                        <li><span id="Here_Comes" class='number'>1: </span><span class='title'>Here Comes The Spider</span></li>
                        <li><span id="Makings" class='number'>3: </span><span class='title'>Makings of a Spider</span></li>
                        <li><span id="Fist" class='number'>7: </span><span class='title'>Enter The Fist</span></li>
                    </ul>
                </div>
            </div>
            <div id='all'>
                <h2>Browse Titles</h2>
                <div class='list'>
                    <span>Mighty Thor</span><br />
                    <span>Incredible Hulk</span><br/>
                    <span>Fantastic Four</span><br/>
                    <span>Ant Man</span><br/>
                    <span>Black Panther</span><br/>
                    <span>Silver Surfer</span><br/>
                    <span>Wolverine</span><br/>
                    <span>Uncanny X-Men</span><br/>
                    <span>Miss Marvel</span><br/>
                </div>
            </div>			
        </div>
    </body>
</html>
