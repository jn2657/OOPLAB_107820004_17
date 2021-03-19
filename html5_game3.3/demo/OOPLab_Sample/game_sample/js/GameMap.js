var GameMap = function(){
    this.MW = 70;
    this.MH = 40;

    this.position = {
        x: 200,
        y: 500
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

    this.draw = function(ctx){
        for(i=0;i<5;i++){
            for(j=0;j<4;j++){
                var picPosition = {
                    x: this.position.x + (this.MW*j) + this.MW/2,
                    y: this.position.y + (this.MH*i) + this.MH/2
                }
            }
        }
    }

}