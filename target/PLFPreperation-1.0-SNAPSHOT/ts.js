var submitButton = document.getElementById('login-button');
var AUTHHEADER = "";
var getAllData = function () {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/products")
        .then(function (response) { return response.json(); })
        .then(function (result) {
        var html = "";
        result.forEach(function (x) {
            html += "<tr><td>".concat(x.id, "</td><td>").concat(x.title, "</td><td>").concat(x.price, "</td><td><button onclick=\"addProductToShoppingCard(").concat(x.id, ")\">addProduct</button></td></tr>");
        });
        document.getElementById("allProductsBody").innerHTML = html;
    });
};
var handleSubmit = function (event) {
    event.preventDefault();
    var mail = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    login(mail, password);
};
var login = function (mail, password) {
    var raw;
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/login?" + "mail=" + mail + "&password=" + password, {
        method: 'POST',
        body: raw
    }).then(function (res) {
        alert(res.status + " " + res.statusText);
        if (res.status === 200) {
            AUTHHEADER = 'Bearer ' + res.headers.get("Authorization");
            console.log(AUTHHEADER);
            createShoppingCart();
        }
    });
};
var createShoppingCart = function () {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/shoppingcart", {
        method: 'POST',
        headers: {
            "Authorization": AUTHHEADER
        }
    }).then(function (res) {
        alert(res.status + " " + res.statusText);
    });
};
var getShoppingCart = function () {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/shoppingcart", {
        method: 'GET',
        headers: {
            "Authorization": AUTHHEADER
        }
    })
        .then(function (response) { return response.json(); })
        .then(function (result) {
        var html = "";
        result.shoppingcart.forEach(function (x) {
            html += "<tr><td>".concat(x.value, "</td><td>").concat(x.key.id, "</td><td>").concat(x.key.title, "</td><td>").concat(x.key.price, "</td></tr>");
        });
        console.log("getting shopping cart....");
        document.getElementById("shoppingCartBody").innerHTML = html;
    });
};
var addProductToShoppingCard = function (id) {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/shoppingcart?" + "id=" + id, {
        method: 'PATCH',
        headers: {
            "Authorization": AUTHHEADER
        }
    }).then(function (res) {
        alert(res.status + " " + res.statusText);
        getShoppingCart();
    });
};
