var Board=function(){
	this.load=function(px,py){
        this.boardPic = new Framework.Sprite(define.imagePath + '1levels.png');  //宣告圖片
        this.boardPic.position={                                             //起始位置
            x:Framework.Game.getCanvasWidth()+250,
            y:Framework.Game.getCanvasHeight()/2
        };

        this.level_x=Framework.Game.getCanvasWidth()-1260;  //設定選關按鈕的x位置
        this.level_n=6;                                    //設定關卡數量

        this.botton=[];            //生成選關按鈕
        for(i=0;i<this.level_n;i++){
        	this.botton[i]=new Botton(120,40);
            if(i == 5){
                this.botton[i].load(this.level_x + 460,670);
            }else{
                this.botton[i].load(this.level_x + (i*100),300);
            }
        }
        //this.button[5] = new Botton(120,40);
        //this.botton[5].load(this.level_x + 460, 670);

        this.t1_isCall=false;
        this.t2_isCall=false;


        this.mBotton=new MovingBotton();
        this.mBotton.load();


	};

	this.update= function(target1,target2) {
		this.t1_isCall=target1.isCall;
        this.t2_isCall=target2.isCall;

        

        if(target1.isCall || target2.isCall){
			if(this.boardPic.lowerRight.x>Framework.Game.getCanvasWidth()-240){
			     this.boardPic.position.x=this.boardPic.position.x-25;
                
			}
		}
		else{
			if(this.boardPic.lowerLeft.x<Framework.Game.getCanvasWidth()){
				this.boardPic.position.x=this.boardPic.position.x+25;
                
			}
		}
        if(this.t1_isCall){
            this.mBotton.go_out();
        }
        if(this.t2_isCall){
            this.mBotton.update();
            this.mBotton.come_back();
        }

        
		

	};


	this.draw=function(ctx){
		ctx.font = '20pt bold';
        ctx.fillStyle = 'white';
        ctx.textBaseline = 'top';
        ctx.textAlign = 'center';
        if(this.t1_isCall){
            ctx.fillText('Level1',this.level_x , 300, 180);
            ctx.fillText('Level2',this.level_x + 100, 300, 180);
            ctx.fillText('Level3',this.level_x + 200, 300, 180);
            ctx.fillText('Level4',this.level_x + 300, 300, 180);
            ctx.fillText('Level5',this.level_x + 400, 300, 180);
            ctx.fillText('Back',this.level_x + 460, 670, 180);
        }
        else if(this.t2_isCall){
            ctx.fillText('Train Mode: on off',this.level_x-20 , 100, 380);
        }




        

	};

};