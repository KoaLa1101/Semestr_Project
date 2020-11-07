document.addEventListener('DOMContentLoaded', function () {
    let lastPost = document.getElementById('lastPost');
    async function getData(){
        let url = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/')) + "/showPost";

        try {
            let response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                }
            });

            let result = await response.json();
            console.log(result[0][1]);
            await showLastPostByUser(result);

        } catch (e) {
            console.log(e);
        }
    }

    function showLastPostByUser(result){
        let lastPostsByUserTmp = result;

        let html = '<ul class="list-group list-group-flush overflow-y">\n<h4 class="ml-3 mr-3">' + lastPostsByUserTmp[lastPostsByUserTmp.length-1][1] + ' </h4>\n';


            html += '<li class="d-flex ml-3 mr-3" data-toggle="modal" data-target="#postModal"><p>(' + lastPostsByUserTmp[lastPostsByUserTmp.length-1][5] + ')</p></li>\n';

        html += '</ul>';
        lastPost.innerHTML = html;

    }
    getData();

}, false)