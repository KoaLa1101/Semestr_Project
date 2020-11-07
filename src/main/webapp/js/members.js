document.addEventListener('DOMContentLoaded', function (){
    let allUsers = document.getElementById('allMembers');

    async function getData(){
        let url = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/members";

        try {
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            });

            let result = await response.json();
            console.log(result);
            await showAllUsers(result);

        } catch (e) {
            console.log(e);
        }
    }

    function showAllUsers(result){
        let showAllUsersTmp = result;
        let html = '<ul class="list-group list-group-flush overflow-y">';

        for (let i=1; i<=result.length; i++){
            html += '<li class="d-flex ml-3 mr-3" data-toggle="modal" data-target="#membersModal"><p>'+ showAllUsersTmp[showAllUsersTmp.length-i][0] + ' ' + showAllUsersTmp[showAllUsersTmp.length-i][1] + ' (' + showAllUsersTmp[showAllUsersTmp.length-i][2] + ')</p></li>\n';
        }
        html += '</ul>';
        allUsers.innerHTML = html;

    }
    getData();
},false);