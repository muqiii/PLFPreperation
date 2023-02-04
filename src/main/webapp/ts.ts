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


const getAllData = () => {

    fetch("http://localhost:8080/Exa_PLFPREP-1.0-SNAPSHOT/api/products")
        .then(response => response.json())
        .then(result => {
            let html = ""
            result.forEach(x => {
                html += `<tr><td>${x.id}</td><td>${x.title}</td><td>${x.description}</td>
                 <td>${x.price}</td><td>${x.discountPercentage}</td><td>${x.rating}</td>
                 <td>${x.stock}</td><td>${x.brand}</td><td>${x.category}</td><td>${x.thumbnail}</td></tr>`
            })

            document.getElementById("ResultBody").innerHTML = html;

        })

}