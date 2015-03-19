// JavaScript Document
if (document.all)
with(document){
write('<div id="starsDiv" style="position:absolute;top:0px;left:0px">')
write('<div style="position:relative;width:2px;height:2px;background:#ffffff;font-size:2px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#fff000;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#ffa000;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#ff00ff;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#00ff00;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#0000ff;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#FF0000;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#ffffff;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#fff000;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#ffa000;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#ff00ff;font-size:1px"></div>')
write('<div style="position:relative;width:2px;height:2px;background:#ff00ff;font-size:2px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#0000ff;font-size:1px"></div>')
write('<div style="position:relative;width:1px;height:1px;background:#FF0000;font-size:1px"></div>')
write('</div>')
}
//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
var Clrs=new Array(9)
Clrs[0]='ff0000';
Clrs[1]='00ff00';
Clrs[2]='000aff';
Clrs[3]='ff00ff';
Clrs[4]='ffa500';
Clrs[5]='ffff00';
Clrs[6]='00ff00';
Clrs[7]='ffffff';
Clrs[8]='fffff0';
var sClrs=new Array(5)
sClrs[0]='ffa500';
sClrs[1]='55ff66';
sClrs[2]='AC9DFC';
sClrs[3]='fff000';
sClrs[4]='fffff0';
var yBase;
var xBase;
var step;
var currStep = 0;
var Xpos = 1;
var Ypos = 1;
var initialStarColor='ffa000';
var Mtop=250;
var Mleft=250;
//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
function Fireworks() {
if (document.all)
{
yBase = window.document.body.offsetHeight/3;
xBase = window.document.body.offsetWidth/8;
}
else if (document.layers)
{
yBase = window.innerHeight/3;
xBase = window.innerWidth/8;
}
if (document.all)
{
step=5;
for ( i = 0 ; i < starsDiv.all.length ; i++ )
{
for (ai=0; ai < Clrs.length; ai++)
{
var c=Math.round(Math.random()*[ai]);
}
if (currStep < 90)
starsDiv.all[i].style.background=initialStarColor;
if (currStep > 90)
starsDiv.all[i].style.background=Clrs[c];
starsDiv.all[i].style.top = Mtop + yBase*Math.sin((currStep+i*5)/3)*Math.sin(550+currStep/100)
starsDiv.all[i].style.left = Mleft + yBase*Math.cos((currStep+i*5)/3)*Math.sin(550+currStep/100)
}
}
else if (document.layers)
{
step = 5;
for ( j = 0 ; j < 14 ; j++ ) //number of NS layers!
{
var templayer="a"+j
for (ai=0; ai < Clrs.length; ai++)
{
var c=Math.round(Math.random()*[ai]);
}
if (currStep < 90)
document.layers[templayer].bgColor=initialStarColor;
if (currStep > 90)
document.layers[templayer].bgColor=Clrs[c];
document.layers[templayer].top = Mtop + yBase*Math.sin((currStep+j*5)/3)*Math.sin(550+currStep/100)
document.layers[templayer].left = Mleft + yBase*Math.cos((currStep+j*5)/3)*Math.sin(550+currStep/100)
}
}
currStep+= step;
T=setTimeout("Fireworks()",5);
if (currStep==220)
{
currStep=-10;
for (n=0; n < sClrs.length; n++)
{
var k=Math.round(Math.random()*n);
}
initialStarColor=sClrs[k];
if (document.all)
{//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
Dtop=window.document.body.clientHeight-250;
Dleft=xBase*3.5;
Mtop=Math.round(Math.random()*Dtop);
Mleft=Math.round(Math.random()*Dleft);
document.all.starsDiv.style.top=Mtop+document.body.scrollTop;
document.all.starsDiv.style.left=Mleft+document.body.scrollLeft;
}
else if (document.layers)
{
Dleft=window.innerWidth-100;
Dtop=window.innerHeight-100;
Mtop=Math.round(Math.random()*Dtop+window.pageYOffset);
Mleft=Math.round(Math.random()*Dleft+window.pageXOffset);
document.layers[templayer].top=Mtop;
document.layers[templayer].left=Mleft;
}
if ((Mtop < 20) || (Mleft < 20))
{
Mtop+=90;
Mleft+=90;
}
}
}//欢迎来到站长特效网，我们的网址是www.zzjs.net，很好记，zz站长，js就是js特效，本站收集大量高质量js代码，还有许多广告代码下载。
if (document.all||document.layers)
Fireworks();