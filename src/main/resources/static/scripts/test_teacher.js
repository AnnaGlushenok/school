function addDivBlock() {
    let div = document.createElement("div")
    div.setAttribute("class", "questions__question question");
    return div;
}

function addInput() {
    let input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("class", "question__text");
    input.setAttribute("placeholder", "Ответ");
    return input;
}

function addButton(question) {
    let button = document.createElement("button");
    button.classList.add("question__add_btn");
    button.addEventListener("click", () => question.remove());

    let img = document.createElement("img");
    img.classList.add("questions__img");
    img.setAttribute("src", "../../images/cross_teacher.svg");
    img.setAttribute("alt", "plus");
    button.appendChild(img);

    return button;
}

function addQuestion(card) {
    const div = addDivBlock();
    const input = addInput();
    const button = addButton(div);

    div.appendChild(input);
    div.appendChild(button);

    const plus = card.querySelector("div.card__questions.questions");
    card.insertBefore(div, plus);
}

function addCard() {
    let wrap = document.createElement("div");
    wrap.classList.add("block__wrap");

    let deleteButton = document.createElement("button");
    deleteButton.addEventListener("click", () => wrap.remove());
    deleteButton.setAttribute("class", "questions__add_btn");

    let img = document.createElement("img");
    img.setAttribute("class", "questions__img");
    img.setAttribute("src", "../../images/cross_teacher.svg");
    img.setAttribute("alt", "cross");
    deleteButton.appendChild(img);

    let div = document.createElement("div");
    div.classList.add("block__card", "card");

    let questionDiv = document.createElement("div");
    questionDiv.classList.add("card__question_title", "question_title");

    let input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("class", "question_title__question");
    input.setAttribute("name", "question");
    input.setAttribute("placeholder", "Вопрос");

    let select = document.createElement("select");
    select.setAttribute("name", "question_types");
    select.setAttribute("class", "question_title__combobox");
    select.setAttribute("id", "question_type");

    let values = ["1 выбор", "Чек боксы", "Самому написать"];
    for (let i = 0; i < values.length; i++) {
        let option = document.createElement("option");
        option.innerHTML = values[i]
        select.appendChild(option);
    }

    let buttonsDiv = document.createElement("div");
    buttonsDiv.setAttribute("class", "card__questions questions");

    let button = document.createElement("button");
    button.setAttribute("class", "questions__add_btn");
    button.addEventListener("click", () => addQuestion(div))

    img = document.createElement("img");
    img.setAttribute("class", "questions__img");
    img.setAttribute("src", "../../images/plus.svg");
    img.setAttribute("alt", "plus");

    let answerInput = document.createElement("input");
    answerInput.setAttribute("type", "text");
    answerInput.setAttribute("class", "question__text");
    answerInput.setAttribute("placeholder", "Правильный ответ");

    questionDiv.appendChild(input);
    questionDiv.appendChild(select);
    button.appendChild(img);
    buttonsDiv.appendChild(button);

    div.appendChild(questionDiv);
    div.appendChild(buttonsDiv);
    div.appendChild(answerInput);

    wrap.appendChild(div);
    wrap.appendChild(deleteButton);

    let parent = document.getElementsByClassName("block__button")[0];
    document.getElementsByClassName("block")[0].insertBefore(wrap, parent);
}

function save() {
    let children = document.querySelector(".block").children;
    let questionJson = [];
    for (let i = 0; i < children.length - 2; i++) {
        let card = children[i].children[0];
        let question1 = card.children[0].children[0];
        let question = question1.value;
        let select = card.children[0].children[1];
        let questionType = select.options[select.selectedIndex].text;
        let questions = card.children;
        let answersJSON = [];
        for (let j = 1; j < questions.length - 2; j++)
            answersJSON[j - 1] = questions[j].children[0].value;

        let answer = card.children[card.children.length - 1].value;
        let q = {
            "question": question,
            "questionType": questionType,
            "answers": answersJSON.join(","),
            "answer": answer
        };
        questionJson.push(q);
    }
    console.log(JSON.stringify(questionJson));
    fetch('/testTeacher', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(questionJson)
    }).then((response) => {
        if (response.ok) {
            alert("Тест добавлен");
        } else {
            return response.text();
        }
    }).then(errorMessage => {
        console.error(errorMessage);
    }).catch(error => {
        console.error(error);
    });
}

