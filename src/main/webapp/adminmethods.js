function showPersonalInfo(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("personalInfo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "AdminServlet?action=showPersonalInfo", true);
    xhttp.send();
}

function insertDoctor(){
    let amka = document.getElementById("amka").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let surname = document.getElementById("surname").value;
    let e = document.getElementById("specialty");
    let specialty = e.options[e.selectedIndex].text;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let x = this.responseText;
            if (x.startsWith("Doctor inserted to the database!")) {
                document.getElementById("amka").value = "";
                document.getElementById("username").value = "";
                document.getElementById("password").value = "";
                document.getElementById("name").value = "";
                document.getElementById("surname").value = "";
            }
            alert(this.responseText);
        }
    };
    xhttp.open("GET", "AdminServlet?action=insertDoctor&amka=" + amka + "&username=" + username + "&password=" + password + "&name=" + name + "&surname=" + surname + "&specialty=" + specialty, true);
    xhttp.send();

}

function deleteDoctor(){
    let amka = document.getElementById("amka1").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let x = this.responseText;
            if (x.startsWith("Doctor deleted from the database!")) {
                document.getElementById("amka1").value = "";
            }
            alert(this.responseText);
        }
    };
    xhttp.open("GET", "AdminServlet?action=deleteDoctor&amka=" + amka, true);
    xhttp.send();
}

function insertPatient() {
    let amka = document.getElementById("p_amka").value;
    let username = document.getElementById("p_username").value;
    let password = document.getElementById("p_password").value;
    let name = document.getElementById("p_name").value;
    let surname = document.getElementById("p_surname").value;

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let x = this.responseText;
            if (x.startsWith("Patient inserted to the database!")) {
                document.getElementById("p_amka").value = "";
                document.getElementById("p_username").value = "";
                document.getElementById("p_password").value = "";
                document.getElementById("p_name").value = "";
                document.getElementById("p_surname").value = "";
            }
            alert(this.responseText);
        }
    };
    xhttp.open("GET", "AdminServlet?action=insertPatient&amka=" + amka + "&username=" + username + "&password=" + password + "&name=" + name + "&surname=" + surname, true);
    xhttp.send();
}