<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-route.js"></script>
</head>
<body>

<div ng-controller="UserController as ctrl">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img style="width: 100px;"
					src="resources/images/niitforums.jpg" alt="Logo"><i></a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#/">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown">Blogs<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#/create_blog">Add New Blogs</a></li>
						<li><a href="#/list_blog">Show List of Blogs</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown">Job Opportunities<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#/view_applied_jobs">View applied Jobs</a></li>
						<li><a href="#/view_job_details">View Job Details</a></li>
						<li><a href="#/post_job">Post a new Job</a></li>
						<li><a href="#/search_job">Search Job</a></li>
					</ul></li>

				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown">Friends<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#/search_friend">Search Friend</a></li>
						<li><a href="#/view_friend">View Friend</a></li>
						<li><a href="#/add_friend">Add Friend</a></li>
					</ul></li>

				<li><a href="#/event">Events</a></li>
				
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown">Chat Forums<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#/chat">Create Chat Forum</a></li>
						<li><a href="#/list_chat_forum">View Existing Forums</a></li>
					</ul></li>
				<li><a href="#/about">About</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				
					<li><a href="#/register"><span
							class="glyphicon glyphicon-user"></span>Sign Up</a></li>
					<li><a href="#/login"><span
							class="glyphicon glyphicon-log-in"></span>Login</a></li>
							
							<div ng-show="currentUser!=''">
					<li><a ng-click="ctrl.logout()"><span
							class="glyphicon glyphicon-log-out"></span>Logout</a></li>
							</div>		
				</ul>
		</div>
	</nav>
</div>

	<div class="jumbotron">
		<hr color="blue" width="100%" size="20">
		<div class="container">
			<div ng-view></div>
		</div>
	</div>

    <script src="resources/js/angular-cookies.js"></script>
    <script src="resources/js/sockjs.js"></script>
    <script src="resources/js/stomp.js"></script>
    <script src="app.js"></script>
	<script src="c_home/HomeController.js"></script>
	<script src="c_user/UserController.js"></script>
	<script src="c_user/UserService.js"></script>
	<script src="c_blog/BlogController.js"></script>
	<script src="c_blog/BlogService.js"></script>
	
	<script src="c_job/JobController.js"></script>
	<script src="c_job/JobService.js"></script>
	
	<script src="c_chat/ChatController.js"></script>
	<script src="c_chat/ChatService.js"></script>
	
	<script src="c_chat_forum/ChatForumController.js"></script>
	<script src="c_chat_forum/ChatForumService.js"></script>
	
	<script src="c_friend/FriendController.js"></script>
	<script src="c_friend/FriendService.js"></script>
	
	<script src="c_upload/FileUploadController.js"></script>
	
</body>
</html>
