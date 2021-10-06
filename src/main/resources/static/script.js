getMethod();
let findCityValue = () => document.getElementById("findCityValue").value;
let addCityValue = () => document.getElementById("addCityValue").value;
let addMessageValue = () => document.getElementById("addMessageValue").value;


const table_header = `
        <tr>
            <th>City</th>
            <th>Message</th>
            <th>Change</th>
            <th>Delete</th>
       </tr>`

function getMethod() {
    $.ajax({
        url: document.location.origin + '/city/all',
        method: 'get',
        success: function (data) {
            let html = table_header;
            data.forEach(e => {
                html += parseTableRow(e)
            })
            document.getElementById("city").innerHTML = html;
            updateEvents();
        },
        error: function (error) {
            alert(error.responseText)
        }
    });
}

function getMethodOne(data) {
    $.ajax({
        url: document.location.origin + '/city/?name=' + data,
        method: 'get',
        success: function (data) {
            let html = table_header + parseTableRow(data);
            document.getElementById("city").innerHTML = html;
            clear();
            updateEvents();
        },
        error: function (error) {
            alert(error.responseText);
        }
    });
}

function deleteOne(data) {
    $.ajax({
        url: document.location.origin + '/city/' + data,
        method: 'delete',
        success: function (data) {
            getMethod();
        }
    });
}


function changeOne(data) {
    const decode = decodeURIComponent(data);
    const {id, name, message} = JSON.parse(decode);
    const newMessage = prompt(`Input message for ${name}`, message);
    let city = {
        id: id,
        name: name,
        message: newMessage
    }
    if (newMessage) {
        $.ajax({
            url: document.location.origin + '/city/',
            method: 'put',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(city),
            success: function (data) {
                getMethod();
                alert("Success updated!")
            },
            error: function (error) {
                alert(error.responseText);
            }
        });
    }
}

function postOne() {
    if (addMessageValue() !== '' && addCityValue() !== '') {
        let data = {
            name: addCityValue(),
            message: addMessageValue()
        }
        $.ajax({
            url: document.location.origin + '/city/',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'post',
            data: JSON.stringify(data),
            success: function (data) {
                getMethod();
                clear();
            },
            error: function (error) {
                alert(error.responseText);
            }
        });
    } else
        alert("Input correct value")
}

function parseTableRow(data) {
    const dataStr = JSON.stringify(data);
    return `
        <tr>
            <td>${data.name}</td>
            <td>${data.message}</td>
            <td> <button class="btn_update" data-info=${encodeURIComponent(dataStr)}> Change </button> </td>
            <td> <button onclick="deleteOne(${data.id})"> Delete </button> </td>
        </tr>
    `;
}


function clear() {
    document.getElementById("findCityValue").value = '';
    document.getElementById("addCityValue").value = '';
    document.getElementById("addMessageValue").value = '';
}

function updateEvents() {
    document.querySelectorAll(".btn_update").forEach(btn => {
        btn.addEventListener("click", () => {
            changeOne(btn.dataset.info);
        })
    })
}