async function auth() {
    let inputs = document.getElementsByClassName("form__input");
    let json = {
        "login": inputs[0].value,
        "password": inputs[1].value
    }
    await fetch('/auth', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(json)
    })
        .then(response => response.text())
        .then(data => {
            const json = JSON.parse(data);
            if (json.role === "STUDENT") {
                window.location.href = "/main";
            } else {
                window.location.href = "/mainTeacher";
            }
        })
        .catch(function (error) {
            console.log("Ошибка при отправке данных на сервер");
        });
}