// JavaScript Document
onload = function () {
    HR.extend(HR, {
     addEvent: function (o, e, f) {
      o.addEventListener ? o.addEventListener(e, f, false) : o.attachEvent('on'+e, function(){f.call(o)})
     },
     $ : function (id) {return document.getElementById(id)},
     $$ : function (c, p) {return p.getElementsByTagName(c)},
     superAnim: {},
     loadImg: function (urls, cb) {
      var n = urls.length, img = [], f = 0;
      for (var i=0; i<n; i++) {
       img[i] = new Image();
       img[i].src = urls[i];
       if (img[i].complete) {
        if (++f == n) {cb && cb(); return} else {f++}
       }
       img[i].onload = function () {
        if (++f == n) {cb && cb()} else {f++}
       }
      }
     }
    })
    HR.superAnim.init = function () {
     var a = HR.$('mask').offsetWidth, 
      b = HR.$('mask').offsetHeight,
      dlwp = HR.$('menu'),
      dd = HR.$$('dd', dlwp),
      flag = null,
      clickable = true,
      at;
     HR.get('bg').setStyle('top', -b+'px');
     HR.loadImg(['specialProductImage/product1/0.jpg',
        'specialProductImage/product1/1.jpg',
        'specialProductImage/product1/2.jpg',
        'specialProductImage/product1/3.jpg',
        'specialProductImage/product1/4.jpg'], 
        function () {
         HR.get('mask').animate({left: -a}, 3000, function () {
          HR.$('waiting').style.display = 'none';
          HR.get('start').animate({opacity:1, left:a/2.2},'Elastic').animate({opacity:0,left:a},'Elastic','easeIn', function(){
           HR.get('bg').animate({top:0},'Bounce',2000, function(){
            HR.get('menu').animate({height: 286},'Back', function () {
             setTimeout(function(){bgAnim(2)}, 2000);
            })
           })
          })
         });
        });
     for (var i=0; i<dd.length; i++) {
      HR.addEvent(dd[i], 'mouseover', function (i) {
       return function () {
        HR.get('menu'+i).stop().animate({top: -48},'Back',500)
       }
      }(i));
      HR.addEvent(dd[i], 'mouseout', function (i) {
       return function () {
        HR.get('menu'+i).stop().animate({top: 0},'Back',500)
       }
      }(i));
      HR.addEvent(dd[i], 'click', function (i) {
       return function () { 
        if (clickable) {
         clickable = false;//console.log('yes')
         if (flag == null) {
          HR.get('info'+i).show().animate({width:300},'Back').animate({height:360},'Elastic',function(){clickable = true;flag = i;})
         } else if (flag != i) {
          HR.get('info'+flag).animate({height:30}).animate({width:0},function(){
           this.style.display = 'none'
           HR.get('info'+i).show().animate({width:300},'Back').animate({height:360},'Elastic',function(){clickable = true;flag = i;}); 
          }); 
         } else return;
        } 
       }
      }(i));
     }
     function bgAnim (i) {
      clearTimeout(at);
      HR.get('mask').animate({left: 0}, function () {
       HR.$('bg').style.backgroundImage = 'url(specialProductImage/product1/'+(i-1)+'.jpg)';
      }).animate({left: -a});
      var n = i+1 == 6 ? 1 : i+1;
      at = setTimeout(function(){bgAnim(n)}, 6000);
     }
    }();
}