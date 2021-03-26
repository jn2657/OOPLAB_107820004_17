var MyMenu = Framework.exClass(Framework.GameMainMenu , {
	//初始化loadingProgress需要用到的圖片
    initializeProgressResource: function() {
        this.loading = new Framework.Sprite(define.imagePath + 'loading.jpg');
        this.loading.position = {x: Framework.Game.getCanvasWidth() / 2 , y: Framework.Game.getCanvasHeight() / 2};

        //為了或得到this.loading這個Sprite的絕對位置, 故需要先計算一次(在Game Loop執行時, 則會自動計算, 但因為loadingProgress只支援draw故需要自行計算)                  
    },

    //在initialize時會觸發的事件
    loadingProgress: function(ctx, requestInfo) {
        //console.log(Framework.ResourceManager.getFinishedRequestPercent())
        this.loading.draw(ctx);
        ctx.font ='90px Arial';
        ctx.textAlign = 'center';
        ctx.fillStyle = 'white';
        ctx.fillText(Math.round(requestInfo.percent) + '%' , ctx.canvas.width / 2 , ctx.canvas.height / 2 + 300);
    },

	load: function(){
		//Animation Sprite會用到的圖片資源        
        ////////Load開始圖片/////////
        this.startPic = new StartPic();
        this.startPic.load();
        this.rootScene.attach(this.startPic.startPic);

        ////////Load開始標語/////////
        this.startpic = new StartSign();
        this.startpic.load(Framework.Game.getCanvasWidth() / 2 , Framework.Game.getCanvasHeight() / 2);
        this.rootScene.attach(this.startpic.startPic);

        ////////Load選關按鈕/////////
        this.select=new SelectSign();
        this.select.load();
        this.rootScene.attach(this.select.bottonPic);

        ////////Load選關按鈕/////////
        this.confling=new ConflingSign();
        this.confling.load();
        this.rootScene.attach(this.confling.bottonPic);

        ////////Load模板/////////
        this.board=new Board();
        this.board.load();
        this.rootScene.attach(this.board.boardPic);
        this.rootScene.attach(this.board.mBotton.bottonPic);

	},
	
    initialize: function() {
		
    },

    update:function(){     
        this.rootScene.update();
        //this.rootScene.update(); 

        //目前的Framework, 當任何一個GameObject不做attach時, 則必須要自行update
        this.center.update();        
        this.scrollBar.update();
    },

    draw: function(parentCtx) { 
        //this.rootScene.draw();一定要在第一行
        this.rootScene.draw(parentCtx);
        
    },

    mouseup: function(e) {

    },

    mousedown: function(e) {
        //console.log為Browser提供的function, 可以在debugger的console內看到被印出的訊息                    
        if (e) {
            console.log(e.x, e.y);
        }
        
        this.previousTouch = { x: e.x, y: e.y };
        if (this.previousTouch.x > this.rightArrow.upperLeft.x && this.previousTouch.x < this.rightArrow.upperRight.x && this.previousTouch.y > this.rightArrow.upperLeft.y && this.previousTouch.y < this.rightArrow.lowerLeft.y) {
            this.isTouchArrow = true;
        }
    },

    mousemove: function(e) {        
        if (this.isTouchArrow) {
            this.currentTouch = { x: e.x, y: e.y };
            if (this.currentTouch.x > this.previousTouch.x && this.currentTouch.y < this.rightArrow.lowerLeft.y && this.currentTouch.y > this.rightArrow.upperLeft.y) {
                //當arrow被Touch到時, 會跟隨著觸控的位置移動
                this.rightArrow.position.x = this.rightArrow.position.x + this.currentTouch.x - this.previousTouch.x 
                if(this.currentTouch.x > Framework.Game.getCanvasWidth() - this.rightArrow.width) {
                    //當要換關時, 可以呼叫goToNextLevel, goToPreviousLevel, goToLevel(levelName)
                    Framework.Game.goToNextLevel();
                }
            }
       }
        this.previousTouch = this.currentTouch;
    },

    mouseup: function(e) {
        this.isTouchArrow = false;
        Framework.Game.goToNextLevel();
    },

    touchstart: function (e) {
        //為了要讓Mouse和Touch都有一樣的事件
        //又要減少Duplicated code, 故在Touch事件被觸發時, 去Trigger Mouse事件
        this.mousedown({ x: e.touches[0].clientX, y: e.touches[0].clientY });
    },

    touchend: function (e) {
        this.mouseup();
    },
    
    touchmove: function (e) {
        this.mousemove({ x: e.touches[0].clientX, y: e.touches[0].clientY });
    }
});