document.addEventListener('DOMContentLoaded', function (){
    let allPosts = document.getElementById("allPosts");

    async function getData(){
        let url = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/forum";

        try {
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            });

            let result = await response.json();
            console.log(result);
            await showAllPosts(result);

        } catch (e) {
            console.log(e);
        }
    }

    function showAllPosts(result){
        let showAllPostsTmp = result;

        let html = '<ul class="list-group list-group-flush overflow-y">';

        for (let i=1; i<=result.length; i++){
            let id = showAllPostsTmp[showAllPostsTmp.length-i][2];
            html += '<li class="d-flex ml-3 mr-3" data-toggle="modal" data-target="#postModal"><h4 class="ml-3 mr-3">' + showAllPostsTmp[showAllPostsTmp.length-i][0] + ' </h4>\n<a class="btn btn-outline-info" href=' + window.location.pathname + '/postId?id=' + id + '>'+ showAllPostsTmp[showAllPostsTmp.length-i][1] + '</a></li>\n<br>';
        }
        html += '</ul>';
        allPosts.innerHTML = html;
    }
    getData();
}, false);