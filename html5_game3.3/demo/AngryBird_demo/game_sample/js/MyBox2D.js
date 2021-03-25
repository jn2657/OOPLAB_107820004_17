var MyBox2D = Framework.Class(Framework.Level, {
		load : function () {
			this.box2D = new Framework.Box2D();    //宣告Box2D物件
			this.box2D.createWorld();              //產生Box2D世界

			//背景圖
			this.background = new Framework.Sprite(define.imagePath + 'background.jpg');
			this.background.position = {
				x: 800,
				y: 450
			};

			//ground
			var ground = this.box2D.createSquareBody(1000, 1.0, this.box2D.bodyType_Static);
			ground.SetPosition(new this.box2D.b2Vec2(0, 24));

			//walls position
			this.wallsValue = [
				{x: 900, y: 500},
				{x: 1100, y: 500},
				{x: 1300, y: 500},
				{x: 1500, y: 500},
				{x: 1150, y: 200},
				{x: 1250, y: 200}
			];

			//roofs position
			this.roofsValue = [
				{x: 1000, y: 300},
				{x: 1400, y: 300},
				{x: 1200, y: 150}
			];

			//宣告walls陣列並加上參數
			this.walls = new Array();
			for(var i = 0; i < this.wallsValue.length; i++){
				this.walls[i] = new wall();
				this.walls[i].init('wall.png', this.box2D);
				this.walls[i].position = {
					x: this.wallsValue[i].x,
					y: this.wallsValue[i].y
				};
				this.walls[i].scale = 1.0;
				this.walls[i].rotation = 0;
			}
			
			this.floor = new wall();
			this.floor.init('floor.png', this.box2D);
			this.floor.position = {
				x: 1200,
				y: 270
			};

			//宣告roofs陣列並加上參數
			this.roofs = new Array();
			for(var i = 0; this.roofsValue.length; i++){
				this.roofs[i] = new roof();
				this.roofs[i].arraySize = [
					new this.box2D.b2Vec2(-5, 1),
					new this.box2D.b2Vec2(0, -1),
					new this.box2D.b2Vec2(5, 1),
				];
				this.roofs[i].init('roof.png', this.box2D);
				this.roofs[i].position = {
					x: this.roofsValue[i].x,
					y: this.roofsValue[i].y
				};
			}

			//angryBird
			this.angryBird = new angryBird();
			this.angryBird.init('hero.png', this.box2D);
			this.angryBird.position = {
				x: 180,
				y: 100
			};

		},
		
		initialize : function () {

			//Scene Attach
			this.rootScene.attach(this.background);
			for(var i = 0; i < this.walls.length; i++){
				this.rootScene.attach(this.walls[i].pic);
			}

			this.rootScene.attach(this.floor.pic);
			for(var i = 0; i < this.roofs.length; i++){
				this.rootScene.attach(this.roofs[i].pic);
			}

			this.rootScene.attach(this.angryBird.pic);
			
		},

		update : function () {

			this.angryBird.update();

			for(var i = 0; i < this.walls.length; i++){
				this.walls[i].update();
			}

			this.floor.update();

			for(var i = 0; i < this.roofs.length; i++){
				this.roofs[i].update();
			}

			this.box2D.draw();
			
		},

		draw : function (parentCtx) {
			
			this.box2D.draw();
			this.rootScene.draw();

		},

		click : function(e){
			angle = Math.atan2(e.y - this.angryBird.component.position.y, e.x - this.angryBird.component.position.x);
			angle = angle * 180 / Math.PI + 90;
			this.angryBird.shoot(angle);
		},
		
	});
