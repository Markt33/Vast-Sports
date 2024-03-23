// Wait until the page loads
window.onload = async function () {
    // Fetch the data
    //const uri = `${window.location.origin}/api/v1/teams/all`;
    const uri = `${window.location.origin}/api/v1/teams/all`;
    const config = {
        method: 'get'
    }

    const response = await fetch(uri, config);
    const data = await response.json();
    showTeams(data);

    // Select the form button and handle form submission
    const button = document.querySelector("#submit");
    button.onclick = addTeam;

    async function addTeam(event) {
        // Stop the form from submitting
        event.preventDefault();

        const newTeam = {
            season: document.querySelector("#season").value,
            teamName: document.querySelector("#teamName").value,
            teamABV: document.querySelector("#teamABV").value,
            wins: document.querySelector("#wins").value,
            losses: document.querySelector("#losses").value,
            avgAge: document.querySelector("#age").value,
            arenaName: document.querySelector("#arena").value
        }

        const uri = `${window.location.origin}/api/v1/teams/all`;
        const config = {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newTeam)
        };

        const response = await fetch(uri, config);
        const team = await response.json();

        const tbody = document.querySelector("#teamBody");
        addTeamRow(tbody, team);
    }

    //select all edit links
    const editLinks = document.querySelectorAll('.edit');
    for (const link of editLinks){
        link.onclick = editRecord;
    }

    const deleteLinks = document.querySelectorAll('.delete');
    for (const link of deleteLinks){
        link.onclick = deleteRecord;
    }
}

function showTeams(teams) {
    const tbody = document.querySelector("#teamBody");

    teams.forEach(team => {
        addTeamRow(tbody, team);
    });
}

function addTeamRow(tbody, team) {
    const row = tbody.insertRow();
    row.innerHTML = `
        <td>${team.id}</td>
        <td>${team.season}</td>
        <td>${team.teamName}</td>
        <td>${team.teamABV}</td>
        <td>${team.wins}</td>
        <td>${team.losses}</td>
        <td>${team.avgAge}</td>
        <td>${team.arenaName}</td>
        <td><button><a href="#" class="edit">Edit</a></button></td>
        <td><button><a href="#" class="delete">Delete</a></button></td>
    `;

    const editButton = row.querySelector('.edit');
    editButton.onclick = editRecord;

    const deleteButton = row.querySelector('.delete');
    deleteButton.onclick = deleteRecord;
}

async function deleteRecord(evt) {
    const deleteLink = evt.target;
    const row = deleteLink.closest("tr");
    const cells = row.children;

    const id = cells[0].innerHTML;

    const uri = `${window.location.origin}/api/v1/teams/${id}`;
    const config = {
        method: "delete"
    };
    await fetch(uri, config);

    row.remove();
}

async function editRecord(evt){
    const editLink = evt.target;
    const row = editLink.closest("tr");
    const cells = row.children;

    // Extracting data from cells
    const season = cells[1].innerHTML;
    console.log(`Editing Team Name ${season}`);

    const teamName = cells[2].innerHTML;
    console.log(`Editing season ${teamName}`);

    const teamAbv = cells[3].innerHTML;
    console.log(`Editing wins ${teamAbv}`);

    const wins = cells[4].innerHTML;
    console.log(`Editing wins ${wins}`);

    const losses = cells[5].innerHTML;
    console.log(`Editing wins ${losses}`);

    const avgAge = cells[6].innerHTML;
    console.log(`Editing wins ${avgAge}`);

    const arenaName = cells[7].innerHTML;
    console.log(`Editing wins ${arenaName}`);


    // Replace text with input fields for editing
    cells[1].innerHTML = `<input type="text" id="season" value="${season}">`;
    cells[2].innerHTML = `<input type="text" id="teamName" value="${teamName}">`;
    cells[3].innerHTML = `<input type="text" id="teamAbv" value="${teamAbv}">`;
    cells[4].innerHTML = `<input type="text" id="wins" value="${wins}">`;
    cells[5].innerHTML = `<input type="text" id="losses" value="${losses}">`;
    cells[6].innerHTML = `<input type="text" id="avgAge" value="${avgAge}">`;
    cells[7].innerHTML = `<input type="text" id="arenaName" value="${arenaName}">`;

    // Adjusting edit link to save the changes
    editLink.textContent = "Save";
    editLink.onclick = saveRecord;


}

async function saveRecord(evt) {
    const saveLink = evt.target;
    const row = saveLink.closest("tr");
    const cells = row.children;

    const id = cells[0].innerHTML;

    // Extracting edited values
    const updatedSeason = cells[1].querySelector("input").value;
    const updatedTeamName = cells[2].querySelector("input").value;
    const updatedTeamAbv = cells[3].querySelector("input").value;
    const updatedWins = cells[4].querySelector("input").value;
    const updatedLosses = cells[5].querySelector("input").value;
    const updatedAvgAge = cells[6].querySelector("input").value;
    const updatedArenaName = cells[7].querySelector("input").value;

    // After updating the data, restore the original format
    cells[1].innerHTML = updatedTeamName;
    cells[2].innerHTML = updatedSeason;
    cells[3].innerHTML = updatedTeamAbv;
    cells[4].innerHTML = updatedWins;
    cells[5].innerHTML = updatedLosses;
    cells[6].innerHTML = updatedAvgAge;
    cells[7].innerHTML = updatedArenaName;

    // Restore the edit button
    const editButton = document.createElement("button");
    editButton.innerHTML = '<a href="#" class="edit">Edit</a>';
    editButton.onclick = editRecord;
    cells[8].innerHTML = "";
    cells[8].appendChild(editButton);

    const newTeam = {
        id: id,
        season: updatedSeason,
        teamName: updatedTeamName,
        teamABV: updatedTeamAbv,
        wins: updatedWins,
        losses: updatedLosses,
        avgAge: updatedAvgAge,
        arenaName: updatedArenaName
    }

    const uri = `${window.location.origin}/api/v1/teams/${id}`;
    const config = {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newTeam)
    };

    const response = await fetch(uri, config);
    const team = await response.json();

}