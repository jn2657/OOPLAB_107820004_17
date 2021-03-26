var GameMap = function(){
    this.MW = 45;
    this.MH = 45;

    this.position = {
        x: 200,
        y: 100
    };

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

    this.load = function(){
        this.block = new Framework.Sprite(define.imagePath + 'block.png');
        this.block1 = new Framework.Sprite(define.imagePath + 'block1.png');
        this.block2 = new Framework.Sprite(define.imagePath + 'block2.png');
        this.pillar = new Framework.Sprite(define.imagePath + 'pillar.png');
        this.pillar1 = new Framework.Sprite(define.imagePath + 'pillar1.png');
        this.pillar2 = new Framework.Sprite(define.imagePath + 'pillar2.png');
    };

    this.setPlayerPosition = function(playerPosition){
        this.player1.position = playerPosition;
    }
    this.addMonster = function(monsterPosition)
    {
        var newMonster = new Monster(define.imagePath + 'monster.png',this, {down: {from: 0, to: 2}, left: {from:3, to: 5}, right: {from: 6, to: 8}, up: {from: 9, to: 11}});
        newMonster.position = monsterPosition;
        this.monster.push(newMonster);
    }

    this.initialize = function(){

    };

    this.update = function(){

    };

    this.draw = function(ctx){
        for(i=0;i<17;i++){
            for(j=0;j<29;j++){
                var picPosition = {
                    x: this.position.x + (this.MW*j) + this.MW/2,
                    y: this.position.y + (this.MH*i) + this.MH/2,
                }

                switch(this.map[i][j]){
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
    }

}