async function getCourses(){
     let response = await fetch("/files/list");
     let courses = await response.json();

     let show = await fetch('/getProfile');
     let user = await show.text();
     let name;
     let list = document.querySelector('.courseCards');
     if(user != "null"){
        for(course in courses){
           name = courses[course].name.split('.')[0]
           console.log(courses[course].id);
           list.innerHTML += `
               <div class="col search-card w-100" data-title="${name}">
                           <div class="card row">
                               <div class="card-body row">
                                   <h5 class="card-title col-10">${name}</h5>
                                   <a class="btn btn-primary col" href="/files/${courses[course].id}" onclick="addCourse(${courses[course].id});">Присоединиться</a>
                               </div>
                           </div>
                       </div>
           `;
        }
     }
     else{
        for(course in courses){
                   name = courses[course].name.split('.')[0]
                   console.log(courses[course].id);
                   list.innerHTML += `
                       <div class="col search-card w-100" data-title="${name}">
                                   <div class="card row">
                                       <div class="card-body row">
                                           <h5 class="card-title col-10">${name}</h5>
                                           <a class="btn btn-primary col" href="/login">Присоединиться</a>
                                       </div>
                                   </div>
                               </div>
                   `;
                }
     }
}
document.getElementById('searchInput').addEventListener('input', function() {
        let searchValue = this.value.toLowerCase();
        let cards = document.querySelectorAll('.search-card');
        cards.forEach(function(card) {
            let title = card.getAttribute('data-title').toLowerCase();
            if (title.includes(searchValue)) {
                card.style.display = 'block';
            } else {
                card.style.display = 'none';
            }
        });
    });
getCourses();