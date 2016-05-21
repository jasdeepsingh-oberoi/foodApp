<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<%-- <script src="<c:url value='/resources/js/app.js' />"></script> --%>
<script src="<c:url value='/resources/js/service/user_service.js' />"></script>
<script
	src="<c:url value='/resources/js/controller/user_controller.js' />"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
	
	
	
	
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap.css' />"></link>
<%-- <link rel="stylesheet" href="<c:url value= '/resouces/css/font-awesome.css' />"></link> --%>
<link rel="stylesheet" href="<c:url value= '/resources/css/font-awesome.css' />"></link>
<link rel="stylesheet" href="<c:url value= '/resources/css/animate.css' />"></link>
<link rel="stylesheet" href="<c:url value = '/resources/css/theme.css' />"></link>


<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>




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
$(document).ready( function() {
  //enabling stickUp on the '.navbar-wrapper' class
	$('.navbar-wrapper').stickUp({
		parts: {
		  0: 'banner',
		  1: 'aboutus',
		  2: 'specialties',
		  3: 'gallery',
		  4: 'feedback',
		  5: 'contact'
		},
		itemClass: 'menuItem',
		itemHover: 'active',
		topMargin: 'auto'
		});
	});
});
</script>


<title>Insert title here</title>
</head>
<body>

