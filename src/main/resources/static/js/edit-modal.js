let formEdit = document.forms["formEdit"];
editUser();

const URLEdit = "http://localhost:8080/api/admin/users/";

async function editModal(id) {
    const modalEdit = new bootstrap.Modal(document.querySelector('#editModal'));
    await open_fill_modal(formEdit, modalEdit, id);
    loadRolesForEdit();
}

function closeModal() {
    document.querySelectorAll(".btn-close").forEach((btn) => btn.click())
}
function editUser() {
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();

        //Приведение ролей из вида js к виду java
        let rolesForEdit = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) rolesForEdit.push({
                id: formEdit.roles.options[i].value,
                role: "ROLE_" + formEdit.roles.options[i].text
            });
        }

        fetch(URLEdit + formEdit.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                firstName: formEdit.firstName.value,
                password: formEdit.password.value,
                lastName: formEdit.lastName.value,
                age: formEdit.age.value,
                roles: rolesForEdit
            })
        }).then(response => {
            if (response.ok) {
                setTimeout(() => {
                    window.close();
                }, 500);
            } else {
                throw new Error('Ошибка обновления данных пользователя');
            }
            getAllUsers();
            closeModal();

        });
    });
}

function loadRolesForEdit() {
    let selectEdit = document.getElementById("edit-roles");
    selectEdit.innerHTML = "";

    fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.name.toString().replace('ROLE_', '');
                selectEdit.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}
window.addEventListener("load", loadRolesForEdit);

