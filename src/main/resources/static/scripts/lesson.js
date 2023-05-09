let id = window.location.pathname.split("/")[2];
fetch("/getLesson/" + id)
    .then(response => response.json())
    .then(data => {
        let title = document.createElement("p");
        title.setAttribute("class", "text__title");
        title.textContent = data.title;
        document.getElementsByClassName("text")[0].appendChild(title);
        parse(data.text);
    })
    .catch(error => console.error(error));

function parse(text) {
    console.log(text);
    let json = JSON.parse(text);
    Object.keys(json).forEach((key) => {
        let p = document.createElement("p",);
        p.setAttribute("class", Classes[key.split("_")[0]]);
        p.innerHTML = json[key];
        document.getElementsByClassName("text")[0].appendChild(p);
    });
}

const Classes = {
    content: 'text__text',
    fact: 'text__fact',
    formula: 'text__formula'
}