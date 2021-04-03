var MyGame = Framework.Class(Framework.Level , {
	load: function(){
        this.practice = new Practice();
        this.practice.load();
        this.rootScene.attach(this.practice);

        this.map = [[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,3,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,1,3,2,2,3,1,2,3,1,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,5,0,0,5,0,5,0,0,5,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,1,3,1,1,2,2,1,3,1,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0],
                    [0,1,3,1,3,1,3,2,1,2,0,0,0,6,0,6,0,0,0,1,1,3,3,1,2,2,3,1,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]];

        
        this.gameMap = new GameMap(this.map);
        this.gameMap.load();
        this.rootScene.attach(this.gameMap);


        // this.pic = new Framework.Sprite(define.imagePath + 'firststage.png');
        // this.pic.position = {
        //     x : 800,
        //     y : 450
        // }

	    var characterPosition = {
            x: 850,
            y: 600
        };

        this.firen = new Character(define.imagePath + 'firen.png', {position: characterPosition, run: {from: 20, to: 22}, beHit: {from:30, to: 35}, hit: {from: 10, to: 13}}); 
        this.freeze = new Character(define.imagePath + 'freeze.png', {position: characterPosition, scale: 1, run: {from: 29, to: 27}, beHit: {from:39, to: 35}, hit: {from: 19, to: 16}});


        this.secondHandRotationRate = 0.3;
        this.rootScene.attach(this.firen.sprite);
        this.rootScene.attach(this.freeze.sprite);

        //繪製Sprite的boundry (Debug用)
        // this.firen.sprite.isDrawBoundry = true;

        //載入要被播放的音樂清單
        //資料夾內只提供mp3檔案, 其餘的音樂檔案, 請自行轉檔測試
        this.audio = new Framework.Audio({
            kick: {
                mp3: define.musicPath + 'kick2.mp3',
                //ogg: define.musicPath + 'kick2.ogg',
                //wav: define.musicPath + 'kick2.wav'
            }, song1:{
                mp3: define.musicPath + 'NTUT_classic.mp3',
                //ogg: define.musicPath + 'Hot_Heat.ogg',
                //wav: define.musicPath + 'Hot_Heat.wav'
            }, song2:{
                mp3: define.musicPath + 'NTUT_modern.mp3',
                //ogg: define.musicPath + 'The_Messenger.ogg',
                //wav: define.musicPath + 'The_Messenger.wav'
            }
        });

        //播放時, 需要給name, 其餘參數可參考W3C
        this.audio.play({name: 'song2', loop: true});

        this.rectPosition = { 
            x: Framework.Game.getCanvasWidth() / 2 - 130,
            y: Framework.Game.getCanvasHeight() / 2 - 90
        };
		
		this.position = {
			x: 100,
			y: 100
		}
		this.rotation = 0;
	},

    initialize: function() {
        this.gameMap.setPlayerPosition({x:800, y:650});
                           
    },

    update: function() {
        var game = this;
        this.rootScene.update(); 
        this.practice.update();
        this.gameMap.update();
		                            
    },

    draw:function(parentCtx){
        //this.pic.draw();
        this.rootScene.draw();
        //可支援畫各種單純的圖形和字
        // parentCtx.fillStyle = (this.secondHandRotationRate > 0)?'green':'red'; 
        // parentCtx.fillRect(this.rectPosition.x , this.rectPosition.y, 260, 90);  
        // parentCtx.font = '65pt bold';
        // parentCtx.fillStyle = 'white';
        // parentCtx.textBaseline = 'top';
        // parentCtx.textAlign = 'center';
        // parentCtx.fillText('Click Me', this.rectPosition.x + 130, this.rectPosition.y, 260);
         
        
    },

    keydown:function(e, list){
        this.map.keydown(e, list);
        if(e.key === "Right"){
            this.characterPosition.x += 10;
        }
    },

    touchstart: function (e) {
        //為了要讓Mouse和Touch都有一樣的事件
        //又要減少Duplicated code, 故在Touch事件被觸發時, 去Trigger Mouse事件
        this.click({ x: e.touches[0].clientX, y: e.touches[0].clientY });
    },
    
    click: function (e) {  

        console.log(e.x, e.y);
        if (!this.rectPosition) {
            return;
        }  
        
        // if(e.x >= this.rectPosition.x && e.x <= this.rectPosition.x + 260 && e.y >= this.rectPosition.y && e.y <= this.rectPosition.y + 90) {
        //     if(!this.isClockStop) {
        //         this.secondHandRotationRate = 0;
        //         this.isClockStop = true;
        //         //Audio可以一次暫停所有的音樂
        //         this.audio.pauseAll();
        //     } else {
        //         this.isClockStop = false;
        //         this.secondHandRotationRate = 0.3;
        //         //Audio也可以針對一首歌進行操作(繼續播放)
        //         this.audio.resume('song2');
        //     }
        // } else if(e.x >= this.clock.upperLeft.x && e.x <= this.clock.lowerRight.x && e.y >= this.clock.upperLeft.y && e.y <= this.clock.lowerRight.y) {
        //     //由於Click Me在太小的螢幕的情況下會蓋到Clock, 導致點擊Click Me時, 會回到前一個Level,
        //     //故使用else if, 並優先選擇Click Me會觸發的條件
        //     this.audio.stopAll();
        //     Framework.Game.goToPreviousLevel();            
        //     return;
        // }
    },
});