$(document).ready(function(){
    $("#greetingSubmit01").click(function(){
        $.ajax({
            crossDomain: true,
            url: 'http://localhost:8080/path/webPage/greetings',
            type : "POST", 
            dataType : "text",
            contentType: "text/plain",
            data : $("#nameInputText01").val(),
            success : function(data) {
                $("#greetingParagraf01").text(data);
            },
            error: function(xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        })
    });
   
});

