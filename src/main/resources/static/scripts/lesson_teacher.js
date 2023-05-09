function addTextBox() {
    let span = document.createElement("span");
    span.setAttribute("role", "textbox");
    span.setAttribute("class", "block__content");
    span.setAttribute("contenteditable", "true");
    return span;
}

function addButton() {
    let button = document.createElement("button");
    button.setAttribute("class", "block__delete_button");
    button.setAttribute("onclick", "deleteBlock()");

    let img = document.createElement("img");
    img.setAttribute("class", "block__img");
    img.setAttribute("src", "/images/cross_teacher.svg");
    img.setAttribute("alt", "button");
    button.appendChild(img);

    return button;
}

function addDivBlock(idName) {
    let id = findLastId(".page");
    let div = document.createElement("div")
    div.setAttribute("class", "page__block block");
    div.setAttribute("id", idName + (id + 1));

    return div;
}

function addContent() {
    let div = addDivBlock("content_");
    let span = addTextBox();
    let button = addButton();

    div.appendChild(span);
    div.appendChild(button);
    document.getElementsByClassName("page")[0].appendChild(div);
}

function addFormula() {
    let div = addDivBlock("formula_");
    let span = addTextBox();
    let button = addButton();

    div.appendChild(span);
    div.appendChild(button);
    document.getElementsByClassName("page")[0].appendChild(div);
}

function addImage() {
    let div = addDivBlock("image_");
    let div1 = document.createElement("div");
    div1.setAttribute("class", "block__content");

    let button = addButton();

    div.appendChild(div1);
    div.appendChild(button);
    document.getElementsByClassName("page")[0].appendChild(div);
}

function addFact() {
    let div = addDivBlock("fact_");
    let span = addTextBox();
    let button = addButton();

    div.appendChild(span);
    div.appendChild(button);
    document.getElementsByClassName("page")[0].appendChild(div);
}

async function save() {
    let children = document.querySelector(".page").children;
    let data = [];
    let ids = [];
    for (let i = 0; i < children.length; i++) {
        ids[i] = children[i].id;
        data[i] = document.getElementById(ids[i]).textContent;
    }
    let json = {
        "title": document.getElementsByClassName("title__text")[0].value,
        "ids": ids,
        "content": data
    }
    console.log(JSON.stringify(json));
    await fetch('/lessonTeacher', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(json),
    }).then(response => {
        if (response.ok) {
            alert("Урок добавлен");
            // window.location.href = "/";
        } else {
            return response.text();
        }
    }).then(errorMessage => {
        console.error(errorMessage);
    }).catch(error => {
        console.error(error);
    });
}

function findLastId(className) {
    let el = document.querySelector(className);
    if (!el)
        return 0;
    let children = el.childNodes;
    let max = -1;
    for (let i = 0; i < children.length; i++) {
        if (max < children[i].id.split("_")[1])
            max = children[i].id.split("_")[1]
    }
    return +max;
}

img1();

function img1() {
    const img = document.getElementsByClassName('block__content')[0];

    img.addEventListener('dragover', event => {
        event.preventDefault();
        img.dataset.over = true;
        event.dataTransfer.dropEffect = 'copy';
    });

    img.addEventListener('dragleave', event => {
        event.preventDefault();
        img.dataset.over = false;
    })

    img.addEventListener('drop', event => {
        event.preventDefault();
        img.dataset.over = false;
        const text = event.dataTransfer.getData("text");
        if (text) {
            let img = document.createElement('img');
            img.src = text;
            document.body.appendChild(img);
        } else {
            const files = event.dataTransfer.files;
            [].map.call(files, file => {
                if (file.type.match(/^image/)) {
                    let reader = new FileReader();
                    reader.onload = file => {
                        let img = document.createElement('img');
                        img.src = file.target.result;
                        document.body.appendChild(img);
                    }
                    reader.readAsDataURL(file);
                }
            });
        }
    });
}

function deleteBlock() {
    document.getElementById(event.target.parentNode.parentNode.id).remove();
}
