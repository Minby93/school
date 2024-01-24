const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let showError = document.querySelector('.some_error');
if(urlParams.has('error')){
showError.innerHTML += `
<p class="text-center text-danger">Неправильный логин или пароль</p>
`;
}
else{
if(urlParams.has('logout')){
showError.innerHTML += `
<p class="text-center text-success">Вы успешно вышли из аккаунта</p>
`;
}
}