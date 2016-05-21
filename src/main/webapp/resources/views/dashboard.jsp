<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href=<c:url value='/resources/css/style.css' />
	media="screen" type="text/css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</head>

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
<script
	src="<c:url value='/resources/js/controller/dashboard_controller.js' />"></script>
<script src="<c:url value='/resources/js/service/app_service.js' />"></script>

<%-- <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link> --%>

<!-- Date Time Picker starts -->

<!-- <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ngTimepicker.css' />"></link>
<script type="text/javascript"
	src="<c:url value='/resources/js/ngTimepicker.min.js' />"></script>

<!-- Date Time Picker ends -->


<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value='/resources/css/bootstrap.css' />"></link>
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
<%-- <script src="<c:url value= '/resources/js/jquery.js' />"></script> --%>
<script src="<c:url value= '/resources/js/modernizr.js' />"></script>
<%-- <script src="<c:url value= '/resources/js/bootstrap.js' />"></script> --%>
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
					1 : 'menuheading',
					2 : 'reviewcart',
					3 : 'orderhistory',
					4 : 'logout'
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
	<div class="wrapper" id="wrapper"
		ng-controller="DashboardController as ctrl"
		ng-init="ctrl.initFunction()">

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
												<li class="menuItem"><a href="#menuheading">Menu</a></li>
												<li class="menuItem"><a href="#reviewcart">Review
														Cart</a></li>
												<li class="menuItem"><a href="#orderhistory">Order
														History</a></li>
												<li class="menuItem"><a href=""
													ng-click="ctrl.logout()">Sign Out</a></li>
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

		<!--gallery-->
		<section class="gallery" id="menuheading">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Order Food</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
				</div>


				<form name="selectMenu">
					<div class="form-group row">
						<div class="col-sm-offset-2 col-sm-3"
							style="text-align: -webkit-right;">
							<label>Please select a type:</label>
						</div>
						<div class="col-sm-4">
							<!-- <select id='category' name='category'
								class="form-control col-sm-4" ng-model='ctrl.category'
								ng-init="ctrl.category='All'"
								ng-change="ctrl.getMenuByCategory(ctrl.category)">
								<option value='0'>All</option>
								<option value='1'>Drink</option>
								<option value='2'>Appetizer</option>
								<option value='3'>Main course</option>
								<option value='4'>Dessert</option>
							</select> -->
							<select id='category' name='category'
								class="form-control col-sm-4" ng-model='ctrl.category'
								ng-init=" ctrl.category = ctrl.menu_category[0]"
								ng-change="ctrl.getMenuByCategory(ctrl.category.id)"
								ng-options="option.name for option in ctrl.menu_category track by option.id">
							</select>
						</div>
					</div>
				</form>


				<div id="grid-gallery" class="grid-gallery">

					<section class="grid-wrap">
						<ul class="grid">
							<li class="grid-sizer"></li>
							<!-- for Masonry column width -->
							<div class="row">
								<li ng-repeat="item in ctrl.menu.items">

									<figure>
										<div class="col-sm-4" style="width: 85%;">
											<img style="height: 30%; padding-bottom: 3%;"
												src="<c:url value= '{{ item.image_path }}' />" alt="" />
											<!-- <figcaption><h3>Thundercats next level</h3><p>Portland nulla butcher ea XOXO, consequat Bushwick Pinterest elit twee pickled direct. </p></figcaption> -->
											<figcaption style="background: black; height: 38%">
												<div style="text-align: center;">
													<h3 class="product-name">{{ item.name }}</h3>
													<div style="padding-left: 38%; padding-top: 3%;">
														<p class="product-price">$ {{ item.unit_price }}</p>
													</div>
													<!-- <form class="add-to-cart"> -->
													<div>
														<label for="cal" style="padding-top: 5%;">{{item.calorie_count}}
															Cal/Serving</label>
													</div>
													<div>
														<label for="qty1"
															style="padding-bottom: 4%; padding-top: 5%;">Quantity</label>
														<input type="text" name="qty1" id="qty1" class="qty"
															ng-model="quantity" ng-init="quantity='1'" value="1" />

													</div>
													<p>
														<input type="submit"
															ng-click="ctrl.addToCart(item,quantity)"
															value="Add to cart" class="btn"
															style="background-color: #cc580c;" />
													</p>
													<!-- </form> -->
												</div>
											</figcaption>
										</div>
									</figure>

								</li>
							</div>

						</ul>
					</section>
					<!-- // end small images -->



				</div>
				<!-- // grid-gallery -->
			</div>
			<div class="row">
				<div class="col-sm-offset-9">
					<input type="submit" class="btn btn-default" value="Checkout"
						ng-click="ctrl.checkout()" />
				</div>
			</div>
		</section>


		<section class="aboutus" id="reviewcart">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Review Cart</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">




					<div>
						<div class="row">
							<div class="col-sm-3">
								<strong>Name</strong>
							</div>
							<div class="col-sm-3">
								<strong>Quantity</strong>
							</div>
							<div class="col-sm-3">
								<strong>Price</strong>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-3">
								<div class="row"
									ng-repeat="item1 in ctrl.finalCart.name track by $index">
									{{item1}}</div>
							</div>
							<div class="col-sm-3">
								<div class="row"
									ng-repeat="item2 in ctrl.finalCart.qty track by $index">
									{{item2}}</div>
							</div>
							<div class="col-sm-3">
								<div class="row"
									ng-repeat="item3 in ctrl.finalCart.price track by $index">
									$ {{item3}}</div>
							</div>
							<div class="col-sm-3">
								<div style="text-align: -webkit-center;"
									ng-repeat="item in ctrl.finalCart.id track by $index">
									<a href="" ng-click="ctrl.removeFromCart(item)"><span
										aria-hidden="true" class="glyphicon glyphicon-remove"
										style="color: chocolate;"></span></a>
								</div>
							</div>
						</div>
					</div>

					<!-- <div class="col-sm-4">
                                <label class="radio-inline">
                                    <input type="radio" name="algorithm" ng-model="algorithm" value="svc">SVC
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="algorithm" ng-model="algorithm" value="dec">Decision Tree
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="algorithm" ng-model="algorithm" value="knn">K-Nearest Neighbour
                                </label>
                            </div> -->

					<div class="row"
						style="border-top: 1px solid #ccc; padding-top: 4%;">
						<div class="col-sm-9">
							<div>
								<label class="radio-inline"> <input type="radio"
									name="usertime" ng-model="ctrl.userinputfortime" value="asap">As
									soon as possible
								</label> <label class="radio-inline"> <input type="radio"
									name="usertime" ng-model="ctrl.userinputfortime" value="at">Specific date and time
								</label><label class="radio-inline"> <input type="radio"
									name="usertime" ng-model="ctrl.userinputfortime" value="on">Specific date
								</label>
							</div>
							<div ng-if="ctrl.showtime">
								<ng-timepicker ng-model="ctrl.pickuptime" step="1"
									init-time="06:00"></ng-timepicker>
								{{ ctrl.pickuptime }}
							</div>
						</div>
						<div class="col-sm-3" ng-if="ctrl.showdate">

							<input type="date" ng-model="ctrl.inputDt" min="2016-05-11"
								max="2016-06-09"></input>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-offset-10">
							<input type="button" class="btn btn-warning" value="Place Order"
								ng-click="ctrl.placeOrder(ctrl.hope)" />
						</div>
					</div>
					<div ng-if="ctrl.displayTime">
						<span>Your Order will be ready by: {{ ctrl.dispTime |
							date:'MM/dd/yyyy HH:mm:ss'}}</span>
					</div>
					<div ng-if="ctrl.showErrorMsg">
						<span>The earliest time possible: {{
							ctrl.dispTime | date:'MM/dd/yyyy HH:mm:ss'}}</span>
					</div>
					<div ng-if="ctrl.displayErrMsg">
						<span>Due to unavailability of free slots, the order cannot
							be fulfilled today.</span><br> <span>Please try again with a
							different date!</span>
					</div>

				</div>
			</div>
		</section>


		<section class="aboutus" id="orderhistory">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Order History</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">

					<form name="selectMenu">
						<div class="form-group row">
							<div class="col-sm-offset-2 col-sm-3"
								style="text-align: -webkit-right;">
								<label>Please select a type:</label>
							</div>
							<div class="col-sm-4">
								<select id='orderStatus' name='orderStatus'
									class="form-control col-sm-4" ng-model='ctrl.status'
									ng-init=" ctrl.status = ctrl.status_category[0]"
									ng-change="ctrl.getHistByStatus(ctrl.status.id)"
									ng-options="option.name for option in ctrl.status_category track by option.id">
								</select>
							</div>
						</div>
					</form>

					<table class="table table-bordered">
						<thead>
							<tr>

								<th>Order Date</th>
								<th>Status</th>
								<!-- <th>Action</th> -->
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in ctrl.status.items">
								<td>{{item.order_date |date:'MM/dd/yyyy'}}</td>
								<td>{{ctrl.status_mapping[item.status]}}</td>

								<!-- <td style="text-align: -webkit-center;"><a href=""
									ng-click="ctrl.remove(item)"><span aria-hidden="true"
										class="glyphicon glyphicon-remove" style="color: chocolate;"></span></a></td> -->
							</tr>
						</tbody>
					</table>




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