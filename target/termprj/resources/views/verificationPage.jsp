<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.4/angular-route.min.js"></script>
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/service/user_service.js' />"></script>
<script
	src="<c:url value='/resources/js/controller/app_controller.js' />"></script>
<script src="<c:url value='/resources/js/service/app_service.js' />"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

<!-- Date Time Picker starts -->

<!-- <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ngTimepicker.css' />"></link>
<script type="text/javascript"
	src="<c:url value='/resources/js/ngTimepicker.min.js' />"></script>

<!-- Date Time Picker ends -->


<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/bootstrap.css' />"></link>
<%-- <link rel="stylesheet" href="<c:url value= '/resouces/css/font-awesome.css' />"></link> --%>
<link rel="stylesheet"
	href="<c:url value= '/resources/css/font-awesome.css' />"></link>
<link rel="stylesheet"
	href="<c:url value= '/resources/css/animate.css' />"></link>
<link rel="stylesheet"
	href="<c:url value = '/resources/css/theme.css' />"></link>


<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Playball'
	rel='stylesheet' type='text/css'>




<!--Javascripts-->
<script src="<c:url value= '/resources/js/jquery.js' />"></script>
<script src="<c:url value= '/resources/js/modernizr.js' />"></script>
<script src="<c:url value= '/resources/js/bootstrap.js' />"></script>
<script src="<c:url value= '/resources/js/menustick.js' />"></script>
<script src="<c:url value= '/resources/js/parallax.js' />"></script>
<script src="<c:url value= '/resources/js/easing.js' />"></script>
<script src="<c:url value= '/resources/js/wow.js' />"></script>
<script src="<c:url value= '/resources/js/smoothscroll.js' />"></script>
<script src="<c:url value= '/resources/js/masonry.js' />"></script>
<script src="<c:url value= '/resources/js/imgloaded.js' />"></script>
<script src="<c:url value= '/resources/js/classie.js' />"></script>
<script src="<c:url value= '/resources/js/colorfinder.js' />"></script>
<script src="<c:url value= '/resources/js/gridscroll.js' />"></script>
<script src="<c:url value= '/resources/js/contact.js' />"></script>
<script src="<c:url value= '/resources/js/common.js' />"></script>


<title>Takeout Services</title>
</head>
<body ng-app="myApp">

	<!--wrapper start-->
	<div class="wrapper" id="wrapper" ng-controller="AppController">

		<nav class="navbar" style="background-color: black; height: 12%;">
			<div class="container-fluid">
				<h2 class="intro wow zoomIn" wow-data-delay="0.4s"
					wow-data-duration="0.9s" style="text-align: center;">Takeout
					Services</h2>
			</div>
		</nav>




		<!--about us-->
		<section class="aboutus" id="verify">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Verify</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">

					<div class="panel panel-warning">
						<div class="panel-heading">Please Enter the Verification
							Code Received in Email</div>
						<div class="panel-body">
							<form name="VerificationForm">

								<div class="form-group row">
									<div class="col-sm-offset-2 col-sm-1">
										<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
									</div>
									<label for="email" class="col-sm-2 form-control-label">Username</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="email"
											ng-model="email" placeholder="Please Enter Username" required>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-offset-2 col-sm-1">
										<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
									</div>
									<label for="code" class="col-sm-2 form-control-label">Code</label>
									<div class="col-sm-5">
										<input type="password" class="form-control" id="code"
											ng-model="code" placeholder="Please Enter Code" required>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-offset-5 col-sm-2">
										<button type="submit" class="btn btn-secondary"
											ng-click="verifyCode()">Verify</button>
									</div>
								</div>
							</form>
							<div ng-if="wrongverification" class="alert alert-danger" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Invalid Verification Code!</strong>
							</div>
						</div>
					</div>

				</div>
			</div>
		</section>



		<!--contact-->
		<section class="contact" id="contact">
			<div class="container">
				<div class="heading">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Contact Us</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h3>
						Hao Wang - 010<br> Jasdeepsingh Oberoi - 010<br> Neha
						Khowala - 010728367<br> Srushti Ekbote - 010726287
					</h3>
				</div>
			</div>
		</section>

		<!--footer-->
		<section class="footer" id="footer">
			<p class="text-center">
				<a href="#wrapper" class="gototop"><i
					class="fa fa-angle-double-up fa-2x"></i></a>
			</p>
			<div class="container">
				<ul>
					<li><a href="#"><i class="fa fa-twitter"></i></a></li>
					<li><a href="#"><i class="fa fa-facebook"></i></a></li>
					<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
					<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
					<li><a href="#"><i class="fa fa-flickr"></i></a></li>
				</ul>
				<p>
					&copy; 2016 Copyright Takeout Services<br> Made for CmpE 275
				</p>
			</div>
		</section>

	</div>
	<!--wrapper end-->

</body>
</html>