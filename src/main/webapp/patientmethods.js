function callServlet(action) {
    let xhttp = new XMLHttpRequest();
    if (action === "showPersonalInfo") {
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("personalInfo").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "patientServlet?action=showPersonalInfo", true);
        xhttp.send();
    } else if (action === "searchAppointments") {
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("mainSection").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "patientServlet?action=searchAppointments", true);
        xhttp.send();
    } else if (action === "bookOrCancel") {
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                console.log(this.responseText);
                document.getElementById("mainSection").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "patientServlet?action=bookOrCancel", true);
        xhttp.send();
    } else if (action === "showHistory") {
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("mainSection").innerHTML = this.responseText;
                document.getElementById("mainSection").style.fontSize = "15";
            }
        };
        xhttp.open("GET", "patientServlet?action=showHistory", true);
        xhttp.send();
    } else if (action === "bookButton") {
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("searchAppointment").click();
                alert("Appointment booked!");
            }
        };
        let e = document.getElementById("appointmentlist");
        let specialty = e.options[e.selectedIndex].text;
        xhttp.open("GET", "patientServlet?action=bookButton&docSpecialty=" + specialty, true);
        xhttp.send();
    } else if (action === "cancelOrShow"){
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                document.getElementById("mainSection").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "patientServlet?action=cancelOrShow", true);
        xhttp.send();
    } else if (action === "cancelApp"){
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                console.log(this.responseText);
                if (this.responseText.startsWith("true")){
                    document.getElementById("searchBtn").click();
                    alert("Appointment cancelled!");
                } else {
                    alert("Appointment could not be cancelled because it's in less than 3 days.")
                }
            }
        };
        let e = document.getElementById("booked1");
        let appoint = e.options[e.selectedIndex].text;
        xhttp.open("GET", "patientServlet?action=cancelApp&appointmentId=" + appoint, true);
        xhttp.send();
    }
}

function callServlet1() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let x = this.responseText.split("(SPLIT)");
            document.getElementById("availableDates2").innerHTML = x[0];
            document.getElementById("appointmentlist").innerHTML = x[1];
        }
    };
    let e = document.getElementById("specialties");
    let specialty = e.options[e.selectedIndex].text;
    document.getElementById("bookDiv").style.display = "inline";
    xhttp.open("GET", "patientServlet?action=bookApp&specialty=" + specialty, true);
    xhttp.send();
}