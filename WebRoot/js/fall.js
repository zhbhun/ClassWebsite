// JavaScript Document
function Falling(){
    this.dict=["&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ӭ���ʣ�"];
    this.canvas=$("#canvas");
    this.step=15;
    this.freq=10;
    this.height=500;
    this.width=400;
    this.si=null;
}
Falling.prototype={
    fallingAction:function(dom){
        var self=this;
        var freqs=[10,15,20];//ÿ������ľ���
        var disS=[];//��¼����dom�ĵ�ǰ����
        var disPerFreqS=[];//ÿ��dom��
        var targetDis=500;
        var domCssTopS=[];//����dom��top����
        var successDom=[];//��¼��Щdom�Ѿ������˶�
        var successCount=0;//�ж��ٸ�dom�Ѿ�����
        var total=dom.length;
        var freqMarkLength=freqs.length;
        for(var i=0,j=dom.length;i<j;i++){
            domCssTopS[i]=dom[i].position().top;
            disS[i]=0;
            disPerFreqS[i]=freqs[parseInt(Math.random()*freqMarkLength)];
        }
        self.si=setInterval(function(){
            if(successCount>=total){
                clearInterval(self.si);
            }
            for(var i=0,j=dom.length;i<j;i++){
                if(typeof(successDom[i])!="undefined" && successDom[i]=="ok"){
                    continue;
                }
                disS[i] += disPerFreqS[i];
                if(disS[i] >= targetDis){
                    dom[i].css("top", targetDis+domCssTopS[i]);
                    successDom[i]="ok";
                    successCount++;;
                }else{
                    dom[i].css("top", disS[i]+domCssTopS[i]);
                }
            }
        },self.freq);
    },
    init:function(){
        var self=this;
        self.canvas.html('');
        var dom=[];
        var l=0;
        var t=0;
        var tempDom=$("<div style='position;absolute;left:-100000;visibility:hidden'></div>").appendTo($("body"));
        for(var i=0,j=self.dict.length;i<j;i++){
            dom[i]=$("<span style='position:absolute'>"+self.dict[i]+"</span>").appendTo(tempDom);
            var domWidth=dom[i].width();
            var domHeight=dom[i].height();
            if(t<self.height){
                if(l<self.width){
                    if(domWidth+l<=self.width){
                        dom[i].css({"top":t,"left":l});
                        self.canvas.append(dom[i]);
                        l += dom[i].width();
                    }else{
                        if(domHeight+t<=self.height){
                            t=t+domHeight;
                            dom[i].css({"top":t,"left":0});
                            self.canvas.append(dom[i]);
                            l = dom[i].width();
                        }else{
                            break;//��������
                        }
                    }
                }else{
                        if(domHeight+t<=self.height){
                            t=t+domHeight;
                            l=0;
                            dom[i].css({"top":t,"left":l});
                            self.canvas.append(dom[i]);
                        }else{
                            break;//��������
                        }
                }
            }//else����
        }
        /*
        for(var i=0,l=self.dict.length;i<l;i++){
            self.fallingAction(dom[i]);
        }
        */
        self.fallingAction(dom);
    }
}
var falling=new Falling();
falling.init();