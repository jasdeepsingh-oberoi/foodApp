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
	src="<c:url value='/resources/js/controller/admin_controller.js' />"></script>
<script src="<c:url value='/resources/js/service/app_service.js' />"></script>

<%-- <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link> --%>

<!-- Date Time Picker starts -->

<!-- <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/ngTimepicker.css' />"></link>
<script type="text/javascript"
	src="<c:url value='/resources/js/ngTimepicker.min.js' />"></script>

<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css"
	rel="stylesheet">

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


<script type="text/javascript">
	jQuery(function($) {
		$(document).ready(function() {
			//enabling stickUp on the '.navbar-wrapper' class
			$('.navbar-wrapper').stickUp({
				parts : {
					0 : 'banner',
					1 : 'adminmenu',
					2 : 'popreports',
					3 : 'statreports',
					3 : 'logout'
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
		ng-controller="AdminController as ctrl" ng-init="ctrl.initFunction()">

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
												<li class="menuItem"><a href="#adminmenu">Review
														Menu</a></li>
												<li class="menuItem"><a href="#popreports">Popularity
														Report</a></li>
												<li class="menuItem"><a href="#statreports">Status
														Report</a></li>
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

		<!--about us-->
		<section class="aboutus" id="adminmenu">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Review Menu</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">

					<form name="selectMenu">
						<div class="form-group row">
							<div class="col-sm-offset-2 col-sm-3"
								style="text-align: -webkit-right;">
								<label>Please select a type:</label>
							</div>
							<div class="col-sm-4">
								<select id='category' name='category'
									class="form-control col-sm-4" ng-model='ctrl.category'
									ng-init="ctrl.category = ctrl.menu_category[0]"
									ng-change="ctrl.getMenuByCategory(ctrl.category.id)"
									ng-options="option.name for option in ctrl.menu_category track by option.id">
								</select>
							</div>
							<div class="col-sm-3">
								<a role="button" data-toggle="modal" data-target="#addItem">Add
									New Menu Item</a>
							</div>
						</div>
					</form>

					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Category</th>
								<th>Item</th>
								<th>Prep Time (min)</th>
								<th>Calories (gms)</th>
								<th>Price ($)</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="item in ctrl.menu.items">
								<td>{{ctrl.category_mapping[item.category]}}</td>
								<td><img src="<c:url value= '{{ item.image_path }}' />"
									alt="" style="height: 23%; width: 18%;"><span>{{item.name}}</span></td>
								<td>{{item.prep_time}}</td>
								<td>{{item.calorie_count}}</td>
								<td>{{item.unit_price}}</td>
								<td style="text-align: -webkit-center;"><a href=""
									ng-click="ctrl.remove(item)"><span aria-hidden="true"
										class="glyphicon glyphicon-remove" style="color: chocolate;"></span></a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</section>

		<section class="aboutus" id="popreports">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Popularity Report</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">

					<form name="selectMenu">
						<div class="form-group row">
							<div class="col-sm-offset-2 col-sm-3"
								style="text-align: -webkit-right;">
								<label>Please select a Category:</label>
							</div>
							<div class="col-sm-4">
								<select id='report_category' name='report_category'
									class="form-control col-sm-4" ng-model='ctrl.report_category'
									ng-init="ctrl.report_category = ctrl.report_menu_category[0]"
									ng-options="option.name for option in ctrl.report_menu_category track by option.id">
									<!-- ng-change="ctrl.getReportByCategory(ctrl.report_category.id)" -->
								</select>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-sm-offset-1 col-sm-3">
							<label>Please select a Date range</label>
						</div>
						<div class="col-sm-3">
							<input type="date" ng-model="ctrl.startDt" />
						</div>
						<div class="col-sm-3">
							<input type="date" ng-model="ctrl.endDt" />
						</div>
						<div class="col-sm-2">
							<input type="button" class="btn btn-warning"
								ng-click="ctrl.getReportByCategory(ctrl.report_category)" value="Show Report" />
						</div>
					</div>
					<div class="row" style="padding-top: 5%;"
						ng-if="ctrl.showPopRep == true">
						<div class="col-sm-offset-3 col-sm-6">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Item</th>
										<th>Order Count</th>
									</tr>
								</thead>
								<tbody>
									<tr
										ng-repeat="item in ctrl.reportdata.items | startFrom:ctrl.currentPage*10 | limitTo:10 track by [$index]">
										<td>{{item.item_name}}</td>
										<td>{{item.item_count}}</td>
									</tr>
								</tbody>
							</table>
							<button ng-disabled="ctrl.currentPage == 0"
								ng-click="ctrl.currentPage=ctrl.currentPage-1">Previous</button>
							<!-- {{ctrl.currentPage+1}}/{{ctrl.numberOfPages()}} -->
							<button ng-click="ctrl.currentPage=ctrl.currentPage+1"
								ng-disabled="ctrl.currentPage >= ctrl.reslength/9">Next
							</button>

						</div>
					</div>
				</div>
			</div>
		</section>

		<section class="aboutus" id="statreports">
			<div class="container">
				<div class="heading text-center">
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">
					<h2>Status Report</h2>
					<img class="dividerline"
						src="<c:url value= '/resources/img/sep.png' />" alt="">

					<div class="row">
						<div class="col-sm-offset-1 col-sm-3">
							<label>Please select a Date range</label>
						</div>
						<div class="col-sm-3">
							<input type="date" ng-model="ctrl.beginDt" />
						</div>
						<div class="col-sm-3">
							<input type="date" ng-model="ctrl.finishDt" />
						</div>
						<div class="col-sm-2">
							<input type="button" class="btn btn-warning"
								ng-click="ctrl.getStatReport()" value="Show Report" />
						</div>
					</div>
					<div class="row" style="padding-top: 5%;"
						ng-if="ctrl.showStatRep == true">
						<div>

							<table class="table table-striped">
								<thead>
									<tr>
										<th>Id</th>
										<th>Email</th>
										<th ng-click="ctrl.sort('order_date')">Order Time <span
											class="glyphicon sort-icon"
											ng-class="{'glyphicon-chevron-up':ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span>
										</th>
										<th ng-click="ctrl.sort('start_time')">Fulfillment Start
											Time <span class="glyphicon sort-icon"
											ng-class="{'glyphicon-chevron-up':ctrl.reverse,'glyphicon-chevron-down':!ctrl.reverse}"></span>
										</th>
										<th>Ready Time</th>
										<th>Pickup Time</th>
										<th>Price</th>
										<th>Status</th>
										<th>Menu</th>
									</tr>
								</thead>
								<tbody>
									<tr
										ng-repeat="item in ctrl.statReportdata.items | orderBy:ctrl.sortKey:ctrl.reverse | startFrom:ctrl.currentPage1*5 | limitTo:5 track by [$index]">
										<td>{{item.order_id}}</td>
										<td>{{item.email}}</td>
										<td>{{item.order_placed_date | date:'MM/dd/yyyy HH:mm:ss'}}</td>
										<td>{{item.start_time | date:'MM/dd/yyyy HH:mm:ss'}}</td>
										<td>{{item.end_time | date:'MM/dd/yyyy HH:mm:ss'}}</td>
										<td>{{item.pickup_time | date:'MM/dd/yyyy HH:mm:ss'}}</td>
										<td>$ {{item.total_price}}</td>
										<td>{{ctrl.status_mapping[item.status]}}</td>
										<td><a style="text-decoration: underline;" role="button"
											data-toggle="modal" data-target="#menuDet"
											ng-click="ctrl.showMenuDet(item)">Click Here</a></td>
									</tr>
									<tr >
										 <div ng-if="ctrl.menuDetailDiv == true" class="row">
										<div class="col-sm-offset-2 col-sm-8">
											<div class="row panel panel-warning" style=" border: none">
												<div class="col-sm-offset-3 col-sm-3 panel-heading">Menu Item</div>
												<div class="col-sm-3 panel-heading">Quantity</div>
											</div>
											<div class="row" ng-repeat="item1 in ctrl.menuDet.data track by [$index]" style="border-bottom: 1px solid #ccc; padding-top: 4%;">
												<div class="col-sm-offset-2 col-sm-4">{{item1.menu_item_name}}</div>
												<div class="col-sm-4">{{item1.quantity}}</div>
											</div>
											<div style="padding-bottom:50px;"></div>
											</div>
										</div> 
									</tr>
								</tbody>
							</table>
							<button ng-disabled="ctrl.currentPage1 == 0"
								ng-click="ctrl.currentPage1=ctrl.currentPage1-1">Previous</button>
							<!-- {{ctrl.currentPage+1}}/{{ctrl.numberOfPages1()}} -->
							<button ng-click="ctrl.currentPage1=ctrl.currentPage1+1"
								ng-disabled="ctrl.currentPage1 >= ctrl.reslength1/4">Next
							</button>
						</div>
					</div>
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
	<div id="addItem" class="modal fade" role="dialog"
		ng-controller="AdminController as ctrl">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">Add a New Menu Item</div>
				<div class="modal-body">
					<form name="addMenuform">
						<div class="form-group row">
							<label for="category"
								class="col-sm-offset-1 col-sm-4 form-control-label">Category</label>
							<div class="col-sm-6">
								<select id='newCategory' name='newCategory' class="form-control"
									ng-model="ctrl.newCategory"
									ng-init="ctrl.newCategory = 'Drink'">
									<option>Drink</option>
									<option>Appetizer</option>
									<option>Main Course</option>
									<option>Dessert</option>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label for="name"
								class="col-sm-offset-1 col-sm-4 form-control-label">Name</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name"
									ng-model="ctrl.newName" required>
							</div>
						</div>
						<div class="form-group row">
							<label for="preptime"
								class="col-sm-offset-1 col-sm-4 form-control-label">Preparation
								Time</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="preptime"
									ng-model="ctrl.newPrepTime" required>
							</div>
						</div>
						<div class="form-group row">
							<label for="calories"
								class="col-sm-offset-1 col-sm-4 form-control-label">Calories</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="calories"
									ng-model="ctrl.newCalorie" required>
							</div>
						</div>
						<div class="form-group row">
							<label for="price"
								class="col-sm-offset-1 col-sm-4 form-control-label">Unit
								Price</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="price"
									ng-model="ctrl.newPrice" required>
							</div>
						</div>
					</form>
					<form method="post" enctype="multipart/form-data"
						action="/termprj/adminDashboard" ng-if="ctrl.dataAdded">
						<div class="form-group row">
							<label for="picture"
								class="col-sm-offset-1 col-sm-4 form-control-label">Upload
								Image</label>
							<div class="col-sm-6">
								<input type="file" name="file" id="file"> <input
									type="submit" class="btn btn-success" value="Save">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-success"
						ng-click="ctrl.addMenuItem()" value="Add" ng-if="!ctrl.dataAdded">
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						aria-hidden="true">Cancel</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>