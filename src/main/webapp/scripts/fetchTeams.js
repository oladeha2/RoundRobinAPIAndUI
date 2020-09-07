/* TODO: Put your js code here */

window.onload = function () {
    getTeams('');
    setonChange();
}

// set event listener for the teams filer input
function setonChange() {
    document.getElementById('teamQuery').onkeyup = function (el) {
        let val = document.getElementById('teamQuery').value;
        setTimeout(function () {
            getTeams(val);
        }, 1000);
    }
}

function getTeams(query){
    const requestUrl = '/full_stack_test_war_exploded/teams?name=' + query;
    const Http = new XMLHttpRequest();
    Http.open('GET', requestUrl);
    Http.setRequestHeader('Access-Control-Allow-Origin', 'null')
    Http.send();

    // appendChild use to add table to the div
    Http.onreadystatechange = function () {
        // if the request is a success and returns the expected JSON
        if(this.readyState == 4 && this.status == 200){
            const response = JSON.parse(this.responseText);
            console.log('length of response -> ' + response.length)
            // get the keys for the table headers
            let columns = ['Name', 'Year Founded', 'Stadium Capacity']
            let keys = ['name', 'yearFounded', 'stadiumCapacity']
            var resultsTable = document.createElement('table')
            // add the header to the row to the table
            let tr = resultsTable.insertRow(-1); // add in last place
            for(let i = 0; i < columns.length; i++){
                let header = document.createElement("th");
                header.innerHTML = columns[i];
                // add header to the newly create row
                tr.appendChild(header)
            }

            // add the data from response to the table --> add row then add column in nested loop
            for(let i = 0; i < response.length; i++){
                let row = resultsTable.insertRow(-1);
                if(i%2 == 1){
                    row.style.background = 'lightgray';
                }
                for(let j = 0; j < columns.length; j++){
                    // add cell
                    let cell = row.insertCell(-1);
                    cell.innerHTML = response[i][keys[j]];
                }
            }

            let queryResult = document.getElementById('teamQueryResult');
            // clear the HTML before appending
            queryResult.innerHTML = '';
            queryResult.appendChild(resultsTable);
        }
    }
}

