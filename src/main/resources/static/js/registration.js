function validationForm() {
    var username = document.forms["registration-form"]["username"].value;
    var password = document.forms["registration-form"]["password"].value;

    if (username == "") {
        alert("Введите имя пользователя!");
        return false;
    }
    if (password == "") {
        alert("Введите фамилию пользователя!");
        return false;
    }

    return true;
}