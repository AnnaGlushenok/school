function addDivBlock(id) {
    let id1 = findLastQuestionId();
    let div = document.createElement("div")
    div.setAttribute("class", "questions__question question");
    div.setAttribute("id", id + "_" + (id1 + 1));

    return div;
}

function addInput() {
    let input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("class", "question__text");
    input.setAttribute("placeholder", "Ответ");
    return input;
}

function addButton() {
    let button = document.createElement("button");
    button.setAttribute("class", "question__add_btn");
    button.setAttribute("onclick", "deleteAnswer()");

    let img = document.createElement("img");
    img.setAttribute("class", "questions__img");
    img.setAttribute("src", "../../images/cross_teacher.svg");
    img.setAttribute("alt", "plus");
    button.appendChild(img);

    return button;
}

function findLastQuestionId() {
    let id = event.target.parentNode.parentNode.parentNode.id;
    let card = document.getElementById(id);
    let questions = card.getElementsByClassName("question");
    let max = -1;
    for (let i = 0; i < questions.length; i++) {
        let d = questions[i].id.split("_")[1];
        if (max < d)
            max = d
    }
    return +max;
}

function findLastId(className) {
    let el = document.querySelector(className);
    if (!el)
        return 0;
    let children = el.childNodes;
    let max = -1;
    for (let i = 0; i < children.length; i++) {
        if (max < children[i].id)
            max = children[i].id
    }
    return +max;
}

function deleteAnswer() {
    document.getElementById(event.target.parentNode.parentNode.id).remove();
}

function deleteCard() {
    document.getElementById(event.target.parentNode.parentNode.id).remove();
}

function addQuestion() {
    let id = event.target.parentNode.parentNode.parentNode.id;
    let div = addDivBlock(id);
    let input = addInput()
    let button = addButton();

    div.appendChild(input);
    div.appendChild(button);

    let elem = document.getElementById(id).children[0].children;
    let parent = elem[elem.length - 2];
    let d = document.getElementById(id).children[0];
    d.insertBefore(div, parent);
}

function addCard() {
    let wrap = document.createElement("div");
    wrap.setAttribute("class", "block__wrap");
    wrap.setAttribute("id", findLastId(".block") + 1);

    let deleteButton = document.createElement("button");
    deleteButton.setAttribute("onclick", "deleteCard()");
    deleteButton.setAttribute("class", "questions__add_btn");

    let img = document.createElement("img");
    img.setAttribute("class", "questions__img");
    img.setAttribute("src", "../../images/cross_teacher.svg");
    img.setAttribute("alt", "cross");

    deleteButton.appendChild(img);

    let div = document.createElement("div");
    div.setAttribute("class", "block__card card");
    div.setAttribute("id", findLastId(".block") + 1);

    let questionDiv = document.createElement("div");
    questionDiv.setAttribute("class", "card__question_title question_title");

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
    button.setAttribute("onclick", "addQuestion()");

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
        let questionsJSON = [];
        for (let j = 1; j < questions.length - 2; j++)
            questionsJSON[j - 1] = questions[j].children[0].value;

        let answer = card.children[card.children.length - 1].value;
        let q = {
            "question": question,
            "questionType": questionType,
            "questions": questionsJSON.join(","),
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

