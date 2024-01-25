function addCourse(id){

    let url = '/course/add/' + id;
    console.log(url);
    fetch(url, {
                method: 'PUT',


            });
}