<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<style class="csscreations">
/*Now the CSS*/
* {
	margin: 0;
	padding: 0;
}

.tree ul {
	padding-top: 20px;
	position: relative;
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

.tree li {
	float: left;
	text-align: center;
	list-style-type: none;
	position: relative;
	padding: 20px 5px 0 5px;
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*We will use ::before and ::after to draw the connectors*/
.tree li::before,.tree li::after {
	content: '';
	position: absolute;
	top: 0;
	right: 50%;
	border-top: 1px solid #ccc;
	width: 50%;
	height: 20px;
}

.tree li::after {
	right: auto;
	left: 50%;
	border-left: 1px solid #ccc;
}

/*We need to remove left-right connectors from elements without 
any siblings*/
.tree li:only-child::after,.tree li:only-child::before {
	display: none;
}

/*Remove space from the top of single children*/
.tree li:only-child {
	padding-top: 0;
}

/*Remove left connector from first child and 
right connector from last child*/
.tree li:first-child::before,.tree li:last-child::after {
	border: 0 none;
}
/*Adding back the vertical connector to the last nodes*/
.tree li:last-child::before {
	border-right: 1px solid #ccc;
	border-radius: 0 5px 0 0;
	-webkit-border-radius: 0 5px 0 0;
	-moz-border-radius: 0 5px 0 0;
}

.tree li:first-child::after {
	border-radius: 5px 0 0 0;
	-webkit-border-radius: 5px 0 0 0;
	-moz-border-radius: 5px 0 0 0;
}

/*Time to add downward connectors from parents*/
.tree ul ul::before {
	content: '';
	position: absolute;
	top: 0;
	left: 50%;
	border-left: 1px solid #ccc;
	width: 0;
	height: 20px;
}

.tree li img {
	border: 1px solid #ccc;
	padding: 5px 10px;
	text-decoration: none;
	color: #666;
	font-family: arial, verdana, tahoma;
	font-size: 11px;
	display: inline-block;
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/*Time for some hover effects*/
/*We will apply the hover effect the the lineage of the element also*/
.tree li img:hover,.tree li img:hover+ul li img {
	background: #c8e4f8;
	color: #000;
	border: 1px solid #94a0b4;
}
/*Connector styles on hover*/
.tree li img:hover+ul li::after,.tree li img:hover+ul li::before,.tree li img:hover+ul::before,.tree li img:hover+ul ul::before
{
	border-color: #94a0b4;
}
/*Thats all. I hope you enjoyed it.
Thanks :)*/
</style>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
$("img[name='img']").bind('mouseover', function() {
	alert("mouseover");
});
$("img[name='img']").bind('mouseout', function() {
	alert("mouseout");
});

</script>
</head>
<body>
	<div style="width: 100%;height: 600px;overflow: scroll;">
		<div id="tree" style="width: 1300px;height: 1000px;text-align: center;overflow: scroll;" class="tree">
			<ul>
				<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
					<ul>
						<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
							<ul>
								<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
									<ul>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
									</ul>
								</li>
								<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
									<ul>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li><img name="img" src="1.jpg" width="20px" height="20px" alt="0">
							<ul>
								<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
									<ul>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
									</ul>
								</li>
								<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
									<ul>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
										<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
											<ul>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0">
													<ul>
														<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
														<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
													</ul>
												</li>
												<li><img name="img" src="0.jpg" width="20px" height="20px" alt="0"></li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div style="width: 20%;height:1000px;float: right;background-color: gray;overflow: scroll;margin-top:-1000px;">
			asdfasdfasdfasfdasdfasdfasdfasdfasdfas
			dfasdfasdfasdfsadfsadfsadf
		</div>
	</div>
</body>
</html>