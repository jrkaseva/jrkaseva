function goDark() {
    var body = document.body;
    body.classList.toggle("darkmode");
    var button = document.getElementById("darkbutton");
    button.classList.toggle("buttondark");
    if (button.innerHTML === "Light" + "<br>" + "Mode"){
        button.innerHTML = "Dark" + "<br>" + "Mode";
    } else {
        button.innerHTML = "Light" + "<br>" + "Mode";
    }
}