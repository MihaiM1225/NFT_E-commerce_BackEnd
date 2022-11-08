var box1 = document.getElementById("price");
var box2 = document.getElementById("qty");
var box3 = document.getElementById("total");

function add() {
    let result = Number(box1.value) + Number(box2.value);
    alert(result)
    box3.innerHTML = result.toString();
}