    document.getElementById('editButton').addEventListener('click', async function() {
    let response = await fetch("/getProfile");
    let user = await response.json();
    document.getElementById('profile').style.display = 'none';
    document.getElementById('editButton').style.display = 'none';
    document.getElementById('saveButton').style.display = 'block';
    document.getElementById('editForm').style.display = 'block';
    document.getElementById("firstName").setAttribute("value", user.firstName);
    document.getElementById("lastName").setAttribute("value", user.secondName);
    document.getElementById("phoneNumber").setAttribute("value", user.phoneNumber);
    document.getElementById("email").setAttribute("value", user.email);
    if(user.role == "TEACHER"){
        document.getElementById("teacherRole").checked = true;
    }
    else document.getElementById("studentRole").checked = true;
});

document.getElementById('saveButton').addEventListener('click', function() {

    document.getElementById('profile').style.display = 'block';
    document.getElementById('editButton').style.display = 'block';
    document.getElementById('saveButton').style.display = 'none';
    document.getElementById('editForm').style.display = 'none';
    updateUser();

});

async function updateUser(){
    let response = await fetch("/getProfile");
    let user = await response.json();
    let id = user.id;
    var firstName = document.getElementById("firstName").value;
    var secondName = document.getElementById("lastName").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    var email = document.getElementById("email").value;
    var role;
    if (document.getElementById('studentRole').checked) {
        role = document.getElementById('studentRole').value;
    }
    else{
        role = document.getElementById('teacherRole').value;
    }
    fetch('/update', {
            method: 'PUT',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            // Формирование json для отправки
            body: JSON.stringify({id: id, firstName: firstName, secondName: secondName, phoneNumber: phoneNumber,
                                    email: email, role: role})
        });
    window.location.replace("/profile");
}

async function getProfile(){
    let response = await fetch("/getProfile");
    let user = await response.json();
    let role ="";
    if(user.role == "ADMIN")
    {
        role = "Админ";
    }
    else if(user.role == "TEACHER"){
        role = "Учитель";
    }
    else role = "Ученик";
    let list = document.querySelector('.myProfile');
        list.innerHTML += `

        <div class="mb-3">
            <p >Имя: ${user.firstName}</p>
        </div>
        <div class="mb-3">
            <p>Фамилия: ${user.secondName}</p>
        </div>
        <div class="mb-3">
            <p >Номер телефона: ${user.phoneNumber}</p>
        </div>
        <div class="mb-3">
            <p>Email: ${user.email}</p>
        </div>
        <div class="mb-3">
            <p>Роль: ${role}</p>
        </div>
        `;
    }
getProfile();