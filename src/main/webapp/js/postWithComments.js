document.addEventListener('DOMContentLoaded', function () {
    let chosenPost = document.getElementById('chosenPost');
    let allCommentsByPost = document.getElementById('showAllComments');
    let id = get("id");

    async function getData() {
        let url = window.location.pathname

        try {
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            });

            let result = await response.text();
            await showChosenPostByUser(JSON.parse(result.split("&&")[0]));
            await showAllCommentsByPost(JSON.parse(result.split("&&")[1]));
            console.log(result);

        } catch (e) {
            console.log(e);
        }
    }

    function showChosenPostByUser(result) {
        let chosenPostsTmp = result;

        let html = '<ul class="list-group list-group-flush overflow-y">\n<h4 class="ml-3 mr-3">' + chosenPostsTmp[id-1][0] + ' </h4>\n';

        html += '<li class="d-flex ml-3 mr-3" data-toggle="modal" data-target="#postModal"><p>(' + chosenPostsTmp[id-1][1] + ')</p></li>\n';

        html += '</ul>';
        chosenPost.innerHTML = html;

    }

    function showAllCommentsByPost(result){
        let allCommentsByPostTmp = result;

        let html = '<ul class="list-group list-group-flush overflow-y">';

        for (let i=1; i<=result.length; i++){
            html += '<li class="d-flex ml-3 mr-3" data-toggle="modal" data-target="#commentModal"><h5 class="ml-3 mr-3">' + allCommentsByPostTmp[allCommentsByPostTmp.length-i][0] + ' </h5>\n<p>'+ allCommentsByPostTmp[allCommentsByPostTmp.length-i][1] + '</p></li>\n<br>';
        }
        html += '</ul>';
        allCommentsByPost.innerHTML = html;
    }

    function get(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }


    getData();

}, false)