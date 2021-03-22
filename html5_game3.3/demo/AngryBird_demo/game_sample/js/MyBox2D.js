var MyBox2D = Framework.Class(Framework.Level, {
		load : function () {
			this.box2d = new Framework.Box2D();
			this.Box2D.createWorld();

			this.background = new Framework.Sprite(define.imagePath + 'background.jpg');
			this.background.position = {
				x:800,
				y:450
			}
			
		},
		
		initialize : function () {
			
		},

		update : function () {
			
		},

		draw : function (parentCtx) {
			
		},
		
	});
