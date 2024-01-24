const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let showError = document.querySelector('.some_error');
if(urlParams.has('error')){
showError.innerHTML += `
<p>Неправильный логин или пароль</p>
`;
}
else{
if(urlParams.has('logout')){
showError.innerHTML += `
<p>Вы успешно вышли из аккаунта</p>
`;
}
}