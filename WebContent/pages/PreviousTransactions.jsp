<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
	
<head>
        <meta charset="utf-8" />
        <title>Mobile</title>
        
	<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/reset_header.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css/style_header.css"> <!-- Resource style -->
        <!--footer styling-->
    <link rel="stylesheet" type="text/css" href="css/footer.css">
        <!--Sidebar styling-->  
    <link rel="stylesheet" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/style_table.css">
        <!--js files-->
        <script src="js/modernizr_header.js"></script> <!-- Modernizr -->
        <script src="js/jquery-2.1.1.js"></script>
        <script src="js/main_header.js"></script> <!-- Resource jQuery -->
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
        <script src="js/main_table.js"></script>
    </head>
    <body>

<%
		com.psl.model.Wallet w = (com.psl.model.Wallet)session.getAttribute("w");
%>
	
	


    
   
    







    <div class="megabox" id="megabox">
      <header id="header">
		<div class="cd-logo"><a href="#0"><img src="/img/Wallet-icon.png" alt="Logo"></a></div>

		<nav class="cd-main-nav-wrapper">
			<ul class="cd-main-nav">
				<li><a href="#0">Transactions</a></li>
				<li><a href="#0">Documents</a></li>
				<!--li><a href="#0">Blog</a></li>
				<li><a href="#0">Contact</a></li-->
				<li>
					<a href="#0" class="cd-subnav-trigger">
					<span>
					<%
					if(w!=null){
						out.println(w.getName());
					}
					%>
					
					
					</span></a>

					<ul>
						<li class="go-back"><a href="#0">Menu</a></li>
						<li><a href="#0">Wallet Money</a></li>
						<li><a href="#0">Settings</a></li>
						<li><a href="#0">Something Else</a></li>
						<li><a href="#0">Logout</a></li>
						<!--li><a href="#0">Category 5</a></li-->
						<li><a href="#0" class="placeholder">
						<span>
						<%
						if(w!=null){
						out.println(w.getName());
						}
						%>														
						</span></a></li>
					</ul>
				</li>
			</ul> <!-- .cd-main-nav -->
		</nav> <!-- .cd-main-nav-wrapper -->
		
		<a href="#0" class="cd-nav-trigger">Menu<span></span></a>
	</header>  
            
        <div class="container">

	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">Add Money Transactions</li>
		<li class="tab-link" data-tab="tab-2">Send/Receive Transactions</li>
	</ul>

	<div id="tab-1" class="tab-content current">
	<%	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost/mobilewallet";
		String username="root";
		String password="12345";
		String query="select * from transactions where id ="+ w.getId();
		Connection conn=DriverManager.getConnection(url,username,password);
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next())
		{
		%>
         <table>    
         	<thead>        
            	<tr>            
                	<th>Transaction ID</th>                        
                    <th>Transaction Type</th>            
                    <th>Sender's Name</th>           
                    <th>Receiver's Name</th>         
                </tr>    
            </thead>    
            <tbody>        
            	<tr><td><%=rs.getInt("tid") %></td>
    				<td><%=rs.getInt("id") %></td>
  					<td><%=rs.getInt("receive") %></td>
					<td><%=rs.getInt("send") %></td>
     				<td><%=rs.getInt("amount") %></td></tr>
        <%
		}
		%>
            </tbody>
          </table>
        
        <%
    	rs.close();
    	stmt.close();
    	conn.close();
    }
	catch(Exception e)
	{
    	e.printStackTrace();
    }
	%>
	</div>
	<div id="tab-2" class="tab-content">
		  <table>    
                        <thead>        
                        <tr>            
                            <th>Transaction ID</th>                        
                            <th>Transaction Type</th>            
                            <th>Sender's Name</th>
                            <th>Sender's Mobile No.</th>              
                            <th>Receiver's Name</th>
                            <th>Receiver's Mobile No.</th>                
                            <th>Amount</th>          
                        </tr>    
                        </thead>    
                        
                        
                        
                        
                        
                        <tbody>        
                        
                        </tbody>
                    </table>
	</div>
	

</div>

      <footer class="footer-distributed" id="footer">

			<div class="footer-left">

				<h3>Mobile<span>Wallet</span></h3>

				<p class="footer-links">
					<a href="#">Home</a>
					·
					<a href="#">Offers</a>
					·
					<a href="#">About</a>
					·
					<a href="#">Faq</a>
					·
					<a href="#">Contact</a>
				</p>

				<p class="footer-company-name">M-Wallet © 2015</p>
			</div>

			<div class="footer-center">

				<div>
					<i class="fa fa-map-marker"></i>
					<p><span>Persistent Systems, Phase-1, Hinjewadi</span> Pune, India</p>
				</div>

				<div>
					<i class="fa fa-phone"></i>
					<p>+12-34567890</p>
				</div>

				<div>
					<i class="fa fa-envelope"></i>
					<p><a href="mailto:support@company.com">support@company.com</a></p>
				</div>

			</div>

			<div class="footer-right">

				<p class="footer-company-about">
					<span>About the app</span>
					This is going to be a really cool app.
				</p>

				<div class="footer-icons">

					<a href="#"><i class="fa fa-facebook"></i></a>
					<a href="#"><i class="fa fa-twitter"></i></a>
					<a href="#"><i class="fa fa-linkedin"></i></a>
					<a href="#"><i class="fa fa-github"></i></a>

				</div>

			</div>

		</footer>

    </div>
    </body>
</html>



	
