async function getMyCourses(){ // Динамическое отображение курсов в которых участвует пользователь в профиле
    let show = await fetch('/getProfile');
    let user = await show.json();
    let id = user.id;
    let url = '/files/'+id+'/courses';
    console.log(url);
    let response = await fetch(url);
    let courses = await response.json();
    let list = document.querySelector('.myCourses');
    console.log(courses);
    let name;
    for(key in courses){
        if(id == courses[key].user_id){
        name = courses[key].name.split('.')[0];
        list.innerHTML += `
            <div class="col search-card w-100" data-title="${courses[key].name}">
                                       <div class="card row bg-secondary">
                                           <div class="card-body row ">
                                               <h5 class="card-title col-7">${name}</h5>
                                               <p class="card-description col-3">Вы создатель этого курса</p>
                                               <a class="btn btn-primary col" href="/files/${courses[key].id}">Скачать</a>
                                           </div>
                                       </div>
                                   </div>
        `;
        }
        else{
        name = courses[key].name.split('.')[0];
                list.innerHTML += `
                    <div class="col search-card w-100" data-title="${courses[key].name}">
                                               <div class="card row">
                                                   <div class="card-body row ">
                                                       <h5 class="card-title col-10">${name}</h5>
                                                       <a class="btn btn-primary col" href="/files/${courses[key].id}">Скачать</a>
                                                   </div>
                                               </div>
                                           </div>
                `;

        }
    }
}
getMyCourses();