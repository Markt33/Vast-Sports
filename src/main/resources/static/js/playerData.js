// Wait until the page loads
window.onload = async function () {
    // Fetch the data
    //const uri = `${window.location.origin}/api/v1/teams/all`;
    const uri = `${window.location.origin}/api/v1/players/all`;
    const config = {
        method: 'get'
    }

    const response = await fetch(uri, config);
    const data = await response.json();
    showPlayers(data);

    // Select the form button and handle form submission
    const button = document.querySelector("#submit");
    button.onclick = addPlayer;

    async function addPlayer(event) {
        // Stop the form from submitting
        event.preventDefault();


        const newPlayer = {
            season: document.querySelector("#season").value,
            playerName: document.querySelector("#playerName").value,
            playerPos: document.querySelector("#playerPos").value,
            age: document.querySelector("#age").value,
            experience: document.querySelector("#experience").value,
            league: document.querySelector("#league").value,
            team: document.querySelector("#team").value,
            assists: document.querySelector("#assists").value,
            steals: document.querySelector("#steals").value,
            blocks: document.querySelector("#blocks").value,
            tov: document.querySelector("#turnovers").value,
            points: document.querySelector("#points").value

        }

        const uri = `${window.location.origin}/api/v1/players/all`;
        const config = {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newPlayer)
        };

        const response = await fetch(uri, config);
        const player = await response.json();

        const tbody = document.querySelector("#playerBody");
        addPlayerRow(tbody, player);
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

function showPlayers(player) {
    const tbody = document.querySelector("#playerBody");

    player.forEach(player => {
        addPlayerRow(tbody, player);
    });
}

function addPlayerRow(tbody, player) {
    const row = tbody.insertRow();
    row.innerHTML = `
        <td>${player.id}</td>
        <td>${player.season}</td>
        <td>${player.name}</td>
        <td>${player.position}</td>
        <td>${player.age}</td>
        <td>${player.experience}</td>
        <td>${player.league}</td>
        <td>${player.team}</td>
        <td>${player.assist}</td>
        <td>${player.steals}</td>
        <td>${player.blocks}</td>
        <td>${player.tov}</td>
        <td>${player.points}</td>
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

    const uri = `${window.location.origin}/api/v1/players/${id}`;
    const config = {
        method: "delete"
    };
    await fetch(uri, config);

    row.remove();
}

async function editRecord(evt) {
    const editLink = evt.target;
    const row = editLink.closest("tr");
    const cells = row.children;

    // Extracting data from cells
    const season = cells[1].innerHTML;
    console.log(`Editing Team Name ${season}`);

    const playerName = cells[2].innerHTML;
    console.log(`Editing season ${playerName}`);

    const playerPos = cells[3].innerHTML;
    console.log(`Editing wins ${playerPos}`);

    const age = cells[4].innerHTML;
    console.log(`Editing wins ${age}`);

    const experience = cells[5].innerHTML;
    console.log(`Editing wins ${experience}`);

    const league = cells[6].innerHTML;
    console.log(`Editing wins ${league}`);

    const team = cells[7].innerHTML;
    console.log(`Editing wins ${team}`);

    const assists = cells[8].innerHTML;
    console.log(`Editing wins ${assists}`);

    const steals = cells[9].innerHTML;
    console.log(`Editing wins ${steals}`);

    const blocks = cells[10].innerHTML;
    console.log(`Editing wins ${blocks}`);

    const tov = cells[11].innerHTML;
    console.log(`Editing wins ${tov}`);

    const points = cells[12].innerHTML;
    console.log(`Editing wins ${points}`);


    // Replace text with input fields for editing
    cells[1].innerHTML = `<input type="text" id="season" value="${season}">`;
    cells[2].innerHTML = `<input type="text" id="playerName" value="${playerName}">`;
    cells[3].innerHTML = `<input type="text" id="playerPos" value="${playerPos}">`;
    cells[4].innerHTML = `<input type="text" id="age" value="${age}">`;
    cells[5].innerHTML = `<input type="text" id="experience" value="${experience}">`;
    cells[6].innerHTML = `<input type="text" id="league" value="${league}">`;
    cells[7].innerHTML = `<input type="text" id="team" value="${team}">`;
    cells[8].innerHTML = `<input type="text" id="assists" value="${assists}">`;
    cells[9].innerHTML = `<input type="text" id="steals" value="${steals}">`;
    cells[10].innerHTML = `<input type="text" id="blocks" value="${blocks}">`;
    cells[11].innerHTML = `<input type="text" id="tov" value="${tov}">`;
    cells[12].innerHTML = `<input type="text" id="points" value="${points}">`;

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
    const updatedPlayerName = cells[2].querySelector("input").value;
    const updatedPlayerPos = cells[3].querySelector("input").value;
    const updatedAge = cells[4].querySelector("input").value;
    const updatedExperience = cells[5].querySelector("input").value;
    const updatedLeague = cells[6].querySelector("input").value;
    const updatedTeam = cells[7].querySelector("input").value;
    const updatedAssists = cells[8].querySelector("input").value;
    const updatedSteals = cells[9].querySelector("input").value;
    const updatedBlocks = cells[10].querySelector("input").value;
    const updatedTov = cells[11].querySelector("input").value;
    const updatedPoints = cells[12].querySelector("input").value;



    // After updating the data, restore the original format
    cells[1].innerHTML = updatedSeason;
    cells[2].innerHTML = updatedPlayerName;
    cells[3].innerHTML = updatedPlayerPos;
    cells[4].innerHTML = updatedAge;
    cells[5].innerHTML = updatedExperience;
    cells[6].innerHTML = updatedLeague;
    cells[7].innerHTML = updatedTeam;
    cells[8].innerHTML = updatedAssists;
    cells[9].innerHTML = updatedSteals;
    cells[10].innerHTML = updatedBlocks;
    cells[11].innerHTML = updatedTov;
    cells[12].innerHTML = updatedPoints;



    // Restore the edit button
    const editButton = document.createElement("button");
    editButton.innerHTML = '<a href="#" class="edit">Edit</a>';
    editButton.onclick = editRecord;
    cells[13].innerHTML = "";
    cells[13].appendChild(editButton);

    const newPlayer = {
        id: id,
        season: updatedSeason,
        playerName: updatedPlayerName,
        playerPos: updatedPlayerPos,
        age: updatedAge,
        experience: updatedExperience,
        league: updatedLeague,
        team: updatedTeam,
        assists: updatedAssists,
        steals: updatedSteals,
        blocks: updatedBlocks,
        tov: updatedTov,
        points: updatedPoints

    }

    const uri = `${window.location.origin}/api/v1/players/${id}`;
    const config = {
        method: "put",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newPlayer)
    };

    const response = await fetch(uri, config);
    const player = await response.json();
}

