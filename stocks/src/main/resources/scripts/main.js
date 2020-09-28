function killme() {
    fetch('http://localhost:8080/restgreeting')
        .then(res => res.json())
        .then(data => console.log(data))
        .catch(error => console.log('Error'))
}