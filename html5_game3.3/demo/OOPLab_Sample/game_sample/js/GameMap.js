var GameMap = function(){
    this.MW = 10;
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
                    x: this.position.x + (this.MH*i) + this.MH/2,
                    y: this.position.y + (this.MH*i) + this.MH/2,
                }
<<<<<<< HEAD
            
=======
>>>>>>> aa545b1829df31593d68eb5576af59f39d3f830a

                switch(this.map[i][j]){
                    case 0:
                        break;
                    case 1:
                        this.block.position = picPosition;
<<<<<<< HEAD
                        this.block.draw();
                    case 2:
                        this.block1.position = picPosition;
                        this.block1.draw();
=======
                        this.block.draw(ctx);
                        break;
                    case 2:
                        this.block1.position = picPosition;
                        this.block1.draw(ctx);
                        break;
>>>>>>> aa545b1829df31593d68eb5576af59f39d3f830a
                }
            }
        }
    };

}