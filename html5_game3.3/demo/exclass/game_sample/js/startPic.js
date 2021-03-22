var StartPic=Framework.exClass({
	load:function(){
        this.startPic = new Framework.Sprite(define.imagePath + 'firststage.png');  //宣告圖片
        this.startPic.position={                                             //起始位置
            x:Framework.Game.getCanvasWidth()/2,
            y:Framework.Game.getCanvasHeight()/2
        };

        this.isCall=false;

	},

	update: function() {

	},

	click: function(e) {

        this.previousTouch = { x: e.x, y: e.y };
        // if (this.previousTouch.x >= this.bottonPic.upperLeft.x && this.previousTouch.x <= this.bottonPic.upperRight.x && this.previousTouch.y >= this.bottonPic.upperLeft.y && this.previousTouch.y <= this.bottonPic.lowerLeft.y) {
        //     if(!this.isCall){     //isCall在按鍵觸發時要轉換狀態
        //         this.isCall=true;
        //     }
        //     else if(this.isCall){
        //         this.isCall=false;
        //     }
        // }
        // return true;

    }


});