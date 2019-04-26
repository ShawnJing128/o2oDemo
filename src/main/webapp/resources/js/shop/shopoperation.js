/**
 * 从后台获取区域，分类等信息填充到表单 获取表单的列表信息，点击提交后获取表单内容通过ajax转发到后台
 */
$(function() {
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;// 传入id默认对网页进行更新，不传就是店铺注册
	var initUrl = '/o2oDemo/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2oDemo/shopadmin/registershop';
	var shopInfoUrl = '/o2oDemo/shopadmin/getshopbyid?shopId=' + shopId;
	var editShopUrl = '/o2oDemo/shopadmin/modifyshop';
	if (!isEdit) {
		getShopInitInfo();
	} else {
		getShopInfo(shopId);
	}

	// 通过店铺Id获取店铺信息
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory = '<option data-id="'
					+ shop.shopCategory.shopCategoryId + '" selected>'
					+ shop.shopCategory.shopCategoryName + '</option>';
			var tempAreaHtml = '';

			data.areaList.map(function(item, index) {
				tempAreaHtml += '<option data-id="' + item.areaId + '">'
						+ item.areaName + '</option>';
			});
				$('#shop-category').html(shopCategory);
				$('#shop-category').attr('disable', 'disable');// 店铺类别不能修改
				$('#area').html(tempAreaHtml);
				// 给店铺选定原先的所属的区域
				$("#area option[data-id='" + shop.area.areaId + "']").attr(
						"selected", "selected");
			}
		});
	}

	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}

	$('#submit').click(function() {
		/**
		 * 这是名字空间，全局变量会绑定到window上，不同的JavaScript文件如果使用了相同的全局变量，
		 * 或者定义了相同名字的顶层函数，都会造成命名冲突，并且很难被发现。 减少冲突的一个方法是把自己的所有变量和函数全部绑定到一个全局变量中。
		 */
		var shop = {};
		if(isEdit){
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		// 列表选择
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		var shopImg = $('#shop-img')[0].files[0];
		var formData = new FormData();// 接收表单内容
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));// 将JavaScript值转换为JSON字符串
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append('verifyCodeActual', verifyCodeActual);
		// 提交后台
		$.ajax({
			url : (isEdit ? editShopUrl : registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功');
				} else {
					$.toast('提交失败' + data.errMsg);
				}
				$('#captcha_img').click();
			}
		});
	});
})
