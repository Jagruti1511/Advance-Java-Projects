const API_URL = "/com.empmanagment/employeeapi";

// CREATE
function addEmployee() {

    const employee = {
        id: Number(document.getElementById("id").value),
        name: document.getElementById("name").value,
        salary: Number(document.getElementById("salary").value)
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

        clearForm();

        getEmployees();

    })
    .catch(error => {

        console.error(error);

        alert("Error while adding employee");

    });
}


// READ ALL
function getEmployees() {

    fetch(API_URL + "/employees")

    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to fetch employees");
        }

        return response.json();

    })
    .then(employees => {

        let output = "";

        employees.forEach(employee => {

            output += `
                <div class="employee">

                    <p><b>ID:</b> ${employee.id}</p>

                    <p><b>Name:</b> ${employee.name}</p>

                    <p><b>Salary:</b> ${employee.salary}</p>

                    <button onclick="editEmployee(
                        ${employee.id},
                        '${employee.name}',
                        ${employee.salary}
                    )">
                        Edit
                    </button>

                    <button onclick="deleteEmployee(${employee.id})">
                        Delete
                    </button>

                </div>
            `;
        });

        document.getElementById("employeeList").innerHTML = output;

    })
    .catch(error => {

        console.error(error);

        alert("Error while loading employees");

    });
}


// UPDATE
function updateEmployee() {

    const employee = {

        id: Number(document.getElementById("id").value),

        name: document.getElementById("name").value,

        salary: Number(document.getElementById("salary").value)
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

        clearForm();

        getEmployees();

    })
    .catch(error => {

        console.error(error);

        alert("Error while updating employee");

    });
}


// DELETE
function deleteEmployee(id) {

    if (!confirm("Are you sure you want to delete this employee?")) {
        return;
    }

    fetch(API_URL + "/deleteemployee/" + id, {

        method: "DELETE"

    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Failed to delete employee");
        }

        return response.text();

    })
    .then(data => {

        alert(data);

        getEmployees();

    })
    .catch(error => {

        console.error(error);

        alert("Error while deleting employee");

    });
}


// EDIT
function editEmployee(id, name, salary) {

    document.getElementById("id").value = id;

    document.getElementById("name").value = name;

    document.getElementById("salary").value = salary;
}


// CLEAR
function clearForm() {

    document.getElementById("id").value = "";

    document.getElementById("name").value = "";

    document.getElementById("salary").value = "";
}