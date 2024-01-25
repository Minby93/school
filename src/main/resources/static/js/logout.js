// Динамическое отображение кнопки входа\выхода
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
// Функция для показа кнопки входа
function showLoginButton(){
    document.getElementById('loginButton').style.display = 'block';
}
// Функция для скрытия кнопки входа
function hideLoginButton(){
    document.getElementById('loginButton').style.display = 'none';
}
checkAuthStatus();