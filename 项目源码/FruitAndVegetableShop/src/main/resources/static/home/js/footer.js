jQuery(function(){
  jQuery(".fixedBox ul.fixedBoxList li.fixeBoxLi").hover(
	function (){
	  jQuery(this).find('span.fixeBoxSpan').addClass("hover");
	  jQuery(this).addClass("hover");
	},
	function () {
	 jQuery(this).find('span.fixeBoxSpan').removeClass("hover");
	  jQuery(this).removeClass("hover");
	}
  );
  jQuery('.BackToTop').click(function(){$('html,body').animate({scrollTop: '0px'}, 800);});
  var oDate=new Date();
  var iHour=oDate.getHours();
  if(iHour>8 && iHour<32){
    jQuery(".Service").addClass("startWork");
    jQuery(".Service").removeClass("Commuting");

  }else{
    jQuery(".Service").addClass("Commuting");
    jQuery(".Service").removeClass("startWork");
  };
  
});

jQuery(function(){
  jQuery('.listLeftMenu dl dt').click(function(){
    var but_list=jQuery(this).attr('rel');
    if(but_list=='off'){
      jQuery(this).attr('rel','on');
	  jQuery('.listLeftMenu dl').removeClass('off');
	  jQuery(this).parent().addClass('on');
    }else{
      jQuery(this).attr('rel','off');
	  jQuery(this).parent().removeClass('on');
	  jQuery(this).parent().addClass('off');
    }
  });
});
/*******************单选框复选框美化效果*****************/
   jQuery.fn.customInput = function(){
            $(this).each(function(i){
                if($(this).is('[type=checkbox],[type=radio]')){
                    var input = $(this);
                    //get the associated label using the input's id
                    var label = $('label[for='+input.attr('id')+']');
                    //get type,for classname suffix
                    var inputType = (input.is('[type=checkbox]')) ? 'checkbox' : 'radio';
                    //wrap the input + label in a div
                    $('<div class="custom-'+ inputType +'"></div>').insertBefore(input).append(input,label);
                    //find all inputs in this set using the shared name attribute
                    var allInputs = $('input[name='+input.attr('name')+']');
                    //necessary for browsers that don't support the :hover pseudo class on labels
                    label.hover(function(){
                        $(this).addClass('hover');
                        if(inputType == 'checkbox' && input.is(':checked')) {
                            $(this).addClass('checkedHover');
                        }
                    },function(){
                        $(this).removeClass('hover checkedHover');
                    });
                    
                    //bind custom event, trigger it, bind click,focus,blur events
                    input.bind('updateState',function(){
                        if(input.is(':checked')){
                            if(input.is(':radio')){
                                allInputs.each(function(){
                                    $('label[for='+$(this).attr('id')+']').removeClass('checked');
                                });
                            };
                            label.addClass('checked');
                        } else {
                            label.removeClass('checked checkedHover checkedFocus');
                        }
                    })
                    .trigger('updateState')
                    .click(function(){
                        $(this).trigger('updateState');
                    })
                    .focus(function(){
                        label.addClass('focus');
                        if(inputType == 'checkbox' && input.is(':checked')) {
                            $(this).addClass('checkedFocus');
                        }
                    })
                    .blur(function(){
                        label.removeClass('focus checkedFocus');
                    });                    
                }
            });
        }
