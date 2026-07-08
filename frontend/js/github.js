const githubUsername = "athxrva03";

const githubContainer =
document.getElementById("githubData");

async function fetchGitHubData(){

    try{

        const response =
        await fetch(
            `https://api.github.com/users/${githubUsername}`
        );

        const data =
        await response.json();

        githubContainer.innerHTML = `

        <div class="github-card">

            <img
                src="${data.avatar_url}"
                alt="${data.login}"
                class="github-avatar">

            <h3>${data.name || data.login}</h3>

            <p>
                ${data.bio || "No bio available"}
            </p>

            <div class="github-stats">

                <div>
                    <h4>${data.public_repos}</h4>
                    <span>Repositories</span>
                </div>

                <div>
                    <h4>${data.followers}</h4>
                    <span>Followers</span>
                </div>

                <div>
                    <h4>${data.following}</h4>
                    <span>Following</span>
                </div>

            </div>

            <a
                href="${data.html_url}"
                target="_blank"
                class="btn">

                View GitHub

            </a>

        </div>

        `;

    }

    catch(error){

        githubContainer.innerHTML = `

        <p>
            Failed to load GitHub data.
        </p>

        `;

        console.error(error);
    }
}

document.addEventListener(
    "DOMContentLoaded",
    fetchGitHubData
);