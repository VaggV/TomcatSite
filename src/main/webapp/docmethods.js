// SHOW PERSONAL INFO ON LOAD
function showPersonalInfo1(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("personalInfo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "DoctorServlet?action=showPersonalInfo", true);
    xhttp.send();
}

// SHOW PERSONAL INFO ON LOAD END


// INSERT AVAILABILITY BUTTON

function openPage() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("mainSection").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "DoctorServlet?action=openEnterAppointment", true);
    xhttp.send();
}

function insertAvailability() {
    let datee = document.getElementById("meeting-date").value;
    let e = document.getElementById("hours");
    let hours = e.options[e.selectedIndex].text;
    let e1 = document.getElementById("minutes");
    let minutes = e1.options[e1.selectedIndex].text;
    let timee = hours + ":" + minutes;
    console.log(timee);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let x = this.responseText;
            if (x.startsWith("alreadyEntered")) {
                alert("This time slot is already entered to the system");
            } else if (x.startsWith("dberror")) {
                alert("There was an error connecting to the database.")
            } else {
                alert("Availability updated")
            }
        }
    };
    xhttp.open("GET", "DoctorServlet?action=insertAvail&date=" + datee + "&time=" + timee, true);
    xhttp.send();
}

// INSERT AVAILABILITY BUTTON END





// MANAGE DATES BUTTON

function showDates(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("mainSection").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "DoctorServlet?action=showDates", true);
    xhttp.send();
}

function cancelAppointment() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            if (this.responseText.startsWith("true")){
                document.getElementById("manageBtn").click();
                alert("Appointment cancelled!");
            } else {
                alert("Appointment could not be cancelled because it's in less than 3 days.")
            }
        }
    };

    let e = document.getElementById("booked1");
    let appoint = e.options[e.selectedIndex].text;

    xhttp.open("GET", "DoctorServlet?action=cancelByDoc&appid=" + appoint, true);
    xhttp.send();
}

// MANAGE DATES BUTTON END
