await fetch("/lesson", {
    method: "GET",
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    },
}).then(response => {
    console.log(response.text());
})