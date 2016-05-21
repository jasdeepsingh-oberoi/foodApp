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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="<c:url value='/resources/js/app.js' />"></script>
<script src="<c:url value='/resources/js/service/user_service.js' />"></script>
<script
	src="<c:url value='/resources/js/controller/app_controller.js' />"></script>
<script src="<c:url value='/resources/js/service/app_service.js' />"></script>

<%-- <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link> --%>

<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ngTimepicker.css' />"></link>
<script type="text/javascript"
	src="<c:url value='/resources/js/ngTimepicker.min.js' />"></script>


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

<script type="text/javascript">
	jQuery(function($) {
		$(document).ready(function() {
			//enabling stickUp on the '.navbar-wrapper' class
			$('.navbar-wrapper').stickUp({
				parts : {
					0 : 'banner',
					1 : 'signup',
					2 : 'signin',
					3 : 'feedback',
					4 : 'contact'
				},
				itemClass : 'menuItem',
				itemHover : 'active',
				topMargin : 'auto'
			});
		});
	});
</script>


<title>Takeout Services</title>
</head>
<body ng-app="myApp">

	<!--wrapper start-->
	<div class="wrapper" id="wrapper" ng-controller="AppController">

		<!--header-->
		<header>
			<div class="banner row" id="banner">
				<div class="parallax text-center"
					style="background-image: url(http://wowthemes.net/demo/leroy/img/dummy1.jpg);">
					<div class="parallax-pattern-overlay">
						<div class="container text-center"
							style="height: 580px; padding-top: 170px;">
							<a href="#"><img id="site-title" class=" wow fadeInDown"
								wow-data-delay="0.0s" wow-data-duration="0.9s"
								src="<c:url value= '/resources/img/logo.png' />" alt="logo" /></a>
							<h2 class="intro wow zoomIn" wow-data-delay="0.4s"
								wow-data-duration="0.9s">Takeout Services</h2>
						</div>
					</div>
				</div>
			</div>
			<div class="menu">
				<div class="navbar-wrapper">
					<div class="container">
						<div class="navwrapper">
							<div class="navbar navbar-inverse navbar-static-top">
								<div class="container">
									<div class="navArea">
										<div class="navbar-collapse collapse">
											<ul class="nav navbar-nav">
												<li class="menuItem active"><a href="#wrapper">Home</a></li>
												<li class="menuItem"><a href="#signup">Sign Up</a></li>
												<li class="menuItem"><a href="#signin">Log In</a></li>
												<li class="menuItem"><a href="#feedback">Feedback</a></li>
												<li class="menuItem"><a href="#contact">Contact Us</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>

		<!--about us-->
		<section class="aboutus" id="signup">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Sign Up</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">

					<div class="panel panel-warning">
						<div class="panel-heading">New User? Create an Account</div>
						<div class="panel-body">
							<form name="SignUpForm">
								<div class="form-group row">
									<div class="col-sm-offset-2 col-sm-1">
										<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
									</div>
									<label for="signUpUsername" class="col-sm-2 form-control-label">Username</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="signUpUsername"
											ng-model="signUpUsername" placeholder="Please Enter Username"
											required>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-offset-2 col-sm-1">
										<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
									</div>
									<label for="signUpPassword" class="col-sm-2 form-control-label">Password</label>
									<div class="col-sm-5">
										<input type="password" class="form-control"
											id="signUpPassword" ng-model="signUpPassword"
											placeholder="Please Enter Password" required>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-offset-5 col-sm-2">
										<button type="submit" class="btn btn-secondary"
											ng-click="signUp()">Create Account</button>
									</div>
								</div>
							</form>
							<div class="alert alert-success" role="alert" ng-if="signedup">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Signed Up Successfully!</strong> Please check your email
								for verification!
							</div>
							<div class="alert alert-danger" role="alert" ng-if="alreadyexists">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Username Already Exists!</strong> Please try again with
								another username!
							</div>
						</div>
					</div>

				</div>
				<div class="row"></div>
			</div>
		</section>

		<!--specialties-->
		<section class="specialties" id="signin">
			<div class="container w960">
				<div class="heading">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Log In</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<div class="panel panel-warning">
						<div class="panel-heading">Already have an account? Log In</div>
						<div class="panel-body">
							<form name="LoginForm">
								<div class="form-group row">
									<div class="col-sm-offset-2 col-sm-1">
										<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
									</div>
									<label for="signInUsername" class="col-sm-2 form-control-label">Username</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="signInUsername"
											ng-model="signInUsername" placeholder="Please Enter Username"
											required>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-offset-2 col-sm-1">
										<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
									</div>
									<label for="signInPassword" class="col-sm-2 form-control-label">Password</label>
									<div class="col-sm-5">
										<input type="password" class="form-control"
											id="signInPassword" ng-model="signInPassword"
											placeholder="Please Enter Password" required>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-offset-5 col-sm-2">
										<button type="submit" class="btn btn-secondary"
											ng-click="signIn()">Login</button>
									</div>
								</div>
							</form>
							<div class="alert alert-danger" role="alert" ng-if="invalidcredentials">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>Invalid Credentials!</strong> Please try again!
							</div>
						</div>
					</div>
				</div>
				<div class="row"></div>
			</div>

		</section>

		<!--feedback-->
		<section class="feedback" id="feedback">
			<div class="container w960">
				<div class="heading">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Clients Say</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h3>
						Phasellus non dolor nibh. Nullam elementum tellus pretium feugiat.<br>
						Cras dictum tellus dui, vitae sollicitudin ipsum tincidunt in. Sed
						tincidunt tristique enim sed sollcitudin.
					</h3>
				</div>
				<div class="row">
					<blockquote>
						Lorem Ipsum is simply dummy text of the printing and typesetting
						industry. Lorem Ipsum has been the industry's standard dummy text
						ever since the 1500s, when an unknown printer took a galley of
						type and scrambled it to make a type specimen book. It has
						survived not only five centuries, but also the leap into
						electronic typesetting, remaining essentially unchanged. It was
						popularised in the 1960s with the release of Letraset sheets
						containing Lorem Ipsum passages." <cite>Jogn De, Birthday
							Event<br /> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i>
						</cite>
					</blockquote>
					<blockquote>
						Have you ever felt worried that your party will not raise up to
						your guest expectations? In design, vertical rhythm is the
						structure that guides a readers eye through the content. Good
						vertical rhythm makes a layout more balanced and beautiful and its
						content more readable. The time signature in sheet music visually
						depicts a songs rhythm, while for us, the lines of the baseline
						grid depict the rhythm of our content and give us guidelines. <cite>Marta
							Kay, Business Cocktalil<br /> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i> <i
							class="fa fa-star"></i> <i class="fa fa-star"></i>
						</cite>
					</blockquote>
				</div>
			</div>
		</section>

		<!--feedback-->
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