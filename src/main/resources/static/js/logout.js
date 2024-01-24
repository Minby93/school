async function checkAuthStatus() {
    let show = await fetch('/getProfile');
    let response = await show.text();
    if (response != "null") {
        showLogoutButton();
        hideLoginButton();
    }
    else {
        hideLogoutButton();
        showLoginButton();
    }

}

// Функция для показа кнопки выхода
function showLogoutButton() {
   document.getElementById('logoutButton').style.display = 'block';

}

// Функция для скрытия кнопки выхода
function hideLogoutButton() {
   document.getElementById('logoutButton').style.display = 'none';
}

function showLoginButton(){
    document.getElementById('loginButton').style.display = 'block';
}

function hideLoginButton(){
    document.getElementById('loginButton').style.display = 'none';
}
checkAuthStatus();