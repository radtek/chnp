





function IMFW(settings) {
	var options = $.extend(true, {}, {
		container: 'imfw',	// 容器ID
		title: 'IMFW',
		server: {
			enable: false,
			url: '',
			type: 'GET'
		},
		data: {
			keys: {
				id: 'id'
			},
			userId: undefined,
			users: [],
			userGroups: [],
			talkGroups: []
		}
	}, settings);
	
	this.id = 'imfw_' + new Date().getTime();
	this.title = options.title;
	this.sessionWins = {};
	
	var userCache = {};
	$.each(options.data.users, function(i, e) {
		userCache[e[options.data.keys.id]] = e;
	});
	this.userCache = userCache;
	
	TitleBar();
	UserBlock();
	SearchBlock();
	Contact();
	Group();
	
	function TitleBar() {
		var strTitleBar = 
			'<div class="imfw-main-title" onmousedown="drag(\'.imfw-main\')">' +
				'<div class="imfw-main-title-text"><span>' + options.title + '</span></div>' +
				'<div class="imfw-main-title-btns"><i class="fa fa-minus"></i><i class="fa fa-close"></i></div>' +
			'</div>';
		$('#' + options.container).append(strTitleBar);
	}
	
	
	function UserBlock() {
		var strUserBlock = 
			'<div class="imfw-main-user">' +
				'<div class="imfw-main-user-imag"><img src="../assets/img/imfw-user-1.jpg"/></div>' +
				'<div class="imfw-main-user-info">' +
					'<div class="user-info-name">' + userCache[options.data.userId].name + '<i style="color:#75bf82;" class="fa fa-circle"></i></div>' +
					'<div class="user-info-decl">' + userCache[options.data.userId].decl + '</div>' +
				'</div>' +
			'</div>';
		$('#' + options.container).append(strUserBlock);
	}
	
	function SearchBlock() {
		var strSearchBlock = '<div class="imfw-main-search"><input placeholder="朋友、群、消息组"></div>';
		
		$('#' + options.container).append(strSearchBlock);
	}
	
	function Contact(groups) {
		var strContact = 		
			'<div class="imfw-main-body">' +
				'<div class="imfw-main-contact-title">' +
					'<div class="active" data-ref="contact_1">联系人</i></div>' +
					'<div data-ref="contact_2">群组</div>' +
					'<div data-ref="contact_3">消息</div>' +
				'</div>' +
				'<div class="imfw-main-contact-body">' +
					'<div id="contact_1" class="">1</div><div id="contact_2" class="" hidden>2</div><div id="contact_3" class="" hidden>3</div>' +
				'</div>' +
				'<div class="imfw-main-contact-tools"><i class="fa fa-user-plus"></i></div>' +
			'</div>';
		$('#' + options.container).append(strContact);
	}
	
	function Group() {
		$.each(options.data.userGroups, function(idx, grp) {
			var strGroup = '<div id="imfw_usergroup_' + grp.id + '" class="user-group">' +
					'<div class="user-group-title active"><i class="fa fa-fw fa-caret-right"></i>' + grp.name + '&nbsp;<span>0</span>/<span>2</span></div>' +
					'<div class="user-list"></div>' +
				'</div>';
			$('#' + options.container + ' .imfw-main-contact-body> #contact_1').html(strGroup);
			
			var strUserList = '';
			$.each(grp.userList, function(idx1, user) {
				strUserList += 
					'<div class="user" data-id="' + user + '">' +
						'<img src="' + userCache[user].img + '"/>' +
						'<div>' +
							'<div class="user-name">' + userCache[user].name + '</div>' +
							'<div class="user-dync">' + userCache[user].decl + '</div>' +
						'</div>' +
					'</div>';
			});
			$('#' + options.container + ' .imfw-main-contact-body> #contact_1> #imfw_usergroup_' + grp.id + '> .user-list').html(strUserList);
		});
	}
			
	$('.user-group-title').on('click', function(e) {
		if($(this).hasClass('active')) $(this).removeClass('active');
		else $(this).addClass('active');
	});
	
			
	$('.imfw-main-contact-title> div').on('click', function(e) {
		var showRef = $(this).data('ref'), hideRef = $('.imfw-main-contact-title> div.active').data('ref');
		if (showRef === hideRef) return;
		
		$('.imfw-main-contact-title> div.active').removeClass('active');
		$(this).addClass('active');
		
		$('#' + showRef).show();
		$('#' + hideRef).hide();
	});
	
				
	$('.user').on('dblclick', this, function(e) {
		var temp = new e.data.SessionWin({
			target: e.data.userCache[$(this).data('id')],
			callback: {
				onSending: function(msg) {
					/**<p>发送消息时的回调函数</p>
					 * <p>
					 * 		注意：该方法须阻塞执行。
					 * </p>
					 *
					 * @param msg 消息对象
					 * {
					 * 		direct：消息来源。self表示自己发送的消息，other表示其他人发的消息
					 * 		message：消息内容
					 * 		userImg：消息用户头像（后期应改造为用户ID，从缓存中获取用户头像）
					 * }
					 * @return Object{
					 * 		code: 0,// 0表示成功，会话窗口正常显示消息；否则提示发送失败
					 * 		info: ''// 失败原因
					 * }
					 */
					 return {
						code: 1,
						info: '网络异常'
					 };
				}
			}
		});
		temp.context = e.data;
		e.data.sessionWins[temp.id] = temp;
		
		e.data.sessionWins[temp.id].addMsg([{
			direct: 'recv',
			message: '明天去1号靶场学习红色七号文件',
			img: '../assets/img/imfw-user-1.jpg',
			timestamp: new Date().getTime()
		}]);
	});


}

