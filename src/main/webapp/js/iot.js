jQuery(function($) {
	$(document).on("click", "a[id^=start]", function(e) {
		e.preventDefault()
		$.ajax({
			url : '/openshift/rest/iot/start',
			type : 'GET',
			success : function(data) {
				$('#welcome').hide('slow')
				$('#device').html(data)
				$('#device').fadeIn('slow')
			},
		});
	});
	$(document).on("click", "a[id^=navactions]", function(e) {
		e.preventDefault()
		$.ajax({
			url : '/openshift/rest/iot/actions/view',
			type : 'GET',
			success : function(data) {
				$('#device').slideUp('slow')
				$('#welcome').slideUp('slow')
				$('#actions').html(data)
				$('#actions').slideDown('slow')
			},
		});
	});
	$(document).on("click", "a[id^=adddevice]", function(e) {
		e.preventDefault()
		var action = $('#action').val()
		var key = $('#yourkey').val()
		$.ajax({
			url : '/openshift/rest/iot/device/' + action + '/' + key,
			type : 'POST',
			success : function(data) {
				getActions();
			},
		});
	});
	$(document).on("click", "button[id^=trigger]", function(e) {
		e.preventDefault()
		var action = $(this).attr("data-action") 
		var key = $(this).attr("data-key") 
		$.ajax({
			url : '/openshift/rest/iot/trigger/' + action + '/' + key,
			type : 'POST',
			beforeSend: function() {
				$('#trigger-progress-' + action).show()
			},
			success : function(data) {
				$('#trigger-progress-' + action).hide()
				$('#trigger-response-' + action).html('<div class="alert alert-success" role="alert">' + data.response + '</div>')
			}
		});
	});
});

function getActions() {
	$.ajax({
		url : '/openshift/rest/iot/actions',
		type : 'GET',
		success : function(data) {
			$('#navactions').html(
					"Actions <span class='tag tag-info'>" + data.length
							+ "</span>")
		},
	});
}