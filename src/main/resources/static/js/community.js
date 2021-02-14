function post() {
    var questionId = $("#question_id").val();
    var connet = $("#commemnt_connet").val();
    if(!connet){
        alert("不能回复空内容");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId":questionId,
            "content": connet,
            "type": 1
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200) {
               window.location.reload()
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.2a33736e1e80c360&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closeable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
console.log(questionId);

}