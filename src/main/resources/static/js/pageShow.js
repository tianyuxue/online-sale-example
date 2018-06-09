
	var $ = function(id){
		return document.getElementById(id);
	}
	
	$('plusNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		if(num > 0){
			num --;
			$('allNum').innerHTML = num;
		}else{
			alert("您没有购买任何商品");
		}
	};

	$('addNum').onclick = function(e){
		e = window.event || e;
		o = e.srcElement || e.target;
		var num = $('allNum').textContent;
		num ++;
		$('allNum').innerHTML = num;
	};
	
	var loading = new Loading();
	var layer = new Layer();

	
	$('add').onclick = function(e){
		var ele = e.target;
		var id = ele && ele.dataset.id;
		var title = ele && ele.dataset.title;
		var price = ele && ele.dataset.price;
		var num = $('allNum').innerHTML;
		var productDetail = {'id':id,'price':price,'title':title,'num':num};
		var name = 'products';
		var productList1 = new Array;
		var productList = util.getCookie(name);
		if(productList == "" || productList == null){
			productList1.push(productDetail);
			util.setCookie(name,productList1);
		}else if(util.findOne(productList,id)){
			util.modifyTwo(productList,id,num);
			util.setCookie(name,productList);
		}else{
			productList.push(productDetail);
			util.setCookie(name,productList);
		}
		console.log(document.cookie);
//		util.deleteCookie(name);
		e == window.event || e;
		layer.reset({
			content:'确认加入购物车吗？',
			onconfirm:function(){
				layer.hide();
				loading.show();
				loading.result('添加购物车成功');
			}.bind(this)
		}).show();
		return;
	};




