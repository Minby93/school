async function getMyCourses(){
    let show = await fetch('/getProfile');
    let user = await show.json();
    let id = user.id;
    let url = '/files/'+id+'/courses';
    console.log(url);
    let response = await fetch(url);
    let courses = await response.json();
    let list = document.querySelector('.myCourses');
    console.log(courses);
    for(key in courses){
        list.innerHTML += `
            <div class="col search-card w-100" data-title="${courses[key].name}">
                                       <div class="card row">
                                           <div class="card-body row">
                                               <h5 class="card-title col-10">${courses[key].name}</h5>
                                               <a class="btn btn-primary col" href="/files/${courses[key].id}">Скачать</a>
                                           </div>
                                       </div>
                                   </div>
        `;
    }
}
getMyCourses();