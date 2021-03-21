var GameMap = function(){
    this.MW = 40;
    this.MH = 40;

    this.position = {
        x: 800,
        y: 450
    };

    this.map = [[1,2,1,2],[2,1,2,1],
                [0,1,0,1],[2,0,2,0],
                [1,2,1,2]];

    this.load = function(){
        this.block = new Framework.Sprite(define.imagePath, 'block.png');
        this.block1 = new Framework.Sprite(define.imagePath, 'block1.png');
    };

    this.initialize = function(){

    };

    this.update = function(){

    };

    this.draw = function(){
        for(i=0;i<5;i++){
            for(j=0;j<4;j++){
                var picPosition = {
                    x: this.position.x + (this.MW*j) + this.MW/2,
                    y: this.position.y + (this.MH*i) + this.MH/2,
                }
            

                switch(this.map[i][j]){
                    case 0:
                        break;
                    case 1:
                        this.block.position = picPosition;
                        this.block.draw();
                    case 2:
                        this.block1.position = picPosition;
                        this.block1.draw();
                }
            }
        }
    };

}