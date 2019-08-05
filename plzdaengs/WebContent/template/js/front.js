$(function () {
    // ------------------------------------------------------- //
    // Sidebar
    // ------------------------------------------------------ //
    $('.sidebar-toggler').on('click', function () {
        $('.sidebar').toggleClass('shrink show');
        var className = $('#sidebar').attr("class");
        var sidebarWidth = parseInt($("#sidebar").css("width"));
    	var marginLeftSideBar = parseInt($("#sidebar").css("margin-left"));
    	var length = sidebarWidth + marginLeftSideBar;
    	var contentsWidth = parseInt($("#contents").css("width"));

    	if(className.indexOf("show")>0 && contentsWidth > 1200){
    		//큰화면에서 메뉴바 줄이기
    		//alert("//큰화면에서 메뉴바 줄이기");
    		$("#contents").css("margin-left", "6rem");
    	} else if(className.indexOf("show")>0 && contentsWidth < 1200){
    		//작은화면에서 메뉴바없애기
    		$("#contents").css("margin-left", "6rem");
    	} else if(className.indexOf("show")<0 && contentsWidth > 1200){
    		//큰화면에서 메뉴바 늘리기
    		$("#contents").css("margin-left", "20rem");
    	} else if(className.indexOf("show")<0 && contentsWidth < 1200){
    		//작은화면에서 메뉴바 보이기	
    		$("#contents").css("margin-left", 0);
    	} 
        
    });
    // 사이드바의 조절 추가
    $(window).resize(function() {
		resize();
	});
	resize();
    

    // ------------------------------------------------------ //
    // For demo purposes, can be deleted
    // ------------------------------------------------------ //

    var stylesheet = $('link#theme-stylesheet');
    $( "<link id='new-stylesheet' rel='stylesheet'>" ).insertAfter(stylesheet);
    var alternateColour = $('link#new-stylesheet');

    if ($.cookie("theme_csspath")) {
        alternateColour.attr("href", $.cookie("theme_csspath"));
    }

    $("#colour").change(function () {

        if ($(this).val() !== '') {

            var theme_csspath = 'css/style.' + $(this).val() + '.css';

            alternateColour.attr("href", theme_csspath);

            $.cookie("theme_csspath", theme_csspath, { expires: 365, path: document.URL.substr(0, document.URL.lastIndexOf('/')) });

        }

        return false;
    });

});

function resize() {
	//var sidebarWidth = $("#sidebar").width();
	//var marginLeftSideBar = $("#sidebar").css("margin-left");
	//console.log("sidebarWidth : " + sidebarWidth + "  ///  " + "marginLeftSideBar : " + marginLeftSideBar);
	
	var className = $('#sidebar').attr("class");
    //var sidebarWidth = parseInt($("#sidebar").css("width"));
	//var marginLeftSideBar = parseInt($("#sidebar").css("margin-left"));
	//var length = sidebarWidth + marginLeftSideBar;
	//var contentsWidth = parseInt($("#contents").css("width"));
	var contentsWidth = $(document).width();
	
	if(contentsWidth > 1200){
		$("#toggle").show();
		$(".header").hide();
		$("#sidebar").css("margin-top", 0);
	}else{
		$("#toggle").hide();
		$(".header").show();
		$("#sidebar").css("margin-top", "72px");
	}
	
	if(className.indexOf("show")>0 && contentsWidth > 1200){
		//큰화면에서 메뉴바 줄이기
		$("#contents").css("margin-left", "6rem");
	} else if(className.indexOf("show")>0 && contentsWidth < 1200){
		//작은화면에서 메뉴바없애기
		$("#contents").css("margin-left", "6rem");
	} else if(className.indexOf("show")<0 && contentsWidth > 1200){
		//큰화면에서 메뉴바 늘리기
		$("#contents").css("margin-left", "20rem");
	} else if(className.indexOf("show")<0 && contentsWidth < 1200){
		//작은화면에서 메뉴바 보이기	
		$("#contents").css("margin-left", 0);
	} 
	//$("#contents").css("margin-left", parseInt(sidebarWidth) + parseInt(marginLeftSideBar));
}

Cookies.set('active', 'true');