<!--wrapper start-->
<div class="wrapper" id="wrapper">
	
	<!--header-->
	<header>
	<div class="banner row" id="banner">		
		<div class="parallax text-center" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummy1.jpg);">
			<div class="parallax-pattern-overlay">
				<div class="container text-center" style="height:580px;padding-top:170px;">
					<a href="#"><img id="site-title" class=" wow fadeInDown" wow-data-delay="0.0s" wow-data-duration="0.9s" src="<c:url value= '/resources/img/logo.png' />" alt="logo"/></a>
					<h2 class="intro wow zoomIn" wow-data-delay="0.4s" wow-data-duration="0.9s">Catering & Special Events</h2>
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
										<li class="menuItem"><a href="#aboutus">About Us</a></li>
										<li class="menuItem"><a href="#specialties">Specialties</a></li>
										<li class="menuItem"><a href="#gallery">Events Gallery</a></li>
										<li class="menuItem"><a href="#feedback">Feedback</a></li>
										<li class="menuItem"><a href="#contact">Hire Us</a></li>
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
	<section class="aboutus" id="aboutus">
	<div class="container">
		<div class="heading text-center">
			<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			<h2>About Leroy</h2>
			<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			<h3>Have you ever felt worried that your party will not raise up to your guest expectations? In design, vertical rhythm is the structure that guides a reader's eye through the content. Good vertical rhythm makes a layout more balanced and beautiful and its content more readable. The time signature in sheet music visually depicts a song's rhythm, while for us, the lines of the baseline grid depict the rhythm of our content and give us guidelines.</h3>
		</div>			
		<div class="row">
			<div class="col-md-4">
				<div class="papers text-center">
					<img src="http://wowthemes.net/demo/leroy/img/dummies/18.jpg" alt=""><br/>
					<h4 class="notopmarg nobotmarg">John Vandeley</h4>
					<p>
						Have you ever felt worried that your party will not raise up to your guest expectations? In design, vertical rhythm is the structure that guides a reader's eye through the content. Good vertical rhythm makes a layout more balanced and beautiful and its content more readable. The time signature in sheet music visually depicts a song's rhythm, while for us, the lines of the baseline grid depict the rhythm of our content and give us guidelines.
					</p>
				</div>
			</div>
			<div class="col-md-4">
				<div class="papers text-center">
					<img src="http://wowthemes.net/demo/leroy/img/dummies/19.jpg" alt=""><br/>
					<h4 class="notopmarg nobotmarg">Ron Hersu</h4>
					<p>
						Have you ever felt worried that your party will not raise up to your guest expectations? In design, vertical rhythm is the structure that guides a reader's eye through the content. Good vertical rhythm makes a layout more balanced and beautiful and its content more readable. The time signature in sheet music visually depicts a song's rhythm, while for us, the lines of the baseline grid depict the rhythm of our content and give us guidelines.
					</p>
				</div>
			</div>
			<div class="col-md-4">
				<div class="papers text-center">
					<img src="http://wowthemes.net/demo/leroy/img/dummies/18.jpg" alt=""><br/>
					<h4 class="notopmarg nobotmarg">Stephanie Hellen</h4>
					<p>
						Have you ever felt worried that your party will not raise up to your guest expectations? In design, vertical rhythm is the structure that guides a reader's eye through the content. Good vertical rhythm makes a layout more balanced and beautiful and its content more readable. The time signature in sheet music visually depicts a song's rhythm, while for us, the lines of the baseline grid depict the rhythm of our content and give us guidelines.
					</p>
				</div>
			</div>
		</div>
	</div>
	</section>
	
	<!--specialties-->
	<section class="specialties" id="specialties">
	<div class="container">
		<div class="heading text-center">
			<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			<h2>Our Specialties</h2>
			<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			<h3>Have you ever felt worried that your party will not raise up to your guest expectations? In design, vertical rhythm is the structure that guides a reader's eye through the content. Good vertical rhythm makes a layout more balanced and beautiful and its content more readable. The time signature in sheet music visually depicts a song's rhythm, while for us, the lines of the baseline grid depict the rhythm of our content and give us guidelines.</h3>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="restmenuwrap">
					<h3 class="maincat notopmarg text-center">APPETIZERS</h3>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/1.jpg)">
						</div>
						<h5>Sweet, Sticky and Spicy Chicken</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/2.jpg)">
						</div>
						<h5>Luxur Oyster</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/3.jpg)">
						</div>
						<h5>Sweet, Sticky and Spicy Chicken</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="restmenuwrap">
					<h3 class="maincat notopmarg text-center">MAIN</h3>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/4.jpg)">
						</div>
						<h5>Sweet, Sticky and Spicy Chicken</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/5.jpg)">
						</div>
						<h5>Luxur Oyster</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/6.jpg)">
						</div>
						<h5>Sweet, Sticky and Spicy Chicken</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="restmenuwrap">
					<h3 class="maincat notopmarg text-center">DESSERTS</h3>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/7.jpg)">
						</div>
						<h5>Sweet, Sticky and Spicy Chicken</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/8.jpg)">
						</div>
						<h5>Luxur Oyster</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
					<div class="restitem clearfix">
						<div class="rm-thumb" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummies/9.jpg)">
						</div>
						<h5>Sweet, Sticky and Spicy Chicken</h5>
						<p>
							Pepperoni, mozzarella cheese and Italian seasonings are rolled together, baked to delicious perfection, then cut into bite-sized delights. Your guests will beg for the recipe!"
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	
	<!--gallery-->
	<section class="gallery" id="gallery">
		<div class="container">
			<div class="heading text-center">
				<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
				<h2>Events Gallery</h2>
				<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			</div>
			
			<div id="grid-gallery" class="grid-gallery">

					<section class="grid-wrap">
						<ul class="grid">
							<li class="grid-sizer"></li><!-- for Masonry column width -->				
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/10.jpg" alt=""/>
									<figcaption><h3>Thundercats next level</h3><p>Portland nulla butcher ea XOXO, consequat Bushwick Pinterest elit twee pickled direct. </p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/11.jpg" alt=""/>
									<figcaption><h3>Bushwick selvage synth</h3><p>Bicycle rights flannel Shoreditch, art party laboris Bushwick sriracha.</p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/12.jpg" alt=""/>
									<figcaption><h3>Bottle wayfarers locavore</h3><p>Once there was a little asparagus, he was green and lonely.</p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/13.jpg" alt=""/>
									<figcaption><h3>Letterpress asymmetrical</h3><p>Chillwave hoodie ea gentrify aute sriracha consequat.</p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/14.jpg" alt=""/>
									<figcaption><h3>Vice velit chia</h3><p>Laborum tattooed iPhone, Schlitz irure nulla Tonx retro 90's chia cardigan quis before they sold out. </p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/15.jpg" alt=""/>
									<figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
								</figure>
							</li>
							
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/16.jpg" alt=""/>
									<figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
								</figure>
							</li>
							
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/17.jpg" alt=""/>
									<figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
								</figure>
							</li>
						
						</ul>
					</section><!-- // end small images -->
					
					<section class="slideshow">
						<ul>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/10.jpg" alt=""/>
									<figcaption><h3>Thundercats next level</h3><p>Portland nulla butcher ea XOXO, consequat Bushwick Pinterest elit twee pickled direct trade vero. </p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/11.jpg" alt=""/>
									<figcaption><h3>Bushwick selvage synth</h3><p>Bicycle rights flannel Shoreditch, art party laboris Bushwick sriracha authentic chambray hella umami sed distillery master cleanse.</p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/12.jpg" alt=""/>
									<figcaption><h3>Bottle wayfarers locavore</h3><p>Once there was a little asparagus, he was green and lonely.</p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/13.jpg" alt=""/>
									<figcaption><h3>Letterpress asymmetrical</h3><p>Chillwave hoodie ea gentrify aute sriracha consequat.</p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/14.jpg" alt=""/>
									<figcaption><h3>Vice velit chia</h3><p>Laborum tattooed iPhone, Schlitz irure nulla Tonx retro 90's chia cardigan quis asymmetrical paleo. </p></figcaption>
								</figure>
							</li>
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/15.jpg" alt=""/>
									<figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
								</figure>
							</li>
							
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/16.jpg" alt=""/>
									<figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
								</figure>
							</li>
							
							<li>
								<figure>
									<img src="http://wowthemes.net/demo/leroy/img/dummies/17.jpg" alt=""/>
									<figcaption><h3>Brunch semiotics</h3><p>Ex disrupt cray yr, butcher pour-over magna umami kitsch before they sold out commodo.</p></figcaption>
								</figure>
							</li>
						</ul>
						<nav>
							<span class="icon nav-prev"></span>
							<span class="icon nav-next"></span>
							<span class="icon nav-close"></span>
						</nav>
						<div class="info-keys icon">Navigate with arrow keys</div>
					</section><!-- // end slideshow -->
					
				</div><!-- // grid-gallery -->
			</div>
	</section>
	
	<!--feedback-->
	<section class="feedback" id="feedback">
	<div class="container w960">
		<div class="heading">
			<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			<h2>Clients Say</h2>
			<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
			<h3>Phasellus non dolor nibh. Nullam elementum tellus pretium feugiat.<br>
			 Cras dictum tellus dui, vitae sollicitudin ipsum tincidunt in. Sed tincidunt tristique enim sed sollcitudin.</h3>
		</div>
		<div class="row">
		<blockquote>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages." <cite>Jogn De, Birthday Event<br/><i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i></cite> </blockquote>
		<blockquote>Have you ever felt worried that your party will not raise up to your guest expectations? In design, vertical rhythm is the structure that guides a readers eye through the content. Good vertical rhythm makes a layout more balanced and beautiful and its content more readable. The time signature in sheet music visually depicts a songs rhythm, while for us, the lines of the baseline grid depict the rhythm of our content and give us guidelines. <cite>Marta Kay, Business Cocktalil<br/><i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i></cite> </blockquote>
		</div>
	</div>
	</section>
	
	<!--feedback-->
	<section class="contact" id="contact">
	<div class="container">
		<div class="heading">
				<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
				<h2>Hire Us</h2>
				<img class="dividerline" src="<c:url value= '/resources/img/sep.png' />" alt="">
				<h3>Phasellus non dolor nibh. Nullam elementum tellus pretium feugiat.<br>
				 Cras dictum tellus dui, vitae sollicitudin ipsum tincidunt in. Sed tincidunt tristique enim sed sollcitudin.</h3>
		</div>
	</div>
	 <div class="container w960">
      <div class="row">
		<div class="done">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">×</button>
				Your message has been sent. Thank you!
			</div>
		</div>
       <form method="post" action="contact.php" id="contactform">
          <input name="name" type="text" class="contact col-md-6" placeholder="Your Name *" >
          <input name="email" type="email" class="contact noMarr col-md-6" placeholder="E-mail address *" >
          <textarea name="comment" class="contact col-md-12" placeholder="Message *"></textarea>
          <input type="submit" id="submit" class="contact submit" value="Send message">
        </form>
      </div>
    </div>
	</section>
  
	<!--footer-->
	<section class="footer" id="footer">
	<p class="text-center">
		<a href="#wrapper" class="gototop"><i class="fa fa-angle-double-up fa-2x"></i></a>
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
			&copy; 2015 Copyright Your Website<br>
			 Theme by <a href="http://www.wowthemes.net">WowThemes.Net</a>
		</p>
	</div>
	</section>
	
</div><!--wrapper end-->

</body>
</html>