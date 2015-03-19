// JavaScript Document
var iPicNum = 30;	//图片总数量
$(function(){
	for(var i=1;i<=iPicNum;i++)
		//添加图片的缩略图
		$(document.body).append($("<div><a href='#'><img src='photo/thumb/"+i.toString()+".jpg'></a></div>"));
	$("div:has(a)").addClass("thumb");
	for(var i=0;i<iPicNum;i++){
		var myimg = new Image();
		myimg.src = $("div a img").get(i).src;
		//根据图片的比例（水平或者竖直），添加不同的样式
		if(myimg.width > myimg.height)
			$("div:has(a):eq("+i+")").addClass("ls");
		else
			$("div:has(a):eq("+i+")").addClass("pt");
	}
	
	$("#showPhoto").hide();	//初始化时不显示大图
	$("#bgblack").css("opacity",0.9);	//显示大图的方块背景设置为透明
	
	$("#close").click(function(){
		//点击按钮close，则关闭大图面板（采用动画）
		$("#showPhoto").add("#showPic").fadeOut(400);
	});
	
	$("div a:has(img)").click(function(){
		//如果点击缩略图，则显示大图
		$("#showPhoto").css({
			//大图的位置根据窗口来判断
			"left":($(window).width()/2-300>20?$(window).width()/2-300:20),
			"top":($(window).height()/2-270>30?$(window).height()/2-270:30)
		}).add("#showPic").fadeIn(400);
		//根据缩略图的地址，获取相应的大图地址
		var mySrc = $(this).find("img").attr("src");
		mySrc = "photo" + mySrc.slice(mySrc.lastIndexOf("/"));
		$("#showPic").find("img").attr("src",mySrc);
		
		if($(this).parent().hasClass("ls"))
			//根据图片属性（水平或者竖直），调整大图的位置
			$("#showPic").find("img").css("marginTop","70px");
		else if($(this).parent().hasClass("pt"))
			$("#showPic").find("img").css("marginTop","0px");
	});
	
	$("#intro").click(function(){
		window.open("product.html");
		$("#showPhoto").add("#showPic").fadeOut(400);
	});
});