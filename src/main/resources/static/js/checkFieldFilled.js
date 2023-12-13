function сheckFieldFilled() {

    var name = document.forms["form"]["anketeName"].value;

    if (name == "") {
        alert("Заполните поле!");
        return false;
    }

    return true;
}