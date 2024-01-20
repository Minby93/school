// Функция обработки и отправки POST запроса в бэк
function registrationForm() {
    // Считываемые поля
    var firstName = document.getElementById("firstName").value;
    var secondName = document.getElementById("secondName").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var email = document.getElementById("email").value;
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var role;
    if (document.getElementById('role_student').checked) {
        role = document.getElementById('role_student').value;
    }
    else{
        role = document.getElementById('role_teacher').value;
    }

    // Отправка данных в бэк
    fetch('/add', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json'
        },
        // Формирование json для отправки
        body: JSON.stringify({firstName: firstName, secondName: secondName, phoneNumber: phoneNumber,
                                email: email, username: username, password: password, role: role})
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