const API_URL = "http://localhost:8080/demo/employeeapi";


function addEmployee() {

    const id = document.getElementById("id").value;
    const name = document.getElementById("name").value;
    const salary = document.getElementById("salary").value;

    const employee = {
        id: Number(id),
        name: name,
        salary: Number(salary)
    };

    fetch(API_URL + "/addemployee", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(employee)

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to add employee");
        }

        return response.json();
    })

    .then(data => {

        alert("Employee added successfully!");

        document.getElementById("id").value = "";
        document.getElementById("name").value = "";
        document.getElementById("salary").value = "";

        getEmployees();

    })

    .catch(error => {

        console.error(error);
        alert("Error while adding employee");

    });
}


function getEmployees() {
    fetch(API_URL + "/employees")
        .then(response => response.json())
        .then(data => {

            document.getElementById("totalEmployees").innerText = data.length;

            const table = document.getElementById("employeeTable");

            table.innerHTML = "";

            data.forEach(employee => {
                const row = `
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.salary}</td>
                    </tr>
                `;

                table.innerHTML += row;
            });
        })
        .catch(error => {
            console.error(error);
            alert("Error while getting employees");
        });
}


function getEmployeeById() {

    const id = document.getElementById("searchId").value;

    fetch(API_URL + "/employee/" + id)

    .then(response => {

        if (!response.ok) {
            throw new Error("Employee not found");
        }

        return response.json();

    })

    .then(employee => {

        document.getElementById("employeeDetails").innerHTML = `
            <p><b>ID:</b> ${employee.id}</p>
            <p><b>Name:</b> ${employee.name}</p>
            <p><b>Salary:</b> ${employee.salary}</p>
        `;

    })

    .catch(error => {

        console.error(error);
        alert("Employee not found");

    });
}


function updateEmployee() {

    const id = document.getElementById("updateId").value;
    const name = document.getElementById("updateName").value;
    const salary = document.getElementById("updateSalary").value;

    const employee = {

        id: Number(id),
        name: name,
        salary: Number(salary)

    };

    fetch(API_URL + "/updateemployee", {

        method: "PUT",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(employee)

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to update employee");
        }

        return response.json();

    })

    .then(data => {

        alert("Employee updated successfully!");

        document.getElementById("updateId").value = "";
        document.getElementById("updateName").value = "";
        document.getElementById("updateSalary").value = "";

        getEmployees();

    })

    .catch(error => {

        console.error(error);
        alert("Error while updating employee");

    });
}


function deleteEmployee() {

    const id = document.getElementById("deleteId").value;

    fetch(API_URL + "/deleteemployee/" + id, {

        method: "DELETE"

    })

    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to delete employee");
        }

        return response.text();

    })

    .then(message => {

        alert(message);

        document.getElementById("deleteId").value = "";

        getEmployees();

    })

    .catch(error => {

        console.error(error);
        alert("Error while deleting employee");

    });
}
 getEmployees();
