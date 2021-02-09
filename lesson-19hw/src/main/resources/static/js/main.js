   	$(document).ready(function() {
            $('#loader').hide();
            $("#signup").on("click", function() {
            $("#signup").prop("disabled", true);
                var name = $("#name").val();
                var age = $("#age").val();
                var file = $("#file").val(); 
                var form = $('#register-form')[0];
                var data = new FormData(form);
                data.append("name",name);
                data.append("age",age);
                var str = JSON.stringify(data);
                $('#loader').show();
                if (name === "" || age === "" || file === "") {
                	$("#signup").prop("disabled", false);
                    $('#loader').hide();
                    $("#name").css("border-color", "red");
                    $("#age").css("border-color", "red");
                    $("#file").css("border-color", "red");
                    $("#error_name").html("Please fill the required field.");
                    $("#error_age").html("Please fill the required field.");
                    $("#error_file").html("Please fill the required field.");
                } else {
                    $("#name").css("border-color", "");
                    $("#age").css("border-color", "");
                    $("#file").css("border-color", "");
                    $('#error_name').css('opacity', 0);
                    $('#error_age').css('opacity', 0);
                    $('#error_file').css('opacity', 0);
                    $.ajax({
                        type: 'POST',
                        enctype: 'multipart/form-data',
                        data: data,
                        url: "/image-upload/saveStudent",
                        processData: false, 
                        contentType: false, 
                        cache: false,     
                        success: function(data, statusText, xhr) {
                        console.log(xhr.status);
                        if(xhr.status == "200") {
                        	$('#loader').hide(); 
                            $("#register-form")[0].reset();
                            $("#error").text("");
                            $("#success").text(data);
                            $('#success').delay(5000).fadeOut('slow');
                            $("#signup").prop("disabled", false);
                         }	   
                        },
                        error: function(e){
                        	$('#loader').hide();
                            $("#error").text(e.responseText);
                            $('#error').delay(10000).fadeOut('slow');
                            $("#signup").prop("disabled", false);
                        }
                    });
                }
            });
        });