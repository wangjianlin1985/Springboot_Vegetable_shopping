/* ==== slider nameSpace ==== */
var slider = function() {
	/* ==== private methods ==== */
	function getElementsByClass(object, tag, className) {
		var o = object.getElementsByTagName(tag);
		for ( var i = 0, n = o.length, ret = []; i < n; i++) {
			if (o[i].className == className) ret.push(o[i]);
		}
		if (ret.length == 1) ret = ret[0];
		return ret;
	}
	function setOpacity (obj,o) {
		if (obj.filters) obj.filters.alpha.opacity = Math.round(o);
		else obj.style.opacity = o / 100;
	}
	/* ==== Slider Constructor ==== */
	function Slider(oCont, speed, iW, iH, oP) {
		this.slides = [];
		this.over   = false;
		this.S      = this.S0 = speed;
		this.iW     = iW;
		this.iH     = iH;
		this.oP     = oP;
		this.oc     = document.getElementById(oCont);
		this.frm    = getElementsByClass(this.oc, 'div', 'slide');
		this.NF     = this.frm.length;
		this.resize();
		for (var i = 0; i < this.NF; i++) {
			this.slides[i] = new Slide(this, i);
		}
		this.oc.parent = this;
		this.view      = this.slides[0];
		this.Z         = this.mx;
		/* ==== on mouse out event ==== */
		this.oc.onmouseout = function () {
			this.parent.mouseout();
			return false;
		}
	}
	Slider.prototype = {
		/* ==== animation loop ==== */
		run : function () {
			this.Z += this.over ? (this.mn - this.Z) * .6 : (this.mx - this.Z) * .6;
			this.view.calc();
			var i = this.NF;
			while (i--) this.slides[i].move();
		},
		/* ==== resize  ==== */
		resize : function () {
			this.wh = this.oc.clientWidth;
			this.ht = this.oc.clientHeight;
			this.wr = this.wh * this.iW;
			this.r  = this.ht / this.wr;
			this.mx = this.wh / this.NF;
			this.mn = (this.wh * (1 - this.iW)) / (this.NF - 1);
		},
		/* ==== rest  ==== */
		mouseout : function () {
			this.over      = false;
			setOpacity(this.view.img, this.oP);
		}
	}
	/* ==== Slide Constructor ==== */
	Slide = function (parent, N) {
		this.parent = parent;
		this.N      = N;
		this.x0     = this.x1 = N * parent.mx;
		this.v      = 0;
		this.loaded = false;
		this.cpt    = 0;
		this.start  = new Date();
		this.obj    = parent.frm[N];
		this.txt    = getElementsByClass(this.obj, 'div', 'text');
		this.img    = getElementsByClass(this.obj, 'img', 'diapo');
		this.bkg    = document.createElement('div');
		//this.bkg.className = 'backgroundText';
		this.obj.insertBefore(this.bkg, this.txt);
		if (N == 0) this.obj.style.borderLeft = 'none';
		this.obj.style.left = Math.floor(this.x0) + 'px';
		setOpacity(this.img, parent.oP);
		/* ==== mouse events ==== */
		this.obj.parent = this;
		this.obj.onmouseover = function() {
			this.parent.over();
			return false;
		}
	}
	Slide.prototype = {
		/* ==== target positions ==== */
		calc : function() {
			var that = this.parent;
			// left slides
			for (var i = 0; i <= this.N; i++) {
				that.slides[i].x1 = i * that.Z;
			}
			// right slides
			for (var i = this.N + 1; i < that.NF; i++) {
				that.slides[i].x1 = that.wh - (that.NF - i) * that.Z;
			}
		},
		/* ==== HTML animation : move slides ==== */
		move : function() {
			var that = this.parent;
			var s = (this.x1 - this.x0) / that.S;
			/* ==== lateral slide ==== */
			if (this.N && Math.abs(s) > .6) {
				this.obj.style.left = Math.floor(this.x0 += s) + 'px';
			}
			/* ==== vertical text ==== */
			var v = (this.N < that.NF - 1) ? that.slides[this.N + 1].x0 - this.x0 : that.wh - this.x0;
			if (Math.abs(v - this.v) > .6) {
				this.bkg.style.top = this.txt.style.top = Math.floor(2 + that.ht - (v - that.Z) * that.iH * that.r) + 'px';
				this.v = v;
				this.cpt++;
			} else {
				if (!this.pro) {
					/* ==== adjust speed ==== */
					this.pro = true;
					var tps = new Date() - this.start;
					if(this.cpt > 1) {
						that.S = Math.max(2, (28 / (tps / this.cpt)) * that.S0);
					}
				}
			}
			if (!this.loaded) {
				if (this.img.complete) {
					this.img.style.visibility = 'visible';
					this.loaded = true;
				}
			}
		},
		/* ==== light ==== */
		over : function () {
			this.parent.resize();
			this.parent.over = true;
			setOpacity(this.parent.view.img, this.parent.oP);
			this.parent.view = this;
			this.start = new Date();
			this.cpt = 0;
			this.pro = false;
			this.calc();
			setOpacity(this.img, 100);
		}
	}
	/* ==== public method - script initialization ==== */
	return {
		init : function() {
			// create instances of sliders here
			// parameters : HTMLcontainer name, speed (2 fast - 20 slow), Horizontal ratio, vertical text ratio, opacity
			this.s1 = new Slider("slider", 10, 1.7/3, 1/2.2, 70);
			setInterval("slider.s1.run();", 16);
		}
	}
}();
