// JavaScript Document
var iPicNum = 30;	//ͼƬ������
$(function(){
	for(var i=1;i<=iPicNum;i++)
		//���ͼƬ������ͼ
		$(document.body).append($("<div><a href='#'><img src='photo/thumb/"+i.toString()+".jpg'></a></div>"));
	$("div:has(a)").addClass("thumb");
	for(var i=0;i<iPicNum;i++){
		var myimg = new Image();
		myimg.src = $("div a img").get(i).src;
		//����ͼƬ�ı�����ˮƽ������ֱ������Ӳ�ͬ����ʽ
		if(myimg.width > myimg.height)
			$("div:has(a):eq("+i+")").addClass("ls");
		else
			$("div:has(a):eq("+i+")").addClass("pt");
	}
	
	$("#showPhoto").hide();	//��ʼ��ʱ����ʾ��ͼ
	$("#bgblack").css("opacity",0.9);	//��ʾ��ͼ�ķ��鱳������Ϊ͸��
	
	$("#close").click(function(){
		//�����ťclose����رմ�ͼ��壨���ö�����
		$("#showPhoto").add("#showPic").fadeOut(400);
	});
	
	$("div a:has(img)").click(function(){
		//����������ͼ������ʾ��ͼ
		$("#showPhoto").css({
			//��ͼ��λ�ø��ݴ������ж�
			"left":($(window).width()/2-300>20?$(window).width()/2-300:20),
			"top":($(window).height()/2-270>30?$(window).height()/2-270:30)
		}).add("#showPic").fadeIn(400);
		//��������ͼ�ĵ�ַ����ȡ��Ӧ�Ĵ�ͼ��ַ
		var mySrc = $(this).find("img").attr("src");
		mySrc = "photo" + mySrc.slice(mySrc.lastIndexOf("/"));
		$("#showPic").find("img").attr("src",mySrc);
		
		if($(this).parent().hasClass("ls"))
			//����ͼƬ���ԣ�ˮƽ������ֱ����������ͼ��λ��
			$("#showPic").find("img").css("marginTop","70px");
		else if($(this).parent().hasClass("pt"))
			$("#showPic").find("img").css("marginTop","0px");
	});
	
	$("#intro").click(function(){
		window.open("product.html");
		$("#showPhoto").add("#showPic").fadeOut(400);
	});
});