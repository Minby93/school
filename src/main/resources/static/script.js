// Функция обработки и отправки POST запроса в бэк
function submitForm() {
    // Считываемые поля
    var firstName = document.getElementById("firstName").value;
    var secondName = document.getElementById("secondName").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var email = document.getElementById("email").value;
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;

    // Отправка данных в бэк
    fetch('/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        // Формирование json для отправки
        body: JSON.stringify({firstName: firstName, secondName: secondName, phoneNumber: phoneNumber,
                                email: email, userName: userName, password: password})
    })
    .then(response => response.json())
    .then(data => {
        // Получение ответа от бэка
        console.log(data);
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
//  Функция для получения всех записей из таблицы user (для отладки), в дальнейшем скорее всего уберу
// Сделал её асинхронной для того, чтобы методы fetch() и .json() успели закончить свой жизненный цикл
async function getListOfUsers(){
    let response = await fetch("/listOfUsers");
    let users = await response.json();
    let list = document.querySelector('.listOfUsers');
    // Формирую новую строку для выводы записи в таблицу
    for(key in users){
        list.innerHTML += `
            <tr>
                <th>${users[key].firstName}</th>
                <th>${users[key].secondName}</th>
                <th>${users[key].phoneNumber}</th>
                <th>${users[key].email}</th>
                <th>${users[key].userName}</th>
                <th>${users[key].password}</th>
             </tr>
        `;
    }

}
// Выполняю функция для отображения всех данных таблицы user (работает только при обновлении страницы, хз как исправить)
getListOfUsers();
