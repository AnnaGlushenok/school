const dropdowns = document.querySelectorAll(".dropdown");

for (const dropdown of dropdowns) {
    dropdown.addEventListener("mouseenter", (e) => {
        const dropdownList = e.currentTarget.querySelector(".dropdown__list");
        dropdownList.classList.remove("dropdown--hidden");
    })
    dropdown.addEventListener("mouseleave", (e) => {
        const dropdownList = e.currentTarget.querySelector(".dropdown__list");
        dropdownList.classList.add("dropdown--hidden");
    })
}

const subdropdowns = document.querySelectorAll(".dropdown__item:has(.dropdown__subitem)");

for (const subdropdown of subdropdowns) {
    subdropdown.addEventListener("mouseenter", (e) => {
        const dropdownList = e.currentTarget.querySelector(".dropdown__subitem");
        dropdownList.classList.remove("dropdown--hidden");
    })
    subdropdown.addEventListener("mouseleave", (e) => {
        const dropdownList = e.currentTarget.querySelector(".dropdown__subitem");
        dropdownList.classList.add("dropdown--hidden");
    })
}