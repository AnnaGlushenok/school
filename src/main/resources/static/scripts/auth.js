function auth() {
    let inputs = document.querySelector(".form").children;
    let json = {
        login: inputs[0].innerHTML,
        password: inputs[1].innerHTML
    }
    alert(json);
    fetch('/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(json)
    }).then(function (response) {
        console.log("Данные успешно отправлены на сервер");
    }).catch(function (error) {
        console.log("Ошибка при отправке данных на сервер");
    });
}