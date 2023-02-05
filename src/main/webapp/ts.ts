interface Product {
    id:number,
    title:string,
    description:string,
    price:number,
    discountPercentage:number,
    rating:number,
    stock:number,
    brand:string,
    category:string,
    thumbnail:string,
    images:string[]
}

interface ShoppingCart {

}

const submitButton = document.getElementById('login-button');


let AUTHHEADER = "";
const getAllData = () => {

    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/products")
        .then(response => response.json())
        .then(result => {
            let html = ""
            result.forEach(x => {
                html += `<tr><td>${x.id}</td><td>${x.title}</td><td>${x.price}</td><td><button onclick="addProductToShoppingCard(${x.id})">addProduct</button></td></tr>`
            })

            document.getElementById("allProductsBody").innerHTML = html;

        })

}

const handleSubmit = (event) => {
    event.preventDefault();

    let mail = (document.getElementById('email') as HTMLInputElement).value;
    let password = (document.getElementById('password') as HTMLInputElement).value;

    login(mail, password);
}

const login = (mail: string, password:string) =>{

    let raw;

    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/login?" +"mail=" + mail + "&password="+ password, {
        method: 'POST',
        body: raw,
    }).then(res => {
        alert(res.status + " " + res.statusText);
        if(res.status === 200){
            AUTHHEADER = 'Bearer ' + res.headers.get("Authorization");
            console.log(AUTHHEADER);
            createShoppingCart();
        }
    });
}

const createShoppingCart = () => {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/shoppingcart", {
        method: 'POST',
        headers: {
            "Authorization": AUTHHEADER
        },
    }).then(res => {
        alert(res.status + " " + res.statusText)
    });
}

const getShoppingCart = () => {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/shoppingcart", {
        method: 'GET',
        headers: {
            "Authorization": AUTHHEADER
        },
    })
        .then(response => response.json())
        .then(result => {
            let html = "";
            result.shoppingcart.forEach(x => {
                html += `<tr><td>${x.value}</td><td>${x.key.id}</td><td>${x.key.title}</td><td>${x.key.price}</td></tr>`
            });

            console.log("getting shopping cart....")
            document.getElementById("shoppingCartBody").innerHTML = html;

        });
}
const addProductToShoppingCard = (id:number) => {
    fetch("http://localhost:8080/PLFPreperation-1.0-SNAPSHOT/api/shoppingcart?"+ "id=" + id, {
        method: 'PATCH',
        headers: {
            "Authorization": AUTHHEADER
        },
    }).then(res => {
        alert(res.status + " " + res.statusText)
        getShoppingCart();
    });
}