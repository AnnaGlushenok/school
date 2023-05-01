async function registration() {
    event.preventDefault();
    let inputs = document.querySelector(".form").children;
    let student = {
        "surname": inputs[0].value,
        "name": inputs[1].value,
        "patronymic": inputs[2].value,
        "classId": inputs[3].value,
        "email": inputs[4].value,
        "login": inputs[5].value,
        "password": inputs[6].value
    }
    if (!inputs[0].value.match("[А-Яа-я]+") || !inputs[1].value.match("[А-Яа-я]+") || !inputs[2].value.match("[А-Яа-я]+") || !inputs[3].value.match("^([1-9]|1[0-1])$") ||
       ! inputs[5].value.match("[A-Za-z(\\d+)?]{4,}") || !inputs[6].value.match("[A-Za-z(\\d+!@#$%^&*:;)?]{6,}")) {
        alert("Неверное заполнение полей");
        return;
    }
    await fetch('/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(student),
    }).then(response => {
        if (response.ok) {
            alert("Регистрация прошла успешно");
            window.location.href = "/";
        } else {
            return response.text();
        }
    }).then(errorMessage => {
        alert(errorMessage);
        console.log(errorMessage);
    }).catch(error => {
        console.error(error);
    });
}