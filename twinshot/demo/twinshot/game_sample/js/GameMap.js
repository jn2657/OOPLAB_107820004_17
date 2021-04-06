var GameMap = function(map){
    this.MW = 45;
    this.MH = 45;

    this.mapArray = map;

    this.position = {
        x: 200,
        y: 100
    };

    this.load = function(){
        this.block = new Framework.Sprite(define.imagePath + 'block.png');
        this.block1 = new Framework.Sprite(define.imagePath + 'block1.png');
        this.block2 = new Framework.Sprite(define.imagePath + 'block2.png');
        this.pillar = new Framework.Sprite(define.imagePath + 'pillar.png');
        this.pillar1 = new Framework.Sprite(define.imagePath + 'pillar1.png');
        this.pillar2 = new Framework.Sprite(define.imagePath + 'pillar2.png');

        this.player1 = new BombMan(define.imagePath + 'player1.png', 
                    {down: {from: 0, to: 2}, left: {from:3, to: 5}, right: {from: 6, to: 8}, up: {from: 9, to: 11}});
        this.player1.position = {x:850, y:600};

        //this.monster = [];
    };

    this.setPlayerPosition = function(playerPosition){
        this.player1.position = playerPosition;
    };
    this.addMonster = function(monsterPosition)
    {
        var newMonster = new Monster(define.imagePath + 'monster.png',this, {down: {from: 0, to: 2}, left: {from:3, to: 5}, right: {from: 6, to: 8}, up: {from: 9, to: 11}});
        newMonster.position = monsterPosition;
        this.monster.push(newMonster);
    };

    this.playerMovedHandler = function(player){
        var constants = new Constants();
        var item = m_map.mapArray[player.position.y][player.position.x];
        if(item === constants.ItemEnum.INCREASE_BOMB){
            player.increaseBombNum();
            m_map.mapArray[player.position.y][player.position.x] = 0;
            m_map.tileArray[player.position.y*22+player.position.x].tileType = 0;
            m_map.score.addScore(200);
        }else if(item === constants.ItemEnum.INCREASE_POWER){
            player.increaseBombPower();
            m_map.mapArray[player.position.y][player.position.x] = 0;
            m_map.tileArray[player.position.y*22+player.position.x].tileType = 0;
            m_map.score.addScore(200);
        }else if(item === constants.ItemEnum.STOP_MONSTER){
            m_map.stopMonster = true;
            m_map.mapArray[player.position.y][player.position.x] = 0;
            m_map.tileArray[player.position.y*22+player.position.x].tileType = 0;
            m_map.score.addScore(200);
        }
    }

    this.initialize = function(){
        this.player1.StepMovedCallBack.push(this.playerMovedHandler);
    };

    this.update = function(){
        if(this.pressWalk === true && this.player1.isWalking === false)
        {
            if(this.checkIsWalkAble(this.player1.position.x+this.playerWalkDirection.x,this.player1.position.y+this.playerWalkDirection.y))
            {
                this.player1.walk(this.playerWalkDirection);
            }
        }
        this.player1.update();
    };

    this.draw = function(ctx){
        this.player1.draw(ctx);
        for(i=0;i<17;i++){
            for(j=0;j<29;j++){
                var picPosition = {
                    x: this.position.x + (this.MW*j) + this.MW/2,
                    y: this.position.y + (this.MH*i) + this.MH/2,
                }

                switch(this.mapArray[i][j]){
                    case 0:
                        break;
                    case 1:
                        this.block.position = picPosition;
                        this.block.draw(ctx);
                        break;
                    case 2:
                        this.block1.position = picPosition;
                        this.block1.draw(ctx);
                        break;
                    case 3:
                        this.block2.position = picPosition;
                        this.block2.draw(ctx);
                        break;
                    case 4:
                        this.pillar.position = picPosition;
                        this.pillar.draw(ctx);
                        break;
                    case 5:
                        this.pillar1.position = picPosition;
                        this.pillar1.draw(ctx);
                        break;
                    case 6:
                        this.pillar2.position = picPosition;
                        this.pillar2.draw(ctx);
                        break;
                    default:
                        break;
                }
            }
        }
    };

    this.checkIsWalkAble = function(x,y){
        if(x < 0 || x > this.mapArray[0].length){ return false; }
        if(y < 0 || y > this.mapArray.length){ return false; }

        if(this.mapArray[y][x] > 0){ return false; }
        else{ return true;}
    };

    this.playerWalkDirection = {x:0,y:0};
    this.pressWalk = false;
    this.keyPress = "";
    this.keydown = function(e, list){
        var playerPosition = this.player1.position;
        if(e.key === 'Down') {
            if(this.checkIsWalkAble(playerPosition.x,playerPosition.y+1)){
                //this.player1.walk({x:0,y:1});
                this.playerWalkDirection = {x:0,y:1};
                this.pressWalk = true;
                this.keyPress = "Down";
            }
        }

        if(e.key === 'Left') {
            if(this.checkIsWalkAble(playerPosition.x-1,playerPosition.y)){
                //this.player1.walk({x:-1,y:0});
                this.playerWalkDirection = {x:-1,y:0};
                this.pressWalk = true;
                this.keyPress = "Left";
            }
        }

        if(e.key === 'Right') {
            if(this.checkIsWalkAble(playerPosition.x+1,playerPosition.y)){
                //this.player1.walk({x:1,y:0});
                this.playerWalkDirection = {x:1,y:0};
                this.pressWalk = true;
                this.keyPress = "Right";
            }
        }

        if(e.key === 'Up') {
            if(this.checkIsWalkAble(playerPosition.x,playerPosition.y-1)){
                //this.player1.walk({x:0,y:-1});
                this.playerWalkDirection = {x:0,y:-1};
                this.pressWalk = true;
                this.keyPress = "Up";
            }
        }

        if(e.key === 'Space'){
            var bomb = this.player1.placeBomb();
            if(!Framework.Util.isNull(bomb))
            {
                bomb.ExploredCallBack.push(Framework.Game._currentLevel.map.bombExploredHandler);
                this.bombArray.push(bomb);
                var bombPosition = bomb.position;
                this.mapArray[bombPosition.y][bombPosition.x] = 3;
            }
        }
    };

    this.keyup = function(e, list){
        if(e.key === 'Down' || e.key === 'Up' || e.key === 'Left' || e.key === 'Right') {

            if(this.keyPress == e.key)
            {
                this.player1.walkEnd();
                this.pressWalk = false;
            };
        }
    };

}