/**
 * memberjoin js 파일 
 */
function fileDropDown() {
	var fileInputText = $(".registerfileupload input[type=text]");
	var fileInput = $(".registerfileupload input[type=file]");

	//드래그 한채로 들어오기
	fileInputText.on("dragenter", function(e) {
		e.stopPropagation();
		e.preventDefault();
		fileInputText.css("border-color", "#c6d8ff");
		fileInputText.css("box-shadow",
				"0 0 0 0.2rem rgba(70, 128, 255, 0.25)");
	});

	//드래그 한채로 나가기
	fileInputText.on("dragleave", function(e) {
		e.stopPropagation();
		e.preventDefault();
		fileInputText.css("border-color", "#ced4da");
		fileInputText.css("box-shadow", "");
	});
	//??
	fileInputText.on('dragover', function(e) {
		e.stopPropagation();
		e.preventDefault();
		fileInputText.css("border-color", "#c6d8ff");
		fileInputText.css("box-shadow",
				"0 0 0 0.2rem rgba(70, 128, 255, 0.25)");
	});

	//드래그 객체 놓기
	fileInputText.on('drop', function(e) {
		e.preventDefault();
		fileInputText.css("border-color", "#ced4da");
		fileInputText.css("box-shadow", "");

		var files = e.originalEvent.dataTransfer.files;

		if (files != null) {
			if (files.length < 1) {
				showAlertModal("이미지 업로드 경고", "잘못된 파일입니다.");
				return;
			}
			fileRegisterProcess(files);
		} else {
			showAlertModal("이미지 업로드 경고", "프로필 등록을 실패하였습니다.");
		}
	});
}

function fileRegisterProcess(files) {
	var fileInputText = $(".registerfileupload input[type=text]");
	var fileInput = $(".registerfileupload input[type=file]");
	var imgtag = $(".registerfileupload img");

	var fileName = files[0].name;

	if (!(files[0].type.startsWith('image/'))) {
		showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
		return;
	}

	fileInput[0].files = files;
	fileName = fileInput[0].files[0].name;
	$(fileInputText[0]).val(fileName);
	//

	imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");
	if (window.FileReader) {
		var reader = new FileReader();
		reader.onload = function(e) {
			var src = e.target.result;
			imgtag.prop("src", src);
		}
		reader.readAsDataURL(fileInput[0].files[0]);
	} else {
		fileInput[0].select();
		fileInput[0].blur();
		var imgSrc = document.selection.createRange().text;
		imgtag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""
				+ imgSrc + "\")";
	}
}

function dropdownItemClick() {
	var text = $(this).text();
	$(this).parent().siblings("button").text(text);
	$(this).parent().siblings("input[type=hidden]").val(text);
}

function zipModalPopUp() {
	$("#doro").val("");
	$("#zipModal").modal("show");
}

function fileUploadChange() {
	var filename = this.files[0].name;
	var imgtag = $(this).siblings("img");
	imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");
	
	if (!($(this)[0].type.startsWith('image/'))) {
		$(this)[0].value = "";
		showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
		return;
	}
	
	$(this).siblings("input[type=text]").val(filename);

	if (window.FileReader) {
		var reader = new FileReader();
		reader.onload = function(e) {
			var src = e.target.result;
			imgtag.prop("src", src);
		}
		reader.readAsDataURL($(this)[0].files[0]);
	} else {
		$(this)[0].select();
		$(this)[0].blur();
		var imgSrc = document.selection.createRange().text;
		//alert(imgSrc);
		imgtag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""
				+ imgSrc + "\")";
	}
}