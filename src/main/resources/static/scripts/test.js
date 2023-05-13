fetch("/getTest")
    .then(response => response.json())
    .then(data => {
        questions = JSON.parse(data.test);
        id = data.id;
        len = questions.length;
        name = data.name;
        startTime = Date.now();
        showQuestion();
    })
    .catch(error => console.error(error));

let id, len, name, questions, startTime;
let answers = []
let currentQuestion = 0;
const types = {
    "1 выбор": "radio",
    "Чек боксы": "checkbox",
    "Самому написать": "textarea"
}

function showQuestion() {
    document.getElementsByClassName("card__question_number")[0].textContent = (currentQuestion + 1) + "/" + questions.length;
    document.getElementsByClassName("card__question_caption")[0].textContent = questions[currentQuestion].question;
    let type = types[questions[currentQuestion].questionType];
    let tasks = questions[currentQuestion].answers.split(",");
    for (let j = 0; j < tasks.length; j++)
        document.getElementsByClassName("card__questions questions")[0].appendChild(createQuestion(type, j, tasks[j]));
}

function check() {
    let buttons = document.getElementsByName('answer');
    let ans = []
    for (let i = 0; i < buttons.length; i++) {
        if (buttons[i].checked) {
            let label = document.querySelector(`[for=${buttons[i].id}]`);
            ans.push(label.textContent);
        }
    }
    answers[currentQuestion] = {
        "question": currentQuestion,
        "ans": ans
    };
}

function clear() {
    let cards = [...document.getElementsByClassName("card__question question")];
    for (let i = 0; i < cards.length; i++)
        cards[i].remove();
}

function createQuestion(type, id, text) {
    let div = document.createElement("div");
    div.setAttribute("class", "card__question question");

    let input = document.createElement("input");
    input.setAttribute("type", type);
    input.setAttribute("name", "answer");
    input.setAttribute("class", "question__checkbox");
    input.setAttribute("id", "question" + id);

    let label = document.createElement("label");
    label.setAttribute("for", "question" + id);
    label.innerHTML = text;

    div.appendChild(input);
    div.appendChild(label);
    return div;
}

async function showResult() {
    let mark, time;
    await fetch("/test/" + id + "/" + startTime, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(answers)
    })
        .then((response) => response.json())
        .then(data => {
            mark = data.mark;
            time = data.time;
        }).catch(error => {
            console.error(error);
        });

    document.getElementsByClassName("block__card card")[0].remove();
    let div = document.createElement("div");
    div.setAttribute("class", "block__card card");

    let p = document.createElement("p");
    p.setAttribute("class", "card__title");
    p.textContent = "Тест";
    div.appendChild(p);

    p = document.createElement("p");
    p.setAttribute("class", "card__question_caption");
    p.textContent = name;
    div.appendChild(p);

    p = document.createElement("p");
    p.setAttribute("class", "card__title");
    p.textContent = "Оценка";
    div.appendChild(p);

    p = document.createElement("p");
    p.setAttribute("class", "card__mark");
    p.textContent = mark;
    div.appendChild(p);

    let date = document.createElement("div");
    date.setAttribute("class", "card__datetime datetime");

    let left = document.createElement("div");
    left.setAttribute("class", "datetime__left_block");
    p = document.createElement("p");
    p.setAttribute("class", "datetime__text");
    p.innerHTML = "Дата:<br>Время выполнения: ";
    left.appendChild(p);
    date.appendChild(left);

    let right = document.createElement("div");
    right.setAttribute("class", "datetime__right_block");
    p = document.createElement("p");
    p.setAttribute("class", "datetime__text");
    p.innerHTML = new Date().toLocaleDateString() + "<br>" + time;
    right.appendChild(p);
    date.appendChild(right);

    div.appendChild(date);
    document.getElementsByClassName("main__block block")[0].appendChild(div);
}

function next() {
    check();
    currentQuestion++;
    if (currentQuestion >= len) {
        clear();
        showResult();
        return;
    }
    clear();
    showQuestion();
}
