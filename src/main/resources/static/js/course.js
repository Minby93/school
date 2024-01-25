 // Загрузка нового курса
 function uploadFile(){
    const formData = new FormData();
    let file = document.getElementById('courseFile');
    formData.append('file', file.files[0]);
     fetch('/files/upload', {
                method: 'POST',
                body: formData
            });
     window.location.replace("/profile");
};

