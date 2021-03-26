var MyBox2D = Framework.Class(Framework.Level, {
		load : function () {
			this.box2d = new Framework.Box2D();
			this.Box2D.createWorld();

			this.background = new Framework.Sprite(define.imagePath + 'background.jpg');
			this.background.position = {
				x:800,
				y:450
			}
			
			var ground = this.box2d.createSquareBody(1000,1.0,this.box2D.bodyType_Static);
			ground.SetPosition(new this.box2d.b2Vec2(0, 24));

			this.wallsValue = [
				{x: 900, y: 500},
				{x: 1100, y: 500},
				{x: 1300, y: 500},
				{x: 1500, y: 500},
				{x: 1150, y: 200},
				{x: 1250, y: 200}
			]

			this.roofsValue = [
				{x: 1000, y: 300},
				{x: 1400, y: 300},
				{x: 1200, y: 150}
			]

			this.walls = new Array();
			for(var i=0; i<this.wallsValue.length; i++){
				this.walls[i] = new wall();
				this.walls[i].init('wall.png', this.box2D);
				this.walls[i].position = {
					x:this.wallsValue[i].x,
					y:this.wallsValue[i].y
				};
				this.walls[i].scale = 1.0;
				this.walls[i].rotation = 0;
			}

			this.floor = new wall();
			this.floor.init('floor.png',this.box2D);
			this.floor.position = {
				x:1200,
				y:270
			};

			this.roofs = new Array();
			for(var i=0; i<this.roofsValue.length; i++){
				this.roofs[i] = new roof();
				this.roofs[i].arraySize = [
					new this.box2D.b2Vec2(-5, 1),
					new this.box2D.b2Vec2(0, -1),
					new this.box2D.b2Vec2(5, 1),
				];
				this.roofs[i].init('roof.png',this.box2D);
				this.roofs[i].position={
					x:this.roofsValue[i].x,
					y:this.roofsValue[i].y
				};
			}
		},
		
		initialize : function () {
			this.rootScene.attach(this.background);
			for(var i=0; i<this.walls.length; i++){
				this.rootScene.attach(this.walls[i].pic);
			}
			this.rootScene.attach(this.floor.pic);
			for(var i=0; i<this.roofs.length; i++){
				this.rootScene.attach(this.roofs[i].pic);
			}
		},

		update : function () {
			for(var i=0; i<this.walls.length; i++){
				this.walls[i].update();
			}

			this.floor.update();

			for(var i=0; i<this.roofs.length; i++){
				this.roofs[i].update();
			}

			this.box2D.draw();
		},

		draw : function (parentCtx) {
			this.box2D.draw();
			this.rootScene.draw();
		},
		
	});
