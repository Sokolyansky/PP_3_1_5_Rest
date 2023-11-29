const URLNavbarAdmin = 'http://localhost:8080/api/admin/show/';
const navbarBrandAdmin = document.getElementById('navbarBrandAdmin');//Элемент, где будет роль и почта текущего юзера
const tableUserAdmin = document.getElementById('tableAdmin');

function getCurrentAdmin() {
    fetch(URLNavbarAdmin)
        .then((res) => res.json())
        .then((user) => {

            let rolesStringAdmin = rolesToStringForAdmin(user.roles);
            let data = '';

            data += `<tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.password}</td>
            <td>${rolesStringAdmin}</td>
            </tr>`;
            tableUserAdmin.innerHTML = data;

            navbarBrandAdmin.innerHTML = `<b><span>${user.firstName}</span></b>
                             <span>with roles:</span>
                             <span>${rolesStringAdmin}</span>`;
        });
}

getCurrentAdmin()

function rolesToStringForAdmin(roles) {
    let rolesString = '';

    for (const element of roles) {
        rolesString += (element.name.toString().replace('ROLE_', '') + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}

