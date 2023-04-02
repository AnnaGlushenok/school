let blocks = document.getElementsByClassName("title");
for (let i = 0; i < blocks.length; i++) {
    blocks[i].addEventListener('click', function () {
        this.classList.toggle('title__active');
        let content = this.nextElementSibling;
        if (content.style.maxHeight)
            content.style.maxHeight = null;
        else
            content.style.maxHeight = content.scrollHeight + "px";
    });
}