/**<p>组件构造方法集合</p>
 * <ul>
 * 		<li>SessionWin：会话窗口。</li>
 * <ul>
 */
IMFW.component = IMFW.prototype = {
	Main: function(settings) {
		
	},
	SessionWin: function(settings) {
		$.extend(true, this, settings);
		this.id = 'sw_' + this.target.id;
		
		var baseFrame = 
			'<div id="' + this.id + '" class="imfw-msg">' +
				'<div class="imfw-msg-title" onmousedown="drag(\'#' + this.id + '\')">' +
					'<div>' + this.target.name + '</div>' +
					'<div>' +
						'<i class="fa fa-minus"></i>' +
						'<i class="fa fa-close" onclick="$(\'#' + this.id + '\').remove()"></i>' +
					'</div>' +
				'</div>' +
				'<div class="imfw-msg-content"></div>' +
				'<div class="imfw-msg-input">' +
					'<div class="imfw-msg-input-tools" onmousedown="drag(\'#' + this.id + '\')">' +
						'<i class="fa fa-folder-o"></i>' +
					'</div>' +
					'<div class="imfw-msg-input-body">' +
						'<textarea rows="5"></textarea>' +
					'</div>' +
					'<div class="imfw-msg-input-btns" onmousedown="drag(\'#' + this.id + '\')">' +
						'<button type="button" onclick="$(\'#' + this.id + '\').remove()">关闭</button>' +
						'<button type="button">发送</button>' +
					'</div>' +
				'</div>' +
			'</div>';
		
		$('body').append(baseFrame);
			
		$('#' + this.id).find('.imfw-msg-input-body> textarea').on('keypress', this, function(e) {
			if (e.keyCode === 13) {
				e.preventDefault();
				if ($(this).val().length <= 0) return false;
				e.data.context.sessionWins[$(this).parents('.imfw-msg').prop('id')].addMsg([{
					direct: 'send',
					message: $(this).val(),
					img: '../assets/img/imfw-user-1.jpg',
					timestamp: new Date().getTime()
				}]);
				$(this).val('');
			}
		});
	
	}
};

/**<p>主界面基类</p>
 * 
 */
IMFW.component.Main.prototype = {
	/**<p>添加用户分组</p>
	 *
	 * @param userGroup 用户分组信息
	 * {
	 * 		id			Int		分组ID
	 * 		name		String	分组名称
	 * 		userList	Array	分组用户ID集合
	 * }
	 */
	addUserGroup: function(userGroup) {
		$.each(userGroup, function(idx, grp) {
			var strGroup = '<div id="imfw_usergroup_' + grp.id + '" class="user-group">' +
					'<div class="user-group-title active"><i class="fa fa-fw fa-caret-right"></i>' + grp.name + '&nbsp;<span>0</span>/<span>2</span></div>' +
					'<div class="user-list"></div>' +
				'</div>';
			$('#' + options.container + ' .imfw-main-contact-body> #contact_1').html(strGroup);
			
			var strUserList = '';
			$.each(grp.userList, function(idx1, user) {
				strUserList += 
					'<div class="user" data-id="' + user + '">' +
						'<img src="' + userCache[user].img + '"/>' +
						'<div>' +
							'<div class="user-name">' + userCache[user].name + '</div>' +
							'<div class="user-dync">' + userCache[user].decl + '</div>' +
						'</div>' +
					'</div>';
			});
			$('#' + options.container + ' .imfw-main-contact-body> #contact_1> #imfw_usergroup_' + grp.id + '> .user-list').html(strUserList);
		});
	}
};

/**<p>会话窗口基类</p>
 * 
 */
IMFW.component.SessionWin.prototype = {
	addMsg: function(msgs) {
		
		var tMsgs = [], tMsg;
		$.each(msgs, function(i, e) {
			console.log(e)
			if (e.direct === 'recv') {
				tMsg = $('<div></div>');
				tMsg.addClass('imfw-message');
				tMsg.addClass('other');
				tMsg.append('<img src="' + e.img + '"/>');
				tMsg.append('<div>' + e.message + '</div>');
				tMsg.append('<div></div>');
				tMsgs.push(tMsg);
			}else if (e.direct === 'send') {
				e.id = this.id + '_msg_' + e.timestamp;
				tMsg = $('<div id="' + e.id + '"></div>');
				tMsg.addClass('imfw-message');
				tMsg.addClass('self');
				tMsg.append('<div></div>');
				tMsg.append('<div>' + e.message + '</div>');
				tMsg.append('<img src="' + e.img + '"/>');
				tMsgs.push(tMsg);
			}
			tMsg = null;
		});
		console.log(tMsgs);
		$('#' + this.id).find('.imfw-msg-content').append(tMsgs);
		
		var content = $('#' + this.id).find('.imfw-msg-content')[0];
		content.scrollTop = content.scrollHeight;
	},
	show: function() {
		if ($('#' + this.id).length > 0) $('#' + this.id).show();
		else {
			console.log(this);
			delete this;
			console.log(this);
		}
	}
};



function inherit(subtype, supertype) {
    var prototype = object(supertype.prototype);
    prototype.constructor = subtype;
    subtype.prototype = prototype;
}

function IMFWSessionWindow() {
    
}


