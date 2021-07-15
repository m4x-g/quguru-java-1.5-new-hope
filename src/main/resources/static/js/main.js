function setLang(langId) {
    $.get("http://localhost:8080/setLang/" + langId, function(data) {
        location.reload();
    });
}