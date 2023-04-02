const db = {
    modules: [
        {
            id: 1,
            objectId: 2,
            classId: 3,
            name: "Реакции в Химии",

            paragraphs: [
                {
                    id: 1,
                    title: "Реакции с хлором",
                    content: [
                        {
                            tag: "p",
                            classes: ["just-text"],
                            text: "Закон сохранения массы 1"
                        },
                        {
                            tag: "p",
                            classes: ["just-text"],
                            text: "Закон сохранения массы 2"
                        },
                        {
                            tag: "img",
                            classes: ["center-image"],
                            src: "путь/к/картинке",
                            alt: "текст вместо картинки"
                        },
                        {
                            tag: "p",
                            classes: ["just-text", "attention-block"],
                            text: "Блок для внимания"
                        },
                        {
                            tag: "p",
                            classes: ["just-text", "text-center", "formula"],
                            text: "H<sub>2</sub>O>"
                        },
                        {
                            tag: "p",
                            classes: ["just-text", "attention-block"],
                            text: "Блок для внимания"
                        },
                        {
                            tag: "presentation",
                            images: ["путь/к/картинке", "путь/к/картинке2", "путь/к/картинке3"],
                            alts: ["текст-вместо-картинки-1", "текст-вместо-картинки-2", "текст-вместо-картинки-3"]
                        },
                        {
                            tag: "videos",
                            images: ["путь/к/видео", "путь/к/видео2", "путь/к/видео3"],
                        }
                    ],
                    tasks: [
                        {question: "Сколько 'H' в слове H2O", correct: ["1"], incorrect: ["2", "3", "4"]},
                        {question: "Сколько унылых Денисов на земле", correct: ["1"], incorrect: ["3", "5", "8"]}
                    ],
                    tests: [
                        {
                            question: "Сколько А в АААААААААААА",
                            type: "choose",
                            correct: ["хз", "8"], //Это !!!: хз или 8
                            incorrect: ["2", "9", "7", "1"]
                        },
                        {question: "Ввеидете формулу воды", type: "input", correct: ["h20"]},
                        {
                            question: "Сколько Б в БББББББББББББ",
                            type: "checkSome",
                            correct: ["хз", "8"], //Это !!!: хз и 8
                            incorrect: ["2", "9", "7", "1"]
                        },
                    ]
                }
            ],
            verificationWork: [
                {
                    question: "Сколько А в АААААААААААА",
                    type: "choose",
                    correct: ["хз", "8"], //Это !!!: хз или 8
                    incorrect: ["2", "9", "7", "1"]
                },
                {question: "Ввеидете формулу воды", type: "input", correct: ["h20"]},
                {
                    question: "Сколько Б в БББББББББББББ",
                    type: "checkSome",
                    correct: ["хз", "8"], //Это !!!: хз и 8
                    incorrect: ["2", "9", "7", "1"]
                },
            ],
            tests: [
                {
                    question: "Сколько А в АААААААААААА",
                    type: "choose",
                    correct: ["хз", "8"], //Это !!!: хз или 8
                    incorrect: ["2", "9", "7", "1"]
                },
                {question: "Ввеидете формулу воды", type: "input", correct: ["h20"]},
                {
                    question: "Сколько Б в БББББББББББББ",
                    type: "checkSome",
                    correct: ["хз", "8"], //Это !!!: хз и 8
                    incorrect: ["2", "9", "7", "1"]
                },
            ]
        }
    ]
}